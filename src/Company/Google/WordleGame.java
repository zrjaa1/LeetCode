package Company.Google;

import java.util.*;

/**
 * Wordle Game, 2022.4 - 2022.6. from: https://www.1point3acres.com/bbs/thread-904423-1-1.html
 * You have 2 words: secret word and guess word. Compare those 2 words and return a sequence that:
 * G: green, the character and location are correct
 * Y: yellow, the character is correct, but in the wrong location(which means the character appears somewhere else)
 * R: else(character incorrect + correct location, or character incorrect + location incorrect -> character does not exist in the Secret Word)
 *
 * For example:
 * Secrect Word: WORDLE
 * Guess   Word: WOLDCA
 * Output:       GGYGRR
 *
 * More examples that I guessed:
 * Secrect Word: BALED
 * Guess   Word: CEABD
 * Output:       RYYYG
 *
 * Follow Up: duplicates in both secret word and guess word.
 *
 * More examples that I guessed:
 * Secrect Word: BAALE
 * Guess   Word: CEAAA
 * Output:       RYGYR
 * So we could use a map/array to record the number of
 *
 * Clarification:
 * 1. Duplicates?
 * 2. All uppercase?
 * 3. Same length?
 * 4. What about case: (does the previous Y cancel out the position which should originally be G? What is the priority)
 * Secrect Word: BCDAALE
 * Guess   Word: CAAAACE
 * Output:       YYYRRRG(Y prioritized) or YRRGGRG? (G prioritized) Personally I think it should be G prioritized
 */

/**
 * This solution works for duplicates case as well. We use a Map<Character, Set<Integer>> to record the indexes of a specific character
 * in secret word. And in case we want to put an yellow, we check the desired index to see if there could be a Green first.
 * If not, then we put the yellow. If no any match found, we put a red.
 * If there is no duplicate, I think we could simply use a Set<Character> to solve this problem.
 */
public class WordleGame {
    public String guess(String secretWord, String guessWord) {
        // assuming same length
        StringBuilder sb = new StringBuilder();
        Map<Character, Set<Integer>> charToIndex = new HashMap<>();

        for (int i = 0; i < secretWord.length(); i++) {
            sb.append("G");
            Set<Integer> index = charToIndex.getOrDefault(secretWord.charAt(i), new HashSet<>());
            index.add(i);
            charToIndex.put(secretWord.charAt(i), index);
        }

        // corner case
        if (secretWord.equals(guessWord)) {
            return sb.toString();
        }

        for (int i = 0; i < guessWord.length(); i++) {
            char chGuess = guessWord.charAt(i);
            char chSecret = secretWord.charAt(i);
            Set<Integer> indexes = charToIndex.get(chGuess);
            if (chGuess == chSecret) { // green
                sb.setCharAt(i, 'G');
                indexes.remove(i);
            } else if (charToIndex.containsKey(chGuess)) {
                int indexToRemove = -1;
                for (Integer index : indexes) {
                    if (secretWord.charAt(index) != guessWord.charAt(index)) { // yellow
                        sb.setCharAt(i, 'Y');
                        indexToRemove = index;
                        break;
                    }
                }
                if (indexToRemove == -1) { // red
                    sb.setCharAt(i, 'R');
                } else {
                    indexes.remove(indexToRemove);
                }
            } else {
                sb.setCharAt(i, 'R');
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        WordleGame tester = new WordleGame();
        String secretWord = "BAALE";
        String guessWord = "CEAAA";
        String res = tester.guess(secretWord, guessWord);
        System.out.println(res);
    }
}
