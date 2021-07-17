import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

//Reading urls from file using URL. Extract authority of url more easily.
public class SecondDomainParser {

    public static String getUrlAuthority(String line) throws MalformedURLException {
        URL url = new URL("https://" + line);
        line = url.getAuthority();
        return line;
    }
    

    public static void readFromFileToMap(Map<String,Integer> map){
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
        Map<String,Integer> map = new HashMap<>();

        readFromFileToMap(map);
        map = DomainParsingService.sortByValue(map);
        DomainParsingService.printTopOf(20,map);


        Thread.sleep(1000);
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println("Прошло времени: " + elapsed + " мc.");

    }
}
