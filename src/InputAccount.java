import java.util.Optional;

public class InputAccount {
    private String cardNumber;
    private String pinCode;

    public InputAccount(String cardNumber, String pinCode ) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
    }

   public Optional<BankAccount> findBankAccount(BankAccount[] accounts) {
       BankAccount res;
       for (int i = 0; i < accounts.length; i++) {
           if (accounts[i].checkAccount(this.cardNumber, this.pinCode)) {
               return Optional.of(accounts[i]);
           }
       }
       return Optional.empty();
   }
}
