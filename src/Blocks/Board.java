package Blocks;

import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Board {

    public enum Direction {
        UP (-1, 0),
        DOWN (1, 0),
        LEFT (0, -1),
        RIGHT (0, 1);

        private final int y;
        private final int x;
        Direction(int y, int x){
            this.y = y;
            this.x = x;
        }
        public int getX(){ return x; }
        public int getY(){ return y; }
    }

    private Set<Block> state;
    private int h, w;
    private Set<Integer> prevStates;

    public Board (List<String> input) {
        String[] dims = input.get(0).split(" ");
        this.h = Integer.parseInt(dims[0]);
        this.w = Integer.parseInt(dims[1]);
        this.state = new HashSet<>();
        this.prevStates = new HashSet<>();

        // Parses List of Strings into 2D Array
        for(int i = 0; i < input.size() - 1; i++) {
            String[] inputBlocks = input.get(i + 1).split(" ");
            int[] block = new int[Block.N];
            for (int j = 0; j < Block.N; j++) {
                block[j] = Integer.parseInt(inputBlocks[j]);
            }
            state.add(new Block(block));
        }
        prevStates.add(this.hashCode());
    }

    // all possible moves, not necessarily legal
    // @return a Block and a Direction
    public BlockDirection move() {
        for (Block b : state) {
            for(Direction d : Direction.values()) {
                b.move(d);
                if(prevStates.contains(this.hashCode())){
                    System.out.println("Previous state encountered");
                }
                if(isOK(b) && !prevStates.contains(this.hashCode())) {
                    prevStates.add(this.hashCode());
                    return new BlockDirection(b, d);
                }
                b.unmove(d);
            }
        }
        return null;
    }

    // Checks whether a board state would be valid
    public boolean isOK() {
        for (Block b: state) {
            isOK(b);
        }
        return true;
    }

    // checks whether a certain block is legal
    public boolean isOK(Block b) {
        // checks whether block is in bounds
        if(b.getX() < 0 || b.getY() < 0 ||
                b.getY() + b.getH() > h || b.getX() + b.getW() > w) {
            return false;
        }
        // checks whether blocks don't overlap
        for(Block b2 : state) {
            if (b != b2) { // of course a block overlaps with itself...
                if(((b.getY() <= b2.getY() && b.getY() + b.getH() > b2.getY()) || // overlaps y
                        (b2.getY() <= b.getY() && b2.getY() + b2.getH() > b.getY())) &&
                        ((b.getX() <= b2.getX() && b.getX() + b.getW() > b2.getX()) || // overlaps x
                        (b2.getX() <= b.getX() && b2.getX() + b2.getW() > b.getX()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Block findBlock(int x, int y) {
        for (Block b : state) {
            if(b.getY() == y && b.getX() == x) {
                return b;
            }
        }
        return null;
    }

    // Returns whether the board contains the block at (y,x) h units long and w units wide
    public boolean hasBlock(Block b) {
        return state.contains(b);
    }

    public Set<Integer> getPrevStates() {
        return prevStates;
    }

    public boolean equals(Object o) {
        if(o == null || !(o instanceof Board)) {
            return false;
        }
        Board other = (Board)o;
        return this.state.equals(other.state);
    }

    public int hashCode() {
        // HashCodeBuilder
//        return state.hashCode();
        int hash = 1;
        for(Block b : state) {
            hash *= b.hashCode();
        }
        return hash;
    }

    public String toString() {
        return state.toString().replace("],", "]\n");
    }

    public class BlockDirection {
        private Block block;
        private Direction direction;

        public BlockDirection(Block b, Direction d) {
            this.block = b;
            this.direction = d;
        }

        public Block getBlock() {
            return block;
        }

        public Direction getDirection() {
            return direction;
        }
    }
}
