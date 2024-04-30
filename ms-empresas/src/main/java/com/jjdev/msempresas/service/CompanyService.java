package com.jjdev.msempresas.service;

import com.jjdev.msempresas.domain.entity.Address;
import com.jjdev.msempresas.domain.entity.Company;
import com.jjdev.msempresas.domain.repository.CompanyRepository;
import com.jjdev.msempresas.rest.dto.CompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final String API_URL = "https://publica.cnpj.ws/cnpj/";

    private final CompanyRepository companyRepository;

    private final RestTemplate restTemplate;


    public Company obterDadosCNPJ(CompanyDTO cnpj) throws Exception {
        String url = API_URL + cnpj;

        Map dadosCNPJ = restTemplate.getForObject(url, Map.class);

        if (dadosCNPJ == null) {
            throw new Exception("CNPJ Incorreto ou no formato errado");
        }

        Map cidadeEstado = (Map) dadosCNPJ.get("estabelecimento");

        Address companyAddress = Address.builder()
                .street(((Map) dadosCNPJ.get("estabelecimento")).get("tipo_logradouro") + " " + ((Map) dadosCNPJ.get("estabelecimento")).get("logradouro"))
                .district((String) ((Map) dadosCNPJ.get("estabelecimento")).get("bairro"))
                .complement((String) ((Map) dadosCNPJ.get("estabelecimento")).get("complemento"))
                .number((String) ((Map) dadosCNPJ.get("estabelecimento")).get("numero"))
                .cityState(((Map) cidadeEstado.get("cidade")).get("nome") + "/" + (((Map) cidadeEstado.get("estado")).get("sigla")))
                .zipCode((String) ((Map) dadosCNPJ.get("estabelecimento")).get("cep"))
                .build();

        System.out.println(dadosCNPJ.get("socios"));
        System.out.println(((Map) dadosCNPJ.get("simples")).get("simples"));
        System.out.println(((Map) dadosCNPJ.get("estabelecimento")).get("data_inicio_atividade"));

        return Company.builder()
                .companyName((String) ((Map) dadosCNPJ.get("estabelecimento")).get("nome_fantasia"))
                .businessName((String) dadosCNPJ.get("razao_social"))
                .cnpj(cnpj.cnpj())
                .email((String) ((Map) dadosCNPJ.get("estabelecimento")).get("email"))
                .phoneNumber(((Map) dadosCNPJ.get("estabelecimento")).get("ddd1") + (String) ((Map) dadosCNPJ.get("estabelecimento")).get("telefone1"))
                .legalNature((String) ((Map) dadosCNPJ.get("natureza_juridica")).get("descricao"))
                .companySize((String) ((Map) dadosCNPJ.get("porte")).get("descricao"))
                .isNationalSimple(((Map) dadosCNPJ.get("simples")).get("simples").equals("Sim") ? Boolean.TRUE : Boolean.FALSE)
                .startActivitiesDate(new Date())
                .addressId(companyAddress)
                .build();
    }
}
