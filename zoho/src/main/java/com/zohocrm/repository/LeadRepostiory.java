package com.zohocrm.repository;

import com.zohocrm.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.Optional;

public interface LeadRepostiory extends JpaRepository<Lead, String> {
    Optional<Lead> findByEmail(String email);
    Optional<Lead> findByMobile(Long mobile);
    boolean existsByEmail(String email);
    boolean existsByMobile(Long mobile);
}
