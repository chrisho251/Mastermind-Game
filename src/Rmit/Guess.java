package Rmit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Guess {
    int initial_guess = 1122;
    public static int indexOfSmallest(List<Integer>array ){
        int index = 0;
        int min = array.get(index);

        for (int i = 1; i < array.size(); i++){
            if (array.get(i) < min){
                min = array.get(i);
                index = i;
            }
        }
        return index;
    }

    public static int maxInList(List<Integer>array){
        int max = array.get(0);
        int index = 0;
        for (int i = 1; i < array.size(); i++){
            if (array.get(i) > max){
                max = array.get(i);
                index = i;
            }
        }
        return max;
    }

    public static int getRemainCount(List<Integer> arr,int target, Result result){
        int count =0;
        GuessRunner guessRunner = new GuessRunner();
        for (int i =0; i < arr.size();i++){
            Result temp = guessRunner.processGuess(target,arr.get(i));
            if (temp.getHits() == result.getHits() && temp.getStrikes() == result.getStrikes()){
                count++;
            }
        }
//        System.out.println(count);
        return count;
    }

    public static int getMaxDifferentCount(List<Integer>arr, List<Result> results,int target){
        List<Integer> temp_diff = new ArrayList<>();
        for (int i =0; i < results.size();i++){
            int count = getRemainCount(arr,target,results.get(i));
            temp_diff.add(count);
        }
//        System.out.println("this is temp diff size " +temp_diff.size());
        return maxInList(temp_diff);
    }
    public static int get_next_num(List<Integer> arr, List<Result> results){
        List<Integer> temp_int = new ArrayList<Integer>();
        for (int i =0; i < arr.size();i++){
            temp_int.add(getMaxDifferentCount(arr,results,arr.get(i)));
        }
//        System.out.println("this is array size"+ arr.size());
//        System.out.println(temp_int.size());
        return indexOfSmallest(temp_int);
    }
}
