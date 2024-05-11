package ma.enset.digitalbank;

import ma.enset.digitalbank.dtos.BankAccountDTO;
import ma.enset.digitalbank.dtos.CurrentBankAccountDTO;
import ma.enset.digitalbank.dtos.CustomerDTO;
import ma.enset.digitalbank.dtos.SavingBankAccountDTO;
import ma.enset.digitalbank.exceptions.CustomerNotFoundException;
import ma.enset.digitalbank.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankApplication.class, args);
    }


@Bean
  /*  CommandLineRunner commandLineRunner(BankAccountService bankAccountService, JdbcUserDetailsManager jdbcUserDetailsManager){
    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return args -> {
            jdbcUserDetailsManager.createUser(User.withUsername("soumaya").password(passwordEncoder.encode("1234")).roles("USER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("meryem").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());

        };
}*/

CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
    return args -> {
        Stream.of("Hassan","Meryem","Mohamed").forEach(name->{
            CustomerDTO customer=new CustomerDTO();
            customer.setName(name);
            customer.setEmail(name+"@gmail.com");
            bankAccountService.saveCustomer(customer);
        });
        bankAccountService.listCustomers().forEach(customer->{
            try {
                bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
                bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

            } catch (CustomerNotFoundException e) {
                e.printStackTrace();
            }
        });
        List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
        for (BankAccountDTO bankAccount:bankAccounts){
            for (int i = 0; i <10 ; i++) {
                String accountId;
                if(bankAccount instanceof SavingBankAccountDTO){
                    accountId=((SavingBankAccountDTO) bankAccount).getId();
                } else{
                    accountId=((CurrentBankAccountDTO) bankAccount).getId();
                }
                bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
            }
        }
    };
}
}