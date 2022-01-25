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
    public String toString() {
        return String.format("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).",
                getScore(), getAge());
    }
}
