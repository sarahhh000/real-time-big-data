File descriptions

src
-DataFormat700line.java(complete dataset by filling empty spot with 0)
-FormatData.java(make data to machine learning input format)
-Spark.java(using Spark MlLib to do regression)
-crawl_data.py(crawl data from https://transitdata.phoenix.gov/api/vehiclepositions?format=gtfs.proto)
-DataAnalysis(first MapReduce, analyze the crawled data, remove duplicate)
--DataAnalysis.java
--DataAnalysisMapper.java
--DataAnalysisReducer.java
-DataFormat(second MapReduce, gathering information together, make data into format which could be easily merged with other 2 dataset)
--DataFormat.java
--DataFormatMapper.java
--DataFormatReducer.java


Input data is all in github repo https://github.com/sarahhh000/real-time-big-data