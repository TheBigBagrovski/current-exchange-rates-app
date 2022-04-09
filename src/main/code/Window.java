package code;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Window {

    @FXML
    private Label eur_data;

    @FXML
    private Label usd_data;

    @FXML
    private Label yndx_data;

    @FXML
    private Label sber_data;

    @FXML
    private Label ogzpy_data;

    @FXML
    private Label aapl_data;

    public void update() throws InterruptedException {
        Main.updateData();
        Thread.sleep(2000);
        usd_data.setText(Main.getData().get("USD"));
        eur_data.setText(Main.getData().get("EUR"));
        yndx_data.setText(Main.getData().get("YNDX"));
        sber_data.setText(Main.getData().get("SBER"));
        ogzpy_data.setText(Main.getData().get("OGZPY"));
        aapl_data.setText(Main.getData().get("AAPL"));
    }

}
