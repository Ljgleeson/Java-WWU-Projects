package Game;


import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;


public class Board{

	
  //needs location array and scene array

  // KEY
  // ------------------------------------
  // trailer  = 0
  // hotel = 1
  // church = 2
  // bank = 3
  // ranch = 4
  // secret hideout = 5
  // casting office = 6
  // train station = 7
  // jail = 8
  // general store = 9
  // saloon = 10
  // main street = 11
  //------------------------------------
	
	Location[] locationArr;
	Scene[] sceneArr;
	HashMap<Location, Scene> boardPairs;
	
	// Location name = new Location(index, name, int[moves], int[roles], shot)
	int[] moving0 = new int[]{1, 10 , 11};
	Location trailer = new Location(0, "Trailer", moving0);


	int[] moving1 = new int[] {0,2,3};
	int[] rolling1 = new int[] {1, 1, 2, 3};
	Location hotel = new Location(1, "Hotel", moving1, rolling1 , 3);


	int [] moving2 = new int[] {1,3,5};
	int [] rolling2 = new int[] {1,2};
	Location church = new Location(2, "Church", moving2, rolling2, 2);


	int [] moving3 = new int[] {1,2,4,10};
	int [] rolling3 = new int[] {2,3};
	Location bank = new Location(3, "Bank", moving3, rolling3, 1);


	int [] moving4 = new int[] {3,5,6,9};
	int[] rolling4 = new int[] {1,2,3};
	Location ranch = new Location(4, "Ranch", moving4, rolling4, 2);


	int [] moving5 = new int[] {2,4,6};
	int [] rolling5 = new int[] {1,2,3,4};
	Location hideout = new Location(5, "Secret Hideout", moving5, rolling5, 3);


	int [] moving6 = new int[] {4,5,7};
	Location casting = new Location(6, "Casting", moving6);


	int [] moving7 = new int[] {6,8,9};
	int [] rolling7 = new int[] {1,1,2,4};
	Location train = new Location(7, "Train Station", moving7, rolling7, 3);


	int [] moving8 = new int[] {7,9,11};
	int [] rolling8 = new int[] {2,3};
	Location jail = new Location(8, "Jail", moving8, rolling8, 1);


	int [] moving9 = new int[] {4,7,8,10};
	int [] rolling9 = new int[] {1,3};
	Location store = new Location(9, "General Store", moving9, rolling9, 2);


	int [] moving10 = new int[] {0,3,9,11};
	int [] rolling10 = new int[] {1,2};
	Location saloon = new Location(10, "Saloon", moving10, rolling10, 2);


	int [] moving11 = new int[] {0,8,10};
	int [] rolling11 = new int[] {1,2,2,4};
	Location mainSt = new Location(11, "MainSt", moving11, rolling11, 3);

	int[] rolling12 = new int[] {0};
	Scene scene40 = new Scene(0, "none", 0, rolling12);




  //build board, sets locations info and pairs with scenes -> hashmap?
  //need to return location/scene pairs

