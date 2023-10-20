package TicTacToe;

import java.util.Random;

class TicTacToe {
    private char[] board;
    private char humanPlayer;
    private char botPlayer;

    public TicTacToe() {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = '-';
        }
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            humanPlayer = 'X';
            botPlayer = 'O';
        } else {
            humanPlayer = 'O';
            botPlayer = 'X';
        }
    }

    public void showBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println("  " + board[0 + (i * 3)] + " | " + board[1 + (i * 3)] + " | " + board[2 + (i * 3)]);
            System.out.println();
        }
    }

    public boolean isBoardFilled() {
        for (char c : board) {
            if (c == '-') {
                return false;
            }
        }
        return true;
    }

    public boolean isPlayerWin(char player) {
        if (board[0] == player && board[1] == player && board[2] == player) {
            return true;
        }
        if (board[3] == player && board[4] == player && board[5] == player) {
            return true;
        }
        if (board[6] == player && board[7] == player && board[8] == player) {
            return true;
        }
        if (board[0] == player && board[3] == player && board[6] == player) {
            return true;
        }
        if (board[1] == player && board[4] == player && board[7] == player) {
            return true;
        }
        if (board[2] == player && board[5] == player && board[8] == player) {
            return true;
        }
        if (board[0] == player && board[4] == player && board[8] == player) {
            return true;
        }
        if (board[2] == player && board[4] == player && board[6] == player) {
            return true;
        }
        return false;
    }

    public boolean checkWinner() {
        if (isPlayerWin(humanPlayer)) {
            System.out.println("   Player " + humanPlayer + " wins the game!");
            return true;
        }
        if (isPlayerWin(botPlayer)) {
            System.out.println("   Player " + botPlayer + " wins the game!");
            return true;
        }
        if (isBoardFilled()) {
            System.out.println("   Match Draw!");
            return true;
        }
        return false;
    }

    public void start() {
        ComputerPlayer bot = new ComputerPlayer(botPlayer);
        HumanPlayer human = new HumanPlayer(humanPlayer);
        while (true) {
            System.out.println("   Player " + humanPlayer + " turn");
            showBoard();
            int square = human.humanMove(board);
            board[square] = humanPlayer;
            if (checkWinner()) {
                break;
            }
            square = bot.machineMove(board);
            board[square] = botPlayer;
            if (checkWinner()) {
                break;
            }
        }
        showBoard();
    }
}