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

import java.util.ArrayList;

public class SingleMatrixApp extends Application {
    private final int rows;
    private final int cols;
    private int[] entries;
    private Label emptyMessage = new Label("");
    ArrayList<TextField> fields = new ArrayList<>();

    public SingleMatrixApp(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane grid = new GridPane();
        VBox box = new VBox();
        Label label = new Label("Fill in your Matrix: ");
        Button button = new Button("Continue");

        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(12));
        box.setSpacing(8);

        label.setFont(new Font("Century Gothic", 14));

        grid.setHgap(5);
        grid.setVgap(2);
        grid.getChildren().clear();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TextField temp = new TextField();
                temp.setPrefWidth(50);
                temp.setId("entry"+i+"-"+j);
                fields.add(temp);
                grid.add(temp, j, i);
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

        box.getChildren().addAll(label, grid, button, emptyMessage);

        Scene scene = new Scene(box);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Creating Matrix");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    int[] getSingleMatrix(){
        return entries;
    }


    boolean buttonPressed(){
        entries = new int[rows*cols];

        for (int i = 0; i < rows*cols; i++) {
            try{
                entries[i] = Integer.parseInt(fields.get(i).getText());
            }
            catch (NumberFormatException ex){
                fields.get(i).requestFocus();
                emptyMessage.setText("Fill in all cells");
                return false;
            }
        }
        return true;
    }
}