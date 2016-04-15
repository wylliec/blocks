package Blocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Caleb on 2/19/2016.
 */
public class Solver {
    public static void main(String[] args) throws FileNotFoundException {
        int pos = 0;
        if(pos < args.length && args[pos].startsWith("-o")) {
            System.out.println(args[pos].substring(2)); // -ooptions
            pos++;
        }
        Scanner input = new Scanner(new File(args[pos]));
        List<String> inputList = new ArrayList<>();
        Scanner goal = new Scanner(new File(args[pos + 1]));
        List<String> goalList = new ArrayList<>();
        while(input.hasNextLine()) {
            inputList.add(input.nextLine());
        }
        while(goal.hasNextLine()) {
            goalList.add(goal.nextLine());
        }
        Board tray = new Board(inputList);
        Game playMe = new Game(tray, goalList);
        System.out.println(tray);
        playMe.loop();
    }
}
