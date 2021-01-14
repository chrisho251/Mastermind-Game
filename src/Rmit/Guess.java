package Rmit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Guess {
    public static int initial_guess = 1123;
    public static List<Integer> arrlist = new ArrayList<>()  ;
    public static GuessRunner guessRunner = new GuessRunner();
    public static List<Result> results = new ArrayList<>();
    public static Result result = new Result();
    public static Iterator<Integer> possibleTemp;
    public static int currentNum;
    public static int make_guess(int hits, int strike) {
//        System.out.println(hits);
//        System.out.println(strike);
//        System.out.println(arrlist);
        if (arrlist.size() == 0 && hits ==0 && strike ==0){
//            System.out.println("here1");
            possibleGuess();
            resultsList();
            possibleTemp = arrlist.iterator();
            currentNum = initial_guess;
            return initial_guess;
        }
        else {

            while (possibleTemp.hasNext()) {
                int num = possibleTemp.next();
                Result temp = guessRunner.processGuess(num, currentNum);
                if (temp.getHits() != hits || temp.getStrikes() != strike) {
                    possibleTemp.remove();
                }
            }
            possibleTemp = arrlist.iterator();
            int next_num = arrlist.get(get_next_num(arrlist, results));
            currentNum = next_num;
            return next_num;
        }
    }

    public static List<Integer> possibleGuess() {
        for (int i = 1000; i < 10000; i++) {
            arrlist.add(i);
        }
//        Iterator<Integer> possibleTemp = arrlist.iterator();
        return arrlist;
    }

    public static List<Result> resultsList() {
        for (int i= 0; i <5; i++){
            for(int j=0;j < 5-i ;j++){
                results.add(new Result(i,j));
            }
        }
        return results;
    }

    public static int indexOfSmallest(List<Integer> array) {
        int index = 0;
        int min = array.get(index);

        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) < min) {
                min = array.get(i);
                index = i;
            }
        }
        return index;
    }

    public static int maxInList(List<Integer> array) {
        int max = array.get(0);
        int index = 0;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
                index = i;
            }
        }
        return max;
    }

    public static int getRemainCount(List<Integer> arr, int target, Result result) {
        int count = 0;
        GuessRunner guessRunner = new GuessRunner();
        for (int i = 0; i < arr.size(); i++) {
            Result temp = guessRunner.processGuess(target, arr.get(i));
            if (temp.getHits() == result.getHits() && temp.getStrikes() == result.getStrikes()) {
                count++;
            }
        }
//        System.out.println(count);
        return count;
    }

    public static int getMaxDifferentCount(List<Integer> arr, List<Result> results, int target) {
        List<Integer> temp_diff = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            int count = getRemainCount(arr, target, results.get(i));
            temp_diff.add(count);
        }
//        System.out.println("this is temp diff size " +temp_diff.size());
        return maxInList(temp_diff);
    }

    public static int get_next_num(List<Integer> arr, List<Result> results) {
        List<Integer> temp_int = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++) {
            temp_int.add(getMaxDifferentCount(arr, results, arr.get(i)));
        }

        return indexOfSmallest(temp_int);
    }


}
