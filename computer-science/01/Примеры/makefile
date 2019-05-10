# makefile

.SUFFIXES: .class .java

JC=javac -g
JM=java
OBJ=z1_10.class z1_3.class

all: $(OBJ)

clean:
	for %%f in ($(OBJ)) do del %%f

.java.class:
	$(JC) $<

# eof makefile 
