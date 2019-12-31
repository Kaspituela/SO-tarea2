CLS=classes
PATH=src/*.java

compile: $(PATH)
	javac -d $(CLS) $(PATH)

run: compile
	java -cp $(CLS) Problema1