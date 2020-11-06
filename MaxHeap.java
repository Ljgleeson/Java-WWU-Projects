import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class MaxHeap{

  ArrayList<Integer> arr;
  int maxsize;

  public MaxHeap(int size){
     maxsize = size;
    if (maxsize < 1) {
      System.out.println("size of heap too small");
    }
    else {
      arr = new ArrayList<Integer>(maxsize);
    }
  }

  public void insert(int element){
    int tmp;
    arr.add(element);
    int i = arr.size() -1;
    while (arr.get(i) > arr.get((i-1)/2)) {
          tmp = arr.get(i);
          arr.set(i,arr.get((i-1)/2));
          arr.set(((i-1)/2),tmp);
          i = (i-1)/2;
        }
  //    display();
  }
//Graps the value in the first spot (assuming the insertion has worked properly) and prints its value
  public int extractMax(){
    if (arr.size() == 0) {
      System.out.println("No value in heap");
      return 0;
    }
    else{
      System.out.println("Max Value: " + arr.get(0));
      return arr.get(0);
    }
  }

//takes the last index and after removing the max replaces it with that value, then calls heapify to sort the values in a max heap
  public int removeMax(){
    int lastindex = arr.size()-1;
    int val = arr.get(lastindex);
    int max = arr.get(0);
    if (arr.size() <= 1) {
      return max;
    }
    arr.remove(lastindex);
    arr.set(0, val);
    heapify(0);
    return max;
  }

//after moving the max value, heapify goes through the arraylist and resorts it to follow the requirement of max heap
  public void heapify(int i){
    int temp;
    int maxChildIndex;
    while ((2*i + 1) < arr.size()) {
      maxChildIndex = maxChild(i);
      if (arr.get(i) < arr.get(maxChildIndex)) {
        temp = arr.get(maxChildIndex);
        arr.set(maxChildIndex, arr.get(i));
        arr.set(i, temp);
      }
      i = maxChildIndex;
    }
  }

//helper function for heapify that finds which child is the highest value
  public int maxChild(int i){
    int cl = 2*i + 1;
    int cr = 2*i + 2;
    if (cr >= arr.size()) {
      return cl;
    }
    if (arr.get(cl) > arr.get(cr)) {
      return cl;
    } else{
      return cr;
    }
  }

//Displays current max heap
  public void display(){
    System.out.println("current heap is: " + arr);
  }




}
