package common;

import java.util.ArrayList;
import java.util.Arrays;

public class HelperService {

    public WinnerModel winnerExists(int n, int m, char[][] matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (existsFullLine(matrix, i, j)) {
                    return new WinnerModel(new ArrayList<>(Arrays.asList(new Position(i, j), new Position(i + 1, j), new Position(i + 2, j))));
                }
                if (existsFullColumn(matrix, i, j)) {
                    return new WinnerModel(new ArrayList<>(Arrays.asList(new Position(i, j), new Position(i, j + 1), new Position(i, j + 2))));

                }
                if (existsFirstDiagonal(matrix, i, j)) {
                    return new WinnerModel(new ArrayList<>(Arrays.asList(new Position(i, j), new Position(i + 1, j + 1), new Position(i + 2, j + 2))));
                }

                if (existsSecondDiagonal(matrix, i, j)) {
                    return new WinnerModel(new ArrayList<>(Arrays.asList(new Position(i, j), new Position(i + 1, j - 1), new Position(i + 2, j - 2))));
                }
            }
        }
        return new WinnerModel();
    }

    private boolean existsFullLine(char[][] matrix, int i, int j) {
        return i + 2 < matrix.length
                && matrix[i][j] != Constants.EMPTY
                && matrix[i][j] == matrix[i + 1][j]
                && matrix[i][j] == matrix[i + 2][j];
    }

    private boolean existsFullColumn(char[][] matrix, int i, int j) {
        return j + 2 < matrix[0].length
                && matrix[i][j] != Constants.EMPTY
                && matrix[i][j] == matrix[i][j + 1]
                && matrix[i][j] == matrix[i][j + 2];
    }

    private boolean existsFirstDiagonal(char[][] matrix, int i, int j) {
        return i + 2 < matrix.length
                && matrix[i][j] != Constants.EMPTY
                && j + 2 < matrix[0].length
                && matrix[i][j] == matrix[i + 1][j + 1]
                && matrix[i][j] == matrix[i + 2][j + 2];
    }

    private boolean existsSecondDiagonal(char[][] matrix, int i, int j) {
        return i + 2 < matrix.length
                && matrix[i][j] != Constants.EMPTY
                && j - 2 >= 0
                && matrix[i][j] == matrix[i + 1][j - 1]
                && matrix[i][j] == matrix[i + 2][j - 2];
    }
}
