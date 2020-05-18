public class WordSearcher {
    private final int[] rowNum = {-1, 1, 0, 0};
    private final int[] colNum = {0, 0, -1, 1};

    public char[][] getFillMatrix(String matrixString, int length) {
        String[] splitString = matrixString.split("(?<=\\G.{" + length + "})");
        char[][] matrix = new char[length][length];
        for (int i = 0; i < splitString.length; i++) {
            matrix[i] = splitString[i].toCharArray();
        }
        return matrix;
    }

    public String findWord(char[] word, char[][] matrix, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] == word[0]) {
                    String answer = getWordInMatrix(i, j, "", 0, word, matrix, length);
                    if (answer.length() != 0) {
                        return answer;
                    }
                }
            }
        }
        return "Can't find word " + new String(word) + " in matrix";
    }

    private String getWordInMatrix(int row, int column, String path,
                                   int index, char[] word, char[][] matrix, int length) {
        if (matrix[row][column] != word[index]) {
            return "";
        }

        path += "[" + row + ", " + column + "]";

        if (index == word.length - 1) {
            return "Answer: " + path.replaceAll("]\\[", "]->[");
        }

        for (int i = 0; i < rowNum.length; i++) {
            if (isValid(row + rowNum[i], column + colNum[i], length)) {
                String answer = getWordInMatrix(row + rowNum[i], column + colNum[i],
                        path, index + 1, word, matrix, length);
                if (answer.length() != 0) {
                    return answer;
                }
            }
        }
        return "";
    }

    private boolean isValid(int row, int col, int length) {
        return (row >= 0) && (row < length) && (col >= 0) && (col < length);
    }
}
