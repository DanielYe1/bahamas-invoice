package controller;

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
    public ResponseEntity saveItem(@PathVariable String invoiceId,
                                   @RequestParam(name = "fiscal_id") String fiscalId,
                                   @RequestParam(name = "name") String customerName,
                                   @RequestParam(name = "email") String customerEmail) {

        invoiceService.addInvoice(invoiceId, fiscalId, customerName, customerEmail);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
