package engine;

public class OptionalWord implements Comparable<OptionalWord> {
    private final String optionalWord;
    private final int editDistance;
    public OptionalWord(String optionalWord,int editDistance)
    {
        this.optionalWord=optionalWord;
        this.editDistance=editDistance;
    }


    @Override
    public int compareTo(OptionalWord o) {
        if(this.editDistance>o.editDistance)
        {
            return 1;
        }
        if(this.editDistance==o.editDistance)
        {
            return 0;
        }
        return -1;

    }

    public String getOptionalWord() {
        return optionalWord;
    }

    public int getEditDistance() {
        return editDistance;
    }
}
