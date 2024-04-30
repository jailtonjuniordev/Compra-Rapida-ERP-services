package com.jjdev.msempresas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "associates")
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cpfOrCnpj;

    private String name;

    @ManyToOne
    @JsonIgnoreProperties("associates")
    private Company companyId;
}
