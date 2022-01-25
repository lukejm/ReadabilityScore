package readability;

public class Main {
    // program processes text once, each sub-object of abstract class
    // ScoreCalculation.java uses the same data. It is arbitrary which
    // sub-object is created first. Creating multiple objects does not
    // add any significant processing and adds minimal memory requirements
    // to the application.

    private static TextProcessor textProcessor;

    public static void main(String[] args) {
        processArguments(args);
        System.out.println(getHeaderInfo());
        System.out.println(getAllInfo());
    }

    // must be called first
    private static void processArguments(String[] args) {
        if (args[0].length() > 0) { // input via console
            textProcessor = new TextProcessor(args[0]);
        } else {
            System.out.println("Error: no file argument.");
        }
    }

    private static String getHeaderInfo() {
        AutomatedReadability ar = new AutomatedReadability(textProcessor);
        return String.format("Words: %d\nSentences: %d\nCharacters: %d\nSyllables: %d\nPolysyllables: %d\n",
                ar.getWordCount(),
                ar.getSentenceCount(),
                ar.getCharacterCount(),
                ar.getSyllableCount(),
                ar.getPolysyllableCount());
    }

    private static String getAllInfo() {
        return String.format("%s\n%s\n%s\n%s\n\nThis text should be " +
                "understood in average by %.2f.",
                getAutoRead(),
                getFKInfo(),
                getGobbleInfo(),
                getColeLiauInfo(),
                calculateAverageAge());
    }

    private static double calculateAverageAge() {
        double testsCount = 4;
        return (new AutomatedReadability(textProcessor).getAge()
                + new FleschKincaid(textProcessor).getAge()
                + new Gobbledgook(textProcessor).getAge()
                + new ColemanLiau(textProcessor).getAge())
                / testsCount;
    }

    private static String getAutoRead() {
        return new AutomatedReadability(textProcessor).toString();
    }

    private static String getFKInfo() {
        return new FleschKincaid(textProcessor).toString();
    }

    private static String getGobbleInfo() {
        return new Gobbledgook(textProcessor).toString();
    }

    private static String getColeLiauInfo() {
        return new ColemanLiau(textProcessor).toString();
    }
}