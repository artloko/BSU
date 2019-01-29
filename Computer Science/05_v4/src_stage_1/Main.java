
public class Main {

    public static Learner[] createGroup(){
        Learner[] learners = new Learner[5];

        learners[0] = new Student("Victor", Learner.Type.STUDENT, new int[]{5, 1, 2, 3}, 3, Student.Degree.BACHELOR);
        learners[1] = new Schoolboy("Semen", Learner.Type.SCHOOLBOY, new int[]{5, 5, 5, 5}, 10);
        learners[2] = new Schoolboy("Evgeny", Learner.Type.SCHOOLBOY, new int[]{2, 2, 2, 2}, 9);
        learners[3] = new Student("Petr", Learner.Type.STUDENT, new int[]{4, 4, 5, 3}, 6, Student.Degree.MASTER);
        learners[4] = new Student("Ivan", Learner.Type.STUDENT, new int[]{5, 5, 5, 4}, 7, Student.Degree.DOCTORAL);

        return learners;
    }

    public static void main(String[] args) {

        try {
            Connector con = new Connector("band.dat");
            con.write( createGroup());
            Learner[] group = con.read();
            System.out.println( "The band: ");
            for ( Learner n : group ) {
                System.out.println( n );
            }
        }
        catch ( Exception e ) {
            System.err.println(e);
        }

    }

}
