package hi.verkefni.vidmot;
/* ****************************************************************************
 Nafn    : Svana Björg Birgisdótiir
 T-póstur: sbb51@hi.is

 Lýsing  : Stýriklasi fyrir Bingó forritið. Hefur samskipti við vinnsluna
 Býr til nýtt bingóspjald. Hefur atburðahandler fyrir hnappana á bingó
 spjaldinu

 ****************************************************************************** */

import hi.verkefni.vinnsla.Bingospjald;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BingoController implements Initializable {

    // fastar
    private static final int LINUR = 5; // fjöldi lína
    private static final int DALKAR = 5; // fjöldi dálka á spjaldi

    // vinnsla
    private Bingospjald bingo; // heldur utan um hvada bingospjald takki tilheyrir
    private final Bingospjald bingo1 = new Bingospjald(LINUR, DALKAR); // bingóspjald
    private final Bingospjald bingo2 = new Bingospjald(LINUR, DALKAR); // bingóspjald
    private final Bingospjald bingo3 = new Bingospjald(LINUR, DALKAR); // bingóspjald
    private final Bingospjald bingo4 = new Bingospjald(LINUR, DALKAR); // bingóspjald


    // viðmótshlutir - samsvarandi breytur í .fxml skránni
    @FXML
    private Label fxBingoVinningur; // segir til hvort það er komið bingó
    @FXML
    private GridPane fxGridOne;    // spjald 1
    @FXML
    private GridPane fxGridTwo;    // spjald 2
    @FXML
    private GridPane fxGridThree;    // spjald 3
    @FXML
    private GridPane fxGridFour;    // spjald 4

    @FXML
    private Label fxRandom1;    // Random tala
    @FXML
    private Label fxRandom2;    // Næsti staður fyrir random tölu
    @FXML
    private Label fxRandom3;    // Næsti staður fyrir random tölu
    @FXML
    private Label fxRandom4;    // Næsti staður fyrir random tölu
    @FXML
    private Label fxRandom5;    // Loka staður fyrir random tölu

    private List<Integer> DoneTolur;

    private List<Integer> LastTolur;

    private Stage stage;
    private Scene scene;
    private Parent root;


    private Timeline t;


    /**
     * Frumstillir viðmótið fyrir bingóspjald með nýju bingóspjaldi sem hefur tölur af
     * handahófi frá 1 til 75
     *
     * @param url            ónotað
     * @param resourceBundle ónotað
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DoneTolur = new ArrayList<>();
        LastTolur = new ArrayList<>(Arrays.asList(-1, -1, -1, -1, -1));
        int[][] tolur = bingo1.nyttSpjald();    // ná í random tölur úr vinnslu
        for (int i = 0; i < tolur.length; i++) {
            for (int j = 0; j < tolur[0].length; j++) {
                if (i == 2 && j == 2) {
                    continue;
                }
                // vörpun gagna úr vinnslu í viðmótið
                ((Button) fxGridOne.getChildren().
                        get(DALKAR * (i + 1) + j)).setText(tolur[i][j] + "");
            }
        }
        // ef spilað er á einu spjaldi þá er hætt
        if (fxGridTwo == null) return;

        int[][] tolur2 = bingo2.nyttSpjald();    // ná í random tölur úr vinnslu
        for (int i = 0; i < tolur2.length; i++) {
            for (int j = 0; j < tolur2[0].length; j++) {
                if (i == 2 && j == 2) {
                    continue;
                }
                // vörpun gagna úr vinnslu í viðmótið
                ((Button) fxGridTwo.getChildren().
                        get(DALKAR * (i + 1) + j)).setText(tolur2[i][j] + "");
            }
        }
        // ef spilað er á tveimur spjöldum þá er hætt
        if (fxGridThree == null) return;

        int[][] tolur3 = bingo3.nyttSpjald();    // ná í random tölur úr vinnslu
        for (int i = 0; i < tolur3.length; i++) {
            for (int j = 0; j < tolur3[0].length; j++) {
                if (i == 2 && j == 2) {
                    continue;
                }
                // vörpun gagna úr vinnslu í viðmótið
                ((Button) fxGridThree.getChildren().
                        get(DALKAR * (i + 1) + j)).setText(tolur3[i][j] + "");
            }
        }
        // ef spilað er á þremur spjöldum þá er hætt
        if (fxGridFour == null) return;

        int[][] tolur4 = bingo4.nyttSpjald();    // ná í random tölur úr vinnslu
        for (int i = 0; i < tolur4.length; i++) {
            for (int j = 0; j < tolur4[0].length; j++) {
                if (i == 2 && j == 2) {
                    continue;
                }
                // vörpun gagna úr vinnslu í viðmótið
                ((Button) fxGridFour.getChildren().
                        get(DALKAR * (i + 1) + j)).setText(tolur4[i][j] + "");
            }
        }
    }


    /**
     * Atburðahandler fyrir að velja reit á bingóspjaldi. Hnappur gerður óvirkur, lögun hans
     * breytt í hring. Ef komið er bingó þá er viðeigandi texti birtur
     *
     * @param actionEvent atburðurinn frá hnappnum
     */
    public void bingoHandler(ActionEvent actionEvent) {

        Button b = ((Button) actionEvent.getSource()); // hvaða hnapp var ýtt á
        String gridId = b.getParent().getId(); // grid sem takki tilheyrir
        switch (gridId) {
            case "fxGridOne":
                bingo = bingo1;
                break;
            case "fxGridTwo":
                bingo = bingo2;
                break;
            case "fxGridThree":
                bingo = bingo3;
                break;
            case "fxGridFour":
                bingo = bingo4;
                break;
            default:
                bingo = bingo1;
        }

        int i = GridPane.getRowIndex(b) - 1;   // hvar er hnappurinn í grid-inu
        int j = GridPane.getColumnIndex(b);
        bingo.aReit(i, j);                  // merkja við á spjaldinu að talan hafi verið lesin
        Circle c = new Circle(b.getWidth() / 2); // breyta lögun hnappsins og bakgrunnur
        b.setShape(c);
        b.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        if (bingo.erBingo())                // er komið bingó
            fxBingoVinningur.setText("B I N G O!!!");
        b.setDisable(true);                 // hnappur gerður óvirkur
    }

    /**
     * @param tala tala sem er komin
     * @return skilar true ef talan er í DoneTolur, annars false
     */
    private boolean contains(int tala) {
        if (DoneTolur.contains(tala)) {
            return true;
        }
        DoneTolur.add(tala);
        return false;
    }


    /**
     * Atburðarhandler fyrir að draga nýja tölu, færa hana niður línuna og merkja hana á spjaldinu.
     *
     * @param actionEvent atburðurinn frá hnappnum
     */
    public void ByrjaLeikHandler(ActionEvent actionEvent) {
        Button b = ((Button) actionEvent.getSource());
        Scene scene = b.getScene();
        fxBingoVinningur.setText("Rugla tölum...");
        KeyFrame j = new KeyFrame(Duration.millis(5000), i -> {
            fxBingoVinningur.setText("");
        });
        t = new Timeline(j);
        t.setCycleCount(1);
        t.play();

        KeyFrame k = new KeyFrame(Duration.millis(5000), i -> {
            Random rand = new Random();
            int n = rand.nextInt(75) + 1;   // Random tala á bilinu 1-75.
            while (contains(n)) {
                n = rand.nextInt(75) + 1;
            }
            GridPane gridPane = (GridPane) scene.lookup("#fxAllarTolur");
            ObservableList<javafx.scene.Node> labelNodes = gridPane.getChildren();
            for (Node node : labelNodes) {
                Label label = (Label) node;
                String labelValue = label.getText();

                try {
                    if (Integer.parseInt(labelValue) == n) {
                        label.setTextFill(Color.color(1, 0, 0));
                    }
                } catch (NumberFormatException e) {
                    // höfum eitt af 'B' 'I' 'N' 'G' 'O'
                }
            }


            LastTolur.remove(0);
            LastTolur.add(n);


            if (LastTolur.get(4) != -1) {
                fxRandom1.setText(numberToBingo(LastTolur.get(4)));
            }
            if (LastTolur.get(3) != -1) {
                fxRandom2.setText(numberToBingo(LastTolur.get(3)));
            }
            if (LastTolur.get(2) != -1) {
                fxRandom3.setText(numberToBingo(LastTolur.get(2)));
            }
            if (LastTolur.get(1) != -1) {
                fxRandom4.setText(numberToBingo(LastTolur.get(1)));
            }
            if (LastTolur.get(0) != -1) {
                fxRandom5.setText(numberToBingo(LastTolur.get(0)));
            }
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);

        t.play();

        b.setDisable(true);

    }

    /**
     * Atburðar handler sem hreinsar spjaldið ef ýtt er á takkann hreinsa.
     *
     * @param actionEvent atburðurinn frá hnappnum
     */
    public void hreinsaHandler(ActionEvent actionEvent) {
        Button h = ((Button) actionEvent.getSource()); // hvaða hnapp var ýtt á
        String gridId = h.getParent().getId(); // grid sem takki tilheyrir
        switch (gridId) {
            case "fxGridOne":
                resetGrid(fxGridOne, bingo1);
                break;
            case "fxGridTwo":
                resetGrid(fxGridTwo, bingo2);
                break;
            case "fxGridThree":
                resetGrid(fxGridThree, bingo3);
                break;
            case "fxGridFour":
                resetGrid(fxGridFour, bingo4);
                break;
            default:
                break;
        }
    }

    /**
     * Til að fara til baka að á skjáinn til að velja fjölda spjalda.
     *
     * @param actionEvent atburðurinn frá hnappnum
     * @throws IOException
     */
    public void switchToValSpjold2(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("valSpjold-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("Velja fjölda spjalda!!!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param fxGrid Bingo spjöldin
     * @param bingo  bingospjaldið
     */
    public void resetGrid(GridPane fxGrid, Bingospjald bingo) {
        fxBingoVinningur.setText("");
        List<Node> takkar = fxGrid.getChildren();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button takki = ((Button) takkar.
                        get((i + 1) * 5 + j));
                if (bingo.getAReit(i, j)) {
                    Rectangle r = new Rectangle(takki.getWidth() * 2, takki.getHeight());
                    takki.setShape(r);
                    takki.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                    takki.setDisable(false);
                }
            }
        }
        bingo.resetSpjald();
    }

    /**
     * @param n Talan sem er dregin
     * @return Viðeigandi staf og tölu
     */
    private String numberToBingo(int n) {
        if (n <= 15) {
            return "B" + n;
        } else if (n <= 30) {
            return "I" + n;
        } else if (n <= 45) {
            return "N" + n;
        } else if (n <= 60) {
            return "G" + n;
        } else {
            return "O" + n;
        }
    }


}
