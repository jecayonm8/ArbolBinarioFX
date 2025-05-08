package org.proyecto.arbolbinariofx.model;
import org.proyecto.arbolbinariofx.model.Nodo;

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

    // Implementación de los métodos de recorrido según especificación
    public List<Integer> preOrden() {
        List<Integer> resultado = new ArrayList<>();
        preOrden(raiz, resultado);
        return resultado;
    }

    //metodo para tipo de recorrido en preorden
    public void preOrden(Nodo raiz) {
        if(raiz == null) return;

        System.out.println(raiz.getValor()); // Visitamos primero la raíz
        preOrden(raiz.getIzquierdo());       // Luego subárbol izquierdo
        preOrden(raiz.getDerecho());         // Finalmente subárbol derecho
    }

    // Versión que almacena los resultados en una lista
    private void preOrden(Nodo raiz, List<Integer> resultado) {
        if(raiz == null) return;

        resultado.add(raiz.getValor());    // Visitamos primero la raíz
        preOrden(raiz.getIzquierdo(), resultado); // Luego subárbol izquierdo
        preOrden(raiz.getDerecho(), resultado);   // Finalmente subárbol derecho
    }

    public List<Integer> inOrden() {
        List<Integer> resultado = new ArrayList<>();
        inOrden(raiz, resultado);
        return resultado;
    }

    public void inOrden(Nodo raiz) {
        if(raiz == null) return;

        inOrden(raiz.getIzquierdo());       // Primero subárbol izquierdo
        System.out.println(raiz.getValor()); // Luego visitamos la raíz
        inOrden(raiz.getDerecho());         // Finalmente subárbol derecho
    }

    // Versión que almacena los resultados en una lista
    private void inOrden(Nodo raiz, List<Integer> resultado) {
        if(raiz == null) return;

        inOrden(raiz.getIzquierdo(), resultado); // Primero subárbol izquierdo
        resultado.add(raiz.getValor());         // Luego visitamos la raíz
        inOrden(raiz.getDerecho(), resultado);   // Finalmente subárbol derecho
    }

    public List<Integer> postOrden() {
        List<Integer> resultado = new ArrayList<>();
        postOrden(raiz, resultado);
        return resultado;
    }

    public void postOrden(Nodo raiz) {
        if(raiz == null) return;

        postOrden(raiz.getIzquierdo());     // Primero subárbol izquierdo
        postOrden(raiz.getDerecho());       // Luego subárbol derecho
        System.out.println(raiz.getValor()); // Finalmente visitamos la raíz
    }

    // Versión que almacena los resultados en una lista
    private void postOrden(Nodo raiz, List<Integer> resultado) {
        if(raiz == null) return;

        postOrden(raiz.getIzquierdo(), resultado); // Primero subárbol izquierdo
        postOrden(raiz.getDerecho(), resultado);   // Luego subárbol derecho
        resultado.add(raiz.getValor());           // Finalmente visitamos la raíz
    }

    public void limpiar() {
        raiz = null;
    }

}
