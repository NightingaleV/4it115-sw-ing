package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *
 * @author xzenj02
 */
public class MenuPole extends MenuBar{
    private Main main;
    private Stage stage;

    public MenuPole(Main main, Stage stage){
        this.main = main;
        this.stage = stage;
        init();
    }

    private void init(){
        Menu menuSoubor = new Menu("Advenura");

        MenuItem itemNovaHra = new MenuItem("Nová hra");
//        MenuItem itemNovaHra = new MenuItem(new Image("Nová hra", new ImageView(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        itemNovaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        Menu menuHelp = new Menu("Help");
        MenuItem itemOProgramu = new MenuItem("O programu");
        MenuItem itemNapoveda = new MenuItem("Napoveda");

        MenuItem itemKonec = new MenuItem("Konec");
        menuHelp.getItems().addAll(itemOProgramu, itemNapoveda);

        menuSoubor.getItems().addAll(itemNovaHra, itemKonec);

        this.getMenus().addAll(menuSoubor, menuHelp);


        itemOProgramu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("O programu");
                alert.setHeaderText("Ancient Hero Adventura");
                alert.setContentText("Slavik , 2017");
                alert.initOwner(main.getPrimaryStage());

                alert.showAndWait();
            }
        });

        itemNapoveda.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                WebView webview = new WebView();

                webview.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());

                stage.setScene(new Scene(webview, 600, 960));
                stage.show();
            }
        });

        itemKonec.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        itemNovaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                main.start(stage);
            }
        });

    }

}