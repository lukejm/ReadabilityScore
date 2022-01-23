package readability;

public class FleschKincaid extends ScoreCalculation {

    FleschKincaid(TextProcessor textProcessorArg) {
        super(textProcessorArg);
    }

    @Override
    public double getScore() {
        return 0.39 * getWordCount() / getSentenceCount()
                + 11.8 * getSyllableCount() / getWordCount()
                - 15.59;
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
        return String.format("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).",
                getScore(), getAge());
    }

    @Override
    public int getAge() {
        return super.getAgeMap().get((int) Math.round(getScore()));
    }
}
