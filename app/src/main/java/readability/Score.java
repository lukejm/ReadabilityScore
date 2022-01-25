package readability;

public interface Score {
    double getScore();
    int getAge();
    long getSentenceCount();
    long getCharacterCount();
    long getWordCount();
    long getSyllableCount();
    long getPolysyllableCount();
}

