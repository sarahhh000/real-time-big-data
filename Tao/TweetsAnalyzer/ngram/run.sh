#!/bin/bash
set -e

JOBNAME=$1
wkdir=$2

# remove last output directory
if $(hadoop fs -test -d $wkdir/output); then
    echo "Remove previous running results."
    hdfs dfs -rm -r $wkdir/output
fi

# run the job
echo
echo "-----Job Run Log-----"

hadoop jar $JOBNAME.jar $JOBNAME $wkdir/input/ $wkdir/output

echo "-----Job Run End-----"
echo

# show result
hdfs dfs -ls $wkdir/output/

echo
echo "-----Result Start-----"

hdfs dfs -cat $wkdir/output/part-r-00000

echo "-----Result Finish-----"
echo

