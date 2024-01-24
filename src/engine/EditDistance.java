package engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EditDistance {
    List<OptionalWord> potentialWords = new ArrayList<>();
    Set<String> dictionary;

    public EditDistance() {
        try {
            dictionary = new HashSet<>(Files.readAllLines(Paths.get("./src/engine/dictionary/words")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<OptionalWord> findPotentialWords(String currentWord, int maxEditDistance) {
        if (!dictionary.contains(currentWord)) {
            for (String word : dictionary) {
                if (Math.abs(currentWord.length() - word.length()) <= maxEditDistance) {
                    int editDistance = findEditDistance(currentWord, word);
                    if (editDistance <= maxEditDistance) {
                        potentialWords.add(new OptionalWord(word, editDistance));
                    }
                }
            }
        }
        Collections.sort(potentialWords);
        return potentialWords;
    }

    public int findEditDistance(String currentWord, String potentialWord) {
        int[][] dp = new int[currentWord.length() + 1][potentialWord.length() + 1];
        initializeMatrix(dp);
        int matrixRowIndex = 1;
        int matrixColumnIndex;
        for (int i = 0; i < currentWord.length(); i++) {
            char currentWordChar = currentWord.charAt(i);
            matrixColumnIndex = 1;
            for (int j = 0; j < potentialWord.length(); j++) {
                char currentPotentialWord = potentialWord.charAt(j);
                int delete = dp[matrixRowIndex][matrixColumnIndex - 1];
                int add = dp[matrixRowIndex - 1][matrixColumnIndex];
                int substitute = dp[matrixRowIndex - 1][matrixColumnIndex - 1];
                if (currentWordChar == currentPotentialWord) {
                    dp[matrixRowIndex][matrixColumnIndex] = min(substitute, add + 1, delete + 1);
                } else {
                    dp[matrixRowIndex][matrixColumnIndex] = min(substitute, add, delete) + 1;
                }
                matrixColumnIndex++;
            }
            matrixRowIndex++;
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    private int min(int... min) {
        int minVal = min[0];
        for (int i = 1; i < min.length; i++) {
            if (min[i] < minVal) {
                minVal = min[i];
            }
        }
        return minVal;
    }

    private void initializeMatrix(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
    }

    public void PrintDictionary() {
        for (String word : dictionary) {
            System.out.println(word);
        }

    }

    public List<OptionalWord> getPotentialWords() {
        return potentialWords;
    }

    public Set<String> getDictionary() {
        return dictionary;
    }
}
