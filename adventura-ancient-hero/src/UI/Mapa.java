package UI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.Hra;
import logika.IHra;
import main.Main;
import utils.Observer;


public class Mapa extends AnchorPane implements Observer {

    private IHra hra;

    ImageView hoplita = new ImageView(new Image(
            Main.class.getResourceAsStream("/zdroje/hoplita.png"),
            50,50,true,true));

    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    private void init() {

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),500,500,true,true));
        this.getChildren().addAll(obrazekImageView, hoplita);
        update();
    }


    @Override
    public void update() {
        this.setTopAnchor(hoplita, hra.getHerniPlan().getAktualniProstor().getPosY());
        this.setLeftAnchor(hoplita, hra.getHerniPlan().getAktualniProstor().getPosX());
    }



}
