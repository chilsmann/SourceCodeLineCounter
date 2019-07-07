/**
 * Cameron Hilsmann - Assignment 4: Source Code line counter
 *
 * This program takes an absolute path to a directory and process the appropriate files to figure out how many actual lines
 * of code there are vs. the comments.
 *
 * The most difficult part I had with this program was parsing the information. I had tried using Regex at first, but as I am
 * inexperienced in that, I felt my time was being streched to thin and I wanted to try and just solve the problem first. The most difficult were edge cases.
 * Because I read line by line, I needed a boolean to keep track of any '/*' lines that did not have a closing bracket int the same line of code. My solution is
 * not elegwent as I would like.
 *
 * I used the sample test, along with two previously written classes. Jolly Jumper, from earlier in the quarter and a class that was part of my Software Eng. class. I also
 * added a .txt file to make sure my program would just pick out the .java files that were requested.
 */

import java.util.*;
import java.io.*;


public class SourceCodeLineCounter {
    public static void main(String [] args)throws IOException {
        String path, extension;
        //getting file
        File inputFile = new File("input.txt");
        if(args.length > 0){
            inputFile = new File(args[0]);
        }
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter an absolute path: ");
        path = kb.nextLine();
        System.out.println("Please specify the file extension ('java' for Java files and 'cs' for C# files): ");
        extension = kb.nextLine();
        File[] specificContents = getProperFiles(path, extension);
        int totalLines = 0;
        int temp = 0;
        //System.out.println(specificContents.length);
        for(File f : specificContents) {
            if (f.exists()) {
                temp = countFile(f.getAbsolutePath());
                totalLines = totalLines + temp;
                System.out.println("There are "+temp+ " lines of code in "+f.getAbsolutePath());
                temp = 0;
            }
        }

        System.out.println("There were "+totalLines+" total in those files");
    }//end main

    public static int countFile(String filename){
        int counter = 0;
        File myFile = new File(filename);

        //read file
        try{
            Scanner scan = new Scanner(myFile);
            boolean flag = false;
            String line;
            while(scan.hasNext()) {
                //System.out.println(scan.nextLine());
                line=scan.nextLine();
                line = line.trim(); // get rid of whitespace
                int num = line.length();
                if(line.startsWith("/*"))
                    flag = true;
                if(line.contains("*/"))
                    flag = false;
                if(line.isEmpty() || line.equals("\t//") || line.equals("\t/*") || line.equals("\t*") || flag){
                    line=".";
                }
                else if((line.startsWith("/*") && !line.contains("*/")) || line.startsWith("//") || line.startsWith("* ") || (line.startsWith("*/") && line.length() == 2)){
                    line = ".";
                }
                if(!line.equals(".") && !line.equals(""))
                    counter++;
                if(line.endsWith("/*"))
                    flag = true;
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }


        return counter;
    }

    public static File[] getProperFiles(String path, String extension){
        File directory = new File(path);
        int mathcingExten = 0;  String exten;
        File[] contents = directory.listFiles();
        for( File F : contents){
            if(extension.equals("java")) {
                exten = F.toString().substring(F.toString().length() - 4);
                if(extension.equals(exten))
                    mathcingExten++;
            }
            else {
                exten = F.toString().substring(F.toString().length() - 2);
                if(extension.equals(exten))
                    mathcingExten++;
            }
        }
        File[] specificContents = new File[mathcingExten];
        //testing reasons
        int index = 0;
        for(File f : contents){
            if(extension.equals("java")) {
                exten = f.toString().substring(f.toString().length() - 4);
                if(extension.equals(exten))
                    specificContents[index] = contents[index];
            }
            else {
                exten = f.toString().substring(f.toString().length() - 2);
                if(extension.equals(exten))
                    specificContents[index] = contents[index];
            }
            index++;
        }
        return specificContents;
    }//end getProperFiles
}
