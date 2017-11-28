package UI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.Postava;
import logika.Prostor;
import utils.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import javafx.scene.control.TextArea;
import logika.HerniPlan;

import main.Main;

import java.util.HashSet;
import java.util.Set;

/**
 * Graficke zpracovani inventare. Ten take na klik vyhazuje predmety.
 *
 * @author  Slavik
 * @version ZM 2017
 */
public class PanelPostav extends AnchorPane implements Observer {

    public  IHra hra;
    private HerniPlan plan;
    private Label postavyLabel;
    private TextArea centralText;
    public String chimera;
    public String hydra;
    public String harpyje;


    /**
     * Konstruktor inverntare + inicialuzace observeru
     *
     * @param hra aktualni hra
     */
    public PanelPostav(HerniPlan plan, TextArea text, IHra hra) {
        this.hra = hra;
        this.plan = plan;
        chimera = "chimera";
        hydra = "hydra";
        harpyje = "harpyje";
        hra.getHerniPlan().registerObserver(this);
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
        postavyLabel = new Label("Postavy:");
        getPostavyLabel().setFont(Font.font("Arial", FontWeight.BOLD, 18));
        getPostavyLabel().setPrefWidth(600);
        int poziceX = 0;
        int poziceY = 0;
        for (String u : hra.getHerniPlan().getAktualniProstor().getPostavy()) {
            ImageView obrazkyPostav = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/postavy/" + u + ".jpg"), 300, 300, true, false));

            this.getChildren().add(obrazkyPostav);
            if (poziceX == 600) {
                poziceY = 200;
                poziceX = 0;
            }
            obrazkyPostav.setLayoutX(poziceX);
            obrazkyPostav.setLayoutY(poziceY);
            poziceX += 300;
            obrazkyPostav.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(u == chimera || u == hydra || u == harpyje){
                        String prikazHry = "bojuj " + u;
                        String odpovedHry = hra.zpracujPrikaz(prikazHry);

                        centralText.appendText("\n" + prikazHry + "\n");
                        centralText.appendText("\n" + odpovedHry + "\n");
                    }
                    else{
                        /**
                         * Event kliknuti na predmet:
                         * vyhodi predmet, vrati text do hry
                         */
                        String prikazHry = "mluv " + u;
                        String odpovedHry = hra.zpracujPrikaz(prikazHry);

                        centralText.appendText("\n" + prikazHry + "\n");
                        centralText.appendText("\n" + odpovedHry + "\n");
                    }
                }
            });
        }


    }
    public Label getPostavyLabel() {
        return postavyLabel;
    }


}