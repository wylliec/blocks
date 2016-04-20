package Blocks;

import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Game {
    private Board board;
    private Set<Block> goal;
    private boolean debug;
    private Stack<int[]> moves;
    private Set<Integer> hashes;

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

    public void loop(){
//        Store the root node in Container
//        While (there are nodes in Container)
//            N = Get the "next" node from Container
//            Store all the children of N in Container
//            Do some work on N
        while(!hasGoal()) {

        }
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
