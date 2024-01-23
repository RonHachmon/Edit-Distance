import engine.EditDistance;
import engine.OptionalWord;

import java.util.List;

public class Main {
    public static EditDistance editDistance =new EditDistance();
    public static void main(String[] args) {

        List<OptionalWord> potentialWords = editDistance.findPotentialWords("adequate", 4);
        for (OptionalWord potentialWord:potentialWords) {
            String format = String.format("%s with %d edit distance", potentialWord.getOptionalWord(), potentialWord.getEditDistance());
            System.out.println(format);
        }


        //runTests();

    }

    private static void runTests() {
        assertEquals(4, editDistance.findEditDistance("listen", "silent"));
        assertEquals(2, editDistance.findEditDistance("book", "back"));
        assertEquals(3, editDistance.findEditDistance("kitten", "sitting"));
        assertEquals(2, editDistance.findEditDistance("floats", "boats"));
        assertEquals(5, editDistance.findEditDistance("", "boats"));
        assertEquals(6, editDistance.findEditDistance("to", "boooooo"));
    }

    private static void assertEquals(int i, int distance) {
        Boolean flag=i==distance;
        System.out.println(flag);
    }


}