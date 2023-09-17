package arbol_binario;

import java.util.*;

public class Main {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Arbol arbol = new Arbol();
        int valor, num_nodos;

        System.out.println( "☆☆☆☆☆☆ Arbol binario ☆☆☆☆☆☆");
        System.out.println( "Ingrese el número de nodos que tendrá el arbol:");
        num_nodos = scan.nextInt();

        for (int i = 1; i<=num_nodos ; i++){
            System.out.print("Ingrese el nodo "+i+":");
            valor = scan.nextInt();
            arbol.insertarNodo(valor);
        }

        System.out.println( "\n☆☆☆☆☆☆ Arbol resultante ☆☆☆☆☆☆");
        arbol.imprimirArbol();

        System.out.println( "\nNodo a buscar:");
        valor = scan.nextInt();
        if(arbol.buscarNodo(valor) == null)
          System.out.println( "✘ Nodo no encontrado");
        if(arbol.buscarNodo(valor) != null)
          System.out.println( "✔ Nodo encontrado");

    }
} 