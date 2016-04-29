package Blocks;

import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Board{

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


    private static int h, w;
    private static Set<Block> goal;
    private Set<Block> state;
    private Board prevState;
    private String move;
    private int numMoves;
    //private int heuristic;

    public Board (List<String> input, Set<Block> goal) {
        String[] dims = input.get(0).split(" ");
        this.h = Integer.parseInt(dims[0]);
        this.w = Integer.parseInt(dims[1]);
        this.state = new HashSet<>();
        this.prevState = null;
        this.move = null;
        this.numMoves = 0;
        this.goal = goal;
        // Parses List of Strings into 2D Array
        for(int i = 0; i < input.size() - 1; i++) {
            String[] inputBlocks = input.get(i + 1).split(" ");
            int[] block = new int[Block.N];
            for (int j = 0; j < Block.N; j++) {
                block[j] = Integer.parseInt(inputBlocks[j]);
            }
            state.add(new Block(block));
        }
        //this.heuristic = heuristic(goal);
    }

    private Board(Set<Block> currState, Board prevState, String move, int moves) {
//        this.h = h;
//        this.w = w;
        this.state = new HashSet<>();
        this.prevState = prevState;
        this.move = move;
        this.numMoves = moves;
//        this.goal = goal;
        for(Block b : currState) {
            this.state.add(b.deepCopy());
        }
        //this.heuristic = heuristic(goal);

    }

    // all possible moves, not necessarily legal
    public Set<Board> newMoves() {
        Set<Board> newBoards = new HashSet<>();
        for (Block b : state) {
            for(Direction d : Direction.values()) {
                String blockPos = b.getY() + " " + b.getX();
                b.move(d);
                if(isOK(b)) {
                    newBoards.add(this.newBoard(blockPos + " " + b.getY() + " " + b.getX()));
                }
                b.unmove(d);
            }
        }
        return newBoards;
    }

    // should this be private?
    // deep copy
    private Board newBoard(String newMove) {
        return new Board(state, this, newMove, numMoves+1);
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

    public int getNumMoves() {
        return numMoves;
    }

    public int heuristic() {
        // for every block in goal, find closest manhattan distance to a block in this board
        int totalDistance = 0;
        for(Block g : goal) {
            int minDistance = Integer.MAX_VALUE;
            for(Block b : state) {
                if(g.sameType(b)){
                    int distance = g.distanceTo(b);
                    if(distance < minDistance) {
                        minDistance = distance;
                    }
                }
            }
            totalDistance += minDistance;
        }
        return totalDistance;
    }

    public int compareTo(Board other) {
        if(Solver.isDebug()) {
            System.out.println("Comparing");
        }
        return (this.heuristic() + this.numMoves) - (other.heuristic() + other.numMoves);
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
        //return toString().hashCode();
    }

//    public String toString() {
//        return state.toString().replace(",", "\n");
//    }

    // very expensive
    public String toString() {
        int[][] tray = new int[h][w];
        for(Block b : state) {
            for(int i = 0; i < b.getH(); i++) {
                for(int j = 0; j < b.getW(); j++) {
                    tray[b.getY() + i][b.getX() + j] = b.getH() * 10 /*or 1000*/ + b.getW();
                }
            }
        }
        String toReturn = "";
        for(int i = 0; i < tray.length; i ++) {
            for (int j = 0; j < tray[0].length; j++) {
                toReturn += String.format("%2d ", tray[i][j]);
                //toReturn += (char)((tray[i][j] % 100) + 'A' - 1) + " ";
            }
            toReturn = toReturn.replace('@', '.') + "\n";
        }
        return toReturn;
    }
}
