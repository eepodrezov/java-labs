import java.util.Optional;
import java.util.Scanner;
public class ATM {

    public static BankAccount[] createAccounts(int personsNum, int[] balances) {
        BankAccount[] res = new BankAccount[personsNum];
        for (int i = 0; i < personsNum; i++) {
            RandomNameGenerator randomNameGenerator = new RandomNameGenerator();
            String randomName = randomNameGenerator.generateRandomName();
            Person owner = new Person(randomName);
            String newCardNumber = "123" + i;
            String newPinCode = "000" + i;
            BankAccount newAccount = new BankAccount(balances[i],newCardNumber, newPinCode,owner);
            res[i] = newAccount;
        }
        return res;
    }

    public static void getAccount(Scanner scanner , BankAccount[] accounts) {
        System.out.print("Введите номер карты: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Введите PIN-код: ");
        String pinCode = scanner.nextLine();
        InputAccount inputAccount = new InputAccount(cardNumber, pinCode);
        Optional<BankAccount> selectedAccount = inputAccount.findBankAccount(accounts);
        if (selectedAccount.isEmpty()) {
            System.out.println("Нет такой карты! Попробуйте еще раз!");
            getAccount(scanner, accounts);
        }
        if (selectedAccount.isPresent()) {
            selectedAccount.get().workWithAccount();
        }
    }
    public static void startATM() {
        int[] balances = { 10000, 3000, 1000 };
        BankAccount[] accounts =  createAccounts(3, balances);
        System.out.println("Есть 3 пользователя: ");
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].printDetailsLine();
        }
        Scanner scanner = new Scanner(System.in);
        getAccount(scanner, accounts);
    }
}
