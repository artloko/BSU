import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Drug implements Serializable {
    String drugStore;
    String name;
    String amount;
    String cost;
    Date dateOfReceipt;
    Date shelfLife;

    public static Drug read(Scanner scanner){
        Drug drug = new Drug();
        drug.drugStore = scanner.nextLine();
        if (!scanner.hasNextLine()) return null;
        drug.name = scanner.nextLine();
        if (!scanner.hasNextLine()) return null;
        drug.amount = scanner.nextLine();
        if (!scanner.hasNextLine()) return null;
        drug.cost = scanner.nextLine();
        if (!scanner.hasNextLine()) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            drug.dateOfReceipt = dateFormat.parse(scanner.nextLine());
            if (!scanner.hasNextLine()) return null;
            drug.shelfLife = dateFormat.parse(scanner.nextLine());
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return drug;
    }

    private Drug(){
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                drugStore + "|" +
                        name + "|" +
                        amount + "|" +
                        cost + "|" +
                        dateOfReceipt.toString() + "|" +
                        shelfLife.toString());
        return builder.toString();
    }
}
