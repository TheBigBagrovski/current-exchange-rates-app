package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends Application {
    public static final String DOLLAR_RUB_URL = "https://www.google.com/search?q=dollar+ruble+exchange+rate";
    public static final String EURO_RUB_URL = "https://www.google.com/search?q=euro+ruble+exchange+rate";
    public static final String YANDEX_STOCK_PRICE_URL = "https://www.google.com/search?q=yandex+stock+price";
    public static final String SBER_STOCK_PRICE_URL = "https://www.google.com/search?q=sber+stock+price";
    public static final String GAZPROM_STOCK_PRICE_URL = "https://www.google.com/search?q=gazprom+stock+price";
    public static final String APPLE_STOCK_PRICE_URL = "https://www.google.com/search?q=aapl+stock+price";

    private static Document doc;
    private static final Map<String, String> data = new HashMap<>() {{
        put("USD", "");
        put("EUR", "");
        put("YNDX", "");
        put("SBER", "");
        put("OGZPY", "");
        put("AAPL", "");
    }};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Current exchange rates");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/window.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Map<String, String> getData() {
        return data;
    }

    public static void updateData() {
        getInfo(DOLLAR_RUB_URL);
        getInfo(EURO_RUB_URL);
        getInfo(YANDEX_STOCK_PRICE_URL);
        getInfo(SBER_STOCK_PRICE_URL);
        getInfo(GAZPROM_STOCK_PRICE_URL);
        getInfo(APPLE_STOCK_PRICE_URL);
    }

    private static void getInfo(String url) {
        Runnable runnable = () -> {
            try {
                doc = Jsoup.connect(url).get();
                switch (url) {
                    case DOLLAR_RUB_URL -> data.put("USD", doc.select("span.DFlfde.SwHCTb").text());
                    case EURO_RUB_URL -> data.put("EUR", doc.select("span.DFlfde.SwHCTb").text());
                    case YANDEX_STOCK_PRICE_URL -> data.put("YNDX", doc.select("span.IsqQVc.NprOob.wT3VGc").text());
                    case SBER_STOCK_PRICE_URL -> data.put("SBER", doc.select("span.IsqQVc.NprOob.wT3VGc").text());
                    case GAZPROM_STOCK_PRICE_URL -> data.put("OGZPY", doc.select("span.IsqQVc.NprOob.wT3VGc").text());
                    case APPLE_STOCK_PRICE_URL -> data.put("AAPL", doc.select("span.IsqQVc.NprOob.wT3VGc").text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        // to get a page with a given url use a secondary thread instead of main
        Thread secThread = new Thread(runnable);
        secThread.start();
    }
}
