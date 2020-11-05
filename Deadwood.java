package Game;

import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;


import Game.BoardLayersListener.boardMouseListener;

import java.util.Random;
import java.util.HashMap;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;





public class Deadwood{  
	

	
  public int die;
  
//  BoardLayersListener board = new BoardLayersListener();


  public static void main(String[] args){
	  
	  Board boardloc = new Board();
	  //If we can figure this out, we would be good spot!
	  
	  BoardLayersListener boardlaylist = new BoardLayersListener();
	  boardlaylist.setVisible(true);

	    // Take input from the user about number of players
//    JOptionPane.showInputDialog(layboard, "How many players?");
	  	  
//	  System.out.println("Welcome to Deadwood. The game of all games");
	  
	  String counter;
	  int playerCount;
	  Scanner pCount = new Scanner(System.in);
	//  System.out.println("Enter number of players");
	//  JOptionPane.showInputDialog(layboard, "How many players?");
	  counter =  JOptionPane.showInputDialog(boardlaylist, "How many players?");
	  playerCount = Integer.parseInt(counter);
	  System.out.println("this is player count " + playerCount);
	  int setDays = 0;
	  int startCredits = 0;
	  int startRank = 0;
	  if(playerCount < 4 && playerCount != 1) {
		  setDays = 3;
	  }else if(playerCount == 4) {
		  setDays = 4;
	  }else if (playerCount == 5) {
  		 setDays = 4;
  		 startCredits = 2;
	  }else if (playerCount == 6) {
		  setDays = 4;
		  	startCredits = 4;
	  }else if (playerCount < 9) {
		  setDays = 4;
		  startRank = 2;
	  } else {
		  System.out.println("Too many players to player game");
		  return;
	  }
	  
	  String[] playerName = new String[playerCount];
	  int i = 0;
   
	  Player player1 = new Player();
	  Player player2 = new Player();
	  Player player3 = new Player();
	  Player player4 = new Player();
	  Player player5 = new Player();
	  Player player6 = new Player();
	  Player player7 = new Player();
	  Player player8 = new Player();
	  Player[] players = new Player[] {player1, player2, player3, player4, player5, player6, player7, player8};
//	  String[] arr = new String[] {"blue", "cyan", "green", "orange", "pink", "red", "violet", "yellow"};
//	  String[] color_array = new String[playerCount];
	  Player [] playersReal = new Player[playerCount];
   
	  for(int k = 0; k < playerCount; k++) {
//    	color_array[k] = arr[k];
		  playersReal[k] = players[k];
	  }

	  
	  while (i < playerCount) {
		  
		 // board.updateStats(1);
		  Scanner name = new Scanner(System.in);
		  playerName[i] = JOptionPane.showInputDialog(boardlaylist, "Please enter player " + (i + 1) + " name ");
		 // System.out.println("Please enter player " + (i + 1) + " name ");
		//  playerName[i] = name.nextLine();
		  playersReal[i].Player(playerName[i]);
		  if(startCredits > 0) {
			  playersReal[i].increaseCredits(startCredits);
		  }
		  //set up each players starting credits
		  if(startRank == 2) {
			  playersReal[i].updateRank(startRank);
		  }
	  
		  String player_real = playersReal[i].getName();
		  String srank = Integer.toString(playersReal[i].getRank());
		  String sdollars = Integer.toString(playersReal[i].getDollars());
		  String scredits = Integer.toString(playersReal[i].getcredits ());
		  String spractice = Integer.toString(playersReal[i].getPractice());
		  boardlaylist.playerStats(player_real, srank, sdollars, scredits, spractice, i);	
		  
		  i++;
	  }

   
	  int dayCounter = 1;  
	  System.out.println("Day " + dayCounter + " has officaly begun!");

	  
	  
	  int location;
	  int cred;
	  int rank;
	  int dolla;
   
	  boolean next = false;
	  HashMap<Location, Scene> hashboard = new HashMap<Location,Scene>();

	  //will be changed to while scene_num != 1 so each player will continue in this loop until only 1 scene left, then day resets and you go again.
	  int scene_count = 10;
	  //while(while daycounter != set days)
   
	  
	  while (dayCounter != setDays) {  
		  if (!next) {
			  //board = newBoard.buildBoard();
			  hashboard = boardloc.buildBoard();
		  } else {
			  //board = newBoard.resetBoard();
			  hashboard = boardloc.resetBoard();
		  }
		  while(scene_count != 1) {
			  int j = 0;
			  while(j < playerCount) {    
				  location = playersReal[j].getLocation();
				  boolean turn_check = false;
				  String current_location = null;
				      				  
				  boardlaylist.playersTurn(playersReal[j], boardloc, 1, hashboard);
				  
				  System.out.println(playerName[j] + " you are at the " + boardloc.locationName(location) + ". What would you like to do on your turn?");    			
				  if(boardloc.locationName(location).equalsIgnoreCase("Trailer")) { //PLAYER LOCATION AT TRAILER
					  System.out.println("You can Move (m) or do Nothing (n)");
					  while(turn_check == false) {
						  Scanner turn_input = new Scanner(System.in);
						  String choice = turn_input.nextLine();
						  if(choice.equalsIgnoreCase("n")) { //Player choose to do nothing
							  turn_check = true;
							  j++;
						  }else if(choice.equalsIgnoreCase("m")) { //PLayer choose to move
							  turn_check = true;
    						
							  //move function	
							  current_location = moving(boardloc, location, playersReal[j], current_location, boardlaylist);
							  //What to do past moving:
    						
							  if(current_location.equalsIgnoreCase("casting")) { //player moved to upgrade
								  playersReal[j].upgrade();
							  }else if(current_location.equalsIgnoreCase("trailer")) { //player is at trailer (somehow)
								  System.out.println("You are currently at " + current_location);          
							  }else {
								  System.out.println("You are on a set"); //You moved to a set
								  //call role function as you have moved to a set	
								  roll_call(boardloc, playersReal[j], hashboard);
								  //role been called
							  }
						  }else {
							  System.out.println("Must choose either m or n");
						  }
					  } 
					  //PLAYER IS AT CASTING OFFICE    
				  }else if (boardloc.locationName(location).equalsIgnoreCase("Casting")) {
					  System.out.println("You can Move (m) or Upgrade (u) or do Nothing (n)");
					  while(turn_check == false) {
						  Scanner turn_input = new Scanner(System.in);
						  String choice = turn_input.nextLine();
						  if(choice.equalsIgnoreCase("n")) { //PLayer choose to do nothing
							  turn_check = true;
							  j++;
						  }else if(choice.equalsIgnoreCase("m")) { //Player choose to MOVE
							  turn_check = true;						
							  //move function
							  current_location = moving(boardloc, location, playersReal[j], current_location, boardlaylist);   												  				
    						
							  if(current_location.equalsIgnoreCase("casting")) { //player moved to upgrade
								  playersReal[j].upgrade();
							  }else if(current_location.equalsIgnoreCase("trailer")) { //player is at trailer (somehow)
								  System.out.println("You are currently at " + current_location);          
							  }else {
								  System.out.println("You are on a set"); //You moved to a set
								  //take a role function
								  roll_call(boardloc, playersReal[j], hashboard);
							  }
						  }else if (choice.equalsIgnoreCase("u")) { //PLayer choose to upgrade
							  turn_check = true;
							  playersReal[j].upgrade();
						  }else {
							  System.out.println("Must choose m, u, or n");
						  }        
					  }      
					  //PLAYER ON A SET
				  }else {
					  //PLayer doesn't have a role
					  if(playersReal[j].roleRank == -1) {
						  System.out.println("You can Move(m), Work(w) or Nothing(n)");
						  while(turn_check == false) {
							  Scanner turn_input = new Scanner(System.in);
							  String choice = turn_input.nextLine();
							  if(choice.equalsIgnoreCase("n")) { //PLayer choose to do nothing
								  turn_check = true;
								  j++;
							  }else if(choice.equalsIgnoreCase("m")) { //PLayer choose to move
								  turn_check = true;
								  //move function
								  
								  current_location = moving(boardloc, location, playersReal[j], current_location, boardlaylist);
								  //same move function
								  
								  //What to do past moving:
								  if(current_location.equalsIgnoreCase("casting")) { //player moved to upgrade
									  playersReal[j].upgrade();
								  }else if(current_location.equalsIgnoreCase("trailer")) { //player is at trailer (somehow)
									  System.out.println("You are currently at " + current_location);          
								  }else {  								  
									  System.out.println("You are on a set"); //You moved to a set
									  //call role function again
									  roll_call(boardloc, playersReal[j], hashboard);
									  //role has be called
								  }
							  }else if (choice.equalsIgnoreCase("w")) { //PLayer Choose to work
								  turn_check = true;
								  //takes role at set at current location.
								  System.out.println("You choose to work on the set");
								  System.out.println("You do not have a role.");
      
								  //call function roll
								  roll_call(boardloc, playersReal[j], hashboard);
   		
								  //work function
								  working(boardloc, playersReal[j], current_location, hashboard, scene_count, playersReal, boardlaylist);  							  							
							  }else {
								  System.out.println("Must choose m, w, or n");
							  }  						
						  }						  
					  }
					  else {
						  System.out.println("You are on role, You can Work(w) or do Nothing (n)");
						  while(turn_check == false) {
							  Scanner turn_input = new Scanner(System.in);
							  String choice = turn_input.nextLine();
							  if(choice.equalsIgnoreCase("n")) {
								  turn_check = true;
								  j++;
							  }else if(choice.equalsIgnoreCase("w")) {
								  turn_check = true;
								  //takes role at set at current location.
								  System.out.println("You choose to work on the set");
								  //work function start   							
								  working(boardloc, playersReal[j], current_location, hashboard, scene_count, playersReal, boardlaylist);  	
							  }else {
								  System.out.println("Must choose w or n");
							  }        
						  }
					  }
				  }
	    			j++;
	    			System.out.println(" ");
			  }
			  scene_count--;
		  }
		  next = true;
	  } //end of outer while loop
   
	  int[] score_arr = new int[playerCount];   
	  for(int x = 0; x < playerCount; x++) {
		  score_arr[x] = (playersReal[x].getcredits() + playersReal[x].getDollars() + (playersReal[x].getRank() * 5));
	  }
	  int max = 0;
	  int max_pos = 0;
	  for(int y = 0; y < playerCount; y++) {
		  if(score_arr[y] > max) {
			  max = score_arr[y];
			  max_pos = y;
		  }
	  }
	  System.out.println("Congrats to " + playersReal[max_pos] + " for being the winner of the game with a score of " + max);
	  
  } //end of main 
  
