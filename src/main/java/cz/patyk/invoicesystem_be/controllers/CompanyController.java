package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/self/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<List<CompanyDtoOut>> getAll() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDtoOut> get(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PostMapping("")
    public ResponseEntity<CompanyDtoOut> newCompany(
            @RequestBody CompanyDtoIn companyDtoIn
    ) {
        return ResponseEntity.ok(
                companyService.newCompany(companyDtoIn)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDtoOut> edit(
            @RequestBody CompanyDtoIn companyDtoIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                companyService.edit(companyDtoIn, id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
