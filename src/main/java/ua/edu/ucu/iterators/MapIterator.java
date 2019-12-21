package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private IntUnaryOperator mapper;
    private Iterator<Integer> it;
    private int curr;
    public MapIterator(IntUnaryOperator mapper, Iterator<Integer> it){
        this.mapper = mapper;
        this.it = it;
    }
    @Override
    public boolean hasNext() {
        if (it.hasNext()) {
            curr = it.next();
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return mapper.apply(curr);
    }
}
