package UI;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import logika.IHra;
import logika.Prostor;

public class Vychody extends ListView implements Observer{

    public IHra hra;
    public ObservableList<String> dataVychodu;
    private Label vychodLabel;

    public Vychody(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
        update();
    }


    @Override
    public void update(){
        dataVychodu.clear();
        for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()){
            dataVychodu.add(prostor.getNazev());
        }
        this.vychodLabel = new Label("Východy:");
        getVychodLabel().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getVychodLabel().setPrefWidth(200);
    }

    private void init(){
        dataVychodu = FXCollections.observableArrayList();
        this.setItems(dataVychodu);
        this.setPrefSize(200, 200);

    }
    public ListView<String> getSeznamVychodu() {
        return this;
    }
    public Label getVychodLabel() {
        return vychodLabel;
    }
}