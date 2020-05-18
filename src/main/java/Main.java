import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        WordSearcher wordSearcher = new WordSearcher();
        boolean bool = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (bool) {
                System.out.println("Enter string for matrix: ");
                String matrixString = scanner.nextLine();
                System.out.println("Enter your word: ");
                char[] word = scanner.nextLine().toCharArray();
                if (matrixString.length() != 0 && word.length != 0
                        && Math.sqrt(matrixString.length()) % 1 == 0
                        && word.length <= matrixString.length()) {
                    int length = (int) Math.sqrt(matrixString.length());
                    char[][] matrix = wordSearcher.getFillMatrix(matrixString, length);
                    System.out.println("Filled matrix: ");
                    printMatrix(matrix);
                    System.out.println(wordSearcher.findWord(word, matrix, length));
                    System.out.println("Do you wanna try again? Enter yes or no");
                    String answer = scanner.nextLine();
                    if (answer.equals("no")) {
                        bool = false;
                    }
                } else {
                    System.out.println("You entered incorrect data! Please enter data again)");
                }
            }
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] cells : matrix) {
            for (char cell : cells) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
