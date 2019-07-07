/**
 * Cameron Hilsmann - HW2 "Jolly Jumper"
 * The test input ('input.txt') contains all of the sample tests that were shown in the specifications.
 * I tested min and max values. ex.('2 1 3000' and '2 3000 1') I tested a few values that I thought would have
 * issues and values that had the same value (' 2 1 1' and '2 1 -1' and '1 1').
 * I also looked up a few sequences that were Jolly Jumpers to help confirm my results. ex. ('11 1 2 4 7 11 16 22 29 37 46 56')
 *
 */

import java.util.*;
import java.io.*;

public class JollyJumper {
    public static void main(String [] args){
        int numOfNumbers;
        int counter = 0;
        ArrayList<Integer> jollyArray = new ArrayList<Integer>();
        //getting file
        File inputFile = new File("input.txt");
        if(args.length > 0){
            inputFile = new File(args[0]);
        }

        //read file
        try{
            Scanner scan = new Scanner(inputFile);

            while(scan.hasNext()) {
                //get first value
                numOfNumbers = scan.nextInt();
                //jollyArray = new int[numOfNumbers];
                for(int x = 0; x < numOfNumbers; x++){
                    jollyArray.add(scan.nextInt());//) = scan.nextInt();
                }
                checkJolly(jollyArray);
                jollyArray.clear();
            }


        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void checkJolly(ArrayList<Integer> myArray){
        int testVal;
        boolean flag = true;
        ArrayList<Integer> testArray = new ArrayList<Integer>();
        ArrayList<Integer> checker = new ArrayList<Integer>();

        for(int i = 0; i < myArray.size()-1; i++){
            testVal = Math.abs(myArray.get(i) - myArray.get(i+1));
            testArray.add(testVal);
        }

        Collections.sort(testArray);
        int check = testArray.size();
        for(int j=0;j < testArray.size(); j++){
            if(!testArray.contains(check))
                flag = false;
            check--;
        }

        if(flag == false)
            System.out.println("Not jolly");
        else
            System.out.println("Jolly");



    }
}
