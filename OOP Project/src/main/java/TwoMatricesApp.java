import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class TwoMatricesApp extends Application {
    private boolean stageCanceled = false;
    private final int aRows;
    private final int aCols;
    private final int bRows;
    private final int bCols;
    private double[] aEntries;
    private double[] bEntries;
    private Label emptyMessage = new Label("");
    ArrayList<TextField> aFields = new ArrayList<>();
    ArrayList<TextField> bFields = new ArrayList<>();

    public TwoMatricesApp(int aRows, int aCols, int bRows, int bCols) {
        this.aRows = aRows;
        this.aCols = aCols;
        this.bRows = bRows;
        this.bCols = bCols;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane aGrid = new GridPane();
        GridPane bGrid = new GridPane();
        VBox box = new VBox();
        Label aLabel = new Label("Fill in your Matrix A: ");
        Label bLabel = new Label("Fill in your Matrix B: ");
        Button button = new Button("Continue");
        Button cancelButton = new Button("Close");

        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(12));
        box.setSpacing(8);
        box.setStyle("-fx-font-family: Verdana Pro Light;");

        aLabel.setFont(new Font("Verdana Pro Light", 14));
        bLabel.setFont(new Font("Verdana Pro Light", 14));
        emptyMessage.setFont(new Font("Verdana Pro Light", 14));
        emptyMessage.setStyle("-fx-text-fill:#c30a0a");

        aGrid.setHgap(5);
        aGrid.setVgap(2);
        aGrid.getChildren().clear();

        bGrid.setHgap(5);
        bGrid.setVgap(2);
        bGrid.getChildren().clear();

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aCols; j++) {
                TextField temp = new TextField();
                temp.setPrefWidth(50);
                temp.setId("a_Entry"+i+"-"+j);
                aFields.add(temp);
                aGrid.add(temp, j, i);
            }
        }

        for (int i = 0; i < bRows; i++) {
            for (int j = 0; j < bCols; j++) {
                TextField temp = new TextField();
                temp.setPrefWidth(50);
                temp.setId("b_Entry"+i+"-"+j);
                bFields.add(temp);
                bGrid.add(temp, j, i);
            }
        }

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(buttonPressed()){
                    primaryStage.close();
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageCanceled = true;
                primaryStage.close();
            }
        });


        box.getChildren().addAll(aLabel, aGrid, bLabel, bGrid, button, emptyMessage, cancelButton);

        Scene scene = new Scene(box);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Creating Matrices");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    double[] getMatrixA(){
        return aEntries;
    }
    double[] getMatrixB() { return bEntries; }


    boolean buttonPressed(){
        aEntries = new double[aRows*aCols];
        bEntries = new double[bRows*bCols];

        for (int i = 0; i < aRows*aCols; i++) {
            try{
                aEntries[i] = Double.parseDouble(aFields.get(i).getText());
            }
            catch (NumberFormatException ex){
                aFields.get(i).requestFocus();
                emptyMessage.setText("Fill in all cells");
                return false;
            }
        }

        for (int i = 0; i < bRows*bCols; i++) {
            try{
                bEntries[i] = Double.parseDouble(bFields.get(i).getText());
            }
            catch (NumberFormatException ex){
                bFields.get(i).requestFocus();
                emptyMessage.setText("Fill in all cells");
                return false;
            }
        }
        return true;
    }

    boolean wasStageCanceled(){
        return stageCanceled;
    }
}

