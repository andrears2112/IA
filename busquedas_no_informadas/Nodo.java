import java.util.ArrayList;

public class Nodo {
    private int[][] matriz;
    private Nodo raiz;
    private int costo;

    public Nodo(Nodo raiz) {
        this.matriz = new int[3][3];
        this.raiz = raiz;
    }

    public Nodo(Nodo raiz, int[][] matriz) {
        this.matriz = matriz;
        this.raiz = raiz;
    }

    public Nodo(Nodo raiz, int[][] matriz, int costo) {
        this.matriz = matriz;
        this.raiz = raiz;
        this.costo = costo;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public int getCosto() {
        return costo;
    }

    public int[][] obtenerMatriz() {
        int[][] matriz = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = this.matriz[i][j];
            }
        }
        return matriz;
    }

    public void imprimirMatriz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d ", matriz[i][j]);
            }
            System.out.println("");
        }
    }
}