  //function for move  
  
  
  public static String moving(Board boardloc, int location, Player playersReal, String current_location,  BoardLayersListener board) {
	  System.out.println("You choose to move!");
	  System.out.println("Where would you like to go?");      
	  int moves[] = boardloc.moveCount(location);
	  int x = 0;
	  while(x < moves.length) {
		  boardloc.printName(moves[x]);
		  if(x + 1 != moves.length) {
			  System.out.print(" or ");
		  }
		  x++;
	  }
	  System.out.println(" ");
	  int k = 1;
	  //call move function with moves[];

	
	  
	  
	  boolean move_check = false;
	  while(move_check == false) {
		  Scanner move_input = new Scanner(System.in);
		  String move_choice = move_input.nextLine();
		  if(moves.length == 3) {
			  if(move_choice.equalsIgnoreCase(boardloc.locationName(moves[0]))) {
				  move_check = true;
				  System.out.println("You moved to " + boardloc.locationName(moves[0]));
				  playersReal.updateLocation(moves[0]);
				  current_location = boardloc.locationName(moves[0]);
			  }else if(move_choice.equalsIgnoreCase(boardloc.locationName(moves[1]))) {
				  move_check = true;
				  System.out.println("You moved to " + boardloc.locationName(moves[1]));      
				  playersReal.updateLocation( moves[1]);
				  current_location = boardloc.locationName(moves[1]);
			  }else if (move_choice.equalsIgnoreCase(boardloc.locationName(moves[2]))) {
				  move_check = true;
				  System.out.println("You moved to " + boardloc.locationName(moves[2]));
				  playersReal.updateLocation(moves[2]);
				  current_location = boardloc.locationName(moves[2]);
			  }else {
				  System.out.println("Not a location available to go to, Try again");
			  }
		  } else { //move length is 4
			  if(move_choice.equalsIgnoreCase(boardloc.locationName(moves[0]))) {
				  move_check = true;
				  System.out.println("You moved to " + boardloc.locationName(moves[0]));
				  playersReal.updateLocation(moves[0]);
				  current_location = boardloc.locationName(moves[0]);
			  }else if(move_choice.equalsIgnoreCase(boardloc.locationName(moves[1]))) {
				  move_check = true;
				  System.out.println("You moved to " + boardloc.locationName(moves[1]));
				  playersReal.updateLocation(moves[1]);
				  current_location = boardloc.locationName(moves[1]);
			  }else if (move_choice.equalsIgnoreCase(boardloc.locationName(moves[2]))) {
				  move_check = true;
				  System.out.println("You moved to " + boardloc.locationName(moves[2]));
				  playersReal.updateLocation(moves[2]);
				  current_location = boardloc.locationName(moves[2]);
			  }else if (move_choice.equalsIgnoreCase(boardloc.locationName(moves[3]))) {
				  move_check = true;
				  System.out.println("You moved to " + boardloc.locationName(moves[3]));
				  playersReal.updateLocation(moves[3]);
				  current_location = boardloc.locationName(moves[3]);
			  } else {
				  System.out.println("Not a location available to go to, Try again");
			  }        
		  }
	  }
	  return current_location;
  }
  
