package ma.enset.digitalbank.dtos;

import ma.enset.digitalbank.entities.OperationType;

import java.util.Date;

import lombok.Data;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}