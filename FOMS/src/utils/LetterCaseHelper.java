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
}