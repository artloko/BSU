import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ThirdTask {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new File("input.txt"));
        System.out.println("**************");
        System.out.println("task 3");
        System.out.println("Make sure you have right data in \"input.txt\" file");
        System.out.println("**************");
        System.out.println();
        thirdVar(scanner);
    }

    static void thirdVar(Scanner scanner){
        while ( scanner.hasNextLine() ) {
            String s = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(s);

            while(tokenizer.hasMoreTokens()){
                String currToken = tokenizer.nextToken();

                if (currToken.matches("[a-zA-Z]"))
                    System.out.println(currToken + " ");
                else {
                    for(int i = 0; i < currToken.length(); i++){
                        if (currToken.charAt(i) == '.' && i + 2 < currToken.length())
                            currToken = currToken.substring(0, i + 2);
                    }
                }
                System.out.print(currToken + " ");
            }
            System.out.println();

        }
        scanner.close();
    }
}
