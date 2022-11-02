import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cola {
    private int MAX_ELEMENTOS;
    List<Integer> cola;

    public Cola(int max) {
        cola = new LinkedList<>();
        this.MAX_ELEMENTOS = max;
    }

    public synchronized boolean estaVacia() {
        return cola.size() == 0;
    }

    public synchronized boolean estaLlena() {
        int numElementos = cola.size();
        if (numElementos == this.MAX_ELEMENTOS) {
            return true;
        }
        return false;
    }

    public synchronized boolean encolar(int numero) {
        if (estaLlena()) {
            return false;
        }
        cola.add(numero);
        return true;
    }

    public synchronized int desencolar() {
        if (estaVacia()) {
            return -1;
        }
        int numero = cola.remove(0);
        return numero;
    }

}