import java.util.Random;

import static javax.print.attribute.standard.MediaSizeName.A;

public class main {
    public static void main(String args[]){
        // MIPS(Millions of Instructions Per Second)
        int[] A = new int[10000000]; //1Mil entries
        Random R = new Random();
        for(int i : A){
            A[i] = R.nextInt();
        }
        long timeAfterSorting = System.currentTimeMillis();
        long timeMillis = System.currentTimeMillis();
        timeMillis = System.currentTimeMillis();
        insertSort(A);
        timeAfterSorting = System.nanoTime();
        System.out.println("Time before function call: "+ timeMillis +
                "\nTime after function call: " + timeAfterSorting
                + "\nDiff: " + (timeAfterSorting-timeMillis));

    }

    private static void insertSort(int[] A){
        for(int i = 1; i < A.length; i++){
            int value = A[i];
            int j = i - 1;
            while(j >= 0 && A[j] > value){
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = value;
        }
    }
}
