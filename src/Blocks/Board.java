package Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    }

    private int[][] state;

    public Board (List<String> input) {
        String[] dims = input.get(0).split(" ");
        this.state = new int[Integer.parseInt(dims[0])][Integer.parseInt(dims[1])];
        int id = 1;
        for(int index = 1; index < input.size(); index++) {
            String[] line = input.get(index).split(" ");
            for(int i = 0; i < Integer.parseInt(line[0]); i++) {
                for(int j = 0; j < Integer.parseInt(line[1]); j++) {
                    this.state[Integer.parseInt(line[2]) + i][Integer.parseInt(line[3]) + j] =
                            id * 100 + Integer.parseInt(line[0]) * 10 + Integer.parseInt(line[1]);
                }
            }
            id++;
        }
    }

    public void move(int block, Direction dir) {
        System.out.println(dir.x);
//        switch(dir) {
//            case UP:
//                break;
//            case DOWN:
//                break;
//            case LEFT:
//                break;
//            case RIGHT:
//                break;
//            default:
//                break;
//        }
    }

    public List<int[]> possibleMoves() {
        List<int[]> moves = new ArrayList<>();
        for(int[] space : emptySpaces()) {
            for(Direction d : Direction.values()) {

            }
        }
        return moves;
    }

    public List<int[]> emptySpaces() {
        List<int[]> moves = new ArrayList<>();
        for(int y = 0; y < state.length; y++) {
            for (int x = 0; x < state[0].length; x++) {
                if(state[y][x] == 0) {
                    moves.add(new int[]{y, x});
                }
            }
        }
        return moves;
    }

    // Returns whether the board contains the block at (y,x) h units long and w units wide
    public boolean hasBlock(int h, int w, int y, int x) {
        // use blocks unique id to make sure its all the same block
        int id = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if( !(id == 0 || id == state[i + y][j + x] / 100) ||
                        h * 10 + w != state[i + y][j + x] % 100) {
                    return false;
                } else {
                    id = state[i + y][j + x] / 100;
                }
            }
        }
        return true;
    }

    public boolean equals(Object o) {
        if(o == null || !(o instanceof Board)) {
            return false;
        }
        Board other = (Board)o;
        return super.equals(o);
    }

    public int hashCode() {
        // HashCodeBuilder
        return super.hashCode();
    }

    public String toString() {
        String toReturn = "";
        for(int i = 0; i < state.length; i ++) {
            for (int j = 0; j < state[0].length; j++) {
                toReturn += String.format("%2d ", state[i][j] / 100);
                // toReturn += (char)((state[i][j] / 100) + 'A' - 1) + " ";
            }
            toReturn = toReturn.trim() + "\n";
        }
        return toReturn;
    }
}
