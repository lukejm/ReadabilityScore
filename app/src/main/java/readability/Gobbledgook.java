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
    public String toString() {
        return String.format("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).",
                getScore(), getAge());
    }
}
