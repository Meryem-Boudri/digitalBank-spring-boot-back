package ma.enset.digitalbank.dtos;

import ma.enset.digitalbank.enums.AccountStatus;

import java.util.Date;

import lombok.Data;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;

}
