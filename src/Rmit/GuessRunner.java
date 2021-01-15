package Rmit;

import javax.swing.*;
//import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GuessRunner {
    static Result processGuess(int target, int guess) {
        char des[] = Integer.toString(target).toCharArray();
        char src[] = Integer.toString(guess).toCharArray();
        int hits = 0;
        int strikes = 0;

        // process strikes
        for (int i = 0; i < 4; i++) {
            if (src[i] == des[i]) {
                strikes++;
                des[i] = 'a';
                src[i] = 'a';
            }
        }
        // process hits
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (src[i] != 'a') {
                    if (src[i] == des[j]) {
                        hits++;
                        des[j] = 'a';
                        break;
                    }
                }
            }
        }
//        System.out.printf("\t");
//        if (strikes == 4) { // game over
//            System.out.printf("4 strikes - Game over\n");
//            return new Result(hits, strikes);
//        }
//        if (hits == 0 && strikes == 0)
//            System.out.printf("Miss\n");
//        else if (hits > 0 && strikes == 0)
//            System.out.printf("%d hits\n", hits);
//        else if (hits == 0 && strikes > 0)
//            System.out.printf("%d strikes\n", strikes);
//        else if (hits > 0 && strikes > 0)
//            System.out.printf("%d strikes and %d hits\n", strikes, hits);

        return new Result(hits, strikes);
    }

    public static void main(String[] args) {
        // write your code here

        FileWriter fileWriter;
        try {
//            fileWriter = new FileWriter("result_knuth.csv",true);
//            fileWriter.append("target,first guess,number of guess,guesses \n");
//            fileWriter.flush();
//            fileWriter.close();
            for (int i = 1538 ; i < 10000; i++) {
                fileWriter = new FileWriter("result_knuth.csv",true);
                int guess_cnt = 0;
                int target = i;
                Result res = new Result();
                System.out.println("Guess\tResponse\n");
                while (res.getStrikes() < 4) {
                    int guess = Guess.make_guess(res.getHits(), res.getStrikes());
                    if (guess_cnt == 0) {
                        fileWriter.append(String.valueOf(target));
                        fileWriter.append(",");
                        fileWriter.append(String.valueOf(guess));
                        fileWriter.append(",");
                    }
                    System.out.printf("%d\n", guess);
                    if (guess == -1) {    // user quits
                        System.out.printf("you quit: %d\n", target);
                        return;
                    }
                    guess_cnt++;

                    res = processGuess(target, guess);
                }
                Guess.arrlist = new ArrayList<>();
                fileWriter.append(String.valueOf(guess_cnt));
                fileWriter.append("\n");
                System.out.printf("Target: %d - Number of guesses: %d\n", target, guess_cnt);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}

