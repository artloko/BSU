  # Валидатор сетевого протокола
  
  **Задание лабораторной работы** – реализовать программное обеспечение
межсетевого экрана, осуществляющее валидацию сообщений между клиентом (на
схеме обозначен как A) и сервером (на схеме обозначен как B). Обмен данными
происходит по протоколу SPLPv1.

| # |STATE| DESCRIPTION| ALLOWED MESSAGES| NEW STATE | EXAMPLE |
| --- | --- | --- | --- |--- | --- |
| 1 | INIT | initial state | A->B CONNECT| 2| |
|2 | CONNECTING      | client is waiting for connection approval from srv| A<-B     CONNECT_OK|     3     | |                  
|3 | CONNECTED       | Connection is established | A->B  GET_VER |  4||                                                 
|  |                 |                           | A->B  One of the following: GET_DATA, GET_COMMAND, GET_FILE |  5||        
|4 | WAITING_VER     | Client is waiting for server to provide version information  value. Only a single space is allowed in the message| A<-B VERSION ver [Where ver is an integer (>0)] |     3     | VERSION 2|                                     
|5 | WAITING_DATA    | Client is waiting for a response from server   | A<-B     CMD data CMD     client in previous message data - string which contains the following allowed characters: small latin letter, digits and '.'  |     3     | GET_DATA a GET_DATA| 
|6 | WAITING_B64_DATA| Client is waiting for a response from server.  | A<-B     B64: data  [where data is a base64 string, only 1 space is allowed] |     3     | B64: SGVsbG8=     |               
| 7 | DISCONNECTING   | Client is waiting for server to close the connection      | A<-B     DISCONNECT_OK   |     1     |      |                

IN CASE OF INVALID MESSAGE THE STATE SHOULD BE RESET TO 1 (INIT)

> *by [@artloko](https://github.com/artloko)*
