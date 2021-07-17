import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

//Вывести топ 10 доменов которые встречаются чаще всего.
//Reading urls from file using String. Each url can be more modified (ex. cutting  m. subdomain)
public class FirstDomainParser {

    public static String getUrlAuthority(String str) {
        //deleting first www. substring
        str = str.replace("www.", "");

        //deleting all after "/"
        int urlIndex = str.indexOf("/");
        str = urlIndex != -1 ? str.substring(0, urlIndex) : str;

        return str;
    }


    //READING FROM FILE STRING BY STRING. AFTER STRING PROCESSING IT'S ADDS TO MAP
    public static void readFromFileToMap(Map<String,Integer> map) {
        try {
            File file = new File("urls.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                line = getUrlAuthority(line);

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
        urls = DomainParsingService.sortByValue(urls);
        DomainParsingService.printTopOf(10, urls);

        Thread.sleep(1000);
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println("Прошло времени: " + elapsed + " мc.");
    }
}
