package TicTacToe;

import java.util.*;

class HumanPlayer {
	private Scanner Leer = new Scanner(System.in);
    private char letter;

    public HumanPlayer(char letter) {
        this.letter = letter;
    }

    public int humanMove(char[] state) {
        while (true) {
            System.out.print("Enter the square to fix spot(1-9): ");
            //int square = Integer.parseInt(System.console().readLine());
            int square = Leer.nextInt();
            System.out.println();
            if (state[square - 1] == '-') {
                return square - 1;
            }
        }
    }
}
