#!/bin/bash
set -e

JOBNAME=$1
CLASSPATH=`yarn classpath`

# Compile Java code:
javac -classpath $CLASSPATH "${JOBNAME}Mapper.java"
javac -classpath $CLASSPATH "${JOBNAME}Reducer.java"
javac -classpath "$CLASSPATH:." "${JOBNAME}.java"

# Create jar file
jar -cvf $JOBNAME.jar *.class

