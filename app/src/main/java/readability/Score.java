/**
 *
 * Luke James Mitton
 * luke.j.mitton@gmail.com
 * https://github.com/lukejm
 *
 */
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

