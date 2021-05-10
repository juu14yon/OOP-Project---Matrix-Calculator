import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MatrixCalculatorController {

    private int[] matrix;
    private int[] matrixA;
    private int[] matrixB;

    @FXML private ChoiceBox<?> additionRows;
    @FXML private ChoiceBox<?> additionColumns;
    @FXML private Button additionSetMatricesButton;
    @FXML private GridPane additionResultGrid;

    @FXML private ChoiceBox<?> multiplicationARows;
    @FXML private ChoiceBox<?> multiplicationBRows;
    @FXML private ChoiceBox<?> multiplicationAColumns;
    @FXML private ChoiceBox<?> multiplicationBColumns;
    @FXML private Button multiplicationSetMatricesButton;
    @FXML private Label multiplicationErrorMessage;
    @FXML private GridPane multiplicationResultGrid;

    @FXML private ChoiceBox<?> transposeRows;
    @FXML private ChoiceBox<?> transposeColumns;
    @FXML private Button transposeSetMatrixButton;
    @FXML private GridPane transposeResultGrid;

    @FXML private Label determinantResult;
    @FXML private ChoiceBox<?> determinantDimension;
    @FXML private Button determinantSetSquareMatrixButton;

    @FXML private ChoiceBox<?> inverseDimension;
    @FXML private Button inverseSetSquareMatrixButton;
    @FXML private GridPane inverseResultGrid;

    @FXML private ChoiceBox<?> gaussRows;
    @FXML private ChoiceBox<?> gaussColumns;
    @FXML private Button gaussSetMatrixButton;
    @FXML private GridPane gaussResultGrid;

    @FXML
    void additionSetMatricesButtonPressed(ActionEvent event) {
        TwoMatricesApp setting = new TwoMatricesApp(getAdditionRows(), getAdditionColumns(), getAdditionRows(), getAdditionColumns());
        setTwoMatricesAction(setting);

        matrixA = setting.getMatrixA();
        matrixB = setting.getMatrixB();

        int[] additionResult = new int[getAdditionColumns()*getAdditionRows()];

        for (int i = 0; i < getAdditionRows(); i++) {
            for (int j = 0; j < getAdditionColumns(); j++) {
                additionResult[i*getAdditionColumns()+j] = matrixA[i*getAdditionColumns()+j] + matrixB[i*getAdditionColumns()+j];
                TextField temp = new TextField(""+additionResult[i*getAdditionColumns()+j]);
                temp.setEditable(false);
                temp.setFocusTraversable(false);
                temp.setAlignment(Pos.CENTER);
                temp.setPrefWidth(30);
                additionResultGrid.add(temp, j, i);
            }
        }
    }

    @FXML
    void multiplicationSetMatricesButtonPressed(ActionEvent event) {
        if(getMultiplicationAColumns()!=Integer.parseInt(multiplicationBRows.getValue().toString())){
            multiplicationBRows.requestFocus();
            multiplicationErrorMessage.setText("dim(columns) of A must be equal to dim(rows) of B");
        } else{
            multiplicationErrorMessage.setText("");

            TwoMatricesApp setting = new TwoMatricesApp(getMultiplicationARows(), getMultiplicationAColumns(), getMultiplicationAColumns(), getMultiplicationBColumns());
            setTwoMatricesAction(setting);

            matrixA = setting.getMatrixA();
            matrixB = setting.getMatrixB();
        }
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

    void setTwoMatricesAction(TwoMatricesApp set){
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