  public static void roll_call(Board boardloc, Player playerName, HashMap<Location, Scene> hashboard){     
	  Location playerLoc = boardloc.getLocation(playerName.getLocation());
	  Scene locScene = hashboard.get(playerLoc);
	  int[] locRole = playerLoc.getRoles();
	  int[] sceneRole = locScene.sceneRoles();
	  System.out.println("Would you like to take a role? y/n");
	  System.out.println("These are on card roles " + Arrays.toString(sceneRole));
	  System.out.println("These are Off card roles " + Arrays.toString(locRole));
	  Scanner inputRole = new Scanner(System.in);
	  String takerole = inputRole.nextLine();
	  
	  if (takerole.equalsIgnoreCase("y")) {		  
		  System.out.println("Would you like an on card role? y/n");
		  Scanner inputCard = new Scanner(System.in);
		  String cardRole = inputCard.nextLine();		  
		  System.out.println("Your rank is " + playerName.getRank());		  
		  if (cardRole.equalsIgnoreCase("y")) {
			  
			  boolean valid = false;

				
			  System.out.println("Pick one of these on card roles.");
			  System.out.println(Arrays.toString(sceneRole));
			  

			  while(!valid) {
				  Scanner roleChoose = new Scanner(System.in);
				  String roleChoice = roleChoose.nextLine();
				  int chooseRole = Integer.parseInt(roleChoice);
				  boolean inRole = false;
				  for(int q = 0; q < sceneRole.length; q++) {
					  if (chooseRole == sceneRole[q]) {
						  inRole = true;
						  break;
					  }
				  }
				  if (chooseRole <= playerName.getRank() && inRole) {
					  System.out.println("You now have this role.");
					  playerName.takeRole(chooseRole, true);
					  valid = true;
				  } else if (chooseRole > playerName.getRank()) {
					  System.out.println("Please choose a role thats equal or less than your rank.");
				  }else {           				
					  System.out.println("Not a valid role. Did you change your mind? y/n");
				  }            		            			
			  }	
		  } else {
			  boolean valid = false;
			  while(!valid) {
				  System.out.println("Pick one of these off card roles.");
				  System.out.println(Arrays.toString(locRole));
				  Scanner roleChoose = new Scanner(System.in);
				  String roleChoice = roleChoose.nextLine();
				  try {
					  int chooseRole = Integer.parseInt(roleChoice);
					  boolean inRole = false;						
					  for(int q = 0; q < locRole.length; q++) {
						  if (chooseRole == locRole[q]) {
							  inRole = true;
							  break;
						  }
					  }	
					  if (chooseRole >= playerName.getRank() && inRole) {
						  System.out.println("You now have this role.");
						  playerName.takeRole(chooseRole, false);
						  valid = true;
					  }
				  } catch (Exception e) {
					  System.out.println("Not a valid choice.");
				  }
			  }
		  }			
	  } else {
		  System.out.println("You did not take a role.");
	  }	  
  }
  
  
  //function for work
  public static void working(Board boardloc, Player player_name, String current_location, HashMap<Location, Scene> hashboard, 
		  int scene_count, Player[] players,  BoardLayersListener board ) {
	  
	  int location_value = player_name.getLocation();
	  current_location = boardloc.locationName(location_value);
	  Location playerLoc = boardloc.getLocation(location_value);
	  Scene locScene = hashboard.get(playerLoc);
	  int shot = playerLoc.getShot();
	  boolean onCard = player_name.getCard();
	  location_value = player_name.getLocation();
	  current_location = boardloc.locationName(location_value);			
	  System.out.println("Would you like to act (a) or rehearse (r)?");
	  Scanner work_input = new Scanner(System.in);
	  String work_choice = work_input.nextLine();

	  if(work_choice.equalsIgnoreCase("a")) {
		  System.out.println("You choose to act!");
		  int die_num = roll();
		  System.out.println("You rolled a " + die_num + " plus " + player_name.getPractice() + " practice chips!");       
		  System.out.println("You needed to get equal to or above " + locScene.getBudget());
		  if (die_num + player_name.getPractice() >= locScene.getBudget()) {
			  System.out.println("Sucessful acting.");
			  playerLoc.updateShot();
			  if (onCard) {
				  player_name.increaseCredits(2);
			  } else {
				  player_name.increaseCredits(1);
				  player_name.increaseDollars(1);
			  }
		  } else {
			  System.out.println("Failed acting.");
			  if (!onCard) {
				  player_name.increaseDollars(1);
			  }
		  }
		  boolean sceneFin = checkScene(playerLoc);
		  if (sceneFin) {
			  System.out.println("Scene is wrapped.");
			  bonus(players, locScene, playerLoc);
			  scene_count = scene_count - 1;
			  player_name.resetRole();
			  //need to let the player move now
		  }		  
	  } else if (work_choice.equalsIgnoreCase("r")) {
		  System.out.println("You choose to Rehearse!");
		  System.out.println("You gained a Practice Chip!");
		  player_name.increasePractice();
		  System.out.println("This is " + player_name + " practice chip count: " + player_name.getPractice());		  
	  } else {
		  System.out.println("You didn't choose to act or rehearse, so we just will wait here");
	  }
	  
	  String player_real = player_name.getName();
	  String srank = Integer.toString(player_name.getRank());
	  String sdollars = Integer.toString(player_name.getDollars());
	  String scredits = Integer.toString(player_name.getcredits ());
	  String spractice = Integer.toString(player_name.getPractice());	  
	//  board.updateStats(player_real, srank, sdollars, scredits, spractice);
  }
   
