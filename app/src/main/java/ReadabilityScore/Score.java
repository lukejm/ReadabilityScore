package readability;

public interface Score {
    double getScore();
    int getAge();
    TextProcessor getTextProcessor();
    long getSentenceCount();
    long getCharacterCount();
    long getWordCount();
    long getSyllableCount();
    long getPolysyllableCount();
}

