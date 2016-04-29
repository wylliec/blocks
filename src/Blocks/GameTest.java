package Blocks;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Caleb on 4/22/2016.
 */
public class GameTest {
    @Test
    public void loop() throws Exception {
        List<String> input = new ArrayList<>();
        input.add("5 4");
        input.add("2 1 0 0");
        input.add("2 1 0 3");
        input.add("2 1 2 0");
        input.add("2 1 2 3");
        input.add("2 2 1 1");
        input.add("1 2 3 1");
        input.add("1 1 4 0");
        input.add("1 1 4 1");
        input.add("1 1 4 2");
        input.add("1 1 4 3");
        List<String> goal = new ArrayList<>();
        goal.add("2 2 3 1");
        Game playMe = new Game(input, goal);
        Board win = playMe.loop();
        System.out.println(win);

        Deque<String> moves = new ArrayDeque<>();
        while(win.getPrev() != null) {
            String move = win.getMove();
            if(Solver.isDebug()) {
                move += "\n" + win.toString();
            }
            moves.push(move);
            win = win.getPrev();
        }

        assertEquals(moves.size(), 120);

        String moveString = "";
        while(!moves.isEmpty()) {
            moveString += moves.pop() + "\n";
        }

//        assertEquals(moveString,
//                "1 1 0 1\n" +
//                "3 1 2 1\n" +
//                "4 2 3 2\n" +
//                "4 1 3 1\n" +
//                "4 0 4 1\n" +
//                "4 3 4 2\n" +
//                "2 0 3 0\n" +
//                "2 3 3 3\n" +
//                "2 1 2 2\n" +
//                "3 1 2 1\n" +
//                "2 1 2 0\n" +
//                "2 2 2 1\n" +
//                "3 3 2 3\n" +
//                "4 2 4 3\n" +
//                "4 1 4 2\n" +
//                "3 0 3 1\n" +
//                "2 0 3 0\n" +
//                "2 1 2 0\n" +
//                "3 0 4 0\n" +
//                "3 2 2 2\n" +
//                "4 2 3 2\n" +
//                "4 3 4 2\n" +
//                "2 3 3 3\n" +
//                "2 2 2 3\n" +
//                "2 0 2 1\n" +
//                "0 0 1 0\n" +
//                "1 0 2 0\n" +
//                "0 1 0 0\n" +
//                "0 3 0 2\n" +
//                "2 3 1 3\n" +
//                "3 3 2 3\n" +
//                "1 3 0 3\n" +
//                "4 2 4 3\n" +
//                "2 3 1 3\n" +
//                "3 2 3 3\n" +
//                "3 1 3 2\n" +
//                "4 0 4 1\n" +
//                "4 1 3 1\n" +
//                "2 0 3 0\n" +
//                "2 1 2 0\n" +
//                "3 2 2 2\n" +
//                "4 3 4 2\n" +
//                "4 2 4 1\n" +
//                "2 2 3 2\n" +
//                "0 2 1 2\n" +
//                "0 3 0 2\n" +
//                "1 3 0 3\n" +
//                "3 3 2 3\n" +
//                "3 2 3 3\n" +
//                "1 2 2 2\n" +
//                "2 2 3 2\n" +
//                "2 3 2 2\n" +
//                "2 2 1 2\n" +
//                "2 0 2 1\n" +
//                "2 1 2 2\n" +
//                "3 0 2 0\n" +
//                "4 1 4 0\n" +
//                "3 1 4 1\n" +
//                "2 0 2 1\n" +
//                "4 0 3 0\n" +
//                "4 1 4 0\n" +
//                "2 1 3 1\n" +
//                "2 2 2 1\n" +
//                "0 3 1 3\n" +
//                "0 2 0 3\n" +
//                "2 1 2 0\n" +
//                "3 2 2 2\n" +
//                "1 2 0 2\n" +
//                "2 2 1 2\n" +
//                "3 1 3 2\n" +
//                "4 0 4 1\n" +
//                "3 0 4 0\n" +
//                "2 0 3 0\n" +
//                "0 0 1 0\n" +
//                "0 2 0 1\n" +
//                "0 3 0 2\n" +
//                "0 1 0 0\n" +
//                "0 2 0 1\n" +
//                "1 2 0 2\n" +
//                "3 2 2 2\n" +
//                "4 1 4 2\n" +
//                "4 0 4 1\n" +
//                "1 3 0 3\n" +
//                "3 3 2 3\n" +
//                "4 2 4 3\n" +
//                "4 1 4 2\n" +
//                "3 0 4 0\n" +
//                "1 0 2 0\n" +
//                "0 1 1 1\n" +
//                "1 1 1 0\n" +
//                "0 2 0 1\n" +
//                "2 2 1 2\n" +
//                "1 2 0 2\n" +
//                "2 0 2 1\n" +
//                "1 0 2 0\n" +
//                "2 0 3 0\n" +
//                "0 0 1 0\n" +
//                "1 0 2 0\n" +
//                "0 1 0 0\n" +
//                "0 2 0 1\n" +
//                "0 3 0 2\n" +
//                "2 3 1 3\n" +
//                "1 3 0 3\n" +
//                "2 1 2 2\n" +
//                "3 0 3 1\n" +
//                "3 1 2 1\n" +
//                "4 0 3 0\n" +
//                "4 2 4 1\n" +
//                "4 3 4 2\n" +
//                "4 1 4 0\n" +
//                "4 2 4 1\n" +
//                "2 2 3 2\n" +
//                "2 1 2 2\n" +
//                "2 2 2 3\n" +
//                "2 0 2 1\n" +
//                "2 1 2 2\n" +
//                "3 0 2 0\n" +
//                "4 1 3 1\n" +
//                "3 1 3 0\n" +
//                "3 2 3 1\n");

    }

    @Test
    public void hasGoal() throws Exception {

    }

}