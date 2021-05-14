
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

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
    @FXML private Label inverseErrorMessage;

    @FXML private ChoiceBox<?> gaussRows;
    @FXML private ChoiceBox<?> gaussColumns;
    @FXML private Button gaussSetMatrixButton;
    @FXML private GridPane gaussResultGrid;
    @FXML private Label gaussErrorMessage;

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
            int rowsA = getMultiplicationARows();
            int dimAB = getMultiplicationAColumns();
            int colsB = getMultiplicationBColumns();

            int[][] matA = MatrixOperations.ArrayToMatrix(matrixA, rowsA, dimAB);
            int[][] matB = MatrixOperations.ArrayToMatrix(matrixB, dimAB, colsB);
            int[][] transposeB = MatrixOperations.Transpose(matB, dimAB, colsB);

            ArrayList result = new ArrayList();
            int entry;

            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    entry = 0;
                    for (int k = 0; k < dimAB; k++) {
                        entry += matA[i][k] * transposeB[j][k];
                    }
                    result.add(entry);
                }
            }

            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    TextField temp = new TextField(result.get(i*colsB+j).toString());
                    temp.setEditable(false);
                    temp.setFocusTraversable(false);
                    temp.setAlignment(Pos.CENTER);
                    temp.setPrefWidth(30);
                    multiplicationResultGrid.add(temp, j, i);
                }
            }

        }
    }

    @FXML
    void determinantSetSquareMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getDeterminantDimension(), getDeterminantDimension());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();

        int[][] mat = MatrixOperations.ArrayToSquareMatrix(matrix);
        int determinant = MatrixOperations.determinantOfMatrix(mat,(int)Math.sqrt(matrix.length));
        determinantResult.setText(String.valueOf(determinant));
    }

    @FXML
    void gaussSetMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getGaussRows(), getGaussColumns());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();
        int rows =  getGaussRows();
        int cols = getGaussColumns();
        double[][] mat = MatrixOperations.DoubleArrayToMatrix(matrix, rows, cols);
        if(cols<=rows){
            gaussErrorMessage.setText("The system is inconsistent");
        }
        else{
            double[][] matA = MatrixOperations.GetMatrixA(mat, rows, cols);
            double[] matB = MatrixOperations.GetMatrixB(mat, rows, cols);


            System.out.println(Arrays.deepToString(matA));
            System.out.println(Arrays.toString(matB));

            int flag = MatrixOperations.PerformOperation(mat, rows);
            if(flag == 1){
                flag = MatrixOperations.CheckConsistency(mat, rows);
            }


            for (int i = 0; i < rows; i++) {
                for (int j = 0; j <= rows; j++){
                    TextField text = new TextField(String. format("%.3f",mat[i][j]));
                    text.setEditable(false);
                    text.setFocusTraversable(false);
                    text.setAlignment(Pos.CENTER);
                    text.setPrefWidth(30);
                    text.setMinWidth(50);
                    gaussResultGrid.add(text, j, i);
                }
            }

            gaussErrorMessage.setText(MatrixOperations.Result(mat, rows, flag));
        }


    }

    @FXML
    void inverseSetSquareMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getInverseDimension(), getInverseDimension());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();
        int size = (int)Math.sqrt(matrix.length);
        int[][] mat = MatrixOperations.ArrayToSquareMatrix(matrix);
        float[][] inv = new float[size][size];
        try {
            if(MatrixOperations.inverse(mat, inv, size)){
                for (int j = 0; j<size; j++){
                    for(int i=0; i<size; i++){
                        TextField text = new TextField(String. format("%.3f",inv[j][i]));
                        text.setEditable(false);
                        text.setFocusTraversable(false);
                        text.setAlignment(Pos.CENTER);
                        text.setPrefWidth(30);
                        text.setMinWidth(50);
                        inverseResultGrid.add(text, j, i);
                    }
                }
            }
            else {
                inverseErrorMessage.setText("Singular matrix, can't find its inverse");
            }
        }catch (Exception e){
            inverseErrorMessage.setText("Inverse matrix doesn't exist");
        }
    }

    @FXML
    void transposeSetMatrixButtonPressed(ActionEvent event) {
        SingleMatrixApp setting = new SingleMatrixApp(getTransposeRows(), getTransposeColumns());
        setSingleMatrixAction(setting);

        matrix = setting.getSingleMatrix();
        int rows =  getTransposeRows();
        int cols = getTransposeColumns();

        int[][] mat = MatrixOperations.ArrayToMatrix(matrix, rows, cols);
        int[][] result = MatrixOperations.Transpose(mat, rows, cols);
        for (int j = 0; j<rows; j++){
            for(int i=0; i<cols; i++){
                TextField text = new TextField(String. format("%d",result[i][j]));
                text.setEditable(false);
                text.setFocusTraversable(false);
                text.setAlignment(Pos.CENTER);
                text.setPrefWidth(30);
                text.setMinWidth(50);
                transposeResultGrid.add(text, j, i);
            }
        }

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

