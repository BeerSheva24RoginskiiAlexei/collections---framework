package telran.collections;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] array = { 3, -10, 20, 1, 10, 8, 100, 17, 20};
    @BeforeEach
    void setUp(){
        Arrays.stream(array).forEach(collection::add); //n -> collection.add(n)
    }
    @Test
    void addTest(){
        collection.add(200);
        collection.add(17);
        assertEquals(array.length + 2,collection.size());
    }
    @Test
    void sizeTest(){
        assertEquals(array.length, collection.size());
    }
    @Test 
    void removeTest(){
        assertTrue(collection.remove(20));
        assertFalse(collection.remove(20));   
        assertEquals(array.length - 2, collection.size());
        assertFalse(collection.remove(22));
    }

    @Test 
    void isEmptyTest(){
        assertFalse(collection.isEmpty());
        Collection<Integer> collectionNew = new ArrayList<Integer>();
        assertTrue(collectionNew.isEmpty()); 
    }

    @Test 
    void containsTest(){
        assertTrue(collection.contains(10));
        assertFalse(collection.contains(22));
    }


    @Test
    void iteratorTestList() {
        Iterator<Integer> it = collection.iterator(); //var
        Integer[] actual = new Integer[array.length];

        int index = 0;
        while (it.hasNext()) {
            actual[index++] = it.next();
        }

        assertArrayEquals(array, actual);
        assertThrows(NoSuchElementException.class, it::next);
    }
}