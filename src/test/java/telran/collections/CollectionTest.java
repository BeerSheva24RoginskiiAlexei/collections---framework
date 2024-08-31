package telran.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] arr = {3, 10, 20, 1, 8, 100, 17};

    void setUp() {
        Arrays.stream(arr).forEach(collection::add);
    }

    @Test
    void addTest() {
        assertTrue(collection.add(200));
        assertTrue(collection.add(202));
        assertEquals(arr.length + 2, collection.size());
    }

    @Test
    void removeTest() {
        assertTrue(collection.remove(10));
        assertTrue(collection.remove(20));
        assertEquals(arr.length - 2, collection.size());
    }

    @Test
    void containsTest() {
        assertTrue(collection.contains(100));
        assertFalse(collection.contains(1000));
    }
    
    @Test
    void sizeTest() {
        assertEquals(arr.length, collection.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
        collection.remove(3);
        collection.remove(10);
        collection.remove(20);
        collection.remove(1);
        collection.remove(8);
        collection.remove(100);
        collection.remove(17);
        assertTrue(collection.isEmpty());
    }

    @Test
    void iteratorTest() {
        Iterator<Integer> it = collection.iterator();
        Integer[] actual = new Integer[arr.length];;

        int index = 0;
        while (it.hasNext()) {
            actual[index++] = it.next();
        }

        Arrays.sort(arr);
        Arrays.sort(actual);

        assertArrayEquals(arr, actual);
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void streamTest() {
        streamUniqTest(collection.stream());
        streamUniqTest(collection.parallelStream());

        streamSumTest(collection.stream());
        streamSumTest(collection.parallelStream());

        streamSortTest(collection.stream());
        streamSortTest(collection.parallelStream());
    }

    private void streamUniqTest(Stream<Integer> stream) {
        Object[] result = stream.distinct().toArray();
        assertEquals(7, result.length);
    }

    private void streamSumTest(Stream<Integer> stream) {
        int result = stream.mapToInt(Integer::intValue).sum();
        assertEquals(159, result);
    }

    private void streamSortTest(Stream<Integer> stream) {
        Object[] result = stream.sorted().toArray();
        Object[] expended = {1, 3, 8, 10, 17, 20, 100};
        assertArrayEquals(expended, result);
    }

    @Test
    void removeIfTest() {
        assertTrue(collection.removeIf(n -> n % 2 == 0));
        assertFalse(collection.removeIf(n -> n % 2 == 0));
        assertTrue(collection.stream().allMatch(n -> n % 2 != 0));
    }

    @Test
    void clearTest() {
        collection.clear();
        assertTrue(collection.isEmpty());
    }

    @Test
    void removeInIteratorTest(){
        Iterator<Integer> it = collection.iterator();
        assertThrowsExactly(IllegalStateException.class, () -> it.remove());
        Integer n = it.next();
        it.remove();
        it.next();
        it.next();
        it.remove();
        assertFalse(collection.contains(n));
        assertThrowsExactly(IllegalStateException.class, () -> it.remove());
    }
}