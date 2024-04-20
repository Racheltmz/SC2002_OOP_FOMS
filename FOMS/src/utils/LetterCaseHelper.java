package utils;

/**
 * Helper to convert text to the right casing for consistency purposes.
 */
public class LetterCaseHelper {
    /**
     * Convert text to proper case.
     *
     * @param text Text to set to proper case.
     * @return Text that has been converted to proper case.
     */
    public static String toProperCase(String text) {
        // If text is empty, return it back
        if (text == null || text.isEmpty()) {
            return text;
        }

        // Initialise StringBuilder instance
        StringBuilder converted = new StringBuilder();

        // Convert characters
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }
        return converted.toString();
    }

    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text; // Return unchanged if string is null or empty
        }
        // Convert first character to uppercase and concatenate with the rest of the string
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}