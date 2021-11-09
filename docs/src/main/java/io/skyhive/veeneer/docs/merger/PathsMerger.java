package io.skyhive.veeneer.docs.merger;

import com.google.common.base.Strings;
import io.swagger.v3.oas.models.Paths;

public class PathsMerger {
    private Paths paths = new Paths();
    public void merge(Paths from, String service) {
        if (from == null) return;
        from.forEach((key, value)-> {
            key = Strings.nullToEmpty(service)+key;
            paths.putIfAbsent(key, value);
        } );
    }
    public Paths get() {
        return paths.size()>0?paths:null;
    }
}
