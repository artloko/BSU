import java.io.*;
import java.util.Locale;

public class Main {
    public static Learner[] createGroup(){
        Learner[] learners = new Learner[5];

        learners[0] = new Student(AppLocale.getString(AppLocale.name), Learner.Type.STUDENT, new int[]{5, 1, 2, 3}, 3, Student.Degree.BACHELOR);
        learners[1] = new Schoolboy(AppLocale.getString(AppLocale.name), Learner.Type.SCHOOLBOY, new int[]{5, 5, 5, 5}, 10);
        learners[2] = new Schoolboy(AppLocale.getString(AppLocale.name), Learner.Type.SCHOOLBOY, new int[]{2, 2, 2, 2}, 9);
        learners[3] = new Student(AppLocale.getString(AppLocale.name), Learner.Type.STUDENT, new int[]{4, 4, 5, 3}, 6, Student.Degree.MASTER);
        learners[4] = new Student(AppLocale.getString(AppLocale.name), Learner.Type.STUDENT, new int[]{5, 5, 5, 4}, 7, Student.Degree.DOCTORAL);

        return learners;
    }

    static Locale createLocale(String[] args){
        if ( args.length == 2 ) {
            return new Locale( args[0], args[1] );
        } else if( args.length == 4 ) {
            return new Locale( args[2], args[3] );
        }
        return null;
    }

    static void setupConsole(String[] args) {
        if ( args.length >= 2 ) {
            if ( args[0].compareTo("-encoding")== 0 ) {
                try {
                    System.setOut( new PrintStream( System.out, true, args[1] ));
                } catch ( UnsupportedEncodingException ex ) {
                    System.err.println( "Unsupported encoding: " + args[1] );
                    System.exit(1);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            PrintStream printStream = new PrintStream(System.out);
            setupConsole( args );
            Locale loc = createLocale( args );
            if ( loc == null ) {
                System.err.println(
                        "Invalid argument(s)\n" +
                                "Syntax: [-encoding ENCODING_ID] language country\n" +
                                "Example: -encoding Cp855 be BY" );
                System.exit(1);
            }
            AppLocale.setLocale(loc);
            Connector connector = new Connector("group.bat");
            connector.write(createGroup());
            Learner[] learners = connector.read();
            printStream.println(AppLocale.getString(AppLocale.group) + ":" );
            for (Learner n : learners) {
                printStream.println(n.toString());
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
