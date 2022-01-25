/**
 *
 * Luke James Mitton
 * luke.j.mitton@gmail.com
 * https://github.com/lukejm
 *
 */
package readability;

import java.io.*;
import java.util.*;

public class InputCollection {

    private static List<String> inputText;

    private InputCollection() { }

    // get text from file
    protected static List<String> processInput(String fileName) {
        inputText = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                inputText.add(sc.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: processing input.");
        }
        return inputText;
    }

    // get text from command line
    protected static List<String> processInput() {
        inputText = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        inputText.add(sc.nextLine());
        sc.close();
        return inputText;
    }
}