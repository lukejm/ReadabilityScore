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
    public String toString() {
        return String.format("Coleman–Liau index: %.2f (about %d-year-olds).",
                getScore(), getAge());
    }
}