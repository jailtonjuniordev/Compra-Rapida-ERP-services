package com.jjdev.msempresas.domain.repository;

import com.jjdev.msempresas.domain.entity.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, UUID> {
}
