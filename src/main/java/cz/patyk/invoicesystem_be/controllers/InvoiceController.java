package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.entities.Invoice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/self/invoice/api/v1")
public class InvoiceController {

    @GetMapping("/{id}")
    public Invoice getInvoiceDetail(@PathVariable int id ){
        return new Invoice();
    }

}
