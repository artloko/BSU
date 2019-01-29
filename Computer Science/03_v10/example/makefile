# makefile z3_2

.SUFFIXES: .class .java

PACK=z3_2
TARG=test
JC=javac -g
JM=java -ea -jar
JR=jar -cfe

OBJ=$(PACK)/Matrix.class \
    $(PACK)/test.class

.java.class:
	$(JC) $<

run: build
	$(JM) $(TARG).jar

build: 	$(OBJ)
	$(JR) $(TARG).jar $(PACK).test $(OBJ)

clean:
	for %%f in ($(OBJ)) do del %%f
	del $(TARG).jar

$(PACK)/Matrix.class: $(PACK)/Matrix.java makefile

$(PACK)/test.class: $(PACK)/test.java makefile

# eof makefile z3_2