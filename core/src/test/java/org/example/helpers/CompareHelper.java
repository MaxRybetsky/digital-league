package org.example.helpers;

import java.util.HashMap;
import java.util.Map;


public class CompareHelper {
    public static boolean haveSameElements(final Iterable<?> iterable1, final Iterable<?> iterable2) {
        if (iterable1 == iterable2) {
            return true;
        }
        if (iterable1 == null || iterable2 == null) {
            return false;
        }
        Map<Object, Count> counts = new HashMap<>();
        for (Object item : iterable1) {
            if (!counts.containsKey(item)) {
                counts.put(item, new Count());
            }
            counts.get(item).count += 1;
        }
        for (Object item : iterable2) {
            if (!counts.containsKey(item)) {
                return false;
            }
            counts.get(item).count -= 1;
        }
        for (Map.Entry<Object, Count> entry : counts.entrySet()) {
            if (entry.getValue().count != 0) {
                return false;
            }
        }
        return true;
    }

    private static class Count {
        public int count = 0;
    }

}
