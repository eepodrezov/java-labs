import java.util.Optional;
import java.util.Scanner;

public class BankAccount {
    private int balance;
    private String cardNumber;
    private String pinCode;

    private Person owner;

    public BankAccount(int balance, String cardNumber, String pinCode, Person owner) {
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.owner = owner;
    }

    public boolean checkAccount(String cardNumber, String pinCode) {
        return this.cardNumber.equals(cardNumber) && this.pinCode.equals(pinCode);
    }

    public static boolean getIsValidOperation(String operation) {
        String[] validOperations = {"1","2","3", "4"};
        return ArrayHelpers.includes(validOperations,operation);
    }

    public int getIntValue(Scanner scanner){
        String input = scanner.nextLine();
        try {
             return Integer.parseInt(input);


        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не число");
            workWithAccount();
        }
        return 0;
    }

    public void doOperation(String operation, Scanner scanner) {
        if (getIsValidOperation(operation)) {
            switch (operation){
                case "4": {
                    return;
                }
                case "1": {
                    balance();
                    workWithAccount();
                    break;
                }
                case "2": {
                    System.out.println("Введите сумму!");
                    int value = getIntValue(scanner);
                    deposit(value);
                    break;
                }
                case "3": {
                    System.out.println("Введите сумму!");
                    int value = getIntValue(scanner);
                    withdraw(value);
                    break;
                }
            }
            workWithAccount();
        } else {
            System.out.println("Вы выбрали не правильную операцию!");
        }
    }

    public void workWithAccount() {
        System.out.println("Выберите операцию. 1-баланс, 2-положить, 3-снять, 4- выйти");
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        doOperation(operation, scanner);
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Недостаточно средств на счету.");
        }
    }

    public void balance() {
        System.out.println("Баланс: " + this.balance);
    }

    public void printDetails() {
        this.owner.print();
        System.out.println("Баланс: " + balance);
        System.out.println("Номер карты: " + cardNumber);
        System.out.println("PIN-код: " + pinCode);
    }

    public void printOwner() {
        this.owner.print();
    }

    public void printDetailsLine() {
        System.out.println(this.owner.getName() + '(' + this.cardNumber + "PIN" + this.pinCode + ')');
    }
}
