package UI;

import javafx.scene.layout.AnchorPane;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

public class VeciProstor extends VBox implements Observer {

    private IHra hra;
    private Map<String, Vec> veciProstoru;
    private Button tlacitkoVeci;
    private Label vecLabel;
    private TextArea centralText;
    /**
     * kontruktor
     * @param hra -
     * @param text - vypisuje text v centru adventury
     */
    public VeciProstor(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;

        init();
    }

    private void init() {
        vecLabel = new Label("Věci v prostoru:");

        getVecLabel().setFont(Font.font("Arial", FontWeight.BOLD, 18));
        getVecLabel().setPrefWidth(200);
        veciProstoru = hra.getHerniPlan().getAktualniProstor().getVeci();
        /**
         * tlacitka pridavajici itemy do inventare
         */
        this.getChildren().clear();

        for (String vec : veciProstoru.keySet()) {
            Vec item = veciProstoru.get(vec);
            tlacitkoVeci = new Button(item.getNazev());
            tlacitkoVeci.setMinSize(100,50);
            tlacitkoVeci.setPadding(new Insets(5, 5, 5, 5));
            tlacitkoVeci.setStyle("-fx-font: 18 arial;");

            this.getChildren().add(getTlacitkoVeci());

            tlacitkoVeci.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    String vstupniPrikaz = "vezmi " + item.getNazev();
                    String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");

                    hra.getHerniPlan().notifyAllObservers();
                }
            });

        }
    }
    @Override
    public void update() {
        this.getChildren().clear();
        veciProstoru = hra.getHerniPlan().getAktualniProstor().getVeci();

        for (String vec : veciProstoru.keySet()) {
            try {
                Vec item = veciProstoru.get(vec);
                tlacitkoVeci = new Button(item.getNazev());
                tlacitkoVeci.setMinSize(100,50);
                tlacitkoVeci.setStyle("-fx-font: 18 arial;");

                this.getChildren().add(getTlacitkoVeci());
                tlacitkoVeci.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent click) {
                        /**
                         * Pozor na záměnu pomocna.getNazev() a tlacitkoVeci.getText()!!
                         * EventHandler pak sebere špatný item
                         */
                        String vstupniPrikaz = "vezmi " + item.getNazev();
                        String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                        /**
                         * přidání odpovědí hry do centralTextu, jako u Inventáře
                         */
                        centralText.appendText("\n" + vstupniPrikaz + "\n");
                        centralText.appendText("\n" + odpovedHry + "\n");

                        hra.getHerniPlan().notifyAllObservers();

                    }
                });
            } catch (Exception exception) {
            }
        }
    }
    /**
     * viz východy
     * @param novaHra -
     */

    /**
     * Aktualizuje věci v místnosti Buttony s obrázkem a popiskem
     */

    /**
     * @return the vecLabel
     */
    public Label getVecLabel() {
        return vecLabel;
    }

    /**
     * @return the tlacitkoVeci
     */
    public Button getTlacitkoVeci() {
        return tlacitkoVeci;
    }
}
