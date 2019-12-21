package ua.edu.ucu.iterators;

import java.util.Iterator;

public class BaseIterator implements Iterator<Integer> {
    private int[] lst;
    private Integer current = 0;

    public BaseIterator(int[] lst) {
        this.lst = lst;
    }

    @Override
    public boolean hasNext() {
        return current < lst.length;
    }

    @Override
    public Integer next() {
        int next = current;
        current += 1;
        return lst[next];
    }
}
