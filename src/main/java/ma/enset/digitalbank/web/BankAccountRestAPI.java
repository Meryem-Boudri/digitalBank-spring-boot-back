package ma.enset.digitalbank.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digitalbank.dtos.AccountHistoryDTO;
import ma.enset.digitalbank.dtos.AccountOperationDTO;
import ma.enset.digitalbank.dtos.BankAccountDTO;
import ma.enset.digitalbank.dtos.CustomerDTO;
import ma.enset.digitalbank.exceptions.BalanceNotSufficientException;
import ma.enset.digitalbank.exceptions.BankAccountNotFoundException;
import ma.enset.digitalbank.exceptions.CustomerNotFoundException;
import ma.enset.digitalbank.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;
    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/accounts/debit")
    public void debit(  @RequestParam String accountId,
                                       @RequestParam Double amount,
                                       @RequestParam String  description) throws BankAccountNotFoundException, BalanceNotSufficientException {
         bankAccountService.debit(accountId,amount,description);
    }
    @PostMapping("/accounts/credit")
    public void credit(  @RequestParam String accountId,
                        @RequestParam Double amount,
                        @RequestParam String  description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.credit(accountId,amount,description);
    }
    @PostMapping("/accounts/transfer")
    public void transfer(  @RequestParam String accountId,
                        @RequestParam Double amount,
                        @RequestParam String  accountDestination) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(accountId,accountDestination,amount);
    }
    @GetMapping("/customer/accounts")
    public List<BankAccountDTO> accountsByCusstomer(@RequestParam Long id){
       return  bankAccountService.getBankAccountListByCustomer(id);
    }


}
