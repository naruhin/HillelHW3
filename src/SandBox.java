import java.util.*;


//Сделать дерево с балансировкой, черно-белое.
public class SandBox {
    public static void main(String[] args) {

        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add((int) ( 0 + Math.random() * 100));
        }
        System.out.println(lst);


        TreeMap<Integer,Integer> map = new TreeMap<>();

        int index = 0;
        for (Integer i : lst) {
            map.put(i,index++);
        }

        System.out.println("TreeMap<Integer, Integer> - " + map);


        //В отличии от HashMap кидает ClassCastExeption
        /*Map map1 = new TreeMap();
        map1.put(23.4,1);
        map1.put("sdsd",'s');
        map1.put(123,2.3);
        map1.put(true,"sff");*/


        TreeMap<String,Integer> map2 = new TreeMap<>();
        map2.put("sdsd",2);
        map2.put("gigig",3);
        map2.put("sdf",2);

        System.out.println("TreeMap<String, Integer> - " + map2);




    }
}
