package mapper;

import model.Invoice;
import model.entity.InvoiceEntity;
import org.mapstruct.Mapper;

@Mapper
public interface InvoiceMapper {

    InvoiceEntity dataToInvoiceEntity(String id, String fiscalId, String customerName, String customerEmail);

    InvoiceEntity ToInvoiceEntity(Invoice invoice);

    Invoice ToInvoice(InvoiceEntity invoice);
}
