package readability;

public class AutomatedReadability extends ScoreCalculation {

    protected AutomatedReadability(TextProcessor textProcessor) {
        super(textProcessor);
    }

    @Override
    public double getScore() {
        return 4.71
                * getCharacterCount() / getWordCount()
                + 0.5 * getWordCount() / getSentenceCount()
                - 21.43;
    }

    @Override
    public String toString() {
        return String
                .format("Automated Readability Index: %.2f (about %d-year-olds).", getScore(), getAge());
    }
}