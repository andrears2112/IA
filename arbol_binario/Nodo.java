package arbol_binario;

class Nodo {

    Nodo nodo_derecho;
    Nodo nodo_izquierdo;
    int datos;

    public Nodo(int datos) {
        this.datos = datos;
        nodo_izquierdo = nodo_derecho = null;
    }

    public void insertar(int valor) {
        if (valor < datos) {
            if (nodo_izquierdo == null)
                nodo_izquierdo = new Nodo(valor);
            else
                nodo_izquierdo.insertar(valor);
        } else {
            if (valor > datos) {
                if (nodo_derecho == null)
                    nodo_derecho = new Nodo(valor);
                else
                    nodo_derecho.insertar(valor);
            }
        }
    }

}