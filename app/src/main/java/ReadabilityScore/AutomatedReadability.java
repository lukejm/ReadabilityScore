package readability;

public class AutomatedReadability extends ScoreCalculation {

    protected AutomatedReadability(TextProcessor textProcessor) {
        super(textProcessor);
    }

    @Override
    public double getScore() {
        return 4.71
                * super.getCharacterCount() / super.getWordCount()
                + 0.5 * super.getWordCount() / super.getSentenceCount()
                - 21.43;
    }

    @Override
    public String toString() {
        return String
                .format("Automated Readability Index: %.2f (about %d-year-olds).", getScore(), getAge());
    }

    @Override
    public long getSyllableCount() {
        return super.getSyllableCount();
    }

    @Override
    public long getSentenceCount() {
        return super.getSentenceCount();
    }

    @Override
    public long getCharacterCount() {
        return super.getCharacterCount();
    }

    @Override
    public long getWordCount() {
        return super.getWordCount();
    }

    @Override
    public long getPolysyllableCount() {
        return super.getPolysyllableCount();
    }
    @Override
    public int getAge() {
        return super.getAgeMap().get((int) Math.round(getScore()));
    }

    public int getAgeOld() {
        return Integer.parseInt(ageRange().split("[-]")[1]); // will be in the format "int-int"
    }

    private String ageRange() {
        double score = getScore();
        String ageRange = "";

        switch ((int) Math.ceil(score)) { // +1 to make up for JBA's use of old readability ranges
            case 1:
                ageRange = "5-6";
                break;
            case 2:
                ageRange = "6-7";
                break;
            case 3:
                ageRange = "7-8";
                break;
            case 4:
                ageRange = "8-9";
                break;
            case 5:
                ageRange = "9-10";
                break;
            case 6:
                ageRange = "10-11";
                break;
            case 7:
                ageRange = "11-12";
                break;
            case 8:
                ageRange = "12-13";
                break;
            case 9:
                ageRange = "13-14";
                break;
            case 10:
                ageRange = "14-15";
                break;
            case 11:
                ageRange = "15-16";
                break;
            case 12:
                ageRange = "16-17";
                break;
            case 13:
                ageRange = "17-18";
                break;
//            case 14:
//                ageRange = "18-24";
//                break;
            default:
                ageRange = "18-24";
        }
        return ageRange;
    }
}
