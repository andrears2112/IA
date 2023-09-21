import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Busqueda buscar = new Busqueda();

        System.out.println("☆☆☆☆☆☆ Bienvenido al juego 8 puzzle ☆☆☆☆☆☆");
        buscar.ingresarMatrices();
        System.out.println("\n☆☆☆☆☆☆ Menú ☆☆☆☆☆☆");
        System.out.println("1.- Búsqueda por anchura");
        System.out.println("2.- Búsqueda por profundidad");
        System.out.println("3.- Búsqueda por cola de prioridad");
        System.out.println("4.- Salir");
        
        int n = scan.nextInt();
        if(n == 1){
            buscar.tipoBusqueda = true;
            buscar.Busqueda();
        }
        if(n == 2){
            buscar.tipoBusqueda = false;
            buscar.Busqueda();
        }
        if(n == 3){
            buscar.costoUniforme = true;
            buscar.Busqueda();
        }
        if (n == 4) 
            System.exit(0);
        
    }
}
