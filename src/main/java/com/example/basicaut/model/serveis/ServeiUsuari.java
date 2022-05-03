package com.example.basicaut.model.serveis;

import com.example.basicaut.model.entitats.Usuari;
import com.example.basicaut.model.repositori.RepositoriUsuari;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServeiUsuari {

    private final RepositoriUsuari repositoriUsuari;

    private final PasswordEncoder xifrat;

    public Usuari consultarPerUsername(String username){
        return repositoriUsuari.findByUserName(username).orElse(null);
    }

    public Usuari crearNouUsuari(Usuari nouUsuari){
        nouUsuari.setPassword(xifrat.encode(nouUsuari.getPassword()));
        repositoriUsuari.save(nouUsuari);
        return nouUsuari;
    }

    public List<Usuari> llistarUsuaris(){
        return repositoriUsuari.findAll();
    }
}
