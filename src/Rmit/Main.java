package Rmit;

import javax.swing.*;
//import java.util.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        List<Integer> arrlist = new ArrayList<>();

        List<Integer> possible = new ArrayList<>();
        for (int i = 1000; i < 10000; i++) {
            arrlist.add(i);
            possible.add(i);
        }
        Iterator<Integer> possibleTemp = arrlist.iterator();
        int target = 1000;
        int initial_guess = 1123;
        int step = 1;
        GuessRunner guessRunner = new GuessRunner();
        Result result = guessRunner.processGuess(target, initial_guess);
//        System.out.println(result.getHits()+ " and "+ result.getStrikes());
//        Result lmao = guessRunner.processGuess(1000,target);
//        Result lmao = guessRunner.processGuess(target,1000);

        while (possibleTemp.hasNext()) {
//            System.out.println("current"+possibleTemp.next());
            int num = possibleTemp.next();
            Result temp = guessRunner.processGuess(num, initial_guess);
            if (temp.getHits() != result.getHits() || temp.getStrikes() != result.getStrikes()) {

//                System.out.println(num +"result"+ result.getStrikes() + "result"+ result.getStrikes());
//                System.out.println(num +"temp"+ temp.getStrikes() + "temp"+ temp.getStrikes());


                possibleTemp.remove();
            }
        }

//        System.out.println(arrlist);

        List<Result> results = new ArrayList<Result>();
        results.add(new Result(0, 0));
        results.add(new Result(0, 1));
        results.add(new Result(0, 2));
        results.add(new Result(0, 3));
        results.add(new Result(0, 4));
        results.add(new Result(1, 0));
        results.add(new Result(1, 1));
        results.add(new Result(1, 2));
        results.add(new Result(1, 3));
        results.add(new Result(2, 0));
        results.add(new Result(2, 1));
        results.add(new Result(2, 2));
        results.add(new Result(3, 0));
        results.add(new Result(3, 1));
        results.add(new Result(4, 0));

        int next_num = arrlist.get(get_next_num(arrlist,results));
//        Result temp = guessRunner.processGuess(target,next_num);
        System.out.println("this is next guess " + next_num);
        while(next_num != target) {
            result = guessRunner.processGuess(target,next_num);
            System.out.println(result.getStrikes()+" hit "+result.getHits());
            while (possibleTemp.hasNext()) {
                Result temp = guessRunner.processGuess(possibleTemp.next(), next_num);
                if (temp.getHits() != result.getHits() || temp.getStrikes() != result.getStrikes()) {
                    possibleTemp.remove();
                }
            }
            possibleTemp = arrlist.iterator();
            next_num = arrlist.get(get_next_num(arrlist, results));
            System.out.println("this is next guess " + next_num);
            step++;
        }
        System.out.println("this is number of guess "+ step);
    }

        public static int findWord (String[]arr) {

            // Create HashMap to store word and it's frequency
            HashMap<String, Integer> hs = new HashMap<String, Integer>();

            // Iterate through array of words
            for (int i = 0; i < arr.length; i++) {
                // If word already exist in HashMap then increase it's count by 1
                if (hs.containsKey(arr[i])) {
                    hs.put(arr[i], hs.get(arr[i]) + 1);
                }
                // Otherwise add word to HashMap
                else {
                    hs.put(arr[i], 1);
                }
            }

            // Create set to iterate over HashMap
            Set<Map.Entry<String, Integer>> set = hs.entrySet();
            String key = "";
            int value = 0;

            for (
    Map.Entry<String, Integer> me : set) {
                // Check for word having highest frequency
                if (me.getValue() > value) {
                    value = me.getValue();
                    key = me.getKey();
                }
            }

            // Return word having highest frequency
//        System.out.println(hs);
//        System.out.println(key);
            return hs.get(key);
        }

        public static int indexOfSmallest (List < Integer > array) {
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

        public static int indexOfMax (List < Integer > array) {
            int index = 0;
            int max = array.get(index);

            for (int i = 1; i < array.size(); i++) {
                if (array.get(i) > max) {
                    max = array.get(i);
                    index = i;
                }
            }
            return index;
        }

        public static int maxInList (List < Integer > array) {
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

        public static int minInList (List < Integer > array) {
            int min = array.get(0);
            int index = 0;
            for (int i = 1; i < array.size(); i++) {
                if (array.get(i) < min) {
                    min = array.get(i);
                    index = i;
                }
            }
            return min;
        }

        public static int getRemainCount ( int target, Result result, List < Integer > arr){
            int count = 0;
            GuessRunner guessRunner = new GuessRunner();
            for (int i = 0; i < arr.size(); i++) {
                Result temp = guessRunner.processGuess(target, arr.get(i));
                if (temp.getHits() != result.getHits() || temp.getStrikes() != result.getStrikes()) {
                    count++;
                }
            }
//        System.out.println(arr.size());
            return count;
        }

        public static int getMaxDifferentCount ( int target, List<Result > results, List < Integer > arr){
            List<Integer> temp_diff = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {
                int count = getRemainCount(target, results.get(i), arr);
                temp_diff.add(count);
            }
//        System.out.println("this is temp diff size " +temp_diff.size());
            return minInList(temp_diff);
        }
        public static int get_next_num (List < Integer > arr, List < Result > results){
//            System.out.println("this is remain size "+arr.size());
            List<Integer> temp_int = new ArrayList<Integer>();
            for (int i = 0; i < arr.size(); i++) {
                temp_int.add(getMaxDifferentCount(arr.get(i), results, arr));
            }
//        System.out.println("this is index"+ indexOfMax(temp_int));
//        System.out.println("this is index"+indexOfSmallest(temp_int));
            return indexOfMax(temp_int);
        }

    }

