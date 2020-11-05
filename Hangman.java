import java.util.Scanner;

public class Hangman {
	
	//method for scanner to make it easier to get inputs
	public static String scanner() {
		
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		
		
				
		return userInput;
	}
	
	//takes string and returns array with entries the characters of the string
	public static char[] selectedWord(String word) {
		
		char[] secretWord = new char[word.length()];
		
		for (int i = 0; i < word.length(); i++) {
			
			char c = word.charAt(i); 
			secretWord[i] = c;
		}
		
		return secretWord;
	}
	
	//returns first character from string
	public static char firstChar(String input) {
		
		char c = input.charAt(0);
		
		return c;
	}

	//method to get difficulty user chooses
	public static char difficulty() {
		
		//flag for while loop
		boolean flag = false;
		char difficulty = 0;
		String answer;
		
		//runs until valid input is entered
		while (flag == false) {
			System.out.println("Enter difficulty: Easy(e), Intermediate(i), Hard(h), or Quit(q)");
			answer = Hangman.scanner();
		
			//if user wants to quit, enter quit
			if (answer.equals("quit")) {
				System.exit(0);
			}
			
			//gets first character from input
			difficulty = Hangman.firstChar(answer);
			
			if (difficulty == 'e' || difficulty == 'i' || difficulty == 'h') {
				flag = true;
			} else {
				System.out.println("Invalid difficulty. Try again...");
			}
		}
		
		return difficulty;
	}
	
	//method to check to see if character guessed is in the word at given spaces
	public static char[] guessCheck(char guess, int[] spaces, char[] secretWord, char[] cover) {
		
		char[] cover1 = new char[cover.length];
		
		for (int y = 0; y < cover.length; y++) {
			cover1[y] = cover[y];
		}
		
		
		for (int j = 0; j < cover1.length; j++) {
			
			if (cover1[j]== '-') {
				for(int x=0; x<spaces.length; x++) {
					if((j==spaces[x]) &&(secretWord[j]==guess)) 
					{
					cover1[j]= guess;
					//System.out.println("1");
					}
					
				}
			}
			else {} 
		}
		
		
		/*
		for (int j = 0; j < spaces.length; j++) {
			
			if (guess == secretWord[spaces[j]]) {
				cover[spaces[j]] = guess;
			}
		}
		
		*/
		
		return cover1;
	}
	
	//method to solve word
	public static boolean solveWord(String word) {
		
		boolean retVar = false;
		
		//asks user to solve word
		System.out.print("Please solve the word: ");
		String guess = Hangman.scanner();
		
		guess=guess.toLowerCase();
		
		//sees if word is same as secret word
		if (guess.equals(word)) {
			
			retVar= true;
			System.out.println("You have guess the word! Congratulations!");
		}
		
		
		return retVar;
		
	}
	
	public static void main(String[] args) {
		//boolean flagT=true;
		//for loop to run at max 20 times
		for (int i = 20; i > 0; i--) {
			boolean gameOver=false;
			//variables for different difficulties
			char difficulty = Hangman.difficulty();
			int tries = 0, space = 0;
			
			//receives random word from RandomWord.java and enters it into an array
			String word = RandomWord.newWord();
			
			char[] secretWord = Hangman.selectedWord(word);
			
			//used to help us test code
			//System.out.println(secretWord);
			
			//array that displays in place of secret word
			char[] cover = new char[secretWord.length];
			
			for (int j = 0; j < secretWord.length; j++) {
				cover[j] = '-';
			}
			
			//prints secret word covered by cover array (testing)
			System.out.print("The word is: ");
			System.out.print(cover);
			System.out.println();
			
			//conditionals to set number of tries, number of spaces to check
			if (difficulty == 'e') {
				tries = 15;
				space = 4;
			} else if (difficulty == 'i') {
				tries = 12;
				space = 3;
			} else if (difficulty == 'h'){
				tries = 10;
				space = 2;
			}
			
			char oldGuess = '0';
			char guess;
			
			//System.out.println(tries);
			
			//for loop to keep track of tries
			while ((tries >= 0)&&(gameOver == false)) {
				
				System.out.println("Guesses remaining: " + tries);
				
				//if word wasn't guess in allotted number of triess
				if (tries == 0) {
					 System.out.println("You have failed to guess the word.");
					 break;
				}
				
				System.out.print("Please enter the letter you want to guess: ");
				
				String guessInput = Hangman.scanner();
				
				//if user wants to solve the word
				if(guessInput.equals("solve")) {
					gameOver=Hangman.solveWord(word);
						if (gameOver==false) {
							tries=tries -1;
						}
				}else {
					
					guess = guessInput.charAt(0);
				
				
					String[] spaces;
					int[] spaceToCheck = new int[space];
				
					//while loop to determine if number of desired spaces to check is valid
					boolean flag = false;
					boolean n = true;
				
					while (flag == false) {
					
						//n gets reset for each iteration
						n = true;
					
						System.out.println("Please enter the spaces you want to check (separated by spaces): ");
						String spacesGuess = Hangman.scanner();
				
						//splitting input into array of spaces user wants to check
						String delimeter = " ";
					
						spaces = spacesGuess.split(delimeter);
					
						for (int k = 0; k < spaces.length; k++) {
							String c = spaces[k];
						
							//looks to see if string is parsable and is therefore a number
							try {
								int num = Integer.parseInt(c);
							} catch (NumberFormatException e) {
								n = false; //set if entry is not parsable and is not a number
							}
						}
						
						//checks to see if number of spaces entered equals the number allowed and if they are all parsable
						if (spaces.length != space || n == false) {
							System.out.println("Your input is not valid. Try agan.");
						} else {
							//parses the entries into another array
							for (int r = 0; r < spaces.length; r++) {
								spaceToCheck[r] = Integer.parseInt(spaces[r]);
							}
							
							//exit while loop
							flag = true;
						}
				
					}
					
					//checks guess 
					char [] newCover = Hangman.guessCheck(guess, spaceToCheck, secretWord, cover);
				
				
					//prints out word with correct guesses 
			//		for(int m = 0; m < newCover.length; m++) {
			//			System.out.print(newCover[m]);
			//		}
			//		
			//		System.out.println("");
					
// the code i wrote, currently testing					//prints out word with correct guesses 
	                int counter3 = 0;
	                for (int z = 0; z < newCover.length; z++) {
	                    if (cover[z]!=newCover[z]) {
	                        counter3 =+ 1;
	                }
	                }if(counter3 == 0) {
	                System.out.println("Your letter was not found in the spaces you provided.");
	                System.out.print("The updated word is: ");
	                for(int m = 0; m < newCover.length; m++) {
	                    System.out.print(newCover[m]);
	                }
	                }else if (counter3 != 0) {
	                    System.out.println("Your guess is in the word!");
	                    System.out.print("The updated word is: ");
	                    for(int m = 0; m < newCover.length; m++) {
	                        System.out.print(newCover[m]);
	                }
	                }
	                System.out.println("");
	                if (tries == 0){
	                    System.out.println("Guesses remaining: " + tries);
	                    System.out.println("you have failed to guess the word.");
	                } 
					
					
					
				
					//variable for tries
					int counter1 = 0;
					//variable for gameOver
					int counter2 = 0;
				
					//checks to see if a try should be used or not
					for (int z = 0; z < newCover.length; z++) {
						if (cover[z]!=newCover[z]) {
							counter1 =+ 1;
						}
						//checks to see if the recently returned word equals the secretword
						if (secretWord[z]!=newCover[z]) {
							counter2=+ 1;
						}
					}
					// if counter does not equal 0 then no correct guess was made
					if (counter1 == 0) {
						
						//if guess has been guessed directly before this turn
						if(guess != oldGuess) {
							tries = tries - 1;
						}
					}
					if (counter2 == 0) {
						gameOver=true;
						System.out.println("Congrats you have guessed the word!");
					}
					//creates the cover for the next iteration
					for (int y = 0; y < cover.length; y++) {
						cover[y] = newCover[y];
					}
					 
					oldGuess = guess;
					 
				}
					
			}
			//asks player if they want to play again
			System.out.println("would you like to play again? Yes(y) or No(n)");
			String continueVar1=Hangman.scanner();
			continueVar1=continueVar1.toLowerCase();
			char continueVar=continueVar1.charAt(0);
			
			//exits if no
			if (continueVar=='n') {
				i=-1;
			}


		}
		
	}

}