# makefile z3_10

.SUFFIXES: .class .java

PACK=z3_10
TARG=Test
JC=javac -g
JM=java -ea -jar
JR=jar -cfe

OBJ=$(PACK)\Group.class \
    $(PACK)\Student.class \
    $(PACK)\Test.class \

.java.class:
	$(JC) $<

run: build
	$(JM) $(TARG).jar

build: 	$(OBJ)
	$(JR) $(TARG).jar $(PACK).Test $(OBJ)

clean:
	for %%f in ($(OBJ)) do del %%f
	del $(TARG).jar

$(PACK)\Group.class: $(PACK)\Group.java makefile

$(PACK)\Student.class: $(PACK)\Student.java makefile

$(PACK)\Test.class: $(PACK)\Test.java makefile

# eof makefile z3_10