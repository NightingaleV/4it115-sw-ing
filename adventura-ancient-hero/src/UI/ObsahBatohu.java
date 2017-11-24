package UI;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import utils.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import javafx.scene.control.TextArea;
import logika.HerniPlan;

import main.Main;

/**
 * Graficke zpracovani inventare. Ten take na klik vyhazuje predmety.
 *
 * @author  Slavik
 * @version ZM 2017
 */
public class ObsahBatohu extends AnchorPane implements Observer {

    public  IHra hra;
    private HerniPlan plan;
    private TextArea centralText;

    /**
     * Konstruktor inverntare + inicialuzace observeru
     *
     * @param hra aktualni hra
     */
    public ObsahBatohu(HerniPlan plan, TextArea text, IHra hra) {
        this.hra = hra;
        this.plan = plan;
        hra.getBatoh().registerObserver(this);
        centralText = text;
        update();
    }

    /**
     * Vykresluje obrazky v inventari
     * PoziceX == x, nam umozni zvetsit horizontalne inventar
     */
    @Override
    public void update() {
        this.getChildren().clear();
        int poziceX = 0;
        int poziceY = 0;
        for (String batoh : hra.getBatoh().getObsahBatohu()) {
            ImageView obrazkyPredmetu = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/inventar/" + batoh + ".jpg"), 100, 100, false, false));
            this.getChildren().add(obrazkyPredmetu);

            if (poziceX == 300) {
                poziceY = 100;
                poziceX = 0;
            }
            obrazkyPredmetu.setLayoutX(poziceX);
            obrazkyPredmetu.setLayoutY(poziceY);
            poziceX += 100;
            obrazkyPredmetu.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    /**
                     * Event kliknuti na predmet:
                     * vyhodi predmet, vrati text do hry
                     */
                    String prikazHry = "poloz " + batoh;
                    String odpovedHry = hra.zpracujPrikaz(prikazHry);

                    centralText.appendText("\n" + prikazHry + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");

                    plan.notifyAllObservers();
                }
            });
        }
    }
}