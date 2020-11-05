/*
Liam Gleeson
5/27/19
Implementing priority queue using either min-heap or max heap
*/

import java.io.*;
import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class TakeoffQueue{

  public static void main(String[] args) {
    //created multiple array list to be filled with specific values
    ArrayList<String> arr = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();
    ArrayList<String> passenger = new ArrayList<String>();
    ArrayList<String> flight = new ArrayList<String>();
    ArrayList<Integer> flightnum = new ArrayList<Integer>();
    int input = Integer.parseInt(args[1]);

    //scanner that adds all values in text file into an Array list.
    try{
    Scanner file = new Scanner(new File(args[0]));
    while(file.hasNextLine()){
      String line = file.nextLine();

      for (String word : line.split(" ")){
        arr.add(word);
        }
      }
        file.close();
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    //create my heap
    MaxHeap myMaxheap = new MaxHeap(arr.size());
//if statement to follow correct user input task
    if (input == 1) {
      //add all request time values into an array list
      for (int i = 3;i<arr.size(); i++) {
           time.add(arr.get(i));
           i += 4;
          }
      //add all flight numbers to new array list
      for (int i = 0;i<arr.size(); i++) {
           flight.add(arr.get(i));
           i+=4;
          }
      //add request time, without colon so its just an int, to a new array list.
      for (int i = 0;i<time.size(); i++) {
        String clock = time.get(i);
        String[] clockstr = clock.split(":");
        int hour = Integer.parseInt(clockstr[0]);
        int min = Integer.parseInt(clockstr[1]);
        if (hour == 12) {
          hour = 0;
        } else {
          hour *= 100;
        }
        int add = hour + min;
        myMaxheap.insert(add);
      }
      //now that each value is in the heap, adding each value that gets removed from removeMax will create a descending list of time request values
      for (int i = 0;i<time.size(); i++) {
        flightnum.add(myMaxheap.removeMax());
      }
      //get the value array list in ascending order then print the flight value that correlates with it
      Collections.reverse(flightnum);
      //a double forloop that will check when value at lowest request time matches the request time in the unsorted array list.
      //when found, add the flight value that matches the unsorted array value as that request time is its pair.
      for (int i = 0;i<flightnum.size(); i++) {
        for (int j = 0;j<time.size(); j++) {
          String clock = time.get(j);
          String[] clockstr = clock.split(":");
          int hour = Integer.parseInt(clockstr[0]);
          int min = Integer.parseInt(clockstr[1]);
          if (hour != 12) {
            min += (100 * hour);
          }
          if (min == flightnum.get(i)) {
            System.out.println(flight.get(j));
          }
        }
      }
    }
    else if (input == 2) {
      //add all passenger count values to new array list
      for (int i = 4; i < arr.size(); i++) {
              passenger.add(arr.get(i));
                i += 4;
              }
              //add all flight numbers to new array list
              for (int i = 0;i<arr.size(); i++) {
                flight.add(arr.get(i));
                i+=4;
              }
              //insert each passenger value into heap to get in order of max heap
              for (int i = 0; i < passenger.size(); i++) {
                int value = Integer.parseInt(passenger.get(i));
                myMaxheap.insert(value);
             }
             //loop that removes max value and adds to array list in descending order
             for (int i = 0; i <=passenger.size()-1; i++) {
               flightnum.add(myMaxheap.removeMax());
           }
           //double forloop that will have correct passenger order and compare each flightnum until they are =, at that
           //index, add that flight as the flightnum and flight are paired in index values.
           for (int i = 0; i<flightnum.size(); i++) {
             if (i > 0) {
              int x = flightnum.get(i);
              int y = flightnum.get(i-1);
              //if duplicates, increment over so not adding double.
              if (x == y) {
                i++;
              int a = flightnum.get(i);
              int b = flightnum.get(i-1);
                if (a == b) {
                  i++;
                  if (x == y) {
                    i++;
                  }
                }
              }
              }
            for (int j = 0; j<passenger.size(); j++) {
             if (Integer.parseInt(passenger.get(j)) == flightnum.get(i)) {
               System.out.println(flight.get(j) + (" ") + flightnum.get(i));
             }
            }
        }
    } else {
      System.out.println("Yeah I didn't get around to the option task 3");
    }
  }

}
