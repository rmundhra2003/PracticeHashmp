package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/*
        Prompt user to enter a number and print out the word value.
        Example:
        Prompt: Enter a number: 10 Response: You entered ten.
        If number is not found (use myMap.containsKey(10) then prompt user to tell the map to add that to the map.
        Add the key and value to the map with the following line of code:
        myMap.put(10,"ten");
        Retrieve the value with
        String value = myMap.get(10)
*/
public class Main {

    public static void main(String[] args) {
        // write your code here
        String[] strArray = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten"};
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        int input;
        String inputStr = "y";
        Scanner keyboard = new Scanner(System.in);
        String filename = (System.getProperty("user.dir")+ File.separatorChar+"numberValues.txt");


        //Load these values from file
        File file = new File(filename);
        try {
            Scanner scannerInput = new Scanner(file);//note that Scanner can read from a file!
            while (scannerInput.hasNextLine()) {
                String line = scannerInput.nextLine();
                System.out.println(line);
                //Populate the map table
                String[] strLineArray = line.split(" ");
                //insert into map table
                map.put(Integer.valueOf(strLineArray[0]), strLineArray[1]);
            }
            scannerInput.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found! exception");
        }


        while(inputStr.equalsIgnoreCase("y")) {
            //Prompt user to enter number
            System.out.print("Enter the number between 1-10: ");
            input = keyboard.nextInt();
            if((input <1) || (input > 10))
                continue;
            if (map.containsKey(input)) {
                String value = map.get(input);
            }
            else {
                //Add this to the hashmap
                map.put(input, strArray[input]);
            }
            keyboard.nextLine();
            System.out.print("Do you want to enter another number y|n: ");
            inputStr = keyboard.nextLine();
        }
        System.out.println("" +map.toString());

        //Writing these values to a file
        System.out.println("Writing these values to a file....");

        System.out.println(filename);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(filename));
        }catch (FileNotFoundException e) {
            System.out.println(filename);
        }

        //Print both the key and the value on same line
        //for each key in the key set write the key, space and the value
        for(Integer key : map.keySet()) {
            System.out.println("write this line: " +key);
            writer.append(key + " " +map.get(key) +"\n");
        }
        writer.close();


    }
}
