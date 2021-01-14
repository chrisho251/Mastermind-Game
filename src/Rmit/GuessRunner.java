package Rmit;

public class GuessRunner {

    static Result processGuess(int target, int guess) {
        char des[] = Integer.toString(target).toCharArray();
        char src[] = Integer.toString(guess).toCharArray();
        int hits=0;
        int strikes=0;

        // process strikes
        for (int i=0; i<4; i++) {
            if (src[i] == des[i]) {
                strikes++;
                des[i] = 'a';
                src[i] = 'a';
            }
        }
        // process hits
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (src[i]!='a') {
                    if (src[i]==des[j]) {
                        hits++;
                        des[j] = 'a';
                        break;
                    }
                }
            }
        }
//        System.out.printf("\t");
//        if (strikes==4)	{ // game over
//            System.out.printf("4 strikes - Game over\n");
//            return new Result(hits, strikes);
//        }

//        if (hits==0 && strikes==0)
//            System.out.printf("Miss\n");
//        else if(hits>0 && strikes==0)
//            System.out.printf("%d hits\n", hits);
//        else if(hits==0 && strikes>0)
//            System.out.printf("%d strikes\n", strikes);
//        else if(hits>0 && strikes>0)
//            System.out.printf("%d strikes and %d hits\n", strikes, hits);

        return new Result(hits, strikes);
    }
}