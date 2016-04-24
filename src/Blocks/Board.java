package Blocks;

import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Board {

    public enum Direction {
        DOWN (1, 0),
        LEFT (0, -1),
        RIGHT (0, 1),
        UP (-1, 0);

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

    private Board prevState;
    private String move;

    public Board (List<String> input) {
        String[] dims = input.get(0).split(" ");
        this.h = Integer.parseInt(dims[0]);
        this.w = Integer.parseInt(dims[1]);
        this.state = new HashSet<>();
        this.prevState = null;
        this.move = null;

        // Parses List of Strings into 2D Array
        for(int i = 0; i < input.size() - 1; i++) {
            String[] inputBlocks = input.get(i + 1).split(" ");
            int[] block = new int[Block.N];
            for (int j = 0; j < Block.N; j++) {
                block[j] = Integer.parseInt(inputBlocks[j]);
            }
            state.add(new Block(block));
        }
    }

    private Board(int h, int w, Set<Block> currState, Board prevState, String move) {
        this.h = h;
        this.w = w;
        this.state = new HashSet<>();
        this.prevState = prevState;
        this.move = move;
        for(Block b : currState) {
            this.state.add((b.deepCopy()));
        }

    }

    // all possible moves, not necessarily legal
    public Set<Board> newMoves() {
        Set<Board> newBoards = new HashSet<>();
        for (Block b : state) {
            for(Direction d : Direction.values()) {
                String blockPos = b.getY() + " " + b.getX();
                b.move(d);
                if(isOK(b)) {
                    newBoards.add(this.deepCopy(blockPos + " " + b.getY() + " " + b.getX()));
                }
                b.unmove(d);
            }
        }
        return newBoards;
    }

    private Board deepCopy(String newMove) {
        return new Board(h, w, state, this, newMove);
    }

    // Checks whether a board state would be valid
    public boolean isOK() {
        for (Block b: state) {
            if(!isOK(b)) {
                return false;
            }
        }
        return true;
    }

    // checks whether a certain block is legal
    public boolean isOK(Block b) {
        // checks whether block is in bounds
        if(b.getY() < 0 || b.getX() < 0 ||
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

    // Returns whether the board contains the block at (y,x) h units long and w units wide
    public boolean hasBlock(Block b) {
        return state.contains(b);
    }

    public String getMove() {
        return move;
    }

    public Board getPrev() {
        return prevState;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || !(o instanceof Board)) {
            return false;
        }
        Board other = (Board)o;
        return this.state.equals(other.state);
    }

    public int hashCode() {
        return state.hashCode();
    }

    public String toString() {
        return state.toString().replace("],", "]\n");
    }

    public String toVisualString() {
        int[][] tray = new int[h][w];
        int id = 1;
        for(Block b : state) {
            for(int i = 0; i < b.getH(); i++) {
                for(int j = 0; j < b.getW(); j++) {
                    tray[b.getY() + i][b.getX() + j] = id * 100 + b.getH() * 10 + b.getW();
                }
            }
            id++;
        }
        String toReturn = "";
        for(int i = 0; i < tray.length; i ++) {
            for (int j = 0; j < tray[0].length; j++) {
                toReturn += String.format("%3d ", tray[i][j] % 100);
                //toReturn += (char)((tray[i][j] % 100) + 'A' - 1) + " ";
            }
            toReturn = toReturn.replace('@', '.') + "\n";
        }
        return toReturn;
    }
}