  public static boolean checkScene(Location loc) {	  
	  int shot = loc.getShot();  
	  if (shot == 0) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  public static void bonus(Player[] players, Scene scene, Location loc) {	  
	  int budget = scene.getBudget();	  
	  ArrayList<Player> playerOn = new ArrayList<Player>();
	  ArrayList<Player> playerOff = new ArrayList<Player>();	  
	  for (int i = 0; i < players.length; i ++) {
		  if(players[i].getLocation() == loc.getIndex()) {
			  if(players[i].getCard()) {
				  playerOn.add(players[i]);
			  }else {
				  playerOff.add(players[i]);
			  }
		  }
	  }	  
	  if (!playerOn.isEmpty()) {			
		  if(!playerOff.isEmpty()) {				
			  for (Player p : playerOff) {
				  p.increaseDollars(p.getRoleRank());
			  }
		  }
		  Player[] playerMaxRole = new Player[playerOn.size()];		  
		  int maxRole = 0;
		  int index = 0;	  
		  for (int p = 0; p < playerOn.size(); p++) {
			  for (int y = 0; y < playerOn.size(); y++) {
				  if (playerOn.get(p).getRoleRank() > maxRole) {
					  index = p;
					  maxRole = playerOn.get(p).getRoleRank();
				  }					
				  playerMaxRole[p] = playerOn.get(index);
				  maxRole = 0;
			  }		  
		  }		  
		  ArrayList<Integer> rolls = new ArrayList<Integer>();	  
		  for (int w = 0; w < budget; w++) {
			  rolls.add(roll());
		  }		   
		  int sceneRoles = scene.sceneRoles().length;
		  int[] roleScene = new int[sceneRoles];		  
		  int max = 0; 			
		  for (int t = 0; t < rolls.size(); t++) {
			  for (int a : rolls) {
				  if (a > max) {
					  max = a;
				  }
			  }
			  roleScene[t%sceneRoles] = roleScene[t%sceneRoles] + max;
			  rolls.remove(max);
			  max = 0;
		  }		  
		  for (int u = 0; 0 < roleScene.length; u++) {
			  playerMaxRole[u].increaseDollars(roleScene[u]);
		  }
	  }	  
	  for (Player r : playerOn) {
		  r.resetPractice();
		  r.resetRole();
	  }
	  for (Player b: playerOff) {
		  b.resetPractice();
		  b.resetRole();
	  }
  }
   
  public static int roll(){
	  return (int)(Math.random()*6) + 1;
  }
  
}
