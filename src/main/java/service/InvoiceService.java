package service;

import mapper.InvoiceMapper;
import model.Invoice;
import model.entity.InvoiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import repository.InvoiceRepository;

import java.util.Optional;

import static constants.UrlConstants.BAHAMAS_URL;

public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private InvoiceMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    public void addInvoice(String invoiceId, String fiscalId, String customerName, String customerEmail) {
        InvoiceEntity invoiceEntity = mapper.dataToInvoiceEntity(invoiceId, fiscalId, customerName, customerEmail);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(BAHAMAS_URL).queryParam("invoice", invoiceId).queryParam("fiscal_id", fiscalId).queryParam("name", customerName).queryParam("email", customerEmail).toUriString();

        restTemplate.getForEntity(urlTemplate, String.class);
        repository.save(invoiceEntity);
    }

    public Invoice getInvoice(String invoiceId) {
        Optional<InvoiceEntity> invoice = repository.findById(invoiceId);

        return invoice.map(mapper::ToInvoice).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "invoice not found", null));
    }


}
