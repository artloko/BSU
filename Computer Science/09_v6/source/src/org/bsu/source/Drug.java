package org.bsu.source;

import java.io.PrintStream;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Drug implements Serializable {
    private static final long serialVersionUID = 1L;

    String drugStore;
    static final String P_drugStore = "Drugstore";
    String name;
    static final String P_name = "Name";
    String amount;
    static final String P_amount = "Amount";
    String cost;
    static final String P_cost = "Cost";
    Date dateOfReceipt;
    static final String P_dateOfReceipt = "Date of receipt";
    Date shelfLife;
    static final String P_shelfLife = "ShelfLife";

    static Boolean validDrugStore(String name){
        int ndig = 0;
        for(int i = 0; i < name.length(); i++){
            char ch = name.charAt(i);
            if (Character.isDigit(ch)){
                ndig++;
            }
        }
        return (ndig > 0 && ndig < 4);
    }

    public static Boolean nextRead(Scanner scanner, PrintStream stream){
        return nextRead(P_drugStore, scanner, stream);
    }

    static Boolean nextRead(String promt, Scanner scanner, PrintStream stream){
        stream.print(promt + ": ");
        return scanner.hasNextLine();
    }

    public static Drug read(Scanner scanner, PrintStream stream){
        Drug drug = new Drug();
        drug.drugStore = scanner.nextLine();
        if (!validDrugStore(drug.drugStore))
            throw new InvalidParameterException("Wrong parameter: " + drug.drugStore);
        if (!nextRead(P_name, scanner, stream)) return null;
        drug.name = scanner.nextLine();
        if (!nextRead(P_amount, scanner, stream)) return null;
        drug.amount = scanner.nextLine();
        if (!nextRead(P_cost, scanner, stream)) return null;
        drug.cost = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            if (!nextRead(P_dateOfReceipt, scanner, stream)) return null;
            drug.dateOfReceipt = dateFormat.parse(scanner.nextLine());
            if (!nextRead(P_shelfLife, scanner, stream)) return null;
            drug.shelfLife = dateFormat.parse(scanner.nextLine());
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return drug;
    }

    private Drug(){
    }

    public static final String areaDel = "\n";

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                drugStore + areaDel +
                        name + areaDel +
                        amount + areaDel +
                        cost + areaDel +
                        dateOfReceipt.toString() + areaDel +
                        shelfLife.toString());
        return builder.toString();
    }
}
