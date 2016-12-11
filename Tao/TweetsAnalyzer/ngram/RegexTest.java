public class RegexTest {

    public static String removeURL(String line) {
        return line.replaceAll("https?://[^ ]*", "").trim();
    }

    public static void main(String[] args) {
        String[] testArray = {"Decorated the whole living room for. Christmas with just one light in all of 10 seconds https://t.co/E1ZaQMw46V", "dnidni http://d nnasidfnia", "https://d aazzz"};
        for (String test : testArray) {
            System.out.println(removeURL(test));
        }
    }
}