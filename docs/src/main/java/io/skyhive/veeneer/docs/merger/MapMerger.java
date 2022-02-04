package io.skyhive.veeneer.docs.merger;

import java.util.Map;
import java.util.TreeMap;

public class MapMerger<T> {
    private final TreeMap<String, T> map = new TreeMap();

    public void merge(Map<String, T> from) {
        if (from == null) return;
        from.forEach((key, value) -> {
            map.putIfAbsent(key, value);
        });
    }

    public Map<String, T> get() {
        return map.size() > 0 ? map : null;
    }
}
