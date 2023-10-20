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
            System.out.print("Ingresa el número de casilla (1-25): ");
            //int square = Integer.parseInt(System.console().readLine());
            int square = Leer.nextInt();
            System.out.println();
            if (state[square - 1] == '-') {
                return square - 1;
            }
        }
    }
}
