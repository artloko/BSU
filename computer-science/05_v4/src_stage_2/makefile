# makefile
# converts properties from UTF-16 to ISO-8859-1

DEF_RES=Msg_en_GB

all:	Msg.properties \
	Msg_be_BY.properties \
	Msg_en_GB.properties \
	Msg_ru_RU.properties

Msg.properties: $(DEF_RES).properties.utf16.txt
	native2ascii -encoding UTF-16 $(DEF_RES).properties.utf16.txt Msg.properties

Msg_be_BY.properties: Msg_be_BY.properties.utf16.txt
	native2ascii -encoding UTF-16 Msg_be_BY.properties.utf16.txt Msg_be_BY.properties

Msg_en_GB.properties: Msg_en_GB.properties.utf16.txt
	native2ascii -encoding UTF-16 Msg_en_GB.properties.utf16.txt Msg_en_GB.properties

Msg_ru_RU.properties: Msg_ru_RU.properties.utf16.txt
	native2ascii -encoding UTF-16 Msg_ru_RU.properties.utf16.txt Msg_ru_RU.properties

# eof makefile