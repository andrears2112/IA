package TicTacToe;

import java.util.Random;

class TicTacToe {
    private char[] board;
    private char humanPlayer;
    private char botPlayer;

    public TicTacToe() {
        board = new char[25];
        for (int i = 0; i < 25; i++) {
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
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + board[0 + (i * 5)] + " | " + board[1 + (i * 5)] + " | " + board[2 + (i * 5)]+ " | " + board[3 + (i * 5)]+ " | " + board[4 + (i * 5)]);
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
        if (board[0] == player && board[1] == player && board[2] == player && board[3] == player && board[4] == player) {
            return true;
        }
        if (board[5] == player && board[6] == player && board[7] == player && board[8] == player && board[9] == player) {
            return true;
        }
        if (board[10] == player && board[11] == player && board[12] == player && board[13] == player && board[14] == player) {
            return true;
        }
        if (board[15] == player && board[16] == player && board[17] == player && board[18] == player && board[19] == player) {
            return true;
        }
        if (board[20] == player && board[21] == player && board[22] == player && board[23] == player && board[24] == player) {
            return true;
        }
        if (board[0] == player && board[5] == player && board[10] == player && board[15] == player && board[20] == player) {
            return true;
        }
        if (board[1] == player && board[6] == player && board[11] == player && board[16] == player && board[21] == player) {
            return true;
        }
        if (board[2] == player && board[7] == player && board[12] == player && board[17] == player && board[22] == player) {
            return true;
        }
        if (board[3] == player && board[8] == player && board[13] == player && board[18] == player && board[23] == player) {
            return true;
        }
        if (board[4] == player && board[9] == player && board[14] == player && board[19] == player && board[24] == player) {
            return true;
        }
        if (board[0] == player && board[6] == player && board[12] == player && board[18] == player && board[24] == player) {
            return true;
        }
        if (board[4] == player && board[8] == player && board[12] == player && board[16] == player && board[20] == player) {
            return true;
        }
        return false;
    }

    public boolean checkWinner() {
        if (isPlayerWin(humanPlayer)) {
            System.out.println("   ¡El jugador " + humanPlayer + " ganó!");
            return true;
        }
        if (isPlayerWin(botPlayer)) {
            System.out.println("   ¡El jugador " + botPlayer + " ganó!");
            return true;
        }
        if (isBoardFilled()) {
            System.out.println("   ¡Empate!");
            return true;
        }
        return false;
    }

    public void start() {
        ComputerPlayer bot = new ComputerPlayer(botPlayer);
        HumanPlayer human = new HumanPlayer(humanPlayer);
        while (true) {
            System.out.println("   Turno del jugador: " + humanPlayer);
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