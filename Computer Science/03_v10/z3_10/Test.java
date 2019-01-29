package z3_10;

import java.security.InvalidParameterException;
import java.util.Random;

public class Test {
    private static final int NUM_OF_MARKS = 5;
    private static final int NUM_OF_STUDENTS = 10;

    public static void main(String[] args) {
        int[][] data = new int[NUM_OF_STUDENTS][NUM_OF_MARKS];

        Random random = new Random();
        random.setSeed(255372517);
        for(int i = 0; i < NUM_OF_STUDENTS; i++){
            for(int j = 0; j < NUM_OF_MARKS; j++){
                data[i][j] = random.nextInt(11);
            }
        }

        /*Random rand = new Random();
        for(int i = 0; i < 4; i++){
            int indexMark = rand.nextInt(NUM_OF_MARKS);
            int indexStud = rand.nextInt(NUM_OF_STUDENTS);
            data[indexStud][indexMark] = -1;
        }*/

        Student[] students = new Student[NUM_OF_STUDENTS];

        for(int i = 0; i < NUM_OF_STUDENTS; i++){
            students[i] = new Student(data[i]);
        }

        Group group = new Group(students);

        try{
            System.out.println("The average grade in tested group is: " + group.getAverage());
            System.out.println();
            System.out.println("The number of high achievers is: " + group.getHighAchievers(9));
            System.out.println();
            System.out.println("The number of dowagers is: " + group.getDowagers(2));
            System.out.println();
            System.out.println("The student's grades are:");
            for (Student student:
                    group.getStudents()) {
                System.out.print("The student #" + student.getNumber() + " got ");
                for (int mark:
                        student.getMarks()) {
                    System.out.print(mark + " ");
                }
                System.out.println("His average is: " + student.getAverage());
            }
        }
        catch (InvalidParameterException e){
            e.printStackTrace();
        }
    }
}
