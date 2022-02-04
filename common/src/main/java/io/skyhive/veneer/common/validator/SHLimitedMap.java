package io.skyhive.veneer.common.validator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author krishna
 * @created 24/01/22
 * @project skyhive-veeneer
 */

public class SHLimitedMap<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize=3;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }
}