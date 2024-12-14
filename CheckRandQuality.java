import java.io.*;
import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;

//Used lab 5 and the online source at https://beginnersbook.com/2013/04/try-catch-in-java/ to understand try and catch handles.

public class CheckRandQuality {
  //create BST tree that all nodes will be added to
  static BinarySearchTree tree = new BinarySearchTree();
  public static void main(String[] args) {
  //scanner to read the file at args[0] and then imediately insert that input into the BST as can only use one scanner.
  File file = new File(args[0]);
  try {
      Scanner sc = new Scanner(file);
        int counter = 0;
        while (sc.hasNext()) {
        // counter is getting inserted into BST also so whenever there is a duplicate it will add the counter value to an arraylist associated with that node.
        // due to this when complete, each node will have an arraylist which its length = the amount of times it appeared and the values inside = the order of insertion
        counter++;
        int line = Integer.parseInt(sc.nextLine());
        tree.insert(line, counter);
      }
      sc.close();
  }
  catch (FileNotFoundException e) {
      e.printStackTrace();
  }
  //create an array that reads the rest of the inputs inputted in the command line and creates an int array.
  int [] iarray = new int [args.length - 1];
  for(int i = 1; i < args.length; i++){
     iarray[i - 1] = Integer.parseInt(args[i]);
  }
  //searches the BST for each value inputted on command line and will print if its in the txt file or not.
  for (int x = 0; x < iarray.length ; x ++) {
    tree.searchBST(tree.root , iarray[x]);
  }
  }
}
