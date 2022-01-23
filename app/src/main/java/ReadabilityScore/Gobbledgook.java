package readability;

public class Gobbledgook extends ScoreCalculation {

    Gobbledgook(TextProcessor textProcessorArg) {
        super(textProcessorArg);
    }

    @Override
    public double getScore() {
        return 1.043 * Math.sqrt(getPolysyllableCount()
                * 30.0 / getSentenceCount()) + 3.1291;
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
        return String.format("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).",
                getScore(), getAge());
    }
}
