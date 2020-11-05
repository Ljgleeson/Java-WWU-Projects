/*
Liam Gleeson
4/23/19
Sort comparing with number comparison outputed
*/
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
import java.util.Random;

public class SortCompare {

  private static int icount = 0;
  private static int mcount = 0;
  private static int qcount = 0;
  private static int rcount = 0;
  //main function that takes user inputs and prints sorting method details
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input Params");
    System.out.println("============");
    System.out.print("How many entries? ");
    int n = sc.nextInt();
    System.out.println();
    System.out.print("Which sort [m,i,q,r,all]? ");
    String sort = sc.next();
    System.out.println();
    //creating an array with n size and values from -n to n
    Random rand = new Random();
    int[] array = new int[n];
    for (int i = 0;i < array.length; i++) {
      array[i] = rand.nextInt(n + 1 + n) -n;
    }
    if (sort.charAt(0) == 'm') {
      System.out.println("Merge sort");
      System.out.println("==========");
      if (n < 20) {
        System.out.println("Unsorted array: " + Arrays.toString(array));
      }
      mergeSort(array);
      System.out.println("Num Comparisons: " + mcount);
      if (n < 20) {
        System.out.println("Sorted array: " + Arrays.toString(array));
      }
    } else if (sort.charAt(0) == 'i') {
      System.out.println("Insertion sort");
      System.out.println("==============");
      if (n < 20) {
        System.out.println("Unsorted array: " + Arrays.toString(array));
      }
      insertionSort(array);
      System.out.println("Num Comparisons: " + icount);
      if (n < 20) {
        System.out.println("Sorted array: " + Arrays.toString(array));
      }
    } else if (sort.charAt(0) == 'q') {
      System.out.println("Quick sort");
      System.out.println("==========");
      if (n < 20) {
        System.out.println("Unsorted array: " + Arrays.toString(array));
      }
      quickSort(array);
      System.out.println("Num Comparisons: " + qcount);
      if (n < 20) {
        System.out.println("Sorted array: " + Arrays.toString(array));
      }
    }
      else if (sort.charAt(0) == 'r') {
      System.out.println("Radix sort");
      System.out.println("==========");
      if (n < 20) {
        System.out.println("Unsorted array: " + Arrays.toString(array));
      }
      radixSort(array);
      System.out.println("Num Comparisons: " + rcount);
      if (n < 20) {
        System.out.println("Sorted array: " + Arrays.toString(array));
      }
    }
     else if (sort.charAt(0) == 'a') {
       all(n, array);
     }
     else{
      System.out.println("Option not available");
    }
  }

  //All function just calls all four functions with different arrays for each sorting method.
  public static void all(int n, int[] marray){
    // made a deep copy for each sort type so all get to sort the array
    int[] iarray = new int [marray.length];
    for (int i = 0; i < marray.length; i++) {
      iarray[i] = marray[i];
    }
    int[] qarray = new int [marray.length];
    for (int i = 0; i < marray.length; i++) {
      qarray[i] = marray[i];
    }
    int[] rarray = new int [marray.length];
    for (int i = 0; i < marray.length; i++) {
      rarray[i] = marray[i];
    }
    System.out.println("Merge sort");
    System.out.println("==========");
    if (n < 20) {
      System.out.println("Unsorted array: " + Arrays.toString(marray));
    }
    mergeSort(marray);
    System.out.println("Num Comparisons: " + mcount);
    if (n < 20) {
      System.out.println("Sorted array: " + Arrays.toString(marray));
    }
    System.out.println();
    System.out.println("Insertion sort");
    System.out.println("==============");
    if (n < 20) {
      System.out.println("Unsorted array: " + Arrays.toString(iarray));
    }
    insertionSort(iarray);
    System.out.println("Num Comparisons: " + icount);
    if (n < 20) {
      System.out.println("Sorted array: " + Arrays.toString(iarray));
    }
    System.out.println();
    System.out.println("Quick sort");
    System.out.println("==========");
    if (n < 20) {
      System.out.println("Unsorted array: " + Arrays.toString(qarray));
    }
    quickSort(qarray);
    System.out.println("Num Comparisons: " + qcount);
    if (n < 20) {
      System.out.println("Sorted array: " + Arrays.toString(qarray));
    }
    System.out.println();
    System.out.println("Radix sort");
    System.out.println("==========");
    if (n < 20) {
      System.out.println("Unsorted array: " + Arrays.toString(rarray));
    }
    radixSort(rarray);
    System.out.println("Num Comparisons: " + rcount);
    if (n < 20) {
      System.out.println("Sorted array: " + Arrays.toString(rarray));
    }
  }

  //Merge sort, but due to restricition of 1 parameter, I created another function that acted as the mergeSort does in lecture slides
  public static void mergeSort(int[] array) {
    mergeSortReal(array, 0, array.length -1);
  }

  //real merge sort, used slides psuedocode to help create
  public static void mergeSortReal(int[] array, int p, int r){
    if (p < r) {
      int q = (p + r)/2;
      mergeSortReal(array, p, q);
      mergeSortReal(array, q+1, r);
      merge(array, p, q, r);
    }
  }

  //merge function, used slides psuedocode to help create also
  public static void merge(int[] array, int p, int q, int x){
    int n1 = q - p + 1;
    int n2 = x - q;
    int l[] = new int [n1 + 1];
    int r[] = new int [n2 + 1];
    for (int i = 0;i < n1; i++) {
      l[i] = array[p + i];
    } for (int j = 0;j < n2; j++) {
      r[j] = array[q + j + 1];
    }
    l[n1] = Integer.MAX_VALUE;
    r[n2] = Integer.MAX_VALUE;
    int i = 0, j = 0;
    for (int k = p; k <= x ; k++) {
      if (l[i] <= r[j]) {
        array[k] = l[i];
        i++;
        mcount++;
      } else{
        array[k] = r[j];
        j++;
        mcount ++;
      }
    }
  }

  //Insertion code created from lab 2 code
  public static void insertionSort(int[] array){
      int count = 0;
      int a = array.length;
      for (int i = 0; i < a; i++){
        int key = array[i];
        int j = i;
        while (j > 0 && array[j - 1] > key) {
          array[j] = array[j-1];
          j = j-1;
          icount++;
        }
        array[j] = key;
        icount ++;
      }
  }

  //Like mergeSort, i created another quick sort that had same parameters as slides psuedocode.
  public static void quickSort(int[] array){
    quickSortReal(array, 0, array.length - 1);
  }

  //Real quick sort
  public static void quickSortReal(int[] array, int first, int last){
    if (first < last) {
      int pivot = partition(array, first, last);
      quickSortReal(array, first, pivot - 1);
      quickSortReal(array, pivot + 1, last);
    }
  }    

  //partition function, only function to return an int as needed for quicksort.
  public static int partition(int[] array, int first, int last){
    int pivot = array[first];
    int l = first + 1;
    int r = last;
    boolean done = false;
    while(! done){
      while (l <= r && array[l] <= pivot) {
        l++;
        qcount++;
      } while(r >= l && array[r] >= pivot){
        r--;
      } if (r< l) {
        done = true;
      } else{
        int temp = array[l];
        array[l] = array[r];
        array[r] = temp;
      }
    }
    int temp = array[first];
    array[first] = array[r];
    array[r] = temp;
    return r;
  }

  //https://eddmann.com/posts/least-significant-digit-lsd-radix-sort-in-java/
  //https://stackoverflow.com/questions/36962105/radix-sort-an-array-of-strings-using-a-queue-in-java
  //Using lecture psuedocode and examples from the two websites above, I created a hybrid version that will handle negatives by creating 19 buckets (-9 to 9) and
  //treating -9 as 0 and 9 as 19 so that each value is positive in the bucket
  public static void radixSort(int[] array){
   //create 19 buckets (queues) for each digit (-9 to 9) to deal with negative numbers as well as positive.
   Queue<Integer>[] buckets = new Queue[19];
   for (int i = 0; i < 19; i++){
       buckets[i] = new LinkedList<Integer>();
     }
   //find max length of highest array integer
   int maxvalue = array[0];
   for (int i = 0; i < array.length ; i++) {
     if (maxvalue < array[i]) {
       maxvalue = array[i];
     }
   }
   String val = Integer.toString(maxvalue);
   int max = val.length();
   int count = 1;
   int exp = 1;
   //for each digit (least to most significant)
   while (count <= max) {
       count++;
       //for each element in array
       for (int i = 0; i < array.length; i++) {
          // value equals LSD of that integer,
           int value = (array[i] / exp) % 10;
           // so now adding 9 to all now makes -9 = 0 and so forth so all go into their proper bucket.
           value += 9;
           buckets[value].add(array[i]);
       }
       //increment divisor so that it moves to the next LSD.
       exp *= 10;
       int inc = 0;
       // for each bucket, starting from smallest digit
       for (Queue<Integer> mybucket : buckets) {
       //while bucket is non-empty
           while (! mybucket.isEmpty()){
             //restore element to list and increment
               array[inc++] = mybucket.remove();
             }
        }
   }
  }
}
