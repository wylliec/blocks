package Blocks;

import org.junit.Test;

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
        Board tray = new Board(list, null);
        //System.out.println(tray.newMoves());
        assertEquals(tray.newMoves().size(), 5);
    }

//    @Test
//    public void deepCopy() throws Exception {
//        List<String> list = new ArrayList<>();
//        list.add("10 10");
//        list.add("4 4 2 2");
//        list.add("4 3 6 2");
//        Board b1 = new Board(list, null);
////        Board b2 = b1.deepCopy("");
////        assertEquals(b1, b2);
////        assertNotSame(b1, b2);
//    }

    @org.junit.Test
    public void isOK() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        list.add("4 3 6 2");
        Board tray = new Board(list, null);
        assertTrue(tray.isOK());
        list.add("4 3 2 2");
        tray = new Board(list, null);
        assertFalse(tray.isOK());
    }

    @org.junit.Test
    public void isOK1() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        list.add("4 3 6 2");
        Board tray = new Board(list, null);
        assertTrue(tray.isOK(new Block(2, 2, 0, 2)));
        assertFalse(tray.isOK(new Block(2, 2, 1, 2)));
    }

    @org.junit.Test
    public void hasBlock() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        Board tray = new Board(list, null);
        assertTrue(tray.hasBlock(new Block(4, 4, 2, 2)));
        assertFalse(tray.hasBlock(new Block(4, 3, 2, 1)));
    }

//    @org.junit.Test
//    public void getPrevStates() throws Exception {
//
//    }

    @org.junit.Test
    public void hashSetEquals() throws Exception {
        Set<Integer> s1 = new HashSet<>();
        s1.add(1326345478);
        s1.add(12341235);
        s1.add(578958958);
        Set<Integer> s2 = new HashSet<>();
        s2.add(1326345478);
        s2.add(12341234);
        s2.add(578958959);
        Set<Integer> s3 = new HashSet<>();
        s3.add(1326345478);
        s3.add(12341234);
        s3.add(578958959);
        assertNotEquals(s1, s2);
        assertEquals(s2, s3);
        assertNotSame(s1, s2);
        assertNotSame(s3, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        Set<Set<Integer>> states = new HashSet<>();
        assertFalse(states.contains(s1));
        assertFalse(states.contains(s2));
        assertFalse(states.contains(s3));
        states.add(s1);
        assertTrue(states.contains(s1));
        assertFalse(states.contains(s2));
        assertFalse(states.contains(s3));
        states.add(s2);
        assertTrue(states.contains(s1));
        assertTrue(states.contains(s2));
        assertTrue(states.contains(s3));
        //System.out.println(s1.hashCode());
        //System.out.println(s2.hashCode());
        //assertEquals((int)s1.stream().reduce(0, Integer::sum), s1.hashCode());

//        Set<int[]> s1 = new HashSet<>();
//        s1.add(new int[]{1, 2, 3, 4});
//        Set<int[]> s2 = new HashSet<>();
//        s2.add(new int[]{1, 2, 3, 4});
//        assertEquals(s1, s2); // Will actually fail
    }

    @org.junit.Test
    public void equals() throws Exception {

    }

    @org.junit.Test
    public void hashCodeTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("256 256");
        list.add("256 256 0 0");
        Board tray = new Board(list, null);
        for (int i = 0; i < 10000; i++) {
            tray.hashCode();
        }
        //assertEquals(16843008, tray.hashCode());
    }

//    @org.junit.Test
//    public void hashCodeSet() throws Exception {
//        List<String> list = new ArrayList<>();
//        list.add("256 256");
//        list.add("1 1 1 1");
//        list.add("255 255 255 255");
//        list.add("0 0 256 256");
//        Board tray = new Board(list);
//        Set<Integer> s = tray.hashCodeSet();
//        //System.out.println(s);
//        assertTrue(s.contains(-1));
//        assertTrue(s.contains(16843009));
//        assertTrue(s.contains(0));
//        assertFalse(s.contains(1));
//    }

//    @org.junit.Test
//    public void hashCodeSetCollisions() throws Exception {
//        List<String> list = new ArrayList<>();
//        list.add("256 256");
//        list.add("1 1 1 1");
//        list.add("255 255 255 255");
//        list.add("0 0 256 256");
//        Board tray = new Board(list);
//        Set<Integer> s = tray.hashCodeSet();
//        //System.out.println(s);
//        assertTrue(s.contains(-1));
//        assertTrue(s.contains(16843009));
//        assertTrue(s.contains(0));
//        assertFalse(s.contains(1));
//    }

    @org.junit.Test
    public void toStringTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("10 10");
        list.add("4 4 2 2");
        list.add("4 3 6 2");
        Board tray = new Board(list, null);
        // nondeterministic set
        assertEquals(tray.toString(),
                " 0  0  0  0  0  0  0  0  0  0 \n" +
                " 0  0  0  0  0  0  0  0  0  0 \n" +
                " 0  0 44 44 44 44  0  0  0  0 \n" +
                " 0  0 44 44 44 44  0  0  0  0 \n" +
                " 0  0 44 44 44 44  0  0  0  0 \n" +
                " 0  0 44 44 44 44  0  0  0  0 \n" +
                " 0  0 43 43 43  0  0  0  0  0 \n" +
                " 0  0 43 43 43  0  0  0  0  0 \n" +
                " 0  0 43 43 43  0  0  0  0  0 \n" +
                " 0  0 43 43 43  0  0  0  0  0 \n");
    }

//    @org.junit.Test
//    public void toVisualString() throws Exception {
//        List<String> list = new ArrayList<>();
//        list.add("10 10");
//        list.add("4 3 6 1");
//        list.add("4 4 2 2");
//        Board tray = new Board(list, null);
//        System.out.println(tray.toString().hashCode());
//    }


//    @Test
//    public void getMove() throws Exception {
//
//    }
//
//    @Test
//    public void getPrev() throws Exception {
//
//    }

    @Test
    public void heuristic() throws Exception {

    }

    @Test
    public void compareTo() throws Exception {

    }

}