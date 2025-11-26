import org.example.TodoList;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add(" task1 ");
        assertEquals(1, t.size());
        assertEquals("[ ] task1", t.getAll().getFirst());
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add(" ");
        assertEquals(0, t.size());
    }

    @Test
    void clear() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");
        assertEquals(2, t.size());
        t.clear();
        assertEquals(0, t.size());
        assertTrue(t.getAll().isEmpty());
    }

    @Test
    void done() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");
        assertTrue(t.done(0));
        assertEquals("[x] task1", t.getAll().get(0));
        assertEquals("[ ] task2", t.getAll().get(1));
        assertFalse(t.done(5));
    }

    @Test
    void search() {
        TodoList t = new TodoList();
        t.add("apple");
        t.add("banana");
        t.add("apricot");
        t.done(0);

        List<String> results = t.search("ap");
        assertEquals(2, results.size());
        assertEquals("[x] apple", results.get(0));
        assertEquals("[ ] apricot", results.get(1));

        results = t.search("xyz");
        assertTrue(results.isEmpty());

        results = t.search(" ");
        assertTrue(results.isEmpty());
    }
}