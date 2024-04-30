package com.jjdev.msempresas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CNPJ
    @Column(unique = true, nullable = false, updatable = false)
    private String cnpj;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "company_size", nullable = false)
    private String companySize;

    @Column(name = "legal_nature", nullable = false)
    private String legalNature;

    @Column(name = "is_national_simple", nullable = false)
    private Boolean isNationalSimple;

    @Column(name = "start_activities_date", nullable = false)
    private Date startActivitiesDate;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String cityState;

    @OneToMany
    private List<Associate> associates;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;
}
