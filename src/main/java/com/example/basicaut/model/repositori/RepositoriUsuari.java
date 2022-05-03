package com.example.basicaut.model.repositori;

import com.example.basicaut.model.entitats.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoriUsuari extends JpaRepository<Usuari, Long> {
    Optional<Usuari> findByUserName(String username);
}
