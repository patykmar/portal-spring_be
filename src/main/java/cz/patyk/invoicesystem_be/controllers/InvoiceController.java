package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.entities.Invoice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom/api/v1")
public class InvoiceController {

    @GetMapping("/invoice/{id}")
    public Invoice getInvoiceDetail(@PathVariable int id ){
        return new Invoice();
    }

}
