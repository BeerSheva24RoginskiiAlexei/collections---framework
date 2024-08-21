package telran.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public abstract class ListTest extends CollectionTest {
    List<Integer> list;
    @Override
    void setUp() {
        super.setUp();
        list = (List<Integer>) collection;
    }


    @Test 
    void addTestList () {
        list.add(2, 12);
        list.add(6, 19);
        assertEquals(12, list.get(2));
        assertEquals(19, list.get(6));
        assertEquals(array.length + 2, collection.size());
    }

    @Test
    void getTestList() {
        assertEquals(20, list.get(2));
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(100));
    }


    @Test 
    void indexOfTest(){
        assertEquals(2, list.indexOf(20));
        assertEquals(-1, list.indexOf(22));
    }

    @Test
    void lastIndexOfTestList() {
        assertEquals(8, list.lastIndexOf(20));
        assertEquals(-1, list.lastIndexOf(22));
    }
}
