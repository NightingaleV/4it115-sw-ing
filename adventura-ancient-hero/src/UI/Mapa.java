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
    private Circle tecka;

    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    private void init() {

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),400,200,true,true));

        tecka = new Circle(30, Paint.valueOf("red"));

//        this.setTopAnchor(tecka, 0.0);
//        this.setLeftAnchor(tecka, 0.0);

        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }



    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosY());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosX());
    }
}
