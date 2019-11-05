import java.math.BigInteger;
import java.time.LocalDate;

public class ItemStatement {
    private String accountType;
    private BigInteger accountNumber;
    private String value;
    private LocalDate operationDate;
    private String reference;
    private String contractor;
    private double income;
    private double withdraw;

    public ItemStatement(String accountType, BigInteger accountNumber,
                         String value, LocalDate operationDate,
                         String reference, String contractor,
                         double income, double withdraw) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.value = value;
        this.operationDate = operationDate;
        this.reference = reference;
        this.contractor = contractor;
        this.income = income;
        this.withdraw = withdraw;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public String getValue() {
        return value;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public String getReference() {
        return reference;
    }

    public String getContractor() {
        return contractor;
    }

    public double getIncome() {
        return income;
    }

    public double getWithdraw() {
        return withdraw;
    }
}