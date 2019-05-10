import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Arrays;

public abstract class Learner implements Serializable {
    private static int nextId = 0;

    public enum Type { SCHOOLBOY, STUDENT}

    private Type type;
    private int id;
    private String name;
    private transient String info = "";

    private int[] marks;



    public Learner(){}

    protected Learner(String name, Type type, int[] marks){
        if (!validateName(name))
            throw new InvalidParameterException("Wrong parameter: " + name);
        if (!validateMarks(marks))
            throw new InvalidParameterException("Wrong parameter: " + Arrays.toString(marks));
        this.id = nextId++;
        this.name = name;
        this.type = type;
        int length = marks.length;
        this.marks = new int[length];
        for (int i = 0; i < length; i++){
            if (marks[i] < 0){
                throw new InvalidParameterException("Grade is less than zero");
            }
            if (marks[i] > 10){
                throw new InvalidParameterException("Grade is more than ten");
            }
            this.marks[i] = marks[i];
        }
        Arrays.sort(this.marks);
    }

    private boolean validateName(String name){
        return (name != null && name.length() > 0);
    }

    private boolean validateMarks(int[] marks){
        return (marks != null && marks.length < 5);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getName() {
        return name;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public int[] getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "type=" + type +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", marks=" + marks +
                '}';
    }
}
