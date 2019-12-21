package ua.edu.ucu.stream;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class AsIntStreamTest {
    private IntStream intStream;
    private int[] intArr;

    @Before
    public void setUp() throws Exception {
        intArr = new int[]{-2, 4, 8, 0, -1, 2, 9};
    }

    @Test
    public void of() {
        intStream = AsIntStream.of(intArr);
        int[] expResult1 = {-2, 4, 8, 0, -1, 2, 9};
        int[] real1 = intStream.toArray();
        assertArrayEquals(expResult1, real1);
        IntStream intStream2 = AsIntStream.of(1, 2, 3);
        int[] expResult2 = {1, 2, 3};
        int[] real2 = intStream2.toArray();
        assertArrayEquals(expResult2, real2);
    }

    @Test
    public void emptyOf() {
        int[] lst = {};
        intStream = AsIntStream.of(lst);
        int[] exp1 = {};
        int[] real1 = intStream.toArray();
        assertArrayEquals(exp1, real1);
    }

    @Test
    public void average() {
        intStream = AsIntStream.of(intArr);
        double realResult = intStream.average();
        double expResult = 2.857142857142857;
        double delta = 0.1;
        assertEquals(expResult, realResult, delta);
    }

    @Test
    public void max() {
        intStream = AsIntStream.of(intArr);
        int expResult = intStream.max();
        int real = 9;
        assertEquals(expResult, real);
    }

    @Test
    public void min() {
        intStream = AsIntStream.of(intArr);
        int expResult = intStream.min();
        int real = -2;
        assertEquals(expResult, real);
    }

    @Test
    public void count() {
        intStream = AsIntStream.of(intArr);
        double expResult = intStream.count();
        double real = 7.0;
        assertEquals(expResult, real, 0.1);
    }

    @Test
    public void sum() {
        intStream = AsIntStream.of(intArr);
        int expResult = intStream.sum();
        int real = 20;
        assertEquals(expResult, real);

    }

    @Test
    public void filter() {
        intStream = AsIntStream.of(intArr);
        intStream = intStream.filter(x -> x > 0);
        int expResult = intStream.min();
        int real = 2;
        assertEquals(expResult, real);
    }

    @Test
    public void forEach() {
        intStream = AsIntStream.of(intArr);
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        String expResult = "-2480-129";
        assertEquals(str.toString(), expResult);

    }

    @Test
    public void map() {
        intStream = AsIntStream.of(intArr);
        intStream = intStream.map(x -> x * x);
        int expResult = intStream.max();
        int real = 81;
        assertEquals(expResult, real);
    }

    @Test
    public void flatMap() {
        intStream = AsIntStream.of(intArr);
        intStream = intStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
        int expResult = intStream.max();
        int real = 10;
        assertEquals(expResult, real);
    }

    @Test
    public void reduce() {
        intStream = AsIntStream.of(intArr);
        int res = intStream.reduce(0, (sum, x) -> sum += x);
        int real = 20;
        assertEquals(res, real);

    }

    @Test
    public void toArray() {
        intStream = AsIntStream.of(intArr);
        int[] res = intStream.filter(x -> x > 0).toArray();
        int[] exp = {4, 8, 2, 9};
        assertArrayEquals(exp, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty(){
        intStream = AsIntStream.of();
        int res = intStream.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty(){
        intStream = AsIntStream.of();
        double res = intStream.average();
    }

    @Test
    public void testEmptyFilter(){
        intStream = AsIntStream.of();
        intStream.filter(x-> x > 0);
        int[] res = intStream.toArray();
        int[] expResult = {};
        assertArrayEquals(res, expResult);
    }

}