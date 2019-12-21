package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMapIterator implements Iterator<Integer> {
    private IntToIntStreamFunction func;
    private Iterator<Integer> it;
    private Iterator<Integer> numberIter;
    private boolean notEmpty = false;
    private Integer next = null;

    public FlatMapIterator(IntToIntStreamFunction func, Iterator<Integer> it) {
        this.it = it;
        this.func = func;
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
        if (notEmpty && numberIter.hasNext()) {
            next = numberIter.next();
            return true;
        }
        return numIterator();
    }

    @Override
    public Integer next() {
        if (next == null && !hasNext()) {
            throw new NoSuchElementException();
        }
        Integer value = next;
        next = null;
        return value;
    }

    public boolean numIterator() {
        if (it.hasNext()) {
            int curr = it.next();
            AsIntStream stream = (AsIntStream) func.applyAsIntStream(curr);
            int[] lst = stream.toArray();
            numberIter = new BaseIterator(lst);
            next = numberIter.next();
            notEmpty = true;
            return true;
        }

        return false;
    }
}
