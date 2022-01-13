package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Invoice {
    private String id;
    private String fiscalId;
    private String customerName;
    private String customerEmail;
}
