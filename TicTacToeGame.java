package labs.lab5;

public class TicTacToeGame implements Game {
	private char[][] gameboard;
	private boolean x;
	private boolean first = false;
	private char firstc;
	private char secondc;
	
	public TicTacToeGame() {
		this.gameboard = new char[3][3];
		this.x = true;
	}
	
	public boolean isValidMove(String move) {
		if (move.length() != 3) {
			return false;
		}
		Integer row = Character.getNumericValue(move.charAt(0));
		Integer col = Character.getNumericValue(move.charAt(1));
		Character turn = move.charAt(2);
		if (turn != 'x' && turn != 'o') {
			return false;
		}
		if (row < 0 || row > 2) {
			return false;
		}
		
		if (col < 0 || col > 2) {
			return false;
		}
		
		if (first) {
		if (turn != firstc && this.x) {
			return false;
		}
		if (turn != secondc && !this.x) {
			return false;
		}
		}
		if (this.gameboard[row][col] == 'x' || this.gameboard[row][col] == 'o') {
			return false;
		}
		if (!first) {
			this.firstc = (turn == 'x') ? 'x' : 'o';
			this.secondc = (turn == 'x') ? 'o' : 'x';
		first = true;}
		return true;
	}
	
	public void executeMove(String move) {

			Integer row = Character.getNumericValue(move.charAt(0));
			Integer col = Character.getNumericValue(move.charAt(1));
			Character turn = move.charAt(2);
			this.gameboard[row][col] = turn;
			if (x) {
				x = false;
			}
			else {
				x = true;
			}
	}
	
	public boolean gameOver() {
        if (determineWinner() != 0 || boardfull()) {
        	return true;
        }
        return false;
    }
	
	public String displayBoard() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	display.append(this.gameboard[i][j] == '\0' ? "   " : (" " + this.gameboard[i][j] + " "));
                if (j < 2) display.append('|');
            }
            display.append(System.lineSeparator());
            if (i < 2){
            	display.append("-----------");
            	display.append(System.lineSeparator());
            }
        }
        return display.toString();
    }
	
	
	
	
	public boolean boardfull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j<3; j++) {
				if (this.gameboard[i][j] == '\0') {
					return false;
				}
			}
		}
		return true;
	}
	
	public int determineWinner() {
		int xRow;
		int oRow;
		for (int i = 0; i < 3; i++) {
			xRow = 0;
			oRow = 0;
			for (int j = 0; j<3; j++) {
				if (this.gameboard[i][j] == 'x') {
					xRow++;
				}
				else if (this.gameboard[i][j] == 'o') {
					oRow++;
				}
			}
			if (xRow == 3) {
				return firstc == 'x' ? 1 : 2;
			}
			if (oRow == 3) {
				return firstc == 'o' ? 1 : 2;
			}
			
		}
		
		for (int i = 0; i < 3; i++) {
			xRow = 0;
			oRow = 0;
			for (int j = 0; j<3; j++) {
				if (this.gameboard[j][i] == 'x') {
					xRow++;
				}
				else if (this.gameboard[j][i] == 'o') {
					oRow++;
				}
			}
			if (xRow == 3) {
				return firstc == 'x' ? 1 : 2;
			}
			if (oRow == 3) {
				return firstc == 'o' ? 1 : 2;
			}
			
		}
		
		Character diag = this.gameboard[1][1];
		if (diag == '\0') {
			return 0;
		}
		
		if (this.gameboard[0][0] == diag && this.gameboard[1][1] == diag && this.gameboard[2][2] == diag) {
			if (diag == firstc){
					return 1;}
			return 2;
		}
		
		if (this.gameboard[0][2] == diag && this.gameboard[1][1] == diag && this.gameboard[2][0] == diag) {
			if (diag == firstc){
					return 1;}
			return 2;
		}
		
		return 0;
	}
	
	
	
	
	}	
