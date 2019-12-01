/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.Migrante;
import modelo.Registro;
import modelo.TipoPersona;
import utilities.CONSTANTES;
import utilities.CircularLinkedList;
import utilities.Utilities;

/**
 *
 * @author Moisés Atupaña
 */
public class VistaVisualizaciones {

    private VBox root;
    private HBox cont;
    private CircularLinkedList<Node> graficas;
    private Button cambiar;

    public VistaVisualizaciones() {
        root = new VBox();
        cont = new HBox();
        encabezado();
        
    }

    public Pane getRoot() {
        return root;
    }

    public void encabezado() {
        Label lblBienvenida = new Label("Estadistícas");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40));

        Label opcion = new Label("Escoja el rango de fecha:");
        Label lLow = new Label("Desde:");
        Label lUp = new Label("Hasta:");
        HBox inicio = new HBox();
        HBox fin = new HBox();
        HBox contenedor = new HBox();
        DatePicker low = new DatePicker();
        low.setStyle(CONSTANTES.GRADIENTE);

        low.setEditable(false);
        DatePicker up = new DatePicker();
        up.setStyle(CONSTANTES.GRADIENTE);
        inicio.getChildren().addAll(lLow, low);
        fin.getChildren().addAll(lUp, up);
        Button aplicar = new Button("Aplicar");
        aplicar.setPrefSize(100, 30);
        aplicar.setStyle(CONSTANTES.GRADIENTE + ";-fx-font-size:15px;-fx-text-fill:white");

        contenedor.getChildren().addAll(inicio, fin, aplicar);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(5, 20, 5, 20));
        contenedor.setSpacing(50);
        root.getChildren().addAll(lblBienvenida, opcion, contenedor);
        aplicar.setOnMouseClicked(accion(low, up));
        Rectangle r = new Rectangle(CONSTANTES.HEIGHT + 200, CONSTANTES.WIDTH - 50, Color.BEIGE);
        cont.setStyle("-fx-background-color:beige");
        cont.setPrefSize(CONSTANTES.HEIGHT + 200, CONSTANTES.WIDTH - 50);
        cont.setAlignment(Pos.CENTER);
        root.getChildren().add(cont);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        inferior();

    }

    private EventHandler<MouseEvent> accion(DatePicker low, DatePicker up) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (low.getValue() == null || up.getValue() == null) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Existen campos nulos");
                    alerta.setContentText("Por favor llene todo los campos");
                    alerta.showAndWait();

                } else if (low.getValue().compareTo(up.getValue()) <= 0) {
                    if (Main.getMigraciones().registros.isEmpty()) {
                        cont.getChildren().clear();
                        Label mensaje = new Label("No hay registros por mostrar");
                        mensaje.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
                        cont.getChildren().add(mensaje);
                    } else{
                        
                        graficas = new CircularLinkedList<>();
                        graficas.addLast(graficoBarra(acotar(low.getValue(), up.getValue()), low.getValue(), up.getValue()));
                        graficas.addLast(graficoCircular(acotar(low.getValue(), up.getValue()), low.getValue(), low.getValue()));
                        graficas.addLast(histograma(acotar(low.getValue(), up.getValue()), low.getValue(), up.getValue()));
                        graficas.addLast(graficoLineas(acotar(low.getValue(), up.getValue()), low.getValue(), up.getValue()));
                        graficas.addLast(graficoPie(acotar(low.getValue(), up.getValue()), low.getValue(), up.getValue()));
                        contenedor();
                    }
                } 
                else {

                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("El rango no es válido");
                    alerta.setContentText("Por favor ingrese un rango válido");
                    alerta.showAndWait();
                }
            }

        };
    }

    public void contenedor() {
        cont.getChildren().clear();
        cont.setStyle("-fx-background-color:beige");
        final StackPane grafica = new StackPane();
        Iterator<Node> it = graficas.iterator();
        grafica.getChildren().add(it.next());
        cont.getChildren().addAll(grafica);
        cambiar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grafica.getChildren().remove(0);
                grafica.getChildren().add(it.next());
            }

        });
        cont.setAlignment(Pos.CENTER);

    }

    private void inferior() {
        HBox contBotones = new HBox();
        StackPane btnSalir = Utilities.boton("ButtonRed");
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        btnSalir.getChildren().add(lblSalir);
        btnSalir.setLayoutX(300);
        btnSalir.setLayoutY(575);
        btnSalir.setOnMouseClicked(e -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });
        cambiar = new Button("Siguiente\nGráfico");
        cambiar.setPrefSize(177, 59);
        cambiar.setStyle(CONSTANTES.GRADIENTE + ";-fx-font-size:18px; -fx-text-fill:white");

        contBotones.getChildren().addAll(btnSalir, cambiar);
        contBotones.setAlignment(Pos.CENTER);
        contBotones.setSpacing(50);
        root.getChildren().addAll(contBotones);
    }

    public Node histograma(List<Registro> registro, LocalDate low, LocalDate up) {
        //Definiendo las series
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Capacidades Especiales");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Normal");
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Tercera Edad");
        //Definiendo el eje x 
        CategoryAxis xAxis = new CategoryAxis();
        ObservableList<String> Años = FXCollections.observableArrayList();
        //Seleccionando los años
        List<String> anios = seleccionarAños(low, up);
        Años.addAll(anios);
        xAxis.setCategories(Años);
        xAxis.setLabel("Años");
        //Defining the y axis 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Población de Migrantes");
        //Eligiendo los datos a mostrar
        Iterator it = anios.iterator();
        while (it.hasNext()) {
            int normal = 0, dis = 0, edad = 0;
            String anio = (String) it.next();
            Iterator it2 = registro.iterator();
            while (it2.hasNext()) {
                Registro r = (Registro) it2.next();
                String anioR = (r.getFecha().toString()).split("-")[0];
                if (anio.equals(anioR)) {
                    if (r.getMigrante().getTipoPersona().toString().equalsIgnoreCase(TipoPersona.NORMAL.toString())) {
                        normal++;
                    } else if (r.getMigrante().getTipoPersona().toString().equalsIgnoreCase(TipoPersona.CAPACIDADESESPECIALES.toString())) {
                        dis++;
                    } else {
                        edad++;
                    }
                }
            }
            series1.getData().add(new XYChart.Data<>(anio, normal));
            series2.getData().add(new XYChart.Data<>(anio, dis));
            series3.getData().add(new XYChart.Data<>(anio, edad));
        }
        //Creating the Bar chart 
        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
        stackedBarChart.setTitle("Población de migrantes por año");

        stackedBarChart.getData().addAll(series1, series2, series3);
        Group rootBarras = new Group(stackedBarChart);
        return rootBarras;
    }

    /**
     * Grafico de Líneas
     */
    //Definiendo Ejes X y Y
    public Node graficoLineas(List<Registro> registro, LocalDate low, LocalDate up) {
        //Defining the x axis 
        CategoryAxis xAxis = new CategoryAxis();
        ObservableList<String> Años = FXCollections.observableArrayList();
        List<String> anios = seleccionarAños(low, up);
        Años.addAll(anios);
        xAxis.setCategories(Años);
        xAxis.setLabel("Años");

        //Defining the y axis 
        NumberAxis yAxis = new NumberAxis();
        NumberAxis EjeX = new NumberAxis(Integer.parseInt(anios.get(0)), Integer.parseInt(anios.get(anios.size() - 1)), 1);
        yAxis.setLabel("No.De Migrantes");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Nº Migrantes por Año");
        Iterator it = anios.iterator();
        int max=0;
        while (it.hasNext()) {
            int cont = 0;
            String anio = (String) it.next();
            Iterator it2 = registro.iterator();
            while (it2.hasNext()) {
                Registro r = (Registro) it2.next();
                String anioR = (r.getFecha().toString()).split("-")[0];
                if (anio.equals(anioR)) {
                    cont++;
                }
                
            }
            if(cont>max)
                max=cont;
            
            series.getData().add(new XYChart.Data(Integer.parseInt(anio), cont));
        }
        NumberAxis EjeY = new NumberAxis(0, max+5, max/10);
        LineChart linechart = new LineChart(EjeX, EjeY);
        linechart.getData().add(series);
        Group rootLine = new Group(linechart);
        return rootLine;
    }

    /**
     * Grafico Pie Chart
     */
    public Node graficoPie(List<Registro> registro, LocalDate low, LocalDate up) {
        CategoryAxis xAxis = new CategoryAxis();
        ObservableList<String> Años = FXCollections.observableArrayList();
        List<String> anios = seleccionarAños(low, up);
        Años.addAll(anios);
        xAxis.setCategories(Años);
        xAxis.setLabel("Años");
        Label lblSelecciones = new Label("Seleccione el año que desea vizualizar: ");
        //diseño
        lblSelecciones.setFont(Font.font("Arial", FontWeight.THIN, FontPosture.ITALIC, 15));
        ComboBox comboAños = new ComboBox(Años);
        comboAños.setValue("2019");
        ObservableList<PieChart.Data> pieChartData = opcionPorAño((String)comboAños.getValue(),registro);
        PieChart pieChart = new PieChart(pieChartData);
        comboAños.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                
                pieChartData.setAll(opcionPorAño((String)comboAños.getValue(),registro).get(0),opcionPorAño((String)comboAños.getValue(),registro).get(1),opcionPorAño((String)comboAños.getValue(),registro).get(2));
            }
        });
        pieChart.setTitle("Migrantes Por año");
        //diseño
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        HBox Seleccion = new HBox();
        Seleccion.getChildren().addAll(lblSelecciones, comboAños);
        VBox rootPie = new VBox();
        rootPie.getChildren().addAll(Seleccion, pieChart);
        return rootPie;
    }

    private ObservableList<PieChart.Data> opcionPorAño(String anio, List<Registro> registros) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int normal = 0, dis = 0, edad = 0;
        Iterator it = registros.iterator();
        while (it.hasNext()) {
            Registro r=(Registro)it.next();
            String anioR = (r.getFecha().toString()).split("-")[0];
            if (anioR.equals(anio)) {
                if (anio.equals(anioR)) {
                    if (r.getMigrante().getTipoPersona().toString().equalsIgnoreCase(TipoPersona.NORMAL.toString())) {
                        normal++;
                    } else if (r.getMigrante().getTipoPersona().toString().equalsIgnoreCase(TipoPersona.CAPACIDADESESPECIALES.toString())) {
                        dis++;
                    } else {
                        edad++;
                    }
                }
            }
        }
        pieChartData.addAll(new PieChart.Data("Normal", normal),new PieChart.Data("Capacidades Especiales", dis),new PieChart.Data("Tercera edad",edad));
        return pieChartData;
    }

    private List<String> seleccionarAños(LocalDate low, LocalDate up) {
        List<String> anios = new ArrayList<>();
        //Escogiendo los años seleccionados
        String anioInicio = low.toString().split("-")[0];
        String anioFin = up.toString().split("-")[0];
        int anioI = Integer.parseInt(anioInicio);
        int anioF = Integer.parseInt(anioFin);
        anios.add(anioInicio);
        while (anioI != anioF) {
            anioI++;
            anios.add(Integer.toString(anioI));
        }
        return anios;
    }

    public Node graficoBarra(List<Registro> registro, LocalDate low, LocalDate up) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Meses");
        Map<String, String> fechas = new TreeMap<>();
        fechas.put("01", "Enero");
        fechas.put("02", "Febrero");
        fechas.put("03", "Marzo");
        fechas.put("04", "Abril");
        fechas.put("05", "Mayo");
        fechas.put("06", "Junio");
        fechas.put("07", "Julio");
        fechas.put("08", "Agosto");
        fechas.put("09", "Septiembre");
        fechas.put("10", "Octubre");
        fechas.put("11", "Noviembre");
        fechas.put("12", "Diciembre");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cantidad");

        BarChart chart = new BarChart(xAxis, yAxis);
        chart.setTitle("Cantidad de ingreso/Salida de Migrantes\n\t"
                + low.toString() + " hasta " + up.toString());
        XYChart.Series<String, Number> entrada = new XYChart.Series<>();
        entrada.setName("Entrada");

        XYChart.Series<String, Number> salida = new XYChart.Series<>();
        salida.setName("Salida");
        Iterator it = fechas.keySet().iterator();
        while (it.hasNext()) {
            int contEntrada = 0;
            int contSalida = 0;
            String mes1 = (String) it.next();
            Iterator it2 = registro.iterator();
            while (it2.hasNext()) {
                Registro r = (Registro) it2.next();
                String mes2 = (r.getFecha().toString()).split("-")[1];
                if (mes1.equals(mes2)) {
                    if (r.getTipoMov().equalsIgnoreCase("Entrada")) {
                        contEntrada++;
                    } else {
                        contSalida++;
                    }
                }

                entrada.getData().add(new XYChart.Data<>(fechas.get(mes1), contEntrada));
                salida.getData().add(new XYChart.Data<>(fechas.get(mes1), contSalida));
            }
        }

        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
        data.addAll(entrada, salida);
        chart.setData(data);
        return chart;

    }

    public Node graficoCircular(List<Registro> registro, LocalDate low, LocalDate up) {
        HBox circulares = new HBox();
        ObservableList<PieChart.Data> pieEntrada = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> pieSalida = FXCollections.observableArrayList();
        String[] tipos = {TipoPersona.CAPACIDADESESPECIALES.toString(), TipoPersona.TERCERAEDAD.toString(), TipoPersona.NORMAL.toString()};
        for (String tipo : tipos) {
            int contEntrada = 0;
            int contSalida = 0;
            Iterator it = registro.iterator();
            while (it.hasNext()) {
                Registro r = (Registro) it.next();
                Migrante m = r.getMigrante();
                if (tipo.equals(m.getTipoPersona().toString())) {
                    if (r.getTipoMov().equalsIgnoreCase("Entrada")) {
                        contEntrada++;
                    } else {
                        contSalida++;
                    }
                }
            }
            pieEntrada.add(new PieChart.Data(tipo, contEntrada));
            pieSalida.add(new PieChart.Data(tipo, contSalida));
        }

        PieChart entrada = new PieChart(pieEntrada);
        entrada.setTitle("Entradas por tipo de Persona");

        PieChart salida = new PieChart(pieSalida);
        salida.setTitle("Salidas por tipo de Persona");
        circulares.getChildren().addAll(entrada, salida);
        return circulares;
    }

    private List<Registro> acotar(LocalDate low, LocalDate up) {
        List<Registro> listaAcotada = new ArrayList<>();     
        Iterator<Registro> it =Main.getMigraciones().registros.iterator();
        while (it.hasNext()) {
            Registro r = it.next();
            LocalDate fecha = r.getFecha();
            if (low.compareTo(fecha) <= 0 && up.compareTo(fecha) >= 0) {
                listaAcotada.add(r);
            }

        }
        return listaAcotada;
    }

}
