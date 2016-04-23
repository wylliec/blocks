package Blocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Solver {
    private static boolean debug = false;

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 3 || args.length < 2 ) {
            System.out.println("Wrong number of arguments.");
            System.out.println("Usage: java Solver [-o[options|debug]] init-file goal-file");
            System.exit(1);
        }
        int pos = 0;

        // Seperates out option arguments
        if(pos < args.length && args[pos].startsWith("-o")) {
            String option = args[pos].substring(2);
            if(option.contains("debug")) {
                Solver.debug = true;
                System.out.println("option: " + option); // -ooptions, -odebug
            }
            if(option.contains("options")) {
                System.out.println("Usage: java Solver [-o[options|debug]] init-file goal-file");
            }
            pos++;
        }

        // Reads in files into Lists of strings
        List<String> input = readFile(args[pos]);
        List<String> goal = readFile(args[pos + 1]);
        if(isDebug()) {
            System.out.println(input);
            System.out.println(goal);
        }
        Game playMe = new Game(input, goal);
        Deque<Board.BlockDirection> moves = playMe.loop();
        printMoves(moves);
    }

    private static List<String> readFile(String filename) throws FileNotFoundException {
        List<String> inputList = new ArrayList<>();
        try(Scanner in = new Scanner(new File(filename))) {
            while (in.hasNextLine()) {
                inputList.add(in.nextLine());
            }
        }
        return inputList;
    }

    // use the call stack to reverse the move stack to print the moves in order
    public static void printMoves(Deque<Board.BlockDirection> moves) {
        while(!moves.isEmpty()) {
            Board.BlockDirection move = moves.removeLast();
            System.out.print(move.getBlock().getY() + " ");
            System.out.print(move.getBlock().getX() + " ");
            System.out.print(move.getBlock().getY() + move.getDirection().getY() + " ");
            System.out.println(move.getBlock().getX() + move.getDirection().getX());
        }
    }

    public static boolean isDebug() {
        return Solver.debug;
    }
}
