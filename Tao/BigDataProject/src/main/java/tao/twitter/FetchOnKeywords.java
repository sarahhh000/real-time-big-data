package twitter;
import java.util.Arrays;

public class FetchOnKeywords {
    public static void main(String[] args) {
        System.err.println("Working Directory = " + System.getProperty("user.dir"));
        System.err.println("Keywords = " + Arrays.toString(args));
        TweetsFetcher fetcher = new TweetsFetcher();
        fetcher.keywords = args;
        fetcher.getLatestTweet();
    }
}