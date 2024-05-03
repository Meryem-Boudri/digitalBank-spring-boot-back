package ma.enset.digitalbank.repositories;

import ma.enset.digitalbank.dtos.CustomerDTO;
import ma.enset.digitalbank.entities.BankAccount;
import ma.enset.digitalbank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAccountRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByNameContains(String keyword);
}