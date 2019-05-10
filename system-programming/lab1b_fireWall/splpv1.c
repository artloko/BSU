/* 
 * SPLPv1.c
 * The file is part of practical task for System programming course. 
 * This file contains validation of SPLPv1 protocol. 
 */


/*
  Лобко Артем Эдуардович
  12 группа
*/



/*
---------------------------------------------------------------------------------------------------------------------------
# |      STATE      |         DESCRIPTION       |           ALLOWED MESSAGES            | NEW STATE | EXAMPLE
--+-----------------+---------------------------+---------------------------------------+-----------+----------------------
1 | INIT            | initial state             | A->B     CONNECT                      |     2     |
--+-----------------+---------------------------+---------------------------------------+-----------+----------------------
2 | CONNECTING      | client is waiting for con-| A<-B     CONNECT_OK                   |     3     |
  |                 | nection approval from srv |                                       |           |                      
--+-----------------+---------------------------+---------------------------------------+-----------+----------------------
3 | CONNECTED       | Connection is established | A->B     GET_VER                      |     4     |                     
  |                 |                           |        -------------------------------+-----------+----------------------
  |                 |                           |          One of the following:        |     5     |                      
  |                 |                           |          - GET_DATA                   |           |                      
  |                 |                           |          - GET_FILE                   |           |                      
  |                 |                           |          - GET_COMMAND                |           |
  |                 |                           |        -------------------------------+-----------+----------------------
  |                 |                           |          GET_B64                      |     6     |                      
  |                 |                           |        ------------------------------------------------------------------
  |                 |                           |          DISCONNECT                   |     7     |                                 
--+-----------------+---------------------------+---------------------------------------+-----------+----------------------
4 | WAITING_VER     | Client is waiting for     | A<-B     VERSION ver                  |     3     | VERSION 2                     
  |                 | server to provide version |          Where ver is an integer (>0) |           |                      
  |                 | information               |          value. Only a single space   |           |                      
  |                 |                           |          is allowed in the message    |           |                      
--+-----------------+---------------------------+---------------------------------------+-----------+----------------------
5 | WAITING_DATA    | Client is waiting for a   | A<-B     CMD data CMD                 |     3     | GET_DATA a GET_DATA 
  |                 | response from server      |                                       |           |                      
  |                 |                           |          CMD - command sent by the    |           |                      
  |                 |                           |           client in previous message  |           |                      
  |                 |                           |          data - string which contains |           |                      
  |                 |                           |           the following allowed cha-  |           |                      
  |                 |                           |           racters: small latin letter,|           |                     
  |                 |                           |           digits and '.'              |           |                      
--+-----------------+---------------------------+---------------------------------------+-----------+----------------------
6 | WAITING_B64_DATA| Client is waiting for a   | A<-B     B64: data                    |     3     | B64: SGVsbG8=                    
  |                 | response from server.     |          where data is a base64 string|           |                      
  |                 |                           |          only 1 space is allowed      |           |                      
--+-----------------+---------------------------+---------------------------------------+-----------+----------------------
7 | DISCONNECTING   | Client is waiting for     | A<-B     DISCONNECT_OK                |     1     |                      
  |                 | server to close the       |                                       |           |                      
  |                 | connection                |                                       |           |                      
---------------------------------------------------------------------------------------------------------------------------

IN CASE OF INVALID MESSAGE THE STATE SHOULD BE RESET TO 1 (INIT)

*/


#include "splpv1.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


char* isVersion(char* msg);
char* isB64(char* msg);
char* isGetter(char* msg);

/* FUNCTION:  validate_message
 * 
 * PURPOSE:  
 *    This function is called for each SPLPv1 message between client 
 *    and server
 * 
 * PARAMETERS:
 *    msg - pointer to a structure which stores information about 
 *    message
 * 
 * RETURN VALUE:
 *    MESSAGE_VALID if the message is correct 
 *    MESSAGE_INVALID if the message is incorrect or out of protocol 
 *    state
 */

