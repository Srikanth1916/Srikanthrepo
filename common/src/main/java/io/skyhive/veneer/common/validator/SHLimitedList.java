package io.skyhive.veneer.common.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author krishna
 * @created 24/01/22
 * @project skyhive-veeneer
 */
public class SHLimitedList<E> extends ArrayList<E> {
    @Override
    public boolean add(E e) {
        if(this.size()>3){this.remove(0);}
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c != null) {
            for(E e : c) {
                if (this.size() > 3) {
                    this.remove(0);
                    this.add(e);
                }
            }
            return true;
        }
        return false;
    }
}
