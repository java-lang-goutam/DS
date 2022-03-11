import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;

import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        MultiKeyMap map = new MultiKeyMap();
        map.put("A1", "A2", "A3", Arrays.asList("AA1", "A2", "A3"));
        map.put("B1", "B2", "B3", Arrays.asList("BB1", "B2", "B3"));
        map.put("C1", "C2", "C3", Arrays.asList("CC1", "C2", "C3"));
        map.put("D2", "A2", null, Arrays.asList("AA1", "A2", null));
        map.put("E2", "B2", null, Arrays.asList("BB1", "B2", null));
        map.put("F2", "C2", null, Arrays.asList("CC1", "C2", null));

        Set set = map.keySet();
        for(Object obj : set) {
            final MultiKey multiKey = (MultiKey) obj;
            System.out.println(map.get(multiKey));
        }


    }
}