//enum test_status validate_message(struct Message *msg)
//{
//	static int prevDir = -1;
//	static bool mistaken = false;
//	if (!mistaken)
//	{
//		if (msg->direction == A_TO_B && prevDir != (int)msg->direction)
//		{
//			prevDir = (int)msg->direction;
//			if (!strcmp(msg->text_message, "CONNECT") || !strcmp(msg->text_message, "GET_VER") ||
//				!strcmp(msg->text_message, "GET_DATA") || !strcmp(msg->text_message, "GET_COMMAND") ||
//				!strcmp(msg->text_message, "GET_FILE") || !strcmp(msg->text_message, "GET_B64") ||
//				!strcmp(msg->text_message, "DISCONNECT"))
//			{
//				return MESSAGE_VALID;
//			}
//		}
//		else if (msg->direction == B_TO_A && prevDir != (int)msg->direction)
//		{
//			prevDir = (int)msg->direction;
//			if (!strcmp(msg->text_message, "CONNECT_OK") || !strcmp(msg->text_message, "DISCONNECT_OK"))
//			{
//				return MESSAGE_VALID;
//			}
//			else if (isB64(msg->text_message) || isVersion(msg->text_message) || isGetter(msg->text_message))
//			{
//				return MESSAGE_VALID;
//			}
//		}
//		mistaken = true;
//	}
//	prevDir = (int)msg->direction;
//	if ((!strcmp(msg->text_message, "DISCONNECT_OK") && mistaken) || !strcmp(msg->text_message, "CONNECT"))
//	{
//		mistaken = false;
//	}
//	return MESSAGE_INVALID;
//}
typedef struct COMM_STRUCT_
{
	const char* command;
	const unsigned char state;
	const unsigned char length;
}COMM_STRUCT;

COMM_STRUCT commands_3[] =
{
	{ "GET_VER", 4, 8 },
{ "GET_DATA", 5, 9 },
{ "GET_COMMAND", 5, 12 },
{ "GET_FILE", 5, 9 },
{ "GET_B64", 6, 8 },
{ "DISCONNECT", 7, 10 }
};

static unsigned char prevCommandIdx;
static unsigned char currState = 1;

enum test_status validate_message(struct Message *msg)
{
	char* msg_s = msg->text_message;
	
	if (msg->direction == A_TO_B)
	{
		if (currState == 1 && !memcmp(msg_s, "CONNECT", 8))
		{
			currState = 2;
			return MESSAGE_VALID;
		}
		if (currState == 3)
		{
			for (unsigned char i = 0; i < 6; i++)
			{
				if (!memcmp(commands_3[i].command, msg_s, commands_3[i].length))
				{
					currState = commands_3[i].state;
					prevCommandIdx = i;
					return MESSAGE_VALID;
				}
			}
		}
		currState = 1;
		return MESSAGE_INVALID;
	}
	if (msg->direction == B_TO_A)
	{
		if (currState == 2 && !memcmp(msg_s, "CONNECT_OK", 11))
		{
			currState = 3;
			return MESSAGE_VALID;
		}
		if (currState == 4 && !memcmp(msg_s, "VERSION ", 8) && isVersion(msg->text_message))
		{
			currState = 3;
			return MESSAGE_VALID;
		}
		if (currState == 5)
		{
			char* prevComm = commands_3[prevCommandIdx].command;
			unsigned char length = commands_3[prevCommandIdx].length - 1;
			if (!memcmp(msg_s, prevComm, length))
			{
				msg_s += length;
				if (*msg_s == ' ')
				{
					char* s = isGetter(++msg_s);
					if (s && !memcmp(s, prevComm, length + 1))
					{
						currState = 3;
						return MESSAGE_VALID;
					}
				}
			}
		}
		if (currState == 6 && !memcmp("B64: ", msg_s, 5) && isB64(msg_s + 5))
		{
			currState = 3;
			return MESSAGE_VALID;
		}
		if (currState == 7 && !memcmp("DISCONNECT_OK", msg_s, 14))
		{
			currState = 1;
			return MESSAGE_VALID;
		}
		currState = 1;
		return MESSAGE_INVALID;
	}	
}

char* isVersion(char* msg)
{
	char* walker = msg + 8;
	if (*walker > 48 && *walker < 58)
	{
		while (*(++walker) != '\0')
		{
			if (*walker < 48 || *walker > 57)
			{
				return false;
			}
		}
		return true;
	}
	return false;
}

const char base64[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 /*=*/, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
char* isB64(char *s)
{
	char *s_ = s;
	for (; base64[*s]; ++s) {};
	char check = 0;
	for (; (check < 2) && (s[check] == '='); ++check) {};
	if (s[check] == '\0' && (s_ - s + 1) % 4)
		return 1;
	return 0;
}

const char data_[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 /*=*/, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
char* isGetter(char *s)
{
	for (; data_[*s]; ++s) {};
	if (*s == ' ')
		return s + 1;
	return 0;
}
