package service;

import mapper.InvoiceMapper;
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
import static org.mockito.Mockito.doReturn;

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

}