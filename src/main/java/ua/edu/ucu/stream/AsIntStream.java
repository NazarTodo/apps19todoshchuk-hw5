package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.BaseIterator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;

import java.util.ArrayList;
import java.util.Iterator;


public class AsIntStream implements IntStream {
    private Iterator<Integer> it;

    private AsIntStream(Iterator<Integer> it) {
        this.it = it;
    }

    public static IntStream of(int... values) {
        int counter = 0;
        int[] lst = new int[values.length];
        for (int value : values) {
            lst[counter] = value;
            counter++;
        }
        return new AsIntStream(new BaseIterator(lst));
    }

    @Override
    public Double average() {
        isEmpty();
        Iterable<Integer> iterable = () -> this.it;
        int sumAll = 0;
        int len = 0;
        for (int value : iterable) {
            len += 1;
            sumAll += value;
        }
        return (double) sumAll / len;
    }

    @Override
    public Integer max() {
        isEmpty();
        Iterable<Integer> iterable = () -> this.it;
        int maxValue = Integer.MIN_VALUE;
        for (int value : iterable) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    @Override
    public Integer min() {
        Iterable<Integer> iterable = () -> this.it;
        isEmpty();
        int minValue = Integer.MAX_VALUE;
        for (int value : iterable) {
            if (minValue > value) {
                minValue = value;
            }
        }
        return minValue;
    }

    @Override
    public long count() {
        Iterable<Integer> iterable = () -> this.it;
        long len = 0;
        for (int ignored : iterable) {
            len += 1;
        }
        return len;
    }


    @Override
    public Integer sum() {
        isEmpty();
        Iterable<Integer> iterable = () -> this.it;
        int sumAll = 0;
        for (int value : iterable) {
            sumAll += value;
        }
        return sumAll;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(predicate, this.it));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (it.hasNext()) {
            action.accept(it.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(mapper, this.it));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(func, this.it));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        Iterable<Integer> iterable = () -> this.it;
        int newNum = identity;
        for (int value : iterable) {
            newNum += op.apply(identity, value);
        }
        return newNum;
    }

    @Override
    public int[] toArray() {
        Iterable<Integer> iterable = () -> this.it;
        ArrayList<Integer> lst = new ArrayList<>();
        for (int value : iterable) {
            lst.add(value);
        }
        int[] arr = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            arr[i] = lst.get(i);
        }
//                Arrays.copyOf(lst.toArray(), lst.size(), Integer[].class);

        return arr;
    }

    private void isEmpty() {
        if (this.it == null || !this.it.hasNext()) {
            throw new IllegalArgumentException("Empty stream");
        }

    }
}