	public HashMap<Location, Scene> buildBoard() {
		
		//scene name = new scene(index, name, budget, int[roles])
			
			
			int[] roles = new int[] {2,3,4};
		    Scene scene0 = new Scene(7, "Evil Wears a Hat", 4, roles);
		    
		    roles = new int[]{2,4,6};
		    Scene scene1 = new Scene(14, "Square Deal City", 6, roles);
		    
		    roles = new int[] {1,2,5};
		    Scene scene2 = new Scene(20, "Law and the Old West", 3, roles);
		    
		    roles = new int[] {4,6};
		    Scene scene3 = new Scene(31, "Davy Crocket: A Drunkard's Tale", 4, roles);
		    
		    roles = new int[] {5,6};
		    Scene scene4 = new Scene(22, "Life and Times of John Skywater", 5, roles);
		    
		    roles = new int[] {2,4,5};
		    Scene scene5 = new Scene(34, "The Way the West was Run", 4, roles);
		    
		    roles = new int[] {3,4,6};
		    Scene scene6 = new Scene(32, "My Years on the Prairie", 5, roles);
		    
		    roles = new int[] {1,3,5};
		    Scene scene7 = new Scene(24, "Down in the Valley", 3, roles);
		    
		    roles = new int[] {2,3,5};
		    Scene scene8 = new Scene(12, "Buffalo Bill: The Lost Years", 4, roles);
		    
		    roles = new int[] {1,2,4};
		    Scene scene9 = new Scene(14, "Ol' Shooter and Little Doll", 4, roles);
		    
		    roles = new int[] {1,4,5};
		    Scene scene10 = new Scene(19, "The Robbers of Trains", 4, roles);
		    
		    roles = new int[] {2,4,5};
		    Scene scene11 = new Scene(8, "Jesse James: Man of Action", 5, roles);
		    
		    roles = new int[] {6};
		    Scene scene12 = new Scene(12, "Beyond the Pail: Life without Lactose", 2 , roles);
		    
		    roles = new int[] {2,3,4};
		    Scene scene13 = new Scene(6, "Disaster at Flying J", 5, roles);
		    
		    roles = new int[] {3,6};
		    Scene scene14 = new Scene(16, "A Man Called “Cow”", 3, roles);
		    
		    roles = new int[] {1,3,4};
		    Scene scene15 = new Scene(23, "Shakespeare in Lubbock", 3, roles);
		    
		    roles = new int[] {3,4};
		    Scene scene16 = new Scene(2, "Taffy Commercial", 2, roles);
		    
		    roles = new int[] {4,6};
		    Scene scene17 = new Scene(30, "Go West, You!", 3, roles);
		    
		    roles = new int[] {2,4};
		    Scene scene18 = new Scene(3, "Gum Commercial", 2, roles);
		    
		    roles = new int[] {3,5,6};
		    Scene scene19 = new Scene(15, "The Life and Times of John Skywater", 5, roles);
		    
		    roles = new int[] {4,5,6};
		    Scene scene20 = new Scene(25, "Gun! The Musical", 6, roles);
		    
		    roles = new int[] {1,2,5};
		    Scene scene21 = new Scene(21, "One False Step for Mankind", 6, roles);
		    
		    roles = new int[] {2,4,5};
		    Scene scene22 = new Scene(16, "Humor at the Expense of Others", 5, roles);
		    
		    roles = new int[] {1,3,5};
		    Scene scene23 = new Scene(15, "Thirteen the Hard Way", 5, roles);
		    
		    roles = new int[] {5,6};
		    Scene scene24 = new Scene(12, "The Search for Maggie White", 6, roles);
		    
		    roles = new int[] {2,3,4};
		    Scene scene25 = new Scene(2, "How They Get Milk", 4, roles);
		    
		    roles = new int[] {3,5};
		    Scene scene26 = new Scene(1, "Picante Sauce Commercial", 2, roles);
		    
		    roles = new int[] {2,3,5};
		    Scene scene27 = new Scene(27, "My Years on the Prairie", 5, roles);
		    
		    roles = new int[] {1,4,6};
		    Scene scene28 = new Scene(14, "Jesse James: Man of Action", 5, roles);
		    
		    roles = new int[] {2,3,4};
		    Scene scene29 = new Scene(12, "Davy Crockett: A Drunkard’s Tale", 4, roles);
		    
		    roles = new int[] {5,6};
		    Scene scene30 = new Scene(25, "Czechs in the Sonora", 4, roles);
		    
		    roles = new int[] {1,2,5};
		    Scene scene31 = new Scene(13, "J. Robert Lucky, Man of Substance", 4, roles);
		    
		    roles = new int[] {1,3,5};
		    Scene scene32 = new Scene(19, "Swing 'em Wide", 6, roles);
		    
		    roles = new int[] {2,4,5};
		    Scene scene33 = new Scene(17, "Thirteen the Hard Way", 5, roles);

		    roles = new int[] {3,5,6};
		    Scene scene34 = new Scene(35, "Swing 'em Wide", 6, roles);
		    
		    roles = new int[] {4,5,6};
		    Scene scene35 = new Scene(8, "How They Get Milk", 4, roles);
		    
		    roles = new int[] {2,4,5};
		    Scene scene36 = new Scene(5, "Trials of the First Pioneers", 4, roles);
		    
		    roles = new int[] {2,6};
		    Scene scene37 = new Scene(19, "Breakin’ in Trick Ponies", 3, roles);
		    
		    roles = new int[] {3,4,5};
		    Scene scene38 = new Scene(9, "How the Grinch Stole Texas", 5, roles);
		    
		    roles = new int[] {2,4,6};
		    Scene scene39 = new Scene(40, "Custer’s Other Stands", 5, roles);
		   
		
		Location[] locationArr = new Location[] { hotel, church, bank, ranch, hideout, train, jail, store, saloon, mainSt};
		Scene[] sceneArr = new Scene[] {scene0,scene1,scene2,scene3,scene4,scene5,scene6,scene7,scene8,scene9,scene10,scene11,scene12,scene13,scene14,scene15,scene16,scene17,scene18,scene19,scene20,scene21,scene22,scene23,scene24,scene25,scene26,scene27,scene28, scene29,scene30,scene31,scene32,scene33,scene34,scene35,scene36,scene37,scene38,scene39};

		
		this.locationArr = locationArr;
		this.sceneArr = sceneArr;
		
		pairSceneLoc(locationArr, sceneArr, trailer, casting, scene40);
		
		return this.boardPairs;
		
	}
	
