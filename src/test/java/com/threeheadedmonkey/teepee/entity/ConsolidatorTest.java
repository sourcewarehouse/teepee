package com.threeheadedmonkey.teepee.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for the Consolidator
 */
public class ConsolidatorTest {

    private Collection<Item> items;

    @Before
    public void setUp() throws Exception {
        items = new ArrayList<Item>();
        items.add(newProject("Project 1", 0));
        items.add(newTask("Task 1", 1));
        items.add(newProject("Project 2", 0));
        items.add(newTask("Task 2", 1));
        items.add(newNote("Note 1", 1));
        items.add(newTask("Task 3", 1));
        items.add(newTask("Task 3A", 2));
        items.add(newTask("Task 3B", 2));
        items.add(newProject("Project 3", 0));
        items.add(newTask("Task 4", 1));
    }

    @Test
    public void testConsolidate() throws Exception {
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        assertNotNull(consolidatedItems);
        assertEquals(3, consolidatedItems.size());

        Iterator<Item> itIt = consolidatedItems.iterator();

        Item i0 = itIt.next();
        assertEquals(Project.class, i0.getClass());
        Project p0 = (Project) i0;
        assertNotNull(p0.getItems());
        assertEquals(1, p0.getItems().size());

        Item i1 = itIt.next();
        assertEquals(Project.class, i1.getClass());
        Project p1 = (Project) i1;
        assertNotNull(p1.getItems());
        assertEquals(2, p1.getItems().size());
        Item subItem5 = p1.getItems().get(1);
        assertEquals(Task.class, subItem5.getClass());
        Task taskWithSubs = (Task) subItem5;
        assertNotNull(taskWithSubs.getItems());
        assertEquals(2, taskWithSubs.getItems().size());

        Item i2 = itIt.next();
        assertEquals(Project.class, i2.getClass());
        Project p2 = (Project) i2;
        assertNotNull(p2.getItems());
        assertEquals(1, p2.getItems().size());

    }

    private Item newNote(String note, int level) {
        Note n = new Note(note);
        n.setLevel(level);
        return n;
    }

    private Item newTask(String name, int level) {
        Task t = new Task(name);
        t.setLevel(level);
        return t;
    }

    private Item newProject(String name, int level) {
        Project p = new Project(name);
        p.setLevel(level);
        return p;
    }

}
