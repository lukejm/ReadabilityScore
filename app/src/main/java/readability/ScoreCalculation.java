package readability;

import java.util.HashMap;
import java.util.Map;

public abstract class ScoreCalculation implements Score {

    private TextProcessor textProcessor;
    private Map<Integer, Integer> agesCalculation;

    ScoreCalculation(TextProcessor textProcessorArg) {
        if (this.textProcessor == null) {
            this.textProcessor = textProcessorArg;
        }
    }

    @Override
    public long getSentenceCount() {
        return textProcessor.getSentenceCount();
    }

    @Override
    public long getCharacterCount() {
        return textProcessor.getCharacterCount();
    }

    @Override
    public long getWordCount() {
        return textProcessor.getWordCount();
    }

    @Override
    public long getSyllableCount() {
        return textProcessor.getSyllableCount();
    }

    @Override
    public long getPolysyllableCount() {
        return textProcessor.getPolySyllableCount();
    }

    public double getCharacterAverage() {
        return textProcessor.getCharacterAverage();
    }

    public double getSentenceAverage() {
        return textProcessor.getSentenceAverage();
    }

    public int getAge() {
        return getAgeMap().get((int) Math.round(getScore()));
    }

    protected Map<Integer, Integer> getAgeMap() {
        if (agesCalculation == null) {
            fillOutAgeMap();
        }
        return agesCalculation;
    }

    private void fillOutAgeMap() {
        agesCalculation = new HashMap<>();
        agesCalculation.put(1, 6);
        agesCalculation.put(2, 7);
        int age = 9;
        for (int i = 3; i < 13; i++) {
            agesCalculation.put(i, age++);
        }
        agesCalculation.put(13,24);
        agesCalculation.put(14,24);
    }
}
