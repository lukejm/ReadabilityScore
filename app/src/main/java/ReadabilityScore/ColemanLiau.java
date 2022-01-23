package readability;

public class ColemanLiau extends ScoreCalculation {

    ColemanLiau(TextProcessor textProcessorArg) {
        super(textProcessorArg);
    }

    @Override
    public double getScore() {
        return 0.0588 * super.getCharacterAverage()
                - 0.296 * super.getSentenceAverage()
                - 15.8;
    }

    @Override
    public int getAge() {
        return super.getAgeMap().get((int) Math.round(getScore()));
    }

    @Override
    public TextProcessor getTextProcessor() {
        return super.getTextProcessor();
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
    public long getSyllableCount() {
        return super.getSyllableCount();
    }

    @Override
    public long getPolysyllableCount() {
        return super.getPolysyllableCount();
    }

    @Override
    public String toString() {
        return String.format("Coleman–Liau index: %.2f (about %d-year-olds).",
                getScore(), getAge());
    }
}