package Blocks;

import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Game {
    //private Board board;
    private Set<Block> goal;
    private Set<Board> prevStates;
    private Queue<Board> statesToTry;


    public Game(List<String> inputList, List<String> goalList) {
        //this.board = new Board(inputList);
        this.goal = new HashSet<>();
        this.prevStates = new HashSet<>();
        this.statesToTry = new LinkedList<>();
        this.statesToTry.add(new Board(inputList));


        // Parses List of Strings into 2D Array
        for(String i : goalList) {
            String[] goalBlocks = i.split(" ");
            int[] block = new int[Block.N];
            for (int j = 0; j < Block.N; j++) {
                block[j] = Integer.parseInt(goalBlocks[j]);
            }
            goal.add(new Block(block));
        }

        if (Solver.isDebug()) {
            //System.out.println("Board: \n" + this.board.toString());
            System.out.println("Goal: \n" + this.goal.toString().replace("],", "]\n"));
            //System.out.println("Has goal? : " + hasGoal());
        }
    }

    public Board loop(){
//        Store the root node in queue
//        While (there are nodes in queue)
//            N = Get the "next" node from queue
//            Store all the children of N in queue
//            Do some work on N

        if(Solver.isDebug()) {
            System.out.println("Playing!");
        }

        while(!statesToTry.isEmpty()) {
            Board tray = statesToTry.remove();
            statesToTry.addAll(tray.newMoves());
            if (hasGoal(tray)) {
                return tray;
            }
        }
        return null;

//        while(!hasGoal()) {
//            if(Solver.isDebug()) {
//                System.out.println(board.toVisualString());
//            }
//            Board.BlockDirection mv = board.move();
//            if(mv == null) {
//                if (moves.isEmpty()) {
//                    // at start position and no possible moves
//                    if(Solver.isDebug()) {
//                        System.out.println("Cannot continue");
//                        System.out.println(board.getPrevStates().size());
//                    }
//                    System.exit(1);
//                } else {
//                    if(Solver.isDebug()) {
//                        System.out.println("Undoing a move");
//                    }
//                    mv = moves.pop();
//                    // undo last move
//                    mv.getBlock().unmove(mv.getDirection());
//                }
//            } else {
//                if(Solver.isDebug()) {
//                    System.out.println("Making a move");
//                }
//                moves.push(mv);
//            }
//        }
//        // print out moves to get to winning position
//        if(Solver.isDebug()) {
//            System.out.println("You win!");
//        }
//        return moves;
    }

    public boolean hasGoal(Board board) {
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
