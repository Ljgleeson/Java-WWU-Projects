import java.io.*;
import java.util.*;
import java.lang.*;

//Used lecture slides on BST to create the insert and search functions and used lab 4 for class node.
//create a BST class
class BinarySearchTree {

  Node root;
   BinarySearchTree() {
    root = null;
  }
// create a class node with certain attributes including array list that will be used throughout the BST file.
  static class Node {
    Node left;
    Node right;
    int data;
    ArrayList<Integer> arrlist = new ArrayList<Integer>();

    public Node(int key, int counter) {
      left = null;
      right = null;
      data = key;
      arrlist.add(counter);
    }
  }
//inser function that calls insertion which will insert the BST node in the proper location while also adding the arraylist counter if theres a duplicate.
  public void insert(int data, int counter) {
    root = insertion(root, data, counter);
  }
  private  Node insertion(Node node, int data, int counter) {
    if (node==null) {
      return new Node(data, counter);
    }
    if (data < node.data) {
      node.left = insertion(node.left, data, counter);
    } else if (data > node.data) {
      node.right = insertion(node.right, data, counter);
    } else {
      //insted of returning node, add counter value to array list.
      node.arrlist.add(counter);
    }
    return node;
  }
// searches each nodes data until it finds the one we inputed, then prints function printarr with arraylist that has the infomation we need.
  public boolean searchBST(Node node,int data){
    if(node == null){
      System.out.println(data + " not found in BST");
      return false;
    }
    if (node.data == data) {
      printarr(node.arrlist, data);
      return true;
    }
    if (data < node.data) {
      return searchBST(node.left, data);
    } else{
      return searchBST(node.right, data);
    }
}
// printarr function prints the array list we created above with statements that match the requirement in rubric.
   public void printarr(ArrayList<Integer> arrlist, int data){
     System.out.println(data + " appears " + arrlist.size() + " times, order of insertion: " + arrlist);
   }
   // print tree function used prior to see if BST was filled with values correctly. Kept in just incase wanted to know + good to have for future reference.
    //  public void printTree() {
    //     printSubtree(root);
    //     System.out.println(" ");
    // }
    // private void printSubtree(Node n) {
    //     if (n == null) {
    //         return;
    //     }
    //     //inorder print format
    //     printSubtree(n.left);
    //     System.out.print(n.data + " ");
    //     printSubtree(n.right);
    //   }
}
