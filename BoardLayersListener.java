package Game;


import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class BoardLayersListener extends JFrame {

  // JLabels
  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;
  JLabel mRank;
  JLabel mDollars;
  JLabel mFame;
  JLabel mRehearse;

  //JButtons
  JButton bLocation0;
  JButton bLocation1;
  JButton bLocation2;
  JButton bLocation3;
  JButton bRoll;
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  JButton bUpgrade;
  JButton bYes;
  JButton bNo;

  // JLayered Pane
  JLayeredPane bPane;

  Player current_player;
  Board b_boardloc;
  HashMap<Location, Scene> boardPairs;
  // Constructor

  public BoardLayersListener() {

       // Set the title of the JFrame
       super("Deadwood");
       // Set the exit option for the JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);

       // Create the JLayeredPane to hold the display, cards, dice and buttons
       bPane = getLayeredPane();

       // Create the deadwood board
       boardlabel = new JLabel();
       ImageIcon icon =  new ImageIcon("board.jpg");
       boardlabel.setIcon(icon);
       boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

       // Add the board to the lowest layer
       bPane.add(boardlabel, new Integer(0));

       // Set the size of the GUI
       setSize(icon.getIconWidth()+200,icon.getIconHeight());

       // Add a scene card to this room
       cardlabel = new JLabel();
       ImageIcon cIcon =  new ImageIcon("cards/cards/01.png");
       cardlabel.setIcon(cIcon);
       cardlabel.setBounds(20,65,cIcon.getIconWidth()+2,cIcon.getIconHeight());
       cardlabel.setOpaque(true);

       // Add the card to the lower layer
       bPane.add(cardlabel, new Integer(1));




       // Add a dice to represent a player.
       // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
       playerlabel = new JLabel();
       ImageIcon pIcon = new ImageIcon("dice/dice/r2.png");
       playerlabel.setIcon(pIcon);
       //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
       playerlabel.setBounds(114,227,46,46);
       playerlabel.setVisible(false);
       bPane.add(playerlabel,new Integer(3));


  }
 
  public void playerStats(String player, String rank, String dollars, String credits, String rehearsal, int i){
	  i = i * 100;
	  ImageIcon icon =  new ImageIcon("board.jpg");
	  String real_player = player;
	  mLabel = new JLabel(real_player);
	  mRank = new JLabel("Rank: " + rank);
	  mDollars = new JLabel("Dollars: " +dollars);
	  mFame = new JLabel("Fame: " +credits);
	  mRehearse = new JLabel("Practice: " + rehearsal);

 
	  mLabel.setBounds(icon.getIconWidth()+20 + i,400,100, 20);
	  mRank.setBounds(icon.getIconWidth()+20 + i,420,100, 20);
	  mDollars.setBounds(icon.getIconWidth()+20 + i,440,100, 20);
	  mFame.setBounds(icon.getIconWidth()+20 + i,460,100, 20);
	  mRehearse.setBounds(icon.getIconWidth()+20 + i,480,100, 20);
	  bPane.add(mLabel,new Integer(2));
	  bPane.add(mRank,new Integer(2));
	  bPane.add(mDollars,new Integer(2));
	  bPane.add(mFame,new Integer(2));
	  bPane.add(mRehearse,new Integer(2));
 
 }
 
  public void playersTurn(Player player, Board boardloc, int x, HashMap<Location, Scene> hashboard) {
	  ImageIcon icon =  new ImageIcon("board.jpg");
	  
 	  mLabel = new JLabel("Select your action");
      mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
      bPane.add(mLabel,new Integer(2));
	  
	  System.out.println("We are in players turn");
	  int location = player.getLocation();
	  boardloc.locationName(location).equalsIgnoreCase("Trailer");
      // Create the Menu for action buttons
	  
      System.out.println("Current location is " + boardloc.locationName(location));
	  if(boardloc.locationName(location).equalsIgnoreCase("Trailer")) {
		  
	      bMove = new JButton("MOVE");
	      bMove.setBackground(Color.white);
	      bMove.setBounds(icon.getIconWidth()+10,30,100, 20);
	      bMove.addMouseListener(new boardMouseListener());
	      
	      // Place the action buttons in the top layer
	      bPane.add(bMove, new Integer(2));		  		 
	      
	      
	  }//at casting office
	  else if(boardloc.locationName(location).equalsIgnoreCase("Casting")) {
		  
	      bMove = new JButton("MOVE");
	      bMove.setBackground(Color.white);
	      bMove.setBounds(icon.getIconWidth()+10,30,100, 20);
	      bMove.addMouseListener(new boardMouseListener());
	      
		  bUpgrade = new JButton("UPGRADE");
		  bUpgrade.setBackground(Color.white);
		  bUpgrade.setBounds(icon.getIconWidth()+10, 60,100, 20);
		  bUpgrade.addMouseListener(new boardMouseListener());

	      
	      // Place the action buttons in the top layer
	      bPane.add(bMove, new Integer(2));
	      bPane.add(bUpgrade, new Integer(2));
		
		  
		  
	  }//on a set
	  else {
		  //doesnt have role
		  if(player.roleRank == -1) {
			  
			  
			  if(x == 0) {	
				  bPane.remove(mLabel);
			 	  mLabel = new JLabel("Take a Role?");
			      mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
			      bPane.add(mLabel,new Integer(2));
				  
				  
			      bYes = new JButton("Yes");
			      bYes.setBackground(Color.white);
			      bYes.setBounds(icon.getIconWidth()+10, 30,100, 20);
			      bYes.addMouseListener(new boardMouseListener());
			      
			      bPane.add(bYes, new Integer(2));	
			      
			      bNo = new JButton("No");
			      bNo.setBackground(Color.white);
			      bNo.setBounds(icon.getIconWidth()+10, 60,100, 20);
			      bNo.addMouseListener(new boardMouseListener());
			      
			      bPane.add(bNo, new Integer(2));	
			      
				  bPane.remove(mLabel);
			  }else {
		  
				  bMove = new JButton("MOVE");
				  bMove.setBackground(Color.white);
				  bMove.setBounds(icon.getIconWidth()+10,30,100, 20);
				  bMove.addMouseListener(new boardMouseListener());
		      
				  bRoll = new JButton("ROLL");
				  bRoll.setBackground(Color.white);
				  bRoll.setBounds(icon.getIconWidth()+10, 60,100, 20);
				  bRoll.addMouseListener(new boardMouseListener());

		      
		      // Place the action buttons in the top layer
				  bPane.add(bMove, new Integer(2));
				  bPane.add(bRoll, new Integer(2));		
			  }
		  }
		  else {
			  
			  bAct = new JButton("ACT");
			  bAct.setBackground(Color.white);
			  bAct.setBounds(icon.getIconWidth()+10,30,100, 20);
			  bAct.addMouseListener(new boardMouseListener());
		      
			  bRehearse = new JButton("REHEARSE");
			  bRehearse.setBackground(Color.white);
			  bRehearse.setBounds(icon.getIconWidth()+10, 60,100, 20);
			  bRehearse.addMouseListener(new boardMouseListener());
		      
		      // Place the action buttons in the top layer
		      bPane.add(bAct, new Integer(2));
		      bPane.add(bRehearse, new Integer(2));				  
		  }		  
	  }	 
	  bPane.remove(mLabel);
	  boardPairs = hashboard;
	  current_player = player;
	  b_boardloc = boardloc;
	  
  }
  
  
  public void moveChoices() {
      ImageIcon icon =  new ImageIcon("board.jpg"); 
 	  mLabel = new JLabel("Where to?");
      mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
      bPane.add(mLabel,new Integer(2));
	  //  System.out.println("we are in move choices");
      int location = current_player.getLocation();
      int moves[] = b_boardloc.moveCount(location);
      if(moves.length == 3) {         
          bLocation0 = new JButton(b_boardloc.locationName(moves[0]));
          bLocation0.setBackground(Color.white);
          bLocation0.setBounds(icon.getIconWidth()+10, 30,100, 20);
          bLocation0.addMouseListener(new boardMouseListener());
          
          bLocation1 = new JButton(b_boardloc.locationName(moves[1]));
          bLocation1.setBackground(Color.white);
          bLocation1.setBounds(icon.getIconWidth()+10, 60,100, 20);
          bLocation1.addMouseListener(new boardMouseListener());
          
          bLocation2 = new JButton(b_boardloc.locationName(moves[2]));
          bLocation2.setBackground(Color.white);
          bLocation2.setBounds(icon.getIconWidth()+10, 90,100, 20);
          bLocation2.addMouseListener(new boardMouseListener());
          
          bPane.add(bLocation0, new Integer(2));
	      bPane.add(bLocation1, new Integer(2));	
	      bPane.add(bLocation2, new Integer(2));	
          
	      
	  } else { //move length is 4
		   bLocation0 = new JButton(b_boardloc.locationName(moves[0]));
		   bLocation0.setBackground(Color.white);
		   bLocation0.setBounds(icon.getIconWidth()+10, 30,100, 20);
		   bLocation0.addMouseListener(new boardMouseListener());
	          
		   bLocation1 = new JButton(b_boardloc.locationName(moves[1]));
		   bLocation1.setBackground(Color.white);
		   bLocation1.setBounds(icon.getIconWidth()+10, 60,100, 20);
		   bLocation1.addMouseListener(new boardMouseListener());
	          
		   bLocation2 = new JButton(b_boardloc.locationName(moves[2]));
		   bLocation2.setBackground(Color.white);
		   bLocation2.setBounds(icon.getIconWidth()+10, 90,100, 20);
		   bLocation2.addMouseListener(new boardMouseListener());
	          
		   bLocation3 = new JButton(b_boardloc.locationName(moves[2]));
		   bLocation3.setBackground(Color.white);
		   bLocation3.setBounds(icon.getIconWidth()+10, 120,100, 20);
		   bLocation3.addMouseListener(new boardMouseListener());
		   
		   bPane.add(bLocation0, new Integer(2));
		   bPane.add(bLocation1, new Integer(2));	
		   bPane.add(bLocation2, new Integer(2));
		   bPane.add(bLocation3, new Integer(2));    
		   
	  }
      bPane.remove(mLabel);
  }
   
  // This class implements Mouse Events

  class boardMouseListener implements MouseListener{

      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {
          int location = current_player.getLocation();
          int moves[] = b_boardloc.moveCount(location);
         if (e.getSource()== bAct){
            playerlabel.setVisible(true);
            System.out.println("Acting is Selected\n");
         }
         else if (e.getSource()== bRehearse){
            System.out.println("Rehearse is Selected\n");
         }
         else if (e.getSource()== bMove){
        	bPane.remove(bMove);
        	moveChoices();
         //   System.out.println("Move is Selected\n");
         }else if ((e.getSource()== bLocation0) || (e.getSource()== bLocation1) || (e.getSource()== bLocation2) || (e.getSource()== bLocation3)) {
        
        	 if(moves.length == 3) {
        		 System.out.println("Removing 3");
        		 bPane.remove(bLocation0);
        		 bPane.remove(bLocation1);
        		 bPane.remove(bLocation2);
        	 }else {
        		 System.out.println("Removing 4");
        		 bPane.remove(bLocation0);
        		 bPane.remove(bLocation1);
        		 bPane.remove(bLocation2);
        		 bPane.remove(bLocation3);
        	 } 
        	 
        	 if(e.getSource()== bLocation0) {
        		 current_player.updateLocation(moves[0]);
        	 }else if(e.getSource()== bLocation1) {
        		 current_player.updateLocation(moves[1]);
        	 }else if (e.getSource()== bLocation2) {
        		 current_player.updateLocation(moves[2]);
        	 }else {
        		 current_player.updateLocation(moves[3]);
        	 }     	        	 
        	 playersTurn(current_player, b_boardloc, 0, boardPairs );
        	 System.out.println("Location is Selected\n");
        	 
         }
         else if(e.getSource() == bYes) {
        	 System.out.println("Roll is Selected\n");
        	 bPane.remove(bYes);
        	 
         }else if(e.getSource() == bNo) {
        	 System.out.println("No Roll is Selected\n");
        	 bPane.remove(bNo);
        	 return;
         }
      }
      
      public void mousePressed(MouseEvent e) {
      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }

   }
 
}
