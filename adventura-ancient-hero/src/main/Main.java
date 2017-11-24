/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import UI.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import logika.Prostor;
import uiText.TextoveRozhrani;

/**
 *
 * @author xzenj02
 */
public class Main extends Application {

    private TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextArea;
    private Mapa mapa;
    private MenuPole menu;
    private Stage primaryStage;
    private Vychody vychody;
    private ObsahBatohu obsahBatohu;
    private VeciProstor veciProstor;
    public HerniPlan plan;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this, primaryStage);
        vychody = new Vychody(hra);
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 20, 10, 20));
        // Text s prubehem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        centralText.setFont(Font.font("Arial", 16));
        borderPane.setCenter(centralText);

        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("Zadej prikaz");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                zadejPrikazTextArea.setText("");

                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });

        //inicializace inventare + veci v prostoru, nutne umistit za konstruktor centralTextu
        obsahBatohu = new ObsahBatohu(hra.getHerniPlan(), centralText,hra);
        veciProstor = new VeciProstor(hra,centralText);
        //panel s mapou a inventarem
        FlowPane levyPanel = new FlowPane();
        levyPanel.setAlignment(Pos.TOP_LEFT);
        levyPanel.setMaxWidth(200);
        levyPanel.getChildren().addAll(vychody.getVychodLabel(), vychody);
        levyPanel.getChildren().addAll(veciProstor.getVecLabel(),veciProstor);

        borderPane.setLeft(levyPanel);


        // klikaci vychody
        vychody.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String jmenoVychodu = vychody.getSeznamVychodu().getSelectionModel().getSelectedItem();
                String vytvorenyPrikaz = "jdi " + jmenoVychodu;
                String odpovedNaVytvorenyPrikaz = hra.zpracujPrikaz(vytvorenyPrikaz);
                appendCentralText(odpovedNaVytvorenyPrikaz);
            }
        });

        FlowPane pravyPanel = new FlowPane();
        pravyPanel.setAlignment(Pos.TOP_RIGHT);
        pravyPanel.getChildren().add(mapa);
        pravyPanel.getChildren().add(obsahBatohu);
        borderPane.setRight(pravyPanel);
        

        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextArea);


        //menu adventury
        borderPane.setTop(menu);
        borderPane.setBottom(dolniLista);
        Scene scene = new Scene(borderPane, 1260, 760);
        primaryStage.setTitle("Adventura Ancient Hero");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();



    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }


    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public void appendCentralText(String vstupniPrikaz) {
        this.getCentralText().appendText("\n" + vstupniPrikaz + "\n");
    }
    public TextArea getCentralText() {
        return centralText;
    }

}
