/*
Liam Gleeson
6/4/19
Assigment4, creating graphs and finding the shortest path
*/

import java.io.*;
import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class FindUsers{

public static void main(String[] args) {
  ArrayList<String> arr = new ArrayList<String>(); //arraylist filled with everything in users file
  ArrayList<String> firstVert = new ArrayList<String>(); //arraylist that takes the first vertex
  ArrayList<String> lastVert = new ArrayList<String>(); //arraylist that takes the second vertex
  ArrayList<Integer> edgeWeight = new ArrayList<Integer>(); //arraylist that takes the weight
  ArrayList<String> vertList = new ArrayList<String>(); //arraylist that has all unique vertex names
 // scanner that reads the user file and adds everything to an arraylist
  try{
  Scanner file = new Scanner(new File(args[0]));
  while(file.hasNextLine()){
    String line = file.nextLine();
    String split[] = line.split(" ", 0);
    int counter = 0;
      for(String s:split)
        arr.add(s);
     }
      file.close();
  }
  catch (FileNotFoundException e) {
      e.printStackTrace();
  }
  Graph theGraph = new Graph();
  String sourcePerson = args[1];
  String targetPerson = args[2];
  //adding the correct values to each arraylist, depending on the arraylist
  for (int j = 0, x = 1, y = 2;j<arr.size();) {
    firstVert.add(arr.get(j));
    lastVert.add(arr.get(x));
    edgeWeight.add(Integer.parseInt(arr.get(y)));
    vertList.add(arr.get(j));
    vertList.add(arr.get(x));
    j += 3;
    x += 3;
    y += 3;
  }
  //removing all duplicates from vertList
  for (int i = 0; i < vertList.size(); i++) {
    for (int j = i + 1; j < vertList.size(); j++) {
      if (vertList.get(i).equals(vertList.get(j))) {
        vertList.remove(j);
        j--;
      }
    }
  }
  //create the max size our vertexList could be
  int max_value =  vertList.size();
  theGraph.Graph(max_value);
  //adding each unique name into the vertices
  for (int i = 0; i<vertList.size(); i++) {
    theGraph.addVertex(vertList.get(i));
  }
  //adding each edge with correct source and target vertices into our graph
  int first = 0,last = 0;
  for (int i = 0;i<edgeWeight.size(); i++) {
      for (int x = 0;x<vertList.size(); x++) {
        if (firstVert.get(i).equals(vertList.get(x))) {
           first = x;
        }
      }
      for (int y = 0; y<vertList.size(); y++) {
        if (lastVert.get(i).equals(vertList.get(y))) {
           last = y;
        }
      }
    theGraph.addEdge(first,last,edgeWeight.get(i));
  }
  //initializing variables
   String letter = String.valueOf(targetPerson.charAt(0));
   int sourceNum = 0;
   int targetNum = 0;
   int output = 0;
   int compValue = 0;
   //find the correct value of location for source vertex.
   for (int i = 0; i<vertList.size(); i++) {
     if (sourcePerson.equals(vertList.get(i))) {
         sourceNum = i;
     }
   }
   //If its a comparrison check, runs 3 if statements to see if its a less than, greater than, or equal comp.
   //once known, takes the value it wants to compare with and plug into theGraph.path to find solution
   if (Character.isDigit(targetPerson.charAt(2))) {
     if (letter.equals("l")) {
       String[] split = targetPerson.split("t");
       compValue = Integer.parseInt(split[1]);
       System.out.print(sourcePerson + (" less than ") + compValue + (" : "));
       theGraph.dijsktra(sourceNum,targetNum, compValue, 1);
     } else if (letter.equals("g")) {
       String[] split = targetPerson.split("t");
       compValue = Integer.parseInt(split[1]);
       System.out.print(sourcePerson + (" greater than ") + compValue + (" : "));
       theGraph.dijsktra(sourceNum,targetNum, compValue, 2);
     } else{
       String[] split = targetPerson.split("q");
       compValue = Integer.parseInt(split[1]);
       System.out.print(sourcePerson + (" equal to ") + compValue + (" : "));
       theGraph.dijsktra(sourceNum,targetNum, compValue, 3);
     }
   }
   //if its not a comparrison, then add the seconds location value for source vertex and plug into thegraph.path to find solution
   else {
     for (int j = 0; j<vertList.size(); j++) {
       if (targetPerson.equals(vertList.get(j))) {
           targetNum = j;
       }
     }
    System.out.print(sourcePerson + (" -> ") + targetPerson + (" : "));
    theGraph.dijsktra(sourceNum,targetNum, 0, 0);
   }
 }
}
