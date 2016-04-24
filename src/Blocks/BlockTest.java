package Blocks;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Caleb on 4/22/2016.
 */
public class BlockTest {
    @Test
    public void move() throws Exception {
        Block b = new Block(new int[]{1, 2, 3, 4});
        b.move(Board.Direction.DOWN);
        assertEquals(b, new Block(1, 2, 4, 4));
        b.move(Board.Direction.LEFT);
        assertEquals(b, new Block(1, 2, 4, 3));
        b.move(Board.Direction.UP);
        assertEquals(b, new Block(1, 2, 3, 3));
        b.move(Board.Direction.RIGHT);
        assertEquals(b, new Block(1, 2, 3, 4));
    }

    @Test
    public void unmove() throws Exception {
        Block b = new Block(new int[]{1, 2, 3, 4});
        b.unmove(Board.Direction.DOWN);
        assertEquals(b, new Block(1, 2, 2, 4));
        b.unmove(Board.Direction.LEFT);
        assertEquals(b, new Block(1, 2, 2, 5));
    }

    @Test
    public void getH() throws Exception {
        Block b = new Block(new int[]{1, 2, 3, 4});
        assertEquals(1, b.getH());
    }

    @Test
    public void getW() throws Exception {
        Block b = new Block(new int[]{1, 2, 3, 4});
        assertEquals(2, b.getW());
    }

    @Test
    public void getY() throws Exception {
        Block b = new Block(new int[]{1, 2, 3, 4});
        assertEquals(3, b.getY());
    }

    @Test
    public void getX() throws Exception {
        Block b = new Block(new int[]{1, 2, 3, 4});
        assertEquals(4, b.getX());
    }

    @Test
    public void equals() throws Exception {
        Block b1 = new Block(new int[]{1, 2, 3, 4});
        Block b2 = new Block(1, 2, 3, 4);
        Block b3 = new Block(1, 2, 4, 4);
        assertTrue(b1.equals(b2));
        assertTrue(b2.equals(b1));
        assertTrue(b1.equals(b1));
        assertFalse(b3.equals(b2));
        assertFalse(b1.equals(b3));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Block b = new Block(new int[]{1, 1, 1, 1});
        assertEquals("1000000010000000100000001", Integer.toBinaryString(b.hashCode()));
        Block b2 = new Block(new int[]{255,255,255,255});
        assertEquals(-1, b2.hashCode());
        Block b3 = new Block(new int[]{256,256,256,256});
    }

    @Test
    public void toStringTest() throws Exception {
        Block b = new Block(1, 2, 3, 4);
        assertEquals(b.toString(), "[1, 2, 3, 4]");
    }

}