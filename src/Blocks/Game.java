package Blocks;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Game {
    private Board board;
    private int[][] goal;

    public Game(Board gameBoard, List<String> goalInput) {
        this.board = gameBoard;
        this.goal = new int[goalInput.size()][4];

        for(int i = 0; i < goalInput.size(); i++) {
            String[] goalBlocks = goalInput.get(i).split(" ");
            for (int j = 0; j < this.goal[0].length; j++) {
                this.goal[i][j] = Integer.parseInt(goalBlocks[j]);
            }

        }
    }

    public void loop(){
//        Store the root node in Container
//        While (there are nodes in Container)
//            N = Get the "next" node from Container
//            Store all the children of N in Container
//            Do some work on N
        while(!hasGoal()) {
            for (Board.Direction d : Board.Direction.values()) {
                board.move(1, d);
            }
        }
    }

    public boolean hasGoal() {
        for(int[] block : goal) {
            if(!board.hasBlock(block[0], block[1], block[2], block[3])) {
                return false;
            }
        }
        return true;
    }
}
