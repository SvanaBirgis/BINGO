package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Frá start-view.fxml að info-view.fxml
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToSceneInfo(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("info-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("Leiðbeiningar!!!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Frá info-view.fxml/valSpjold-view.fxml á start-view.fxml.
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToSceneStart(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("Start");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Frá start-view.fxml á valSpjold-view.fxml.
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToSceneValSpjold(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("valSpjold-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("Velja fjölda spjalda!!!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Frá valSpjold-view.fxml á bingo-view.fxml.
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToSceneBingo1(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bingo-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("1 spjald");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Frá valSpjold-view.fxml á bingo2-view.fxml.
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToSceneBingo2(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bingo2-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("2 spjöld");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Frá valSpjold-view.fxml á bingo3-view.fxml.
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToSceneBingo3(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bingo3-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("3 spjöld");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Frá valSpjold-view.fxml á bingo4-view.fxml.
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToSceneBingo4(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bingo4-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("4 spjöld");
        stage.setScene(scene);
        stage.show();
    }
}
