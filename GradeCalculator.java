public class GradeCalculator {
    /**
     * Extracts valid doubles from an array of raw string inputs.
     * Throws NumberFormatException if text is corrupted.
     * Throws IllegalArgumentException if a number is outside 0-100.
     */
    public double[] processInputs(String[] rawInputs) throws NumberFormatException, IllegalArgumentException {
        double[] temporaryArray = new double[10]; // Array to hold valid marks for up to 10 courses
        int validCount = 0;

        for (String input : rawInputs) {
            String cleanInput = input.trim();
            
            if (cleanInput.isEmpty()) {
                continue; // Skip blank inputs seamlessly
            }

            double mark = Double.parseDouble(cleanInput);

            if (mark < 0 || mark > 100) {
                throw new IllegalArgumentException("Mark must be between 0 and 100.");
            }

            temporaryArray[validCount] = mark;
            validCount++;
        }

        // Trim the array to match exactly how many entries were actually filled
        double[] filledMarks = new double[validCount];
        System.arraycopy(temporaryArray, 0, filledMarks, 0, validCount);
        return filledMarks;
    }
}
