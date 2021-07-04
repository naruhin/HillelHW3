import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//Вывести топ 10 доменов которые встречаются чаще всего.ц
public class DomainParsing {

    public static String stringCutter(String str){
        //deleting first www. substring
        str = str.replace ("www.", "");

        //If necessary, you can delete the subdomain "m."
        //str = str.replace ("m.", "");

        //deleting all after "/"
        int slashIndex = str.indexOf("/");
        str = slashIndex != -1 ? str.substring(0, slashIndex) : str;

        return str;
    }


    //METHOD TO SORT COMING MAP
    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        Map<String, Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<String, Integer>> st = map.entrySet().stream();

        st.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static void printTopOf(int topOf, Map<String,Integer> map){
        System.out.println("TOP-" + topOf + " URLS");
        int index = 0;
        for (Map.Entry url : map.entrySet() ) {
            if(index < topOf){
                System.out.println(index + 1 + ". " + url.getKey() + " - " + url.getValue() + " times.");
            }
            index++;
        }
    }


    //READING FROM FILE STRING BY STRING. AFTER STRING PROCESSING IT'S ADDS TO MAP
    public static void readFromFileToMap(Map<String,Integer> map) {
        try {
            File file = new File("urls.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                line = stringCutter(line);

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


    public static void main(String[] args) {
        Map<String,Integer> urls = new HashMap<>();

        readFromFileToMap(urls);
        urls = sortByValue(urls);
        printTopOf(10,urls);
    }
}
