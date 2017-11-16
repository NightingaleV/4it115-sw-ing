package UI;

import utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import logika.Prostor;
import main.Main;

/**
 * Třída ObsahBatohu tvoří grafické zpracování inventáře (obrázky předmětů,
 * které má postava u sebe) pomocí návrhového vzoru Observer. K aktualizaci
 * dochází po přidání/odebrání předmětu.
 *
 * @author     Marek Bernard
 * @version LS 2017
 */
public class ObrazkovyInventar extends AnchorPane implements Observer {

    public IHra hra;

    /**
     * Slouží pro inicializaci Observeru
     *
     * @param hra aktualní hra
     */
    public ObrazkovyInventar(IHra hra) {
        this.hra = hra;
        hra.getBatoh().registerObserver(this);
        //init();
        update();
    }

    /**
     * Slouží pro resetování stavu po zavolání nové hry. Dojde k vymazání všech
     * zobrazených předmětů, které měla postava u sebe
     *
     * @param hra aktuální hra
     */
    public void novaHra(IHra hra) {
        this.hra.getBatoh().deleteObserver(this);
        this.hra = hra;
        hra.getBatoh().registerObserver(this);
        update();
    }


    /**
     * Slouží pro vykreslení obrázku přemětů, které má postava v inventáři.
     * Předměty jsou vykreslovány do pole 3x2.
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
        }

    }
    /**
     * Slouží pro nastavení šířky
     */
    private void init() {
        this.setPrefWidth(200);
    }
}