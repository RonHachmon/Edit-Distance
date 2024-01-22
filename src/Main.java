import java.util.List;

public class Main {
    public static EditDistance editDistance =new EditDistance();
    public static void main(String[] args) {

        List<String> potentialWords = editDistance.findPotentialWords("subtitue", 2);
        for (String potentialWord:potentialWords) {
            System.out.println(potentialWord);
        }


        runTests();

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