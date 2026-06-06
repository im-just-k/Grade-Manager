public class Grade extends Evaluation {
    
    // Constructor to initialize the mark
    public Grade(double mark) {
        super(mark);
    }

    @Override // Implementation of the abstract method to determine the letter grade
    public String getLetterGrade() {
    // Logic for assigning letter grades to marks
        
        if (mark >= 80) {
            return "A";
        }
        
        else if ((mark < 80) & (mark > 70)) {
            return "B";
        }
        
        else if ((mark < 70) & (mark > 60)) {
            return "C";
        }
        
        else if ((mark < 60) & (mark > 50)) {
            return "D";
        }

        else return "F";
    }



}
