package readability;

import java.util.Scanner;

public class Main {
    // program processes text once, each sub-object of abstract class
    // ScoreCalculation.java uses the same data. It is arbitrary which
    // sub-object is created first. Creating multiple objects does not
    // add any significant processing and adds minimal memory requirements
    // to the application.

    private static TextProcessor textProcessor;

    public static void main(String[] args) {
        processArguments(args);
        System.out.print(getHeaderInfo());
        System.out.print(getUserInput());
    }

    // must be called first
    private static void processArguments(String[] args) {
        if (args.length == 0) { // input via console
            textProcessor = new TextProcessor();
        } else { // input via file
            textProcessor = new TextProcessor(args[0]);
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

    private static String getUserInput() {

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        String input = "";
        Scanner sc = new Scanner(System.in);

        input = sc.nextLine();
        System.out.println();

        if (input.equals("ARI")) {
            return getAutoRead();
        } else if (input.equals("FK")) {
            return getFKInfo();
        } else if (input.equals("SMOG")) {
            return getGobbleInfo();
        } else if (input.equals("CL")) {
            return getColeLiauInfo();
        } else if (input.equals("all")) {
            return getAllInfo();
        } else {
            System.out.println("Error: wrong input, please try again");
            getUserInput();
        }
        return "Error: in Main.getUserInput()";
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