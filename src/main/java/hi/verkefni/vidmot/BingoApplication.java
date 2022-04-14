package hi.verkefni.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BingoApplication extends Application {
    // static BingoController bingoController;

    /**
     * Aðalnotendaviðmót lesið inn, titillinn settur og glugginn birtur
     *
     * @param stage aðalglugginn
     * @throws IOException ef bingo-view.fxml skráin er ekki til
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                BingoApplication.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Bingo");
        stage.setScene(scene);
        stage.show();   // birta gluggann
    }

    public static void main(String[] args) {
        launch();
    }
}
