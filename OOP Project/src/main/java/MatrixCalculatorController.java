import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MatrixCalculatorController {

    private int[] matrix;

    @FXML private ChoiceBox<?> additionRows;
    @FXML private ChoiceBox<?> additionColumns;
    @FXML private Button additionSetMatricesButton;
    @FXML private ChoiceBox<?> multiplicationARows;
    @FXML private ChoiceBox<?> multiplicationBRows;
    @FXML private ChoiceBox<?> multiplicationAColumns;
    @FXML private ChoiceBox<?> multiplicationBColumns;
    @FXML private Button multiplicationSetMatricesButton;
    @FXML private ChoiceBox<?> transposeRows;
    @FXML private ChoiceBox<?> transposeColumns;
    @FXML private Button transposeSetMatrixButton;
    @FXML private Label determinantResult;
    @FXML private ChoiceBox<?> determinantDimension;
    @FXML private Button determinantSetSquareMatrixButton;
    @FXML private ChoiceBox<?> inverseDimension;
    @FXML private Button inverseSetSquareMatrixButton;
    @FXML private ChoiceBox<?> gaussRows;
    @FXML private ChoiceBox<?> gaussColumns;
    @FXML private Button gaussSetMatrixButton;

    @FXML
    void additionSetMatricesButtonPressed(ActionEvent event) {

    }

    @FXML
    void multiplicationSetMatricesButtonPressed(ActionEvent event) {

    }

    @FXML
    void determinantSetSquareMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getDeterminantDimension(), getDeterminantDimension());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();

    }

    @FXML
    void gaussSetMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getGaussRows(), getGaussColumns());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();

    }

    @FXML
    void inverseSetSquareMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getInverseDimension(), getInverseDimension());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();


    }

    @FXML
    void transposeSetMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getTransposeRows(), getTransposeColumns());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();

    }

    void setSingleMatrixAction(SingleMatrixApp set){
        try {
            Stage stage = new Stage();
            set.start(stage);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private int getAdditionRows(){
        return (int) additionRows.getValue();
    }

    private int getAdditionColumns(){
        return (int) additionColumns.getValue();
    }

    private int getMultiplicationARows(){
        return (int) multiplicationARows.getValue();
    }

    private int getMultiplicationAColumns(){
        return (int) multiplicationAColumns.getValue();
    }

    private int getMultiplicationBColumns(){
        return (int) multiplicationBColumns.getValue();
    }

    private int getTransposeRows(){
        return (int) transposeRows.getValue();
    }

    private int getTransposeColumns(){
        return (int) transposeColumns.getValue();
    }

    private int getDeterminantDimension(){
        return (int) determinantDimension.getValue();
    }

    private int getInverseDimension(){
        return (int) inverseDimension.getValue();
    }

    private int getGaussRows(){
        return (int) gaussRows.getValue();
    }

    private int getGaussColumns(){
        return (int) gaussColumns.getValue();
    }
}

