import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Busqueda {
    private ArrayList<Integer> lista;
    PriorityQueue<Nodo> colaPrioridad;
    private Queue<Nodo> cola;
    private Stack<Nodo> pila;
    private ArrayList<Nodo> solucion;
    private ArrayList<Nodo> nodosCreados;
    public boolean tipoBusqueda;
    public boolean costoUniforme;
    private int sumaNodos;
    private int[][] matrizInicial;
    private int[][] matrizSolucion;

    public void Busqueda() {
        this.lista = new ArrayList<>();

        Nodo nodoRaiz = new Nodo(null, matrizInicial);

        while (true) {
            this.sumaNodos = 0;
            this.cola = new LinkedList<>();
            this.pila = new Stack<>();
            this.solucion = new ArrayList<>();
            this.nodosCreados = new ArrayList<>();
            long inicio = 0;
            long fin = 0;

            if (costoUniforme) {
                System.out.println("☆☆☆☆☆☆ Ejecutando Búsqueda por Prioridad ☆☆☆☆☆☆");
                inicio = System.currentTimeMillis();
                busquedaPorCostoUniforme();
                fin = System.currentTimeMillis();
                System.out.println("Tiempo Transcurrido: " + (int) (fin - inicio) / 1000 + " segundos");
                break;
            }
            if (tipoBusqueda) {
                System.out.println("☆☆☆☆☆☆ Ejecutando Búsqueda por Anchura ☆☆☆☆☆☆");
                cola.add(nodoRaiz);
                nodosCreados.add(nodoRaiz);
                inicio = System.currentTimeMillis();
                busquedaPorAnchura();
                fin = System.currentTimeMillis();
                System.out.println("Tiempo Transcurrido: " + (int) (fin - inicio) / 1000 + " segundos");
                break;
            } else {
                System.out.println("☆☆☆☆☆☆ Ejecutando Búsqueda por Profundidad ☆☆☆☆☆☆");
                pila.push(nodoRaiz);
                nodosCreados.add(nodoRaiz);
                inicio = System.currentTimeMillis();
                busquedaPorProfundidad();
                fin = System.currentTimeMillis();
                System.out.println("Tiempo Transcurrido: " + (int) (fin - inicio) / 1000 + " segundos");
                break;
            }
        }
    }

    public void ingresarMatrices() {
        System.out.println("\n☆☆☆☆☆☆ Ingrese la Matriz Inicial ☆☆☆☆☆☆");
        matrizInicial = ingresarMatriz();
        System.out.println("\n☆☆☆☆☆☆ Ingrese la Matriz Solución ☆☆☆☆☆☆");
        matrizSolucion = ingresarMatriz();
    }

    public int[][] ingresarMatriz() {
        Scanner scan = new Scanner(System.in);
        int[][] matriz = new int[3][3];
        System.out.println("Ingrese los valores para el tablero:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Ingrese el valor en la posición (" + i + ", " + j + "): ");
                matriz[i][j] = scan.nextInt();
            }
        }

        return matriz;
    }

    public void busquedaPorAnchura() {
        while (!cola.isEmpty()) {
            if (cola.peek() != null) {
                if (revisar(cola.poll()))
                    break;
            }
        }
    }

    public void busquedaPorProfundidad() {
        while (!pila.isEmpty()) {
            Nodo actual = pila.pop();
            if (actual != null) {
                if (revisar(actual)) {
                    return;
                }
                agregarHijos(actual);
            }
        }
    }

    public void busquedaPorCostoUniforme() {
        colaPrioridad = new PriorityQueue<>(new Comparator<Nodo>() {
            @Override
            public int compare(Nodo nodo1, Nodo nodo2) {
                return Integer.compare(nodo1.getCosto(), nodo2.getCosto());
            }
        });
        colaPrioridad.add(new Nodo(null, matrizInicial, 0));

        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();

            if (revisar(actual)) {
                return;
            }

            agregarHijosCostoUniforme(actual, colaPrioridad);
        }
    }

    private void agregarHijosCostoUniforme(Nodo actual, PriorityQueue<Nodo> colaPrioridad) {
        int filaVacio = -1;
        int colVacio = -1;

        outerloop: for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                if (actual.obtenerMatriz()[fila][col] == 0) {
                    filaVacio = fila;
                    colVacio = col;
                    break outerloop;
                }
            }
        }

        if (filaVacio > 0) {
            int[][] matrizHijo = movimiento(actual.obtenerMatriz(), filaVacio, colVacio, filaVacio - 1, colVacio);
            if (revisarNodosCreados(actual, matrizHijo)) {
                Nodo hijo = new Nodo(actual, matrizHijo, actual.getCosto() + 1);
                colaPrioridad.add(hijo);
                nodosCreados.add(hijo);
                sumaNodos++;
            }
        }

        if (filaVacio < 2) {
            int[][] matrizHijo = movimiento(actual.obtenerMatriz(), filaVacio, colVacio, filaVacio + 1, colVacio);
            if (revisarNodosCreados(actual, matrizHijo)) {
                Nodo hijo = new Nodo(actual, matrizHijo, actual.getCosto() + 1);
                colaPrioridad.add(hijo);
                nodosCreados.add(hijo);
                sumaNodos++;
            }
        }

        if (colVacio > 0) {
            int[][] matrizHijo = movimiento(actual.obtenerMatriz(), filaVacio, colVacio, filaVacio, colVacio - 1);
            if (revisarNodosCreados(actual, matrizHijo)) {
                Nodo hijo = new Nodo(actual, matrizHijo, actual.getCosto() + 1);
                colaPrioridad.add(hijo);
                nodosCreados.add(hijo);
                sumaNodos++;
            }
        }

        if (colVacio < 2) {
            int[][] matrizHijo = movimiento(actual.obtenerMatriz(), filaVacio, colVacio, filaVacio, colVacio + 1);
            if (revisarNodosCreados(actual, matrizHijo)) {
                Nodo hijo = new Nodo(actual, matrizHijo, actual.getCosto() + 1);
                colaPrioridad.add(hijo);
                nodosCreados.add(hijo);
                sumaNodos++;
            }
        }
    }

    public boolean revisar(Nodo actual) {
        if (esSolucion(actual)) {
            generarSolucion(actual);
            return true;
        }
        agregarHijos(actual);
        return false;
    }

    private boolean esSolucion(Nodo actual) {
        return Arrays.deepEquals(actual.obtenerMatriz(), matrizSolucion);
    }

    private void generarSolucion(Nodo actual) {
        while (actual.getRaiz() != null) {
            solucion.add(actual);
            actual = actual.getRaiz();
        }
        solucion.add(actual);
        int suma = 0;
        System.out.println("\n✔ Solución encontrada");
        for (int i = solucion.size() - 1; i >= 0; i--) {
            solucion.get(i).imprimirMatriz();
            suma++;
            System.out.println("");
        }
        System.out.println("Total de nodos recorridos en la solucion: " + suma);
        System.out.println("Total de nodos creados: " + sumaNodos);
    }

    public int[][] movimiento(int[][] matriz, int filaVacio, int colVacio, int filaNum, int colNum) {
        int num = matriz[filaNum][colNum];
        int num1 = matriz[filaVacio][colVacio];
        if (num1 == 0) {
            matriz[filaNum][colNum] = num1;
            matriz[filaVacio][colVacio] = num;
        }
        return matriz;
    }

    public void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d ", matriz[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void agregarHijos(Nodo actual) {
        switch (buscarCero(actual.obtenerMatriz())) {
            case "00":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 0, 0, 0, 1));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 0, 0, 1, 0));
                break;
            case "01":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 0, 1, 0, 0));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 0, 1, 1, 1));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 0, 1, 0, 2));
                break;
            case "02":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 0, 2, 0, 1));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 0, 2, 1, 2));
                break;
            case "10":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 0, 0, 0));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 0, 1, 1));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 0, 2, 0));
            case "11":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 1, 0, 1));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 1, 1, 0));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 1, 1, 2));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 1, 2, 1));
                break;
            case "12":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 2, 0, 2));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 2, 1, 1));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 1, 2, 2, 2));
                break;
            case "20":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 2, 0, 1, 0));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 2, 0, 2, 1));
                break;
            case "21":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 2, 1, 2, 0));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 2, 1, 1, 1));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 2, 1, 2, 2));
                break;
            case "22":
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 2, 2, 1, 2));
                revisarConPadre(actual, movimiento(actual.obtenerMatriz(), 2, 2, 2, 1));
                break;
        }
    }

    public void revisarConPadre(Nodo actual, int[][] hijo) {
        if (actual.getRaiz() != null) {
            if (revisarNodosCreados(actual, hijo)) {
                if (tipoBusqueda) {
                    cola.add(new Nodo(actual, hijo));
                    nodosCreados.add(new Nodo(actual, hijo));
                    sumaNodos++;
                    imprimirMatriz(hijo);
                } else {
                    pila.push(new Nodo(actual, hijo));
                    nodosCreados.add(new Nodo(actual, hijo));
                    sumaNodos++;
                    imprimirMatriz(hijo);
                }

            }
        } else {
            if (tipoBusqueda) {
                cola.add(new Nodo(actual, hijo));
                nodosCreados.add(new Nodo(actual, hijo));
                sumaNodos++;
                imprimirMatriz(hijo);
            } else {
                pila.push(new Nodo(actual, hijo));
                nodosCreados.add(new Nodo(actual, hijo));
                sumaNodos++;
                imprimirMatriz(hijo);
            }
        }
    }

    public boolean revisarNodosCreados(Nodo actual, int[][] hijo) {
        for (int i = 0; i < nodosCreados.size(); i++) {
            if (Arrays.deepEquals(nodosCreados.get(i).obtenerMatriz(), hijo)) {
                return false;
            }
        }
        return true;
    }

    private String buscarCero(int[][] matriz) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == 0)
                    return "" + i + j;
            }
        }
        return "";
    }
}