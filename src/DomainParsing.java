import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//Вывести топ 10 доменов которые встречаются чаще всего.
public class DomainParsing {

    public static String stringCutter(String str){
        //deleting first www. substring
        str = str.replace ("www.", "");

        //If necessary, you can delete the subdomain "m."
        //str = str.replace ("m.", "");

        //deleting all after "/"
        int urlIndex = str.indexOf("/");
        str = urlIndex != -1 ? str.substring(0, urlIndex) : str;

        return str;
    }


    //METHOD TO SORT COMING MAP
    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        Map<String, Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<String, Integer>> st = map.entrySet().stream();

        st.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static void printTopOf(int lastInTop, Map<String,Integer> map){
        System.out.println("TOP-" + lastInTop + " URLS");
        int index = 0;

        for (Map.Entry url : map.entrySet() ) {
            if(index < lastInTop){
                System.out.println(index + 1 + ". " + url.getKey() + " - " + url.getValue() + " times.");
                index++;
            }
            else{ break; }
        }
    }

    public static void main(String[] args) {
        Map<String,Integer> urls = new HashMap<>();


        //READING FROM FILE STRING BY STRING. AFTER STRING PROCESSING IT'S ADDS TO MAP
        try {
            File file = new File("urls.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                line = stringCutter(line);

                if(!urls.containsKey(line))
                    urls.put(line,1);
                else
                    urls.put(line, urls.get(line) + 1);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //DESCENDING SORT
        urls = sortByValue(urls);

        printTopOf(10,urls);
    }
}
