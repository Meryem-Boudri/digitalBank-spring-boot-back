package ma.enset.digitalbank.repositories;

import ma.enset.digitalbank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

    List<BankAccount> findBankAccountByCustomerId(Long customer_id);
}