/*
 	INTEGRANTES:
 		-Rios Sicairos Andrea
 		-Villegas Solis Jose Manuel
*/

package TicTacToe;

import java.util.Scanner;

public class MainJuego {
	

	public static void main(String[] args) {
		Scanner Leer = new Scanner(System.in);
		int jugadorInicia=0;
		
		while(true) {
			System.out.println("TicTacToe \n\nSeleccione quien empezara:\n 1:Bot\n 2:Persona\n 3:Salir");
			jugadorInicia = Leer.nextInt();
			if(jugadorInicia==3){System.out.println("Finalizado");break;}
			else if(jugadorInicia==1 || jugadorInicia==2){
				TicTacToe ticTacToe = new TicTacToe();
		        ticTacToe.start(jugadorInicia);
			}else {
				System.out.println("Elija una de las opciones del Menu:");
			}
		}
	
	}
}
