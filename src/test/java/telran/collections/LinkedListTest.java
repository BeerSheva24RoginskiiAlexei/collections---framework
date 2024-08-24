package telran.collections;

import org.junit.jupiter.api.BeforeEach;

public class LinkedListTest extends ListTest{
    @BeforeEach
    @Override
    void setUp() {
        collection = new LinkedList<>();
        super.setUp();
    }
}
