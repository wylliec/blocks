package Blocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Caleb on 4/22/2016.
 */
public class BoardTest {
    @org.junit.Test
    public void move() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        list.add("4 3 6 2");
        Board tray = new Board(list);
        //System.out.println(tray.toVisualString());
        for (int i = 0; i < 30; i++) {
            //tray.move();
            //System.out.println(tray.toVisualString());
        }
    }

    @org.junit.Test
    public void isOK() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        list.add("4 3 6 2");
        Board tray = new Board(list);
        assertTrue(tray.isOK());
        list.add("4 3 2 2");
        tray = new Board(list);
        assertFalse(tray.isOK());
    }

    @org.junit.Test
    public void isOK1() throws Exception {

    }

    @org.junit.Test
    public void hasBlock() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        Board tray = new Board(list);
        assertTrue(tray.hasBlock(new Block(4, 4, 2, 2)));
        assertFalse(tray.hasBlock(new Block(4, 3, 2, 1)));
    }

    @org.junit.Test
    public void getPrevStates() throws Exception {

    }

    @org.junit.Test
    public void hashSetEquals() throws Exception {
        Set<Long> s1long = new HashSet<>();
        s1long.add(1326345478222l);
        s1long.add(12341234l);
        s1long.add(57895895895895895l);
        Set<Long> s2long = new HashSet<>();
        s2long.add(1326345478222l);
        s2long.add(12341234l);
        s2long.add(57895895895895895l);
        assertEquals(s1long, s2long);
        assertNotSame(s1long, s2long);

        Set<int[]> s1 = new HashSet<>();
        s1.add(new int[]{1, 2, 3, 4});
        Set<int[]> s2 = new HashSet<>();
        s2.add(new int[]{1, 2, 3, 4});
        assertEquals(s1, s2);
    }

    @org.junit.Test
    public void hashCodeTest() throws Exception {

    }

    @org.junit.Test
    public void hashCodeSet() throws Exception {

    }

    @org.junit.Test
    public void toStringTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        list.add("4 3 6 2");
        Board tray = new Board(list);
        assertEquals(tray.toString(),
                "[[4, 4, 2, 2]\n" +
                " [4, 3, 6, 2]]");
    }

    @org.junit.Test
    public void toVisualString() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        list.add("4 3 6 2");
        Board tray = new Board(list);
        // System.out.println(tray.toVisualString());
    }

}