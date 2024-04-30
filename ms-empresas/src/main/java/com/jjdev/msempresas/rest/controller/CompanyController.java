package com.jjdev.msempresas.rest.controller;

import com.jjdev.msempresas.domain.entity.Company;
import com.jjdev.msempresas.rest.dto.CompanyDTO;
import com.jjdev.msempresas.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/")
    public Company obterDadosCNPJ(@RequestBody CompanyDTO cnpj) throws Exception {
        return companyService.obterDadosCNPJ(cnpj);
    }
}
