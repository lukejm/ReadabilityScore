package readability;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {

    private final String SYLLABLES = "syllables";
    private final String POLYSYLLABLES = "polySyllables";

    private long sentenceCount = -1;
    private long wordCount = -1;
    private long syllableCount = -1;
    private long characterCount = -1;
    private long polysyllableCount = -1;
    private double sentenceAverage = -1;
    private double characterAverage = -1;

    List<String> inputCollection;
    private Map<String, Long> results;

    protected TextProcessor(String fileName) {
        try {
            this.inputCollection = InputCollection.processInput(fileName);
        } catch (Exception ex) {
            System.err.println("Error: load text twice, only allowed to load text once");
            ex.printStackTrace();
        }
    }

    protected TextProcessor() {
        try {
            this.inputCollection = InputCollection.processInput();
        } catch (Exception ex) {
            System.err.println("Error: load text twice, only allowed to load text once");
            ex.printStackTrace();
        }
    }

    protected long getCharacterCount() {
        if (characterCount == -1) {
            List<String> newText = getCharacters();
            long count = 0L;
            for (String el : newText) {
                count += el.length();
            }
            characterCount = count;
        }
        return characterCount;
    }

    protected long getSentenceCount() {
        if (sentenceCount == -1) {
            sentenceCount = getSentences().size();
        }
        return sentenceCount;
    }

    protected long getWordCount() {
        if (wordCount == -1) {
            wordCount = getWords().size();
        }
        return wordCount;
    }

    protected long getSyllableCount() {
        if (results == null) {
            getSyllables();
        }
        if (syllableCount == -1) {
            syllableCount = results.get(SYLLABLES);
        }
        return syllableCount;
    }

    protected long getPolySyllableCount() {
        if (polysyllableCount == -1) {
            polysyllableCount = results.get(POLYSYLLABLES);
        }
        return polysyllableCount;
    }

    protected double getCharacterAverage() {
        if (characterAverage == -1) {
            if (characterCount == -1) {
                characterCount = getCharacterCount();
            }
            if (sentenceCount == -1) {
                sentenceCount = getSentenceCount();
            }
            characterAverage = (double) characterCount / wordCount * 100.0;
        }
        return characterAverage;
    }

    protected double getSentenceAverage() {
        if (sentenceAverage == -1) {
            if (sentenceCount == -1) {
                sentenceCount = getSentenceCount();
            }
            if (wordCount == -1) {
                wordCount = getWordCount();
            }
            sentenceAverage = (double) sentenceCount / wordCount * 100.0;
        }
        return sentenceAverage;
    }

    private List<String> getCharacters() {
        List<String> text = inputCollection;
        List<String> newText = new ArrayList<>();

        for (String el : text) {
            newText.addAll(List.of(el.split("[\\s\\n\\t]")));
        }

        return newText;
    }

    private List<String> getSentences() {
        List<String> text = inputCollection;
        List<String> val = new ArrayList<>();

        for (String el : text) {
            val.addAll(Arrays.asList(el.split("[.!?]+")));
        }
        return val;
    }

    private List<String> getWords() {
        List<String> text = inputCollection;
        List<String> words = new ArrayList<>();

        for (String el : text) {
            words.addAll(Arrays.asList(el.split("[\\s.!?]+")));
        }
        return words;
    }

    private void getSyllables() {
        fixLeEndings(inputCollection);
    }

    private void fixLeEndings(List<String> text) {
        List<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile("([l][e])$");
        for (String el : text) {
            Matcher matcher = pattern.matcher(el);
            output.add(matcher.replaceAll("la"));
        }
        removeTrailingE(output);
    }

    private void removeTrailingE(List<String> text) {
        List<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile("([e]$|[e][.?!,>)\\]}])");
        for (String el : text) {
            String[] line = el.split("\\s");
            for (String wordEl : line) {
                Matcher matcher = pattern.matcher(wordEl);
                output.add(matcher.replaceAll(""));
            }
        }
        removeDoubleVowel(output);
    }

    private void removeDoubleVowel(List<String> text) {
        List<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile("[aeiouyAEIOUY]{2,}");
        for (String el : text) {
            Matcher matcher = pattern.matcher(el);
            output.add(matcher.replaceAll("a"));
        }
        removeDigits(output);
    }


    private void removeDigits(List<String> text) {
        List<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+)[,]*(\\d*)");
        for (String el : text) {
            Matcher matcher = pattern.matcher(el);
            output.add(matcher.replaceAll(""));
        }
        countSyllables(output);
    }

    private void countSyllables(List<String> text) {
        Pattern pattern = Pattern.compile("\\(([\\w|])|([^aeiouyAEIOUY])|([\\w])\\)");
        long syllableCount = 0;
        long polySyllableCount = 0;
        for (String el : text) {
            String[] words = el.split("[\\s,.?!]");
            for (String wordEl : words) {
                Matcher matcher = pattern.matcher(wordEl);
                String newWord = matcher.replaceAll("");
                long syllable = newWord.length();
                syllableCount += (0 < syllable) ? syllable : 1;
                polySyllableCount += (2 < syllable) ? 1 : 0;
            }
        }
        results = new HashMap<>();
        results.put(SYLLABLES, syllableCount);
        results.put(POLYSYLLABLES, polySyllableCount);
    }
}