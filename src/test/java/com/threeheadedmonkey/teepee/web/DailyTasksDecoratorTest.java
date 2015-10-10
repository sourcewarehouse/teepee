package com.threeheadedmonkey.teepee.web;

import com.threeheadedmonkey.teepee.FileTest;
import com.threeheadedmonkey.teepee.entity.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit test for the DailyTasksDecorator
 */
public class DailyTasksDecoratorTest extends FileTest {

    private static final String location = "src/test/resources/Personal-output.taskpaper";
    private DailyTasksDecorator decorator;

    @Before
    public void setUp() throws Exception {

        Collection<Item> items = readFile(location);
        decorator = new DailyTasksDecorator(items);
    }

    @Test
    public void testHasOverdue() throws Exception {
        assertTrue(decorator.hasOverdue());
    }

    @Test
    public void testGetOverdue() throws Exception {
        assertEquals(1, decorator.getOverdue().size());
    }

    @Test
    public void testHasToday() throws Exception {
        assertTrue(decorator.hasToday());
    }

    @Test
    public void testGetToday() throws Exception {
        assertEquals(1, decorator.getToday().size());
    }

    @Test
    public void testHasTomorrow() throws Exception {
        assertTrue(decorator.hasTomorrow());
    }

    @Test
    public void testGetTomorrow() throws Exception {
        assertEquals(1, decorator.getTomorrow().size());
    }

    @Test
    public void testHasRegularTasks() throws Exception {
        assertTrue(decorator.hasRegularTasks());
    }

    @Test
    public void testGetRegularTasks() throws Exception {
        assertEquals(5, decorator.getRegularTasks().size());
    }
}
