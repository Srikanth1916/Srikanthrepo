package io.skyhive.veeneer.docs.merger;

import java.util.LinkedList;
import java.util.List;

public class ListMerger<T> {
    private List<T> list = new LinkedList<>();
    public void merge(List<T> from) {
        if (from== null) return;
        from.forEach(item -> {
            if(!list.contains(item)) {
                list.add(item);
            }
        });
    }
    public List<T> get() {
        return list.size()>0?list: null;
    }
}
