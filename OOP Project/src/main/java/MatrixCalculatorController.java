
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixCalculatorController {

    private double[] matrix;
    private double[] matrixA;
    private double[] matrixB;

    @FXML private ChoiceBox<?> additionRows;
    @FXML private ChoiceBox<?> additionColumns;
    @FXML private Button additionSetMatricesButton;
    @FXML private GridPane additionResultGrid;
    @FXML private AnchorPane additionAnchorPane;

    @FXML private ChoiceBox<?> multiplicationARows;
    @FXML private ChoiceBox<?> multiplicationBRows;
    @FXML private ChoiceBox<?> multiplicationAColumns;
    @FXML private ChoiceBox<?> multiplicationBColumns;
    @FXML private Button multiplicationSetMatricesButton;
    @FXML private Label multiplicationErrorMessage;
    @FXML private GridPane multiplicationResultGrid;
    @FXML private AnchorPane multiplicationAnchorPane;

    @FXML private ChoiceBox<?> transposeRows;
    @FXML private ChoiceBox<?> transposeColumns;
    @FXML private Button transposeSetMatrixButton;
    @FXML private GridPane transposeResultGrid;
    @FXML private AnchorPane transposeAnchorPane;

    @FXML private Label determinantResult;
    @FXML private ChoiceBox<?> determinantDimension;
    @FXML private Button determinantSetSquareMatrixButton;
    @FXML private AnchorPane determinantAnchorPane;

    @FXML private ChoiceBox<?> inverseDimension;
    @FXML private Button inverseSetSquareMatrixButton;
    @FXML private GridPane inverseResultGrid;
    @FXML private Label inverseErrorMessage;
    @FXML private AnchorPane inverseAnchorPane;

    @FXML private ChoiceBox<?> gaussRows;
    @FXML private ChoiceBox<?> gaussColumns;
    @FXML private Button gaussSetMatrixButton;
    @FXML private GridPane gaussResultGrid;
    @FXML private Label gaussErrorMessage;
    @FXML private AnchorPane gaussAnchorPane;

    @FXML
    void additionSetMatricesButtonPressed(ActionEvent event) {
        additionResultGrid.getChildren().clear();
        additionAnchorPane.setStyle("-fx-background-color:#f4f4f4;");

        TwoMatricesApp setting = new TwoMatricesApp(getAdditionRows(), getAdditionColumns(), getAdditionRows(), getAdditionColumns());
        setTwoMatricesAction(setting);

        if(!setting.wasStageCanceled()) {
            additionAnchorPane.setStyle("-fx-background-color:#b9ff96;");

            matrixA = setting.getMatrixA();
            matrixB = setting.getMatrixB();

            double[] additionResult = new double[getAdditionColumns() * getAdditionRows()];

            for (int i = 0; i < getAdditionRows(); i++) {
                for (int j = 0; j < getAdditionColumns(); j++) {
                    additionResult[i * getAdditionColumns() + j] = matrixA[i * getAdditionColumns() + j] + matrixB[i * getAdditionColumns() + j];
                    TextField temp = new TextField("" + additionResult[i * getAdditionColumns() + j]);
                    temp.setEditable(false);
                    temp.setFocusTraversable(false);
                    temp.setAlignment(Pos.CENTER);
                    temp.setPrefWidth(30);
                    temp.setMinWidth(50);
                    additionResultGrid.add(temp, j, i);
                }
            }
        }
    }

    @FXML
    void multiplicationSetMatricesButtonPressed(ActionEvent event) {
        multiplicationResultGrid.getChildren().clear();
        multiplicationAnchorPane.setStyle("-fx-background-color:#f4f4f4;");

        if(getMultiplicationAColumns()!=Integer.parseInt(multiplicationBRows.getValue().toString())){
            multiplicationBRows.requestFocus();
            multiplicationErrorMessage.setText("dim(columns) of A must be equal to dim(rows) of B");
        } else{
            multiplicationErrorMessage.setText("");

            TwoMatricesApp setting = new TwoMatricesApp(getMultiplicationARows(), getMultiplicationAColumns(), getMultiplicationAColumns(), getMultiplicationBColumns());
            setTwoMatricesAction(setting);

            if(!setting.wasStageCanceled()) {
                multiplicationAnchorPane.setStyle("-fx-background-color:#b9ff96;");

                matrixA = setting.getMatrixA();
                matrixB = setting.getMatrixB();
                int rowsA = getMultiplicationARows();
                int dimAB = getMultiplicationAColumns();
                int colsB = getMultiplicationBColumns();

                double[][] matA = MatrixOperations.ArrayToMatrix(matrixA, rowsA, dimAB);
                double[][] matB = MatrixOperations.ArrayToMatrix(matrixB, dimAB, colsB);
                double[][] transposeB = MatrixOperations.Transpose(matB, dimAB, colsB);

                ArrayList result = new ArrayList();
                double entry;

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
                        TextField temp = new TextField(result.get(i * colsB + j).toString());
                        temp.setEditable(false);
                        temp.setFocusTraversable(false);
                        temp.setAlignment(Pos.CENTER);
                        temp.setPrefWidth(50);
                        temp.setMinWidth(50);
                        multiplicationResultGrid.add(temp, j, i);
                    }
                }
            }
        }
    }

    @FXML
    void determinantSetSquareMatrixButtonPressed(ActionEvent event) {
        determinantResult.setText("");
        determinantAnchorPane.setStyle("-fx-background-color:#f4f4f4;");

        SingleMatrixApp setting = new SingleMatrixApp(getDeterminantDimension(), getDeterminantDimension());
        setSingleMatrixAction(setting);

        if(!setting.wasStageCanceled()) {
            determinantAnchorPane.setStyle("-fx-background-color:#b9ff96;");

            matrix = setting.getSingleMatrix();

            double[][] mat = MatrixOperations.ArrayToSquareMatrix(matrix);
            double determinant = MatrixOperations.determinantOfMatrix(mat, (int) Math.sqrt(matrix.length));
            determinantResult.setText(String.valueOf(determinant));
        }
    }

    @FXML
    void gaussSetMatrixButtonPressed(ActionEvent event) {
        gaussAnchorPane.setStyle("-fx-background-color:#f4f4f4;");
        gaussResultGrid.getChildren().clear();
        gaussErrorMessage.setText("");

        SingleMatrixApp setting = new SingleMatrixApp(getGaussRows(), getGaussColumns());
        setSingleMatrixAction(setting);

        if(!setting.wasStageCanceled()) {
            matrix = setting.getSingleMatrix();
            int rows = getGaussRows();
            int cols = getGaussColumns();
            double[][] mat = MatrixOperations.DoubleArrayToMatrix(matrix, rows, cols);
            if (cols <= rows) {
                gaussErrorMessage.setText("The system is inconsistent");
                gaussAnchorPane.setStyle("-fx-background-color:#ff9696;");
            } else {
                gaussAnchorPane.setStyle("-fx-background-color:#b9ff96;");

                double[][] matA = MatrixOperations.GetMatrixA(mat, rows, cols);
                double[] matB = MatrixOperations.GetMatrixB(mat, rows, cols);


                System.out.println(Arrays.deepToString(matA));
                System.out.println(Arrays.toString(matB));

                int flag = MatrixOperations.PerformOperation(mat, rows);
                if (flag == 1) {
                    flag = MatrixOperations.CheckConsistency(mat, rows);
                }


                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j <= rows; j++) {
                        TextField text = new TextField(String.format("%.3f", mat[i][j]));
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
    }

    @FXML
    void inverseSetSquareMatrixButtonPressed(ActionEvent event) {
        inverseAnchorPane.setStyle("-fx-background-color:#f4f4f4;");
        inverseResultGrid.getChildren().clear();
        inverseErrorMessage.setText("");

        SingleMatrixApp setting = new SingleMatrixApp(getInverseDimension(), getInverseDimension());
        setSingleMatrixAction(setting);

        if (!setting.wasStageCanceled()){
            matrix = setting.getSingleMatrix();
            int size = (int)Math.sqrt(matrix.length);
            double[][] mat = MatrixOperations.ArrayToSquareMatrix(matrix);
            double[][] inv = new double[size][size];
            try {
                if (MatrixOperations.inverse(mat, inv, size)) {
                    inverseAnchorPane.setStyle("-fx-background-color:#b9ff96;");

                    for (int j = 0; j < size; j++) {
                        for (int i = 0; i < size; i++) {
                            TextField text = new TextField(String.format("%.3f", inv[i][j]));
                            text.setEditable(false);
                            text.setFocusTraversable(false);
                            text.setAlignment(Pos.CENTER);
                            text.setPrefWidth(30);
                            text.setMinWidth(50);
                            inverseResultGrid.add(text, j, i);
                        }

                    }
                } else {
                    inverseErrorMessage.setText("Singular matrix, can't find its inverse");
                    inverseAnchorPane.setStyle("-fx-background-color:#ff9696;");
                }
            } catch (Exception e) {
                inverseErrorMessage.setText("Inverse matrix doesn't exist");
                inverseAnchorPane.setStyle("-fx-background-color:#ff9696;");
            }
        }
    }
    @FXML
    void transposeSetMatrixButtonPressed(ActionEvent event) {
        transposeResultGrid.getChildren().clear();
        transposeAnchorPane.setStyle("-fx-background-color:#f4f4f4;");

        SingleMatrixApp setting = new SingleMatrixApp(getTransposeRows(), getTransposeColumns());
        setSingleMatrixAction(setting);

        if(!setting.wasStageCanceled()) {
            transposeAnchorPane.setStyle("-fx-background-color:#b9ff96;");

            matrix = setting.getSingleMatrix();
            int rows = getTransposeRows();
            int cols = getTransposeColumns();

            double[][] mat = MatrixOperations.ArrayToMatrix(matrix, rows, cols);
            double[][] result = MatrixOperations.Transpose(mat, rows, cols);
            for (int j = 0; j < rows; j++) {
                for (int i = 0; i < cols; i++) {
                    TextField text = new TextField(String.format("%.2f", result[i][j]));
                    text.setEditable(false);
                    text.setFocusTraversable(false);
                    text.setAlignment(Pos.CENTER);
                    text.setPrefWidth(30);
                    text.setMinWidth(50);
                    transposeResultGrid.add(text, j, i);
                }
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
        return Integer.parseInt(additionRows.getValue().toString());
    }

    private int getAdditionColumns(){
        return Integer.parseInt(additionColumns.getValue().toString());
    }

    private int getMultiplicationARows(){
        return Integer.parseInt(multiplicationARows.getValue().toString());
    }

    private int getMultiplicationAColumns(){
        return Integer.parseInt(multiplicationAColumns.getValue().toString());
    }

    private int getMultiplicationBColumns(){
        return Integer.parseInt(multiplicationBColumns.getValue().toString());
    }

    private int getTransposeRows(){
        return Integer.parseInt(transposeRows.getValue().toString());
    }

    private int getTransposeColumns(){
        return Integer.parseInt(transposeColumns.getValue().toString());
    }

    private int getDeterminantDimension(){
        return Integer.parseInt(determinantDimension.getValue().toString());
    }

    private int getInverseDimension(){
        return Integer.parseInt(inverseDimension.getValue().toString());
    }

    private int getGaussRows(){
        return Integer.parseInt(gaussRows.getValue().toString());
    }

    private int getGaussColumns(){
        return Integer.parseInt(gaussColumns.getValue().toString());
    }

}