	public void pairSceneLoc (Location[] locationArr, Scene[] sceneArr, Location trailer, Location casting, Scene scene40) {
		
		HashMap<Location, Scene> boardPairs = new HashMap<Location, Scene>();
		
		for (int i = 0; i < locationArr.length; i++) {
			
			boolean flag = false;
			
			while(!flag) {
				int card = (int)(Math.random()*40);
				//System.out.println(card);
				
				if (sceneArr[card].getUse()) {
					boardPairs.put(locationArr[i], sceneArr[card]);
					sceneArr[card].updateUse();
					flag = true;
				}
			}
			
		}
		
		boardPairs.put(trailer, scene40);
		boardPairs.put(casting, scene40);
		
		this.boardPairs = boardPairs;
	}
	
	//clears hashmap
	//pairs new scenes with locations
	public HashMap<Location, Scene> resetBoard() {
		this.boardPairs.clear();
		
		pairSceneLoc(this.locationArr, this.sceneArr, this.trailer, this.casting, this.scene40);
		
		return this.boardPairs;
	}
	
	public void printBoardPairs() {
		
		HashMap<Location, Scene> pairs = this.boardPairs;
		
		for (Location i : pairs.keySet()) {
			System.out.println("Location: " + i.getName() + "  Scene: " + pairs.get(i).getName() );
			System.out.println();
		}
	}
	
	public Location getLocation(int index) {
		
		if (index == 0) {
			return trailer;
		} else if (index == 1) {
			return hotel;
		}else if (index == 2) {
			return church;
		}else if (index == 3) {
			return bank;
		}else if (index == 4) {
			return ranch;
		}else if (index == 5) {
			return hideout;
		}else if (index == 6) {
			return casting;
		}else if (index == 7) {
			return train;
		}else if (index == 8) {
			return jail;
		} else if (index == 9) {
			return store;
		}else if (index == 10) {
			return saloon;
		}else {
			return mainSt;
		}
		
		
	}
	
  public void printName(int location){

		if(location == 0) {
			System.out.print(trailer.getName());
		}else if (location == 1) {
			System.out.print(hotel.getName());
		}else if (location == 2) {
			System.out.print(church.getName());
		}else if (location == 3) {
			System.out.print(bank.getName());
		}else if (location == 4) {
			System.out.print(ranch.getName());
		}else if (location == 5) {
			System.out.print(hideout.getName());
		}else if (location == 6) {
			System.out.print(casting.getName());
		}else if (location == 7) {
			System.out.print(train.getName());
		}else if (location == 8) {
			System.out.print(jail.getName());
		}else if (location == 9) {
			System.out.print(store.getName());
		}else if (location == 10) {
			System.out.print(saloon.getName());
		}else if (location == 11) {
			System.out.print(mainSt.getName());
			
		}else {
			System.out.println("Not an index value");
		}
	
  }

  public int[] moveCount(int location) {
	  
	  if(location == 0) {
		  return trailer.getMoves();
	  }else if (location == 1) {
		  return hotel.getMoves();
	  }else if (location == 2) {
		  return church.getMoves();
	  }else if (location == 3) {
		  return bank.getMoves();
	  }else if (location == 4) {
		  return ranch.getMoves();
	  }else if (location == 5) {
		  return hideout.getMoves();
	  }else if (location == 6) {
		  return casting.getMoves();
	  }else if (location == 7) {
		  return train.getMoves();
	  }else if (location == 8) {
		  return jail.getMoves();
	  }else if (location == 9) {
		  return store.getMoves();
	  }else if (location == 10) {
		  return saloon.getMoves();
	  }else if (location == 11) {
		  return mainSt.getMoves();
	  }else {
		  return trailer.getMoves();
	  }
}

  public String locationName(int location) {
	  
	  if(location == 0) {
		  return (trailer.getName());
	  }else if (location == 1) {
		  return (hotel.getName());
	  }else if (location == 2) {
		  return (church.getName());
	  }else if (location == 3) {
		  return (bank.getName());
	  }else if (location == 4) {
		  return (ranch.getName());
	  }else if (location == 5) {
		  return (hideout.getName());
	  }else if (location == 6) {
		  return (casting.getName());
	  }else if (location == 7) {
		  return (train.getName());
	  }else if (location == 8) {
		  return (jail.getName());
	  }else if (location == 9) {
		  return (store.getName());
	  }else if (location == 10) {
		  return (saloon.getName());
	  }else if (location == 11) {
		  return (mainSt.getName());
	  }else {
		  return ("Not an index value");
	  }
}



}
