import java.io.Serializable;
import java.security.InvalidParameterException;

public class Student extends Learner implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Degree { BACHELOR, MASTER, DOCTORAL }

    private int year;
    private Degree degree;

    public Student(String name, Type type, int[] marks, int year, Degree degree){
        super(name, type, marks);
        if (!validateYear(year))
            throw new InvalidParameterException("Wrong parameter: " + year);
        this.year = year;
        this.degree = degree;
    }

    private boolean validateYear(int year){
        return (year > 0 && year < 8);
    }

    public int getYear() {
        return year;
    }

    public Degree getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        return super.toString() + " Student{" +
                "year=" + year +
                ", degree=" + degree +
                "} ";
    }
}
