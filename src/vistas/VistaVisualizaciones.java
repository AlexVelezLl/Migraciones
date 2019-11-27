/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utilities.CONSTANTES;
import utilities.Utilities;

/**
 *
 * @author Katiuska Marín
 */
public class VistaVisualizaciones {
    private Pane root;
    public VistaVisualizaciones(){
        root = new Pane();
        createRoot();
    }
    public Pane getRoot(){
        return root;
    }
    
    private void createRoot(){
        Label lblBienvenida = new Label("Estadistícas");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
        //Lista de gráficos
        ObservableList<String> Gráficos = FXCollections.observableArrayList();
        Gráficos.addAll("Gráfica general", "Gráfico de barras apiladas", "Pie chart");
        ComboBox comboGráficos = new ComboBox(Gráficos);
        
        /**
         * Gráfico Histograma
         */               
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
        
        /**
         * Grafico Pie Chart
         */
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
        
        
        /**
         * Grafico de Líneas
         */
        //Definiendo Ejes X y Y
        NumberAxis EjeX = new NumberAxis(2010, 2020, 10); 
        xAxis.setLabel("Años");  
        NumberAxis EjeY = new NumberAxis(0, 350, 50); 
        yAxis.setLabel("No.De Migrantes");
        
        LineChart linechart = new LineChart(EjeX, EjeY);
        linechart.getData().add(series4);
        Group rootLine = new Group(linechart);

        //Pane
        VBox onRoot = new VBox();
        onRoot.setMinWidth(750);
        HBox hbComobox = new HBox();
        hbComobox.setSpacing(20);
        hbComobox.setAlignment(Pos.CENTER);
        onRoot.setSpacing(20);
        onRoot.setAlignment(Pos.CENTER);
        onRoot.getChildren().add(lblBienvenida);
        onRoot.getChildren().add(comboGráficos);

        //Elegir gráfico
        comboGráficos.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newVal)->{
            if(newVal =="Pie chart"){
                
                hbComobox.getChildren().remove(rootLine);
                hbComobox.getChildren().remove(rootBarras);
                hbComobox.getChildren().add(rootPie);
            }
            if(newVal == "Gráfica general")
            {
                hbComobox.getChildren().remove(rootPie);
                hbComobox.getChildren().remove(rootBarras);
                hbComobox.getChildren().add(rootLine);
            }
            if(newVal == "Gráfico de barras apiladas"){
                hbComobox.getChildren().remove(rootLine); 
                hbComobox.getChildren().remove(rootPie); 

                hbComobox.getChildren().add(rootBarras);
            }           
        });

  
        onRoot.getChildren().add(hbComobox);
        
        StackPane btnSalir = Utilities.boton("ButtonRed");
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        btnSalir.getChildren().add(lblSalir);
        btnSalir.setLayoutX(300);
        btnSalir.setLayoutY(575);
        btnSalir.setOnMouseClicked(e->{
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });
        
        Rectangle rc = new Rectangle(750,650);
        rc.setFill(Color.WHITE);
        root.getChildren().addAll(rc,onRoot, btnSalir);
    }
    
    private void Datos(XYChart.Series<String, Number> series1, XYChart.Series<String, Number> series2, XYChart.Series<String, Number> series3
    , XYChart.Series series){
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
}
