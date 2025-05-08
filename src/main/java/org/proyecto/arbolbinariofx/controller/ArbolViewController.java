package org.proyecto.arbolbinariofx.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.proyecto.arbolbinariofx.model.ArbolBinario;
import org.proyecto.arbolbinariofx.model.Nodo;

import java.util.List;

public class ArbolViewController {
    @FXML
    private TextField valorField;

    @FXML
    private Button insertarBtn;

    @FXML
    private Button preordenBtn;

    @FXML
    private Button inordenBtn;

    @FXML
    private Button postordenBtn;

    @FXML
    private Button limpiarBtn;

    @FXML
    private Canvas arbolCanvas;

    @FXML
    private TextArea resultadosArea;

    private ArbolBinario arbol;

    @FXML
    public void initialize() {
        arbol = new ArbolBinario();

        // Configurar eventos de botones
        insertarBtn.setOnAction(e -> insertarValor());
        preordenBtn.setOnAction(e -> mostrarPreorden());
        inordenBtn.setOnAction(e -> mostrarInorden());
        postordenBtn.setOnAction(e -> mostrarPostorden());
        limpiarBtn.setOnAction(e -> limpiarArbol());

        // Cargar árbol de ejemplo
        cargarArbolEjemplo();

        // Actualizar vista
        dibujarArbol();
    }

    private void insertarValor() {
        try {
            int valor = Integer.parseInt(valorField.getText());
            arbol.insertar(valor);
            valorField.clear();
            dibujarArbol();
        } catch (NumberFormatException ex) {
            mostrarAlerta("Error", "Por favor, ingrese un número válido.");
        }
    }

    private void mostrarPreorden() {
        mostrarRecorrido("Preorden", arbol.preOrden());
    }

    private void mostrarInorden() {
        mostrarRecorrido("Inorden", arbol.inOrden());
    }

    private void mostrarPostorden() {
        mostrarRecorrido("Postorden", arbol.postOrden());
    }

    private void limpiarArbol() {
        arbol.limpiar();
        resultadosArea.clear();
        dibujarArbol();
    }

    private void cargarArbolEjemplo() {
        // Carga un árbol de ejemplo para visualización inicial
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int valor : valores) {
            arbol.insertar(valor);
        }
    }

    private void mostrarRecorrido(String tipo, List<Integer> recorrido) {
        StringBuilder sb = new StringBuilder();
        sb.append(tipo).append(": ");
        for (Integer valor : recorrido) {
            sb.append(valor).append(" ");
        }
        resultadosArea.setText(sb.toString());
    }

    private void dibujarArbol() {
        GraphicsContext gc = arbolCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, arbolCanvas.getWidth(), arbolCanvas.getHeight());

        if (arbol.getRaiz() == null) return;

        dibujarNodo(gc, arbol.getRaiz(), arbolCanvas.getWidth() / 2, 50, arbolCanvas.getWidth() / 4);
    }

    private void dibujarNodo(GraphicsContext gc, Nodo nodo, double x, double y, double offset) {
        if (nodo == null) return;

        // Dibujar el nodo
        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        // Dibujar el valor del nodo
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(nodo.getValor()), x - 5, y + 5);

        // Dibujar conexiones a los hijos
        if (nodo.getIzquierdo() != null) {
            double childX = x - offset;
            double childY = y + 50;
            gc.strokeLine(x, y + 15, childX, childY - 15);
            dibujarNodo(gc, nodo.getIzquierdo(), childX, childY, offset / 2);
        }

        if (nodo.getDerecho() != null) {
            double childX = x + offset;
            double childY = y + 50;
            gc.strokeLine(x, y + 15, childX, childY - 15);
            dibujarNodo(gc, nodo.getDerecho(), childX, childY, offset / 2);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
