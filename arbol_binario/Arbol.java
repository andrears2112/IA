package arbol_binario;

import java.util.Stack;

class Arbol {
  private Nodo raiz;

  public Arbol() {
    raiz = null;
  }

  public void insertarNodo(int valor) {
    if (vacio(raiz))
      raiz = new Nodo(valor);
    else
      raiz.insertar(valor);
  }

  public void imprimirArbol() {
    recorrer(raiz, "", true);
  }

  public Nodo buscarNodo(int nodo) {
    if (vacio(raiz))
      return null;

    Nodo nodo_actual = raiz;
    Stack<Nodo> pila = new Stack<>();

    while(!vacio(nodo_actual) || !pila.isEmpty()){
      while(!vacio(nodo_actual)){
        pila.push(nodo_actual);
        nodo_actual = nodo_actual.nodo_izquierdo;
      }

      nodo_actual = pila.pop();

      if(nodo_actual.datos == nodo){
        return nodo_actual;
      }

      nodo_actual = nodo_actual.nodo_derecho;
    }
    return null;
  }

  private void recorrer(Nodo nodo, String divisor, boolean izquierdo){
    if (vacio(nodo))
      return;
    System.out.println(divisor+(izquierdo ? "├── " : "└── ")+nodo.datos); 
    recorrer(nodo.nodo_izquierdo, divisor+(izquierdo ? "│   " : "    "), true); 
    recorrer(nodo.nodo_derecho, divisor+(izquierdo ? "│   " : "    "), false);
  }

  private boolean vacio(Nodo nodo){
    if(nodo==null)
      return true;
		else 
      return false;
  }

}