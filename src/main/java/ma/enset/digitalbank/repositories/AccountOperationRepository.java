package ma.enset.digitalbank.repositories;

import ma.enset.digitalbank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<BankAccount,String> {
}