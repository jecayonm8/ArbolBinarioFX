package org.proyecto.arbolbinariofx.model;

public class Nodo {
    private int valor;
    private Nodo izquierdo;
    private Nodo derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

}
