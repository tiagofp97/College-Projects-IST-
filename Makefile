# Variables
JAVAC=javac
JAVA=java
CLASSPATH=.:po-uilib.jar
SRC=$(shell find . -name "*.java")  # Using shell command to find .java files

# Default target
all: compile run clean

# Compile Java files
compile:
	@if [ -z "$(SRC)" ]; then \
		echo "No source files to compile."; \
		exit 1; \
	else \
		$(JAVAC) -cp $(CLASSPATH) $(SRC); \
	fi

# Run the application
run:
	$(JAVA) -cp $(CLASSPATH) hva.app.App

# Clean up .class files
clean:
	find . -name "*.class" -delete
# Only compile
compile-only: 
	@if [ -z "$(SRC)" ]; then \
		echo "No source files to compile."; \
		exit 1; \
	else \
		$(JAVAC) -cp $(CLASSPATH) $(SRC); \
	fi
