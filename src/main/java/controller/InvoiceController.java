package controller;

import model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.InvoiceService;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/store-bahamas-client/{invoiceId}")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveInvoice(@PathVariable String invoiceId, @RequestParam(name = "fiscal_id") String fiscalId, @RequestParam(name = "name") String customerName, @RequestParam(name = "email") String customerEmail) {
        invoiceService.addInvoice(invoiceId, fiscalId, customerName, customerEmail);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/retrieve-bahamas-client/{invoiceId}")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getInvoice(@PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getInvoice(invoiceId);
        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }

}
