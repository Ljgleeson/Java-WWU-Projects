import java.io.*;
import java.util.*;
import java.lang.*;

//initializing public/private variables given from lecture slides
class Graph{
  public String vertexList[];
  public Vertex checkedList[];
  public int adjMat[][];
  private int nVerts;
  private int nTree;
  private int currentVert;
  private int startToCurrent;

//graph function taken from lecture slides
  public void Graph(int size) {
    vertexList = new String[size];
    //adjaceny matrix
    adjMat = new int[size][size];
    for (int j = 0;j< size;j++) {
      for (int k = 0;k < size;k++) {
        adjMat[j][k] = 0;
      }
    }
  }
  //vertex class taken from lecture slides
  class Vertex {
      String label;
      public Vertex(String label) {
            this.label = label;
          }
      }
  //taken from lecture slides
  public void addVertex(String label){
    vertexList[nVerts++] = new String(label);
  }
  //taken from lecture slides
  public void addEdge(int start, int end, int weight){
    adjMat[start][end] = weight;
  }
  //I noticed path just calls dijsktra's algorithm so I merged the two together.
  //I added most of the helper functions into this one function, such as single source and relax.
  public void dijsktra(int src, int tar, int compValue, int comp){
    //initializing some values needed when needing to compare values
    int arr[] = new int[vertexList.length];
    int counter = 0;
    int sPath[] = new int[vertexList.length];
    Boolean visit[] = new Boolean[vertexList.length];
    //Single source function
    for (int i = 0; i < vertexList.length; i++) {
      sPath[i] = Integer.MAX_VALUE;
      visit[i] = false;
    }
    sPath[src] = 0;
    //The "while" loop that runs for each unique vertex in the graph.
    for (int x  = 0; x < vertexList.length; x++){
      int u = extractmin(sPath, visit);
      visit[u] = true;
      //relax function just built into path instead. checks the criteria given in slides as well as if the extractmin didnt receive a pathway or if the adjMat wasnt set to 0.
      for (int j = 0; j<vertexList.length;j++) {
        if (sPath[j] > sPath[u] + adjMat[u][j] && sPath[u] != Integer.MAX_VALUE  && adjMat[u][j] != 0) {
          sPath[j] = sPath[u] + adjMat[u][j];
        }
      }
    }
    //3 if statements to see if the comparrison is a less, greater, or equal statement.
    if (comp == 1) {
      //once known, scans through the sPath values to see which ones fit the comparrison criteria and then prints them out.
      //extra if statements to make sure its own value or a path that doesnt exists is added also.
      for (int i = 0; i<sPath.length; i++) {
        if (sPath[i] == sPath[src]) {

        } else if (sPath[i] < compValue) {
          counter++;
        System.out.print(vertexList[i]);
        System.out.print(" ");
        }
      }
      if (counter == 0) {
        System.out.print("None");
      }
      System.out.println(" ");
    }
    else if (comp == 2) {
      //once known, scans through the sPath values to see which ones fit the comparrison criteria and then prints them out.
      //extra if statements to make sure its own value or a path that doesnt exists is added also.
      for (int i = 0; i<sPath.length; i++) {
        if (sPath[i] == sPath[src]) {

        } else if (sPath[i] == Integer.MAX_VALUE) {

        } else if (sPath[i] > compValue) {
          counter++;
        System.out.print(vertexList[i]);
        System.out.print(" ");
        }
      }
      if (counter == 0) {
        System.out.print("None");
      }
      System.out.println(" ");
    }
    //once known, scans through the sPath values to see which ones fit the comparrison criteria and then prints them out.
    //extra if statements to make sure its own value or a path that doesnt exists is added also.
    else if (comp == 3) {
      for (int i = 0; i<sPath.length; i++) {
        if (sPath[i] == sPath[src]) {

        } else if (sPath[i] == compValue) {
          counter++;
        System.out.print(vertexList[i]);
        System.out.print(" ");
        }
      }
      if (counter == 0) {
        System.out.print("None");
      }
      System.out.println(" ");
    }
    //if not a comparrison statement, prints the shortest path from the source to the target location
    if (compValue == 0 && comp == 0) {
      System.out.println(sPath[tar]);
    }
  }
  //finds the smallest values from the pathways created and returns its location
  public int extractmin(int sPath[], Boolean visit[]){
    int min = Integer.MAX_VALUE;
    int index = -1;
    for (int i = 0; i < vertexList.length; i++) {
      if (sPath[i] <= min && visit[i] == false) {
        min = sPath[i];
        index = i;
      }
    }
    return index;
  }
}
