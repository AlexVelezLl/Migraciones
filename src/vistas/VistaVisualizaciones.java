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
 * @author Katiuska Marín
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
        low.setEditable(false);
        DatePicker up = new DatePicker();
        inicio.getChildren().addAll(lLow, low);
        fin.getChildren().addAll(lUp, up);
        Button aplicar = new Button("Aplicar");
        aplicar.setMaxSize(75, 30);
        contenedor.getChildren().addAll(inicio, fin, aplicar);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(5, 20, 5, 20));
        contenedor.setSpacing(50);
        root.getChildren().addAll(lblBienvenida, opcion, contenedor);
        aplicar.setOnMouseClicked(accion(low, up));
        Rectangle r=new Rectangle(CONSTANTES.HEIGHT +200,CONSTANTES.WIDTH-50,Color.BEIGE);
        cont.getChildren().add(r);
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
                    graficas = new CircularLinkedList<>();
                    graficas.addLast(graficoBarra(acotar(low.getValue(), up.getValue()), low.getValue(), up.getValue()));
                    graficas.addLast(graficoCircular(acotar(low.getValue(), up.getValue()), low.getValue(), low.getValue()));
                    graficas.addLast(histograma());
                    graficas.addLast(graficoLineas());
                    graficas.addLast(graficoPie());
                    contenedor();
             
                } else {

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
        cambiar = new Button("Next Graphic");
        contBotones.getChildren().addAll(btnSalir, cambiar);
        contBotones.setAlignment(Pos.CENTER);
        contBotones.setSpacing(50);
        root.getChildren().addAll(contBotones);
    }

    public Node histograma() {
        //Defining the x axis 
        CategoryAxis xAxis = new CategoryAxis();
        ObservableList<String> Años = FXCollections.observableArrayList();
        Años.addAll("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019");
        xAxis.setCategories(Años);
        xAxis.setLabel("Años");

        //Defining the y axis 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Población de Migrantes");

        //Creating the Bar chart 
        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
        stackedBarChart.setTitle("Población de migrantes por año");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        XYChart.Series series4 = new XYChart.Series();
        Datos(series1, series2, series3, series4);

        stackedBarChart.getData().addAll(series1, series2, series3);
        Group rootBarras = new Group(stackedBarChart);
        return rootBarras;
    }

    /**
     * Grafico de Líneas
     */
    //Definiendo Ejes X y Y
    public Node graficoLineas() {
        //Defining the x axis 
        CategoryAxis xAxis = new CategoryAxis();
        ObservableList<String> Años = FXCollections.observableArrayList();
        Años.addAll("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019");
        xAxis.setCategories(Años);
        xAxis.setLabel("Años");

        //Defining the y axis 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Población de Migrantes");
        NumberAxis EjeX = new NumberAxis(2010, 2020, 10);
        xAxis.setLabel("Años");
        NumberAxis EjeY = new NumberAxis(0, 350, 50);
        yAxis.setLabel("No.De Migrantes");

        LineChart linechart = new LineChart(EjeX, EjeY);
        XYChart.Series series4 = new XYChart.Series();
        linechart.getData().add(series4);
        Group rootLine = new Group(linechart);
        return rootLine;
    }

    /**
     * Grafico Pie Chart
     */
    public Node graficoPie() {
        CategoryAxis xAxis = new CategoryAxis();
        ObservableList<String> Años = FXCollections.observableArrayList();
        Años.addAll("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019");
        xAxis.setCategories(Años);
        xAxis.setLabel("Años");

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Capacidades Especiales", 75),
                new PieChart.Data("Tercera Edad", 90),
                new PieChart.Data("Normal", 220));
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Migrantes Por año");
        Label lblSelecciones = new Label("Seleccione el año que desea vizualizar: ");
        lblSelecciones.setFont(Font.font("Arial", FontWeight.THIN, FontPosture.ITALIC, 15));
        ComboBox comboAños = new ComboBox(Años);
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

    private void Datos(XYChart.Series<String, Number> series1, XYChart.Series<String, Number> series2, XYChart.Series<String, Number> series3,
            XYChart.Series series) {
        //Prepare XYChart.Series objects by setting data 

        series1.setName("Capacidades Especiales");
        series1.getData().add(new XYChart.Data<>("2010", 107));
        series1.getData().add(new XYChart.Data<>("2011", 31));
        series1.getData().add(new XYChart.Data<>("2012", 635));
        series1.getData().add(new XYChart.Data<>("2013", 203));
        series1.getData().add(new XYChart.Data<>("2014", 2));

        series2.setName("Normal");
        series1.getData().add(new XYChart.Data<>("2010", 156));
        series1.getData().add(new XYChart.Data<>("2011", 947));
        series1.getData().add(new XYChart.Data<>("2012", 635));
        series1.getData().add(new XYChart.Data<>("2013", 203));
        series1.getData().add(new XYChart.Data<>("2014", 408));
        series2.getData().add(new XYChart.Data<>("2015", 103));

        series3.setName("Tercera Edad");
        series3.getData().add(new XYChart.Data<>("2010", 973));
        series3.getData().add(new XYChart.Data<>("2011", 914));
        series3.getData().add(new XYChart.Data<>("2012", 4054));
        series3.getData().add(new XYChart.Data<>("2013", 732));
        series1.getData().add(new XYChart.Data<>("2014", 34));

        series.setName("Nº Migrantes por Año");
        series.getData().add(new XYChart.Data(2010, 15));
        series.getData().add(new XYChart.Data(2011, 30));
        series.getData().add(new XYChart.Data(2012, 60));
        series.getData().add(new XYChart.Data(2013, 120));
        series.getData().add(new XYChart.Data(2014, 240));
        series.getData().add(new XYChart.Data(2015, 300));
        series.getData().add(new XYChart.Data(2016, 350));
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
        List<Registro> base = new ArrayList<>();
        Migrante m1 = new Migrante("Juan perez", "0987654321", "Ecuatoriano", "Guayas", "Guayaquil", TipoPersona.NORMAL);
        Migrante m2 = new Migrante("Juan perez", "0987654321", "Ecuatoriano", "Guayas", "Guayaquil", TipoPersona.CAPACIDADESESPECIALES);
        Migrante m3 = new Migrante("Juan perez", "0987654321", "Ecuatoriano", "Guayas", "Guayaquil", TipoPersona.TERCERAEDAD);
        Registro r1 = new Registro(m1, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra");
        Registro r2 = new Registro(m1, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra");
        base.add(new Registro(m1, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m1, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m2, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Entrada", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));
        base.add(new Registro(m3, "Salida", "Ecuador", "Auto", "Peu", "Lima", "Compra"));

        List<Registro> listaAcotada = new ArrayList<>();
        Iterator<Registro> it = base.iterator();
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
