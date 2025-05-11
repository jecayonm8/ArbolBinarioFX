package org.proyecto.arbolbinariofx.model;

import java.util.List;
import java.util.ArrayList;

public class ArbolBinario {

    private Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), valor));
        }

        return nodo;
    }

    // Recorrido en Preorden
    public List<Integer> preOrden() {
        List<Integer> resultado = new ArrayList<>();
        preOrden(raiz, resultado);
        return resultado;
    }

    private void preOrden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) return;
        resultado.add(nodo.getValor());
        preOrden(nodo.getIzquierdo(), resultado);
        preOrden(nodo.getDerecho(), resultado);
    }

    //Recorrido en Inorden
    public List<Integer> inOrden() {
        List<Integer> resultado = new ArrayList<>();
        inOrden(raiz, resultado);
        return resultado;
    }

    private void inOrden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) return;
        inOrden(nodo.getIzquierdo(), resultado);
        resultado.add(nodo.getValor());
        inOrden(nodo.getDerecho(), resultado);
    }

    //Recorrido en Postorden
    public List<Integer> postOrden() {
        List<Integer> resultado = new ArrayList<>();
        postOrden(raiz, resultado);
        return resultado;
    }

    private void postOrden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) return;
        postOrden(nodo.getIzquierdo(), resultado);
        postOrden(nodo.getDerecho(), resultado);
        resultado.add(nodo.getValor());
    }

    // Metodos para Composición del Árbol

    /**
     * Calcula el peso del árbol (número total de nodos).
     * @return El peso del árbol.
     */
    public int getPeso() {
        return getPesoRecursivo(raiz);
    }

    private int getPesoRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + getPesoRecursivo(nodo.getIzquierdo()) + getPesoRecursivo(nodo.getDerecho());
    }

    /**
     * Calcula la altura del árbol.
     * La altura de un árbol vacío es -1 (aquí usaremos -1 para vacío y 0 para un solo nodo).
     * La altura de un árbol con un solo nodo es 0.
     * @return La altura del árbol.
     */
    public int getAltura() {
        return getAlturaRecursivo(raiz);
    }

    private int getAlturaRecursivo(Nodo nodo) {
        if (nodo == null) {
            return -1; // Altura de un árbol vacío
        }
        int alturaIzquierda = getAlturaRecursivo(nodo.getIzquierdo());
        int alturaDerecha = getAlturaRecursivo(nodo.getDerecho());
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    /**
     * Calcula la longitud de camino interno del árbol.
     * Es la suma de las profundidades de todos los nodos internos.
     * La profundidad de la raíz es 0.
     * @return La longitud de camino interno.
     */
    public int getLongitudCaminoInterno() {
        return getLongitudCaminoInternoRecursivo(raiz, 0);
    }

    private int getLongitudCaminoInternoRecursivo(Nodo nodo, int profundidad) {
        if (nodo == null) {
            return 0;
        }
        // Un nodo es interno si no es una hoja (es decir, tiene al menos un hijo)
        // O, más simple para la suma, sumamos la profundidad de todos los nodos.
        // Por ahora, sumaremos la profundidad de todos los nodos existentes.
        return profundidad +
                getLongitudCaminoInternoRecursivo(nodo.getIzquierdo(), profundidad + 1) +
                getLongitudCaminoInternoRecursivo(nodo.getDerecho(), profundidad + 1);
    }

    /**
     * Calcula la longitud de camino externo del árbol.
     * Es la suma de las profundidades de todos los "nodos externos" conceptuales
     * (los lugares donde se podría insertar un nuevo nodo, es decir, los hijos nulos de las hojas).
     * Una forma de calcularlo es usando la relación: CPE = CPI + 2 * N (para árboles binarios llenos).
     * Otra forma es contar la profundidad de cada punto nulo.
     * Para este ejemplo, calcularemos la suma de las profundidades de las hojas.
     * Si una hoja está a profundidad 'd', contribuye 'd' a esta suma.
     * @return La suma de las profundidades de las hojas.
     */
    public int getSumaProfundidadHojas() { // Renombrado para mayor claridad
        return getSumaProfundidadHojasRecursivo(raiz, 0);
    }

    private int getSumaProfundidadHojasRecursivo(Nodo nodo, int profundidad) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.esHoja()) { // Usamos el metodo esHoja() de la clase Nodo
            return profundidad;
        }
        return getSumaProfundidadHojasRecursivo(nodo.getIzquierdo(), profundidad + 1) +
                getSumaProfundidadHojasRecursivo(nodo.getDerecho(), profundidad + 1);
    }


    public void limpiar() {
        raiz = null;
    }

}