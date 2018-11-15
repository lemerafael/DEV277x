package battleship;

import java.util.Random;
import java.util.Scanner;

public class BattleshipProject {
	Random computerRandom;
	private int board[][];
	private int computerShips;
	private int userShips;
	
	public enum location {
		EMPTY, USER, COMPUTER, USER_LOSS, COMPUTER_LOSS, USER_MISS, COMPUTER_MISS, BOTH_MISS;
	}
	
	public BattleshipProject() {
		computerRandom = new Random();
		board = new int[10][10];
		computerShips = 0;
		userShips = 0;
	}

	public void startGame (Scanner input) {
		System.out.println("Deploy your ships:");
		while (userShips < 5) {
			addNewShip(input);
		}
		deployComputerShips();
		printBattleship();
		System.out.println("-----------------------");
		while (computerShips > 0 && userShips > 0) {
			runRound(input);
		}
		System.out.println("Game Over");
	}
	
	private void addNewShip (Scanner input) {
		if (userShips < 5) {
			int nextShip = userShips+1;
			System.out.print("Enter X coordinate for your "+nextShip+". ship: ");
			int x = input.nextInt();
			System.out.print("Enter Y coordinate for your "+nextShip+". ship: ");
			int y = input.nextInt();
			if (isLocationEmpty (x, y)) {
				board [x][y] = location.USER.ordinal();
				userShips++;
			}
		}
	}
	
	private void printBattleship () {
		System.out.print("   ");
		for (int i=0; i < board.length; i++) {
			System.out.print(i);
		}
		for (int i=0; i < board.length; i++) {
			System.out.print("\n"+i+" |");
			for (int j = 0; j < board[i].length; j++) {
				location loc = location.values()[board[i][j]];
				switch (loc) {
				case USER:
					System.out.print("@");
					break;
					
				case USER_MISS:
				case BOTH_MISS:
					System.out.print("-");
					break;
					
				case USER_LOSS:
					System.out.print("X");
					break;
				
				case COMPUTER_LOSS:
					System.out.print("!");
					break;
					
				default:
					System.out.print(" ");
					break;
				}
			}
			System.out.print("| "+i);
		}
		System.out.print("\n"+"   ");
		for (int i=0; i < board.length; i++) {
			System.out.print(i);
		}
		System.out.println();
	}
	
	private void printShipCounts () {
		System.out.println("Your ships: "+userShips+" Computer ships: "+computerShips);
	}
	
	private void deployComputerShips () {
		System.out.println("Computer is deploying ships");
		while (computerShips < 5) {
			int x = computerRandom.nextInt(10);
			int y = computerRandom.nextInt(10);
			if (isLocationEmpty(x, y)) {
				board [x][y] = location.COMPUTER.ordinal();
	        	computerShips++;
	        	System.out.println(computerShips+" ship DEPLOYED");
			}
		}
	}
	
	private void userGuess (Scanner input) {
		System.out.println("YOUR TURN");
		System.out.print("Enter X coordinate: ");
        int x = input.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = input.nextInt();
        if (isValidUserGuess(x, y)) {
        	location loc = location.values()[board[y][x]];
        	switch (loc) {
			case COMPUTER:
				System.out.println("Boom! You sunk the ship!");
				board [x][y] = location.COMPUTER_LOSS.ordinal();
				computerShips--;
				break;
				
			case USER:
				System.out.println("Oh no, you sunk your own ship :(");
				board [x][y] = location.USER_LOSS.ordinal();
				userShips--;
				break;
				
			case EMPTY:
				System.out.println("Sorry, you missed");
				board [x][y] = location.USER_MISS.ordinal();
				break;
				
			case COMPUTER_MISS:
				System.out.println("Sorry, you missed");
				board [x][y] = location.BOTH_MISS.ordinal();
				break;

			default:
				break;
			}
        } else {
        	System.out.println("Invalid guess. Guess again:");
        	userGuess(input);
        }
	}
	
	private void computerGuess () {
		System.out.println("COMPUTER's TURN");
		boolean guess = true;
		while (guess) {
			int x = computerRandom.nextInt(10);
			int y = computerRandom.nextInt(10);
	        if (isValidComputerGuess(x, y)) {
	        	location loc = location.values()[board[y][x]];
	        	switch (loc) {
				case COMPUTER:
					System.out.println("The Computer sunk one of its own ships");
					board [x][y] = location.COMPUTER_LOSS.ordinal();
					computerShips--;
					break;
					
				case USER:
					System.out.println("The Computer sunk one of your ships!");
					board [x][y] = location.USER_LOSS.ordinal();
					userShips--;
					break;
					
				case EMPTY:
					System.out.println("Computer missed");
					board [x][y] = location.COMPUTER_MISS.ordinal();
					break;
					
				case USER_MISS:
					System.out.println("Computer missed");
					board [x][y] = location.BOTH_MISS.ordinal();
					break;
	
				default:
					break;
				}
	        	guess = false;
	        }
		}
	}

	private void runRound (Scanner input) {
		userGuess(input);
		computerGuess();
		printBattleship();
		printShipCounts();
		System.out.println("-----------------------");
	}

	private boolean isLocationValid (int x, int y) {
		if (x >= 0 && x <=9 && y >= 0 && y <= 9) {
			return true;
		}
		return false;
	}
	
	private boolean isLocationEmpty (int x, int y) {
		if (isLocationValid(x, y) && board[y][x] == location.EMPTY.ordinal()) {
			return true;
		}
		return false;
	}
	
	private boolean isValidUserGuess (int x, int y) {
		if (isLocationValid(x, y) && board[y][x] != location.USER_MISS.ordinal() && board[y][x] != location.BOTH_MISS.ordinal()
				&& board[y][x] != location.USER_LOSS.ordinal() && board[y][x] != location.USER_LOSS.ordinal()) {
			return true;
		}
		return false;
	}
	
	private boolean isValidComputerGuess (int x, int y) {
		if (isLocationValid(x, y) && board[y][x] != location.COMPUTER_MISS.ordinal() && board[y][x] != location.BOTH_MISS.ordinal()
				&& board[y][x] != location.COMPUTER_LOSS.ordinal() && board[y][x] != location.USER_LOSS.ordinal()) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		BattleshipProject proj = new BattleshipProject();
		Scanner input = new Scanner(System.in);
		
		proj.startGame(input);		
	}
}
