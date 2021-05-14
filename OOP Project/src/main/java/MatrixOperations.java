import java.util.Arrays;

public class MatrixOperations {

    static int[][] ArrayToSquareMatrix(int[] matrix){
        int[][] result = {};
        for (int i = 0; i<Math.sqrt(matrix.length);i++){
            int[] row = {};
            for (int j = 0; j<=Math.sqrt(matrix.length)-1; j++){
                row = Arrays.copyOf(row, row.length + 1);
                row[row.length-1] = matrix[(int) (j+Math.sqrt(matrix.length)*i)];
            }
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length-1] = row;}
        return result;
    }

    static double[][] GetMatrixA(double[][] mat, int rows, int cols){
        double[][] matA = {};
        for(int i = 0; i<rows; ++i){
            double[] row = {};
            for(int j = 0; j<cols-1; ++j){
                row = Arrays.copyOf(row, row.length + 1);
                row[row.length-1] = mat[i][j];
            }
            matA = Arrays.copyOf(matA, matA.length + 1);
            matA[matA.length-1] = row;
        }
        return matA;
    }

    static double[] GetMatrixB(double[][] mat, int rows, int cols){
        double[] matB = {};
        for(int i = 0; i<rows; i++){
            matB = Arrays.copyOf(matB, matB.length + 1);
            matB[matB.length-1] = mat[i][cols-1];
        }
        return matB;
    }


    static int[][] Transpose(int[][] mat, int rows, int cols){
        int[][] result = {};
        for(int i = 0; i < cols; i++){
            int[] row = {};
            for(int j = 0; j < rows; j++){
                row = Arrays.copyOf(row, row.length + 1);
                row[row.length-1] = mat[j][i];
            }
            result = Arrays.copyOf(result, result.length+1);
            result[result.length-1] = row;
        }
        return result;
    }

    static int[][] ArrayToMatrix(int[] matrix, int rows, int cols){
        int[][] result = {};
        for (int j = 0; j<rows;j++){
            int[] row = {};
            int i = 0;
            while (i<cols) {
                row = Arrays.copyOf(row, row.length + 1);
                row[row.length-1] = matrix[i+cols*j];
                i++;
            }
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length-1] = row;
        }
        return result;
    }

    static double[][] DoubleArrayToMatrix(int[] matrix, int rows, int cols){
        double[][] result = {};
        for (int j = 0; j<rows;j++){
            double[] row = {};
            for (int i = 0; i<cols; i++){
                row = Arrays.copyOf(row, row.length + 1);
                row[row.length-1] = matrix[i+cols*j];
            }
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length-1] = row;
        }
        return result;
    }

    static void getCofactor(int[][] mat, int[][] temp,
                            int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element of
        // the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase
                    // row index and reset col
                    // index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /* Recursive function for finding determinant
    of matrix. n is current dimension of mat[][]. */
    static int determinantOfMatrix(int[][] mat, int n)
    {
        int D = 0; // Initialize result

        // Base case : if matrix contains single
        // element
        if (n == 1)
            return mat[0][0];

        // To store cofactors
        int[][] temp = new int[n][n];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++)
        {
            // Getting Cofactor of mat[0][f]
            getCofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f]
                    * determinantOfMatrix(temp, n - 1);

            // terms are to be added with
            // alternate sign
            sign = -sign;
        }

        return D;
    }

    static void adjoin(int[][] A, int [][]adj, int n)
    {
        if (n == 1)
        {
            adj[0][0] = 1;
            return;
        }

        // temp is used to store cofactors of A[][]
        int sign;
        int [][]temp = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                // Get cofactor of A[i][j]
                getCofactor(A, temp, i, j, n);

                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                sign = ((i + j) % 2 == 0)? 1: -1;

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = (sign)*(determinantOfMatrix(temp, n-1));
            }
        }
    }

    static boolean inverse(int[][] A, float[][] inverse, int n)
    {
        // Find determinant of A[][]
        int det = determinantOfMatrix(A, n);
        if (det == 0)
        {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        // Find adjoin
        int [][]adj = new int[n][n];
        adjoin(A, adj, n);

        // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inverse[i][j] = adj[i][j]/(float)det;

        return true;
    }

    static int PerformOperation(double[][] a, int n) {
        int i, j, k, c, flag = 0;

        // Performing elementary operations
        for (i = 0; i < n; i++)
        {
            if (a[i][i] == 0)
            {
                c = 1;
                while ((i + c) < n && a[i + c][i] == 0)
                    c++;
                if ((i + c) == n)
                {
                    flag = 1;
                    break;
                }
                for (j = i, k = 0; k <= n; k++)
                {
                    double temp =a[j][k];
                    a[j][k] = a[j+c][k];
                    a[j+c][k] = temp;
                }
            }

            for (j = 0; j < n; j++)
            {

                // Excluding all i == j
                if (i != j)
                {

                    // Converting Matrix to reduced row
                    // echelon form(diagonal matrix)
                    double p = a[j][i] / a[i][i];

                    for (k = 0; k <= n; k++)
                        a[j][k] = a[j][k] - (a[i][k]) * p;
                }
            }
        }
        return flag;
    }

    static String Result(double[][] a, int n, int flag) {
        if (flag == 2){
            return "Infinite Solutions Exists";
        }
        else if (flag == 3){
            return "No Solution Exists";
        }
            // Printing the solution by dividing constants by
            // their respective diagonal elements
        else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < n; i++){
                result.append(String.format("%.2f", (a[i][n] / a[i][i]))).append(" ");
            }
            return result.toString();
        }

    }

    static int CheckConsistency(double[][] a, int n) {
        int i, j;
        double sum;

        // flag == 2 for infinite solution
        // flag == 3 for No solution
        int flag = 3;
        for (i = 0; i < n; i++)
        {
            sum = 0;
            for (j = 0; j < n; j++)
                sum = sum + a[i][j];
            if (sum == a[i][j])
                flag = 2;
        }
        return flag;
    }

}
