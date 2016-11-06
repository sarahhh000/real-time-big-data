Author: Yicong Tao

This is a simple demonstration program for getting tweets.

First, edit the "OAuthConfig.txt" file and replace "XXXXX" with your twitter consumer and application tokens. Be sure to grant "Read" access to your application.

Second, compile "TryTwitter4j.java" file using javac. Besure to include two twitter4j jar librarys in your classpath which is used by this program (versions may vary):
twitter4j-core-4.0.4.jar
twitter4j-stream-4.0.4.jar

By default, the program collects the latest ten-second tweets and write them into a text file called "tweets.txt".