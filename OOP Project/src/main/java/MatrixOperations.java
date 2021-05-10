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
            for (int i = 0; i<cols; i++){
                row = Arrays.copyOf(row, row.length + 1);
                row[row.length-1] = matrix[(int) (i+cols*j)];
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

    static void adjoint(int A[][],int [][]adj, int n)
    {
        if (n == 1)
        {
            adj[0][0] = 1;
            return;
        }

        // temp is used to store cofactors of A[][]
        int sign = 1;
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

    static boolean inverse(int A[][], float[][] inverse, int n)
    {
        // Find determinant of A[][]
        int det = determinantOfMatrix(A, n);
        if (det == 0)
        {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        // Find adjoint
        int [][]adj = new int[n][n];
        adjoint(A, adj, n);

        // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inverse[i][j] = adj[i][j]/(float)det;

        return true;
    }

    static void display(int A[][], int n)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                System.out.print(A[i][j]+ " ");
            System.out.println();
        }
    }

    static void display(float A[][], int n)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                System.out.printf("%.6f ",A[i][j]);
            System.out.println();
        }
    }

}
