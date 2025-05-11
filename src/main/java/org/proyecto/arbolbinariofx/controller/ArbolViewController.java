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
import java.util.StringJoiner; // Para construir strings de forma más eficiente

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
    private TextArea resultadosArea; // Aquí mostraremos toda la información

    private ArbolBinario arbol;

    @FXML
    public void initialize() {
        arbol = new ArbolBinario();

        insertarBtn.setOnAction(e -> insertarValor());
        preordenBtn.setOnAction(e -> mostrarPreorden());
        inordenBtn.setOnAction(e -> mostrarInorden());
        postordenBtn.setOnAction(e -> mostrarPostorden());
        limpiarBtn.setOnAction(e -> limpiarArbol());

        cargarArbolEjemplo();
        actualizarVistaCompleta(); // Llama a un metodo que actualiza
    }

    private void insertarValor() {
        try {
            int valor = Integer.parseInt(valorField.getText());
            arbol.insertar(valor);
            valorField.clear();
            actualizarVistaCompleta(); // Actualiza dibujo y estadísticas
        } catch (NumberFormatException ex) {
            mostrarAlerta("Error de Entrada", "Por favor, ingrese un número entero válido.");
        }
    }

    private void mostrarPreorden() {
        if (arbol.getRaiz() == null) {
            resultadosArea.setText("Árbol vacío.");
            return;
        }
        mostrarRecorrido("Preorden", arbol.preOrden());
        // Por ahora, dejaremos que la acción principal (insertar, limpiar) actualice todo.
    }

    private void mostrarInorden() {
        if (arbol.getRaiz() == null) {
            resultadosArea.setText("Árbol vacío.");
            return;
        }
        mostrarRecorrido("Inorden", arbol.inOrden());
    }

    private void mostrarPostorden() {
        if (arbol.getRaiz() == null) {
            resultadosArea.setText("Árbol vacío.");
            return;
        }
        mostrarRecorrido("Postorden", arbol.postOrden());
    }

    private void limpiarArbol() {
        arbol.limpiar();
        actualizarVistaCompleta();
    }

    private void cargarArbolEjemplo() {
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int valor : valores) {
            arbol.insertar(valor);
        }
    }

    /**
     * Actualiza el dibujo del árbol y la información de estadísticas.
     */
    private void actualizarVistaCompleta() {
        dibujarArbol();
        actualizarEstadisticasArbol();
    }

    /**
     * Muestra las estadísticas del árbol (peso, altura, etc.) en el TextArea.
     */
    private void actualizarEstadisticasArbol() {
        if (arbol.getRaiz() == null) {
            resultadosArea.setText("El árbol está vacío.\n" +
                    "Peso: 0\n" +
                    "Altura: -1 (o 0 si prefieres para vacío)\n" +
                    "Longitud Camino Interno: 0\n" +
                    "Suma Profundidad Hojas: 0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- Estadísticas del Árbol ---\n");
        sb.append("Peso (Nodos): ").append(arbol.getPeso()).append("\n");
        sb.append("Altura: ").append(arbol.getAltura()).append("\n");
        sb.append("Longitud Camino Interno (Suma Profundidades): ").append(arbol.getLongitudCaminoInterno()).append("\n");
        sb.append("Suma Profundidades de Hojas: ").append(arbol.getSumaProfundidadHojas()).append("\n\n");


        // Si quiero que los recorridos se muestren siempre debajo:
        sb.append("--- Recorridos ---\n");
        sb.append("Preorden: ").append(listToString(arbol.preOrden())).append("\n");
        sb.append("Inorden: ").append(listToString(arbol.inOrden())).append("\n");
        sb.append("Postorden: ").append(listToString(arbol.postOrden())).append("\n");


        resultadosArea.setText(sb.toString());
    }

    // Metodo para mostrar un recorrido específico (puede ser llamado por los botones de recorrido)
    private void mostrarRecorrido(String tipo, List<Integer> recorrido) {
        StringBuilder sb = new StringBuilder();
        // Primero, mostramos las estadísticas actuales del árbol
        if (arbol.getRaiz() == null) {
            sb.append("El árbol está vacío.\n");
        } else {
            sb.append("--- Estadísticas del Árbol ---\n");
            sb.append("Peso (Nodos): ").append(arbol.getPeso()).append("\n");
            sb.append("Altura: ").append(arbol.getAltura()).append("\n");
            sb.append("Longitud Camino Interno (Suma Profundidades): ").append(arbol.getLongitudCaminoInterno()).append("\n");
            sb.append("Suma Profundidades de Hojas: ").append(arbol.getSumaProfundidadHojas()).append("\n\n");
        }
        // Luego, el recorrido solicitado
        sb.append(tipo).append(": ");
        sb.append(listToString(recorrido));

        resultadosArea.setText(sb.toString());
    }

    // Helper para convertir una lista a String
    private String listToString(List<Integer> list) {
        if (list.isEmpty()) return "[]";
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (Integer valor : list) {
            joiner.add(String.valueOf(valor));
        }
        return joiner.toString();
    }


    private void dibujarArbol() {
        GraphicsContext gc = arbolCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, arbolCanvas.getWidth(), arbolCanvas.getHeight());

        if (arbol.getRaiz() == null) return;

        dibujarNodo(gc, arbol.getRaiz(), arbolCanvas.getWidth() / 2, 50, arbolCanvas.getWidth() / 4);
    }

    private void dibujarNodo(GraphicsContext gc, Nodo nodo, double x, double y, double offset) {
        if (nodo == null) return;

        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(nodo.getValor()), x - (String.valueOf(nodo.getValor()).length() * 3.5), y + 5); // Ajuste para centrar texto

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