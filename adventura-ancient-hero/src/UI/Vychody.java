package UI;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import logika.IHra;
import logika.Prostor;

public class Vychody extends ListView implements Observer{

    public IHra hra;
    public ObservableList<String> dataVychodu;

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
    }

    private void init(){
        dataVychodu = FXCollections.observableArrayList();
        this.setItems(dataVychodu);
        this.setPrefSize(150, 200);
    }
    public ListView<String> getSeznamVychodu() {
        return this;
    }
}