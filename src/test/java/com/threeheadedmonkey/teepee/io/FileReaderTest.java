package com.threeheadedmonkey.teepee.io;

import com.threeheadedmonkey.teepee.FileTest;
import com.threeheadedmonkey.teepee.entity.Consolidator;
import com.threeheadedmonkey.teepee.entity.Item;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for the FileReader
 */
public class FileReaderTest extends FileTest {

    private static final String testfile = "src/test/resources/Personal-output.taskpaper";
    private static final String mywater = "src/test/resources/My Water.taskpaper";
    private static final String personal = "src/test/resources/Personal.taskpaper";

    @Test
    public void testRead() throws Exception {
        Collection<Item> items = readFile(testfile);
        assertEquals(18, items.size());
    }

    @Ignore
    @Test
    public void testReadMyWater() throws Exception {
        Collection<Item> items = readFile(mywater);
        assertEquals(357, items.size());
    }

    @Ignore
    @Test
    public void testReadPersonal() throws Exception {
        Collection<Item> items = readFile(personal);
        assertEquals(347, items.size());
    }

    @Test
    public void testReadAndConsolidate() throws Exception {
        Collection<Item> items = readFile(testfile);
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        assertEquals(3, consolidatedItems.size());
    }

    @Ignore
    @Test
    public void testReadAndConsolidateMyWater() throws Exception {
        Collection<Item> items = readFile(mywater);
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        assertEquals(7, consolidatedItems.size());
    }

    @Ignore
    @Test
    public void testReadAndConsolidatePersonal() throws Exception {
        Collection<Item> items = readFile(personal);
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        assertEquals(7, consolidatedItems.size());
    }
}
