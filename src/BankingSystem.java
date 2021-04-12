import java.io.Console;
import java.util.Scanner;

class Banking{
    //customer information
    private int id=127999;
    private String name;
    private double amount;
    private double balance;
    private int retry;
    private double previuosBalance;
    Console console = System.console();
    //customer create account
    void createAccount(){
        int choice;
        System.out.println("***");

        choice = Integer.parseInt(console.readLine(">Enter 1 to create an account:\n>Press any key to exit:"));
        if(choice == 1) {
            do {
                name = console.readLine();
                for (int i = 0; i < name.length(); i++) {
                    if (Character.isLetter(name.charAt(i)) || name.charAt(i) == ' ') {
                        retry = 0;
                    } else {
                        System.out.println("WARNING: Name should contain only letters");
                        retry = 1;
                        break;
                    }
                }
            } while (retry == 1);
        }
        else {
            System.exit(0);
        }

            do{
                try{
                    balance = Double.parseDouble(console.readLine(">Deposit initial balance.\n"));
                    retry = 0;
                    if (balance >= 5000){
                        System.out.println("***Account created successfully!***");
                        System.out.println("Customer name:"+name);
                        System.out.println("Account number:"+id);
                        System.out.println("***Thank you for choosing our bank");
                    } else if (balance < 5000){
                        System.out.println("Initial balance cannot be less than 5000");
                        retry = 1;
                    }
                } catch (NumberFormatException e){
                    System.out.println("WARNING: Please enter amount in digits!");
                    retry = 1;
                }
            } while (retry == 1);
        }

        void deposit(){
            do{
                try{
                    System.out.println(">Enter 0 to terminate Transaction");
                    amount = Double.parseDouble(console.readLine(">Enter amount to deposit:\n"));
                    if (amount > 0){
                        balance+=amount;
                        System.out.println("Successfully deposited amount");
                        previuosBalance = amount;
                        retry = 0;
                    } else if (amount == 0){
                        System.out.println("Terminated!");
                        retry = 0;
                    } else if (amount < 0){
                        System.out.println("Amount cannot be < 0");
                        retry = 1;
                    }
                } catch (NumberFormatException e){
                    System.out.println("WARNING: Enter amount only in digits!\n");
                    retry = 1;
                }
            } while (retry == 1);
        }


    void withdraw(){
        do{
            try {
                System.out.println("***");
                System.out.println(">Enter 0 to terminate transaction.");
                amount = Double.parseDouble(console.readLine(">Enter amount to withdraw: \n"));
                retry = 0;

                if (amount <= balance && amount > 0){
                    balance-=amount;
                    System.out.println("Withdraw successful!");
                    previuosBalance-=amount;
                    retry = 0;
                } else if (amount > balance){
                    System.out.println("Insufficient funds!");
                    retry = 1;
                } else if (amount == 0){
                    System.out.println("Terminated!");
                    retry = 0;
                } else if (amount < 0){
                    System.out.println("Amount cannot be < 0!");
                    retry = 1;
                }
            } catch (NumberFormatException e){
                System.out.println("WARNING:Enter amount in digits!\n");
                retry = 1;
            }
        } while (retry == 1);
    }

    void PreviuosTransaction(double previuosBalance){
        if (previuosBalance > 0){
            System.out.println("Last balance deposit was:"+previuosBalance);
        } else {
            System.out.println("Last balance withdraw was:"+Math.abs(previuosBalance));
        }
    }

    void showBalance(double balance){
        System.out.println("Updated balance is:"+balance);
    }

    void showAccountInformation(int id, String name, double balance){
        System.out.println("Customer name:"+name);
        System.out.println("Account number:"+id);
        System.out.println("Updated balance:"+balance);
    }

    public void menu() {
        createAccount();
        int flag = 0;
        do
        {
            System.out.println();
            System.out.println("***");
            System.out.println("SELECTED OPERATION TO BE PERFORMED ");
            System.out.println("1. Deposit\n2.Withdraw\n3.Show previous transaction\n4.Show balance\n5.Show account information\n6.Exit\n");

            do
            {
                try
                {
                    flag = Integer.parseInt(console.readLine());
                    retry = 0;
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Please select valid option: ");
                    retry = 1;
                }
            }while(retry == 1);

            switch (flag) {
                case 1 -> {
                    System.out.println();
                    System.out.println("***");
                    deposit();
                }
                case 2 -> {
                    System.out.println();
                    System.out.println("***");
                    withdraw();
                }
                case 3 -> PreviuosTransaction(previuosBalance);
                case 4 -> {
                    System.out.println();
                    System.out.println("***");
                    showBalance(balance);
                }
                case 5 -> {
                    System.out.println();
                    System.out.println("***");
                    showAccountInformation(Math.toIntExact(id), name, balance);
                }
                case 6 -> {
                    System.out.println();
                    System.out.println("***Thank you for using our services!");
                }
                default -> System.out.println("Please select valid option: ");
            }
        } while (flag != 6);
    }
}

public class BankingSystem {
    public static void main(String[] args) throws Exception{
        Banking banking = new Banking();
        banking.menu();
    }
}
