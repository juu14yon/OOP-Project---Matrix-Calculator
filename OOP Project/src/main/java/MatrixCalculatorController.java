import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class MatrixCalculatorController {

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
    void determinantSetSquareMatrixButtonPressed(ActionEvent event) {
        setSingleMatrixAction(new SingleMatrixApp(getDeterminantDimension(), getDeterminantDimension()));
    }

    @FXML
    void gaussSetMatrixButtonPressed(ActionEvent event) {
        setSingleMatrixAction(new SingleMatrixApp(getGaussRows(), getGaussColumns()));
    }

    @FXML
    void inverseSetSquareMatrixButtonPressed(ActionEvent event) {
        setSingleMatrixAction(new SingleMatrixApp(getInverseDimension(), getInverseDimension()));

    }

    @FXML
    void multiplicationSetMatricesButtonPressed(ActionEvent event) {

    }

    @FXML
    void transposeSetMatrixButtonPressed(ActionEvent event) {
        setSingleMatrixAction(new SingleMatrixApp(getTransposeRows(), getTransposeColumns()));
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

