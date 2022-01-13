package service;

import mapper.InvoiceMapper;
import model.entity.InvoiceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import repository.InvoiceRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository repository;

    @Mock
    private InvoiceMapper mapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    public void should_return_404_when_invoice_null() {
        String invalidInvoiceId = "invalidInvoiceId";

        doReturn(Optional.empty()).when(repository).findById(invalidInvoiceId);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> invoiceService.getInvoice(invalidInvoiceId));

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void should_add_invoice_and_send_notification() {
        String invalidInvoiceId = "invalidInvoiceId";
        InvoiceEntity invoiceEntity = mock(InvoiceEntity.class);

        doReturn(invoiceEntity).when(mapper).dataToInvoiceEntity("invoiceId", "fiscalId", "customerName", "customerEmail");

        invoiceService.addInvoice("invoiceId", "fiscalId", "customerName", "customerEmail");

        verify(restTemplate).getForEntity("https://bahamas.gov/register?invoice=invoiceId&fiscal_id=fiscalId&name=customerName&email=customerEmail", String.class);
        verify(repository).save(invoiceEntity);
    }

}