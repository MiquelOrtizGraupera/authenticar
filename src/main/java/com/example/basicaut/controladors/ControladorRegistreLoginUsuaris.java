package com.example.basicaut.controladors;

import com.example.basicaut.model.entitats.Usuari;
import com.example.basicaut.model.entitats.UsuariConsultaDTO;
import com.example.basicaut.model.serveis.ServeiUsuari;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorRegistreLoginUsuaris {
    private ServeiUsuari serveiUsuari;

    @GetMapping("/me")
    public UsuariConsultaDTO QuiEts(@AuthenticationPrincipal Usuari usuari){
        return new UsuariConsultaDTO(usuari.getUsername(), usuari.getAvatar(), usuari.getRol());
    }

    @GetMapping("/users")
    public ResponseEntity<?> llistarUsuarisDTO(){
        List<Usuari> userList = serveiUsuari.llistarUsuaris();
        List<UsuariConsultaDTO> aux = new ArrayList<>();
        for (Usuari usuari : userList){
            aux.add(new UsuariConsultaDTO(usuari.getUsername(), usuari.getAvatar(), usuari.getRol()));
        }
        if(userList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else return ResponseEntity.ok(aux);
    }

    @PostMapping("/users")
    public ResponseEntity<?> nouUsuari(@RequestBody Usuari nouUsuari){
        try{
            Usuari user = serveiUsuari.crearNouUsuari(nouUsuari);
            UsuariConsultaDTO userDTO = new UsuariConsultaDTO(user.getUsername(),user.getAvatar(),user.getRol());
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }catch (DataIntegrityViolationException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
