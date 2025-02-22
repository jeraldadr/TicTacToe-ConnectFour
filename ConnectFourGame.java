package labs.lab5;

public class ConnectFourGame implements Game {
	private char[][] gameboard;
	private boolean y;
	private boolean first = false;
	private char firstc;
	private char secondc;
	private char lastmove;
	
	public ConnectFourGame() {
		this.gameboard = new char[6][7];
		this.y = true;
	}
	
	public boolean isValidMove(String move) {
		if (move.length() != 2) {
			return false;
		}
		Integer col = Character.getNumericValue(move.charAt(0));
		if (col < 0 || col > 5) {
			return false;
		}
		Integer row = whichRow(col);
		Character turn = move.charAt(1);
		
		if (turn != 'r' && turn != 'y') {
			return false;
		}
		if (first) {
		if (turn != firstc && this.y) {
			return false;
		}
		if (turn != secondc && !this.y) {
			return false;
		}
		}
		if (this.gameboard[row][col] == 'r' || this.gameboard[row][col] == 'y') {
			return false;
		}
		if (!first) {
			this.firstc = (turn == 'r') ? 'r' : 'y';
			this.secondc = (turn == 'r') ? 'y' : 'r';
		first = true;}
		if (this.y) {
			this.y = false;
		}
		else {
			this.y = true;
		}
		return true;
	}
	
	public void executeMove(String move) {
			Integer col = Character.getNumericValue(move.charAt(0));
			Integer row = whichRow(col);
			Character turn = move.charAt(1);
			this.gameboard[row][col] = turn;
		
	}
	
	public boolean gameOver() {
		 if (determineWinner() != 0 || boardfull()) {
	        	return true;
	        }
	        return false;
    }
	
	public String displayBoard() {
		StringBuilder display = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
            	display.append(this.gameboard[i][j] == '\0' ? "   " : (" " + this.gameboard[i][j] + " "));
                if (j < 6) display.append('|');
            }
            	display.append(System.lineSeparator());
            	if (i < 5) {
            	display.append("---------------------------");
            	display.append(System.lineSeparator());}
            }
   
        return display.toString();
       
    }
	
	
	public int whichRow(Integer col){
		Integer row = -1;
		for (int i = 5; i >= 0; i--) {
			if (this.gameboard[i][col] == '\0') {
				return i;
			}
		}
		return row;
	}
	
	
	public boolean boardfull() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j<7; j++) {
				if (this.gameboard[i][j] == '\0') {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public int determineWinner() {
		int rRow;
		int yRow;
		for (int i = 0; i < 6; i++) {
			rRow = 0;
			yRow = 0;
			for (int j = 0; j<7; j++) {
				if (this.gameboard[i][j] == 'r') {
					rRow++;
					yRow = 0;
				}
				else if (this.gameboard[i][j] == 'y') {
					yRow++;
					rRow = 0;
				}
				if (rRow == 4) {
					return firstc == 'r' ? 1 : 2;
				}
				if (yRow == 4) {
					return firstc == 'y' ? 1 : 2;
				}
			}
		}
		
		for (int i = 0; i < 7; i++) {
			rRow = 0;
			yRow = 0;
			for (int j = 0; j<6; j++) {
				if (this.gameboard[j][i] == 'y') {
					yRow++;
					rRow = 0;
				}
				else if (this.gameboard[j][i] == 'r') {
					rRow++;
					yRow = 0;
				}
				if (yRow == 4) {
					return firstc == 'r' ? 1 : 2;
				}
				if (rRow == 4) {
					return firstc == 'y' ? 1 : 2;
				}
			}
		}
		
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.gameboard[i][j] != '\0' && this.gameboard[i][j] == this.gameboard[i-1][j+1] && this.gameboard[i-1][j+1] == this.gameboard[i-2][j+2] && this.gameboard[i-2][j+2] == this.gameboard[i-3][j+3]) {
                    return this.gameboard[i][j] == this.firstc ? 1 : 2;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.gameboard[i][j] != '\0' && this.gameboard[i][j] == this.gameboard[i+1][j+1] && this.gameboard[i+1][j+1] == this.gameboard[i+2][j+2] && this.gameboard[i+2][j+2] == this.gameboard[i+3][j+3]) {
                    return this.gameboard[i][j] == this.firstc ? 1 : 2;
                }
            }
        }
		
		
		
		return 0;
	}
	
	
	}	
