import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

//SHOW TOP 10 URL IN FILE
/**
 * //Reading urls from file using String class or URL class.
 * By using String class method each url can be more modified (ex. cutting  m. subdomain)
 * By using URL class method getting authority of link (ex. www.youtube.com) is more easy
 * These are two different approaches to this task. They shows approximately the same execution time.
 */
public class DomainParser {

    public static String getUrlAuthorityUsingString(String str) {
        //deleting first www. substring
        str = str.replace("www.", "");

        //deleting all after "/"
        int urlIndex = str.indexOf("/");
        str = urlIndex != -1 ? str.substring(0, urlIndex) : str;

        return str;
    }

    public static String getUrlAuthorityUsingURL(String line) throws MalformedURLException {
        URL url = new URL("https://" + line);
        line = url.getAuthority();
        return line;
    }


    //READING FROM FILE STRING BY STRING. AFTER STRING PROCESSING IT'S ADDS TO MAP
    public static void readFromFileToMap(Map<String,Integer> map) {
        try {
            File file = new File("urls.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {

                line = getUrlAuthorityUsingString(line);
                // OR line = getUrlAuthorityUsingURL(line);


                if(!map.containsKey(line))
                    map.put(line,1);
                else
                    map.put(line, map.get(line) + 1);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();

        Map<String, Integer> urls = new HashMap<>();

        readFromFileToMap(urls);
        urls = DomainService.sortByValue(urls);
        DomainService.printTopOf(10, urls);

        Thread.sleep(1000);
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println("Прошло времени: " + elapsed + " мc.");
    }
}
