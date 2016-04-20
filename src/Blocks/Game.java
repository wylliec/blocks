package Blocks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Game {
    private Board board;
    private Set<Block> goal;
    private boolean debug;
    private Stack<Board.BlockDirection> moves;

    public Game(List<String> inputList, List<String> goalList) {
        this(inputList, goalList, false);
    }

    public Game(List<String> inputList, List<String> goalList, boolean debug) {
        this.debug = debug;
        this.board = new Board(inputList);
        this.goal = new HashSet<>();
        this.moves = new Stack<>();

        // Parses List of Strings into 2D Array
        for(String i : goalList) {
            String[] goalBlocks = i.split(" ");
            int[] block = new int[Block.N];
            for (int j = 0; j < Block.N; j++) {
                block[j] = Integer.parseInt(goalBlocks[j]);
            }
            goal.add(new Block(block));
        }

        if (this.debug) {
            System.out.println("Board: \n" + this.board.toString());
            System.out.println("Goal: \n" + this.goal.toString().replace("],", "]\n"));
        }
    }

    public Stack<Board.BlockDirection> loop(){
        if(debug) {
            System.out.println("Playing!");
        }
        while(!hasGoal()) {
            if(debug) {
                // System.out.println("loop");
            }
            Board.BlockDirection mv = board.move();
            if(mv == null) {
                if (moves.isEmpty()) {
                    // at start position and no possible moves
                    if(debug) {
                        System.out.println("Cannot continue");
                        System.out.println(board.getPrevStates().size());
                    }
                    System.exit(1);
                } else {
                    if(debug) {
                        System.out.println("Undoing a move");
                    }
                    mv = moves.pop();
                    // undo last move
                    mv.getBlock().unmove(mv.getDirection());
                }
            } else {
                if(debug) {
                    System.out.println("Making a move");
                }
                moves.push(mv);
            }
        }
        // print out moves to get to winning position
        if(debug) {
            System.out.println("You win!");
        }
        return moves;
    }

    public boolean hasGoal() {
        for(Block block : goal) {
            if(!board.hasBlock(block)) {
                return false;
            }
        }
        return true;
    }
}
