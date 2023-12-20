package com.zohocrm.repository;

import com.zohocrm.entity.Contact;
import com.zohocrm.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepostiory extends JpaRepository<Contact, String> {
    Optional<Contact> findByEmail(String email);
}
