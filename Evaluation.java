// Acts as the blueprint for any evaluation
public abstract class Evaluation {
    protected double mark; // stores the numeric grade

    public Evaluation(double mark) {
        this.mark = mark;
    }

    // Abstract method that must be implemented by subclasses
    public abstract String getLetterGrade();

    public double getMark() {
        return mark;
    }

}
