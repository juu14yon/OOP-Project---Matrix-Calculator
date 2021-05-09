import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class MatrixCalculatorController {

    @FXML
    private ChoiceBox<?> additionRows;

    @FXML
    private ChoiceBox<?> additionColumns;

    @FXML
    private Button additionSetMatricesButton;

    @FXML
    private ChoiceBox<?> multiplicationARows;

    @FXML
    private ChoiceBox<?> multiplicationBRows;

    @FXML
    private ChoiceBox<?> multiplicationAColumns;

    @FXML
    private ChoiceBox<?> multiplicationBColumns;

    @FXML
    private Button multiplicationSetMatricesButton;

    @FXML
    private ChoiceBox<?> transposeRows;

    @FXML
    private ChoiceBox<?> transposeColumns;

    @FXML
    private Button transposeSetMatrixButton;

    @FXML
    private ChoiceBox<?> determinantDimension;

    @FXML
    private Button determinantSetSquareMatrixButton;

    @FXML
    private ChoiceBox<?> inverseDimension;

    @FXML
    private Button inverseSetSquareMatrixButton;

    @FXML
    private ChoiceBox<?> gaussRows;

    @FXML
    private ChoiceBox<?> gaussColumns;

    @FXML
    private Button gaussSetMatrixButton;

    @FXML
    void additionSetMatricesButtonPressed(ActionEvent event) {

    }

    @FXML
    void multiplicationSetMatricesButtonPressed(ActionEvent event) {

    }

    @FXML
    void setMatrixButtonPressed(ActionEvent event) {

    }

    @FXML
    void setSquareMatrixButtonPressed(ActionEvent event) {

    }

}
