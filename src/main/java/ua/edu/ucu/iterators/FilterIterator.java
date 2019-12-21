package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {
    private IntPredicate predicate;
    private Iterator<Integer> it;
    //    previous iterator
    private int curr;

    //    current number
    public FilterIterator(IntPredicate predicate, Iterator<Integer> it) {
        this.predicate = predicate;
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        while (it.hasNext()) {
            curr = it.next();
            if (predicate.test(curr)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return curr;
    }
}
