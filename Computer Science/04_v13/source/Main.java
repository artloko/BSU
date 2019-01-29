import account.Account;
import account.ArgException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ArgException, IOException {
        Scanner in = new Scanner(System.in);
        Account[] accounts = new Account[5];
        accounts[0] = new Account("ARTEM|11022000222222222222|DLR|20|1000");
        accounts[1] = new Account("DANIIL|44444444444444444444|RUB|15|1200");
        accounts[2] = new Account("VOVA|33333333333333333333|EUR|25|10");
        accounts[3] = new Account("SANIA|55555555555555555555|DLR|50|200");
        accounts[4] = new Account("SEMEN|99999999999999999999|DLR|10|3000");

        int choice = -1;
        while(choice != 0){
            System.out.println("--------------------- What are u gonna do?");
            System.out.println("1) Change field for Comparable");
            System.out.println("2) Sort by chosen field");
            System.out.println("3) Print");
            System.out.print("Enter the option: ");
            choice = in.nextInt();
            switch (choice){
                case 1: {
                    int idxForSort = 0;
                    System.out.println("--------------------- By which field to sort");
                    System.out.println("1) Username");
                    System.out.println("2) Bank Account Number");
                    System.out.println("3) Currency");
                    System.out.println("4) Percent Per Year");
                    System.out.println("5) Value");
                    System.out.print("Enter the option: ");
                    idxForSort = in.nextInt();
                    Account.setSortBy(idxForSort - 1);
                    break;
                }
                case 2: {
                    Arrays.sort(accounts, Account::compareTo);
                    break;
                }
                case 3: {
                    for (int i = 0; i < accounts.length; i++){
                        System.out.println(accounts[i].toString());
                    }
                    break;
                }
                default:
                    break;
            }
        }

    }



}
