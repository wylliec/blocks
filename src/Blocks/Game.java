package Blocks;

import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Game {
    private Set<Block> goal;
    private Set<Board> prevStates;
    private Queue<Board> statesToTry;


    public Game(List<String> inputList, List<String> goalList) {
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

//      BFS
//        Store the root node in queue
//        While (there are nodes in queue)
//            N = Get the "next" node from queue
//            Store all the children of N in queue
//            Do some work on N
    public Board loop(){

        if(Solver.isDebug()) {
            System.out.println("Playing!");
        }

        while(!statesToTry.isEmpty()) {
            Board tray = statesToTry.remove();
            if(!prevStates.contains(tray)) {
                prevStates.add(tray);
                statesToTry.addAll(tray.newMoves());
                if (hasGoal(tray)) {
                    return tray;
                }
            }
        }
        System.exit(1);
        return null;
    }

    public boolean hasGoal(Board board) {
        for(Block block : goal) {
            if(!board.hasBlock(block)) {
                return false;
            }
        }
        return true;
    }
}
