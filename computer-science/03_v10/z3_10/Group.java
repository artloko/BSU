package z3_10;

import java.security.InvalidParameterException;

public class Group {
    Student[] students = null;
    int es = 0;

    public Group(Student[] students) throws InvalidParameterException {
        int length = students.length;
        this.students = new Student[length];
        for (int i = 0; i < length; i++){
            if (students[i] == null){
                throw new InvalidParameterException("null-pointer on Student in Group's Constructor");
            }
            this.students[i] = new Student(students[i].getMarks());
        }
    }

    public float getAverage(){
        float average = 0;
        for (int i = 0; i < this.students.length; i++){
            average += this.students[i].getAverage();
        }
        return average / this.students.length;
    }

    public Student[] getStudents(){
        return this.students;
    }

    public int getHighAchievers(int limit) throws InvalidParameterException{
        int quantity = 0;
        if (limit < 0){
            throw new InvalidParameterException("Grade less than zero");
        }
        if (limit > 10){
            throw new InvalidParameterException("Grade more than ten");
        }
        for(int i = 0; i < this.students.length; i++){
            if (students[i].getAverage() >= limit){
                quantity++;
            }
        }
        return quantity;
    }

    public int getDowagers(int toFind){
        for(int i = 0; i < this.students.length; i++){
            if (students[i].contains(toFind, 0, students[i].getSize())){
                es++;
            }
        }
        return es;
    }
}
