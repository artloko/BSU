package z3_10;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class Student {

    private static int counter = 0;
    private int[] marks = null;
    private int number = 0;

    public Student(int[] marks) throws InvalidParameterException{
        int length = marks.length;
        this.marks = new int[length];
        for (int i = 0; i < length; i++){
            if (marks[i] < 0){
                throw new InvalidParameterException("Grade less than zero");
            }
            if (marks[i] > 10){
                throw new InvalidParameterException("Grade more than ten");
            }
            this.marks[i] = marks[i];
        }
        Arrays.sort(marks);
        number = counter;
        counter++;
    }

    public int getNumber(){
        return this.number;
    }

    public int getSize(){
        return marks.length;
    }

    public int[] getMarks(){
        return marks;
    }

    public float getAverage(){
        float average = 0;
        for (int i = 0; i < this.marks.length; i++){
            average += this.marks[i];
        }
        return average / this.marks.length;
    }

    public boolean contains(int mark, int low, int high){
        int mid;
        while (low < high) {
            mid = (low + high)/2;
            if (mark == this.marks[mid]) {
                return true;
            } else {
                if (mark <= this.marks[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return false;
    }
}
