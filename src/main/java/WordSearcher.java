public class WordSearcher {
    private final int[] rowNum = {-1, 1, 0, 0};
    private final int[] colNum = {0, 0, -1, 1};
    private final char[] word;
    private char[][] matrix;
    private int length;

    public WordSearcher(char[] word) {
        this.word = word;
    }

    public char[][] fillMatrix(String matrixString) {
        length = (int) Math.sqrt(matrixString.length());
        String[] splitString = matrixString.split("(?<=\\G.{" + length + "})");
        matrix = new char[length][length];
        for (int i = 0; i < splitString.length; i++) {
            matrix[i] = splitString[i].toCharArray();
        }
        return matrix;
    }

    public void findWord() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] == word[0]) {
                    if (isWordInMatrix(i, j, "", 0, word.length - 1)) {
                        return;
                    }
                }
            }
        }
        System.out.println("Can't find word " + new String(word) + " in matrix");
    }

    private boolean isWordInMatrix(int row, int column, String path, int index, int wordLength) {
        if (index > wordLength || matrix[row][column] != word[index]) {
            return false;
        }

        path += "[" + row + ", " + column + "]";

        if (index == wordLength) {
            System.out.print("Answer: " + path.replaceAll("]\\[", "]->[") + "\n");
            return true;
        }

        for (int i = 0; i < rowNum.length; i++) {
            if (isValid(row + rowNum[i], column + colNum[i])) {
                if (isWordInMatrix(row + rowNum[i], column + colNum[i],
                        path, index + 1, wordLength)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int row, int col) {
        return (row >= 0) && (row < length) && (col >= 0) && (col < length);
    }
}
