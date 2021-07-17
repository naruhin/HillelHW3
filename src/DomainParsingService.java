import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class DomainParsingService {

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
}
