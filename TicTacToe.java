// TicTacToe.java

import java.util.Arrays;

public class TicTacToe {

    // size
    private static final int BOARD_SIZE = 3;
    // status
    private char[][] board;
    // 
    private char currentPlayer;
    // ending?
    private boolean isGameOver;
    // result
    private char winner;
    // 紀錄已下棋子的數量
    private int movesCount;

    //initialize
    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        currentPlayer = 'O';
        isGameOver = false;
        winner = 'N';
        movesCount = 0;
    }

    public boolean set(int row, int col) {
        // 檢查遊戲是否結束
        if (isGameOver) {
            return false;
        }

        // 檢查位置
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }

        if (board[row][col] != ' ') {
            return false;
        }

        // 下棋
        board[row][col] = currentPlayer;
        movesCount++;

        // 判斷遊戲狀態
        evaluate();

        // 切換玩家 (如果遊戲尚未結束)
        if (!isGameOver) {
            currentPlayer = (currentPlayer == 'O') ? 'X' : 'O';
        }

        return true;
    }

    // 判斷遊戲狀態
    public void evaluate() {
    
        if (checkWin(currentPlayer)) {
            isGameOver = true;
            winner = currentPlayer;
            return;
        }

    
        if (movesCount == BOARD_SIZE * BOARD_SIZE) {
            isGameOver = true;
            winner = 'T'; // Tie
            return;
        }
        

    }

    private boolean checkWin(char player) {
    
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

    
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
    
    // --- 供測試使用的 Getter 方法 ---

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public char getWinner() {
        return winner;
    }

    public char getCell(int row, int col) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            return board[row][col];
        }
        return ' ';
    }
}