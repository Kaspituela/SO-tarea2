JCC = javac
JCR = java
FLAGS = -g

all:
	if ! -d "classes"; then\
		mkdir classes;\
	fi
	$(JCC) $(FLAGS) -d classes src/*.java

run:
	$(JCR) -cp classes Problema1

clean:
	rm -rf ./classes