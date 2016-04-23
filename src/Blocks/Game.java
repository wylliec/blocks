package Blocks;

import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Game {
    private Board board;
    private Set<Block> goal;
    private Deque<Board.BlockDirection> moves;

    public Game(List<String> inputList, List<String> goalList) {
        this.board = new Board(inputList);
        this.goal = new HashSet<>();
        this.moves = new ArrayDeque<>();

        // Parses List of Strings into 2D Array
        for(String i : goalList) {
            String[] goalBlocks = i.split(" ");
            System.out.println("len " + goalBlocks.length);
            int[] block = new int[Block.N];
            for (int j = 0; j < Block.N; j++) {
                block[j] = Integer.parseInt(goalBlocks[j]);
            }
            goal.add(new Block(block));
        }

        if (Solver.isDebug()) {
            System.out.println("Board: \n" + this.board.toString());
            System.out.println("Goal: \n" + this.goal.toString().replace("],", "]\n"));
            System.out.println("Has goal? : " + hasGoal());
        }
    }

    public Deque<Board.BlockDirection> loop(){
        if(Solver.isDebug()) {
            System.out.println("Playing!");
        }
        while(!hasGoal()) {
            if(Solver.isDebug()) {
                System.out.println(board.toVisualString());
            }
            Board.BlockDirection mv = board.move();
            if(mv == null) {
                if (moves.isEmpty()) {
                    // at start position and no possible moves
                    if(Solver.isDebug()) {
                        System.out.println("Cannot continue");
                        System.out.println(board.getPrevStates().size());
                    }
                    System.exit(1);
                } else {
                    if(Solver.isDebug()) {
                        System.out.println("Undoing a move");
                    }
                    mv = moves.pop();
                    // undo last move
                    mv.getBlock().unmove(mv.getDirection());
                }
            } else {
                if(Solver.isDebug()) {
                    System.out.println("Making a move");
                }
                moves.push(mv);
            }
        }
        // print out moves to get to winning position
        if(Solver.isDebug()) {
            System.out.println("You win!");
        }
        return moves;
    }

    public boolean hasGoal() {
        for(Block block : goal) {
            if(!board.hasBlock(block)) {
//                if (Solver.isDebug()) {
//                    System.out.println("Missing block: " + block);
//                }
                return false;
            }
        }
        return true;
    }
}
