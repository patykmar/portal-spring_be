package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.VatDto;
import cz.patyk.invoicesystem_be.service.VatService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/self/rest-manual")
@AllArgsConstructor
public class VatController {
    @NonNull
    private final VatService vatService;

    @GetMapping("/simple-vats")
    public List<VatDto> findAll(){
        return vatService.getAllVats();
    }
}
