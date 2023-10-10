package com.example.ExamenSem10_Luis_Burga.Application;

import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.LoginUsuario;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Users;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Repository.UsersJpaRepository;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Security.CustomDetailServices;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Security.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UsersServices {
    @Autowired
    UsersJpaRepository usersJpaRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomDetailServices customDetailServices;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Users createPerson(Users users) {
        Users findUser = usersJpaRepository.findByUsuario(users.getUsuario());
        if (Objects.isNull(findUser)) {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setFecha_crea(new Date());
            users.setEstado(1);
            return usersJpaRepository.save(users);
        }
        return null;
    }

    public List<Users> getPersons() {
        return usersJpaRepository.findAllByEstado(1);
    }

    public Users updatePerson(long id, Users users) {
        Users findUser = usersJpaRepository.findById(id).orElse(null);
        if (!Objects.isNull(findUser) && findUser.getEstado() == 1) {
            findUser.setFecha_mod(new Date());
            if (!Objects.isNull(users.getUsuario())) {
                findUser.setUsuario(users.getUsuario());
            }
            if (!Objects.isNull(users.getPassword())) {
                findUser.setPassword(passwordEncoder.encode(users.getPassword()));
            }
            usersJpaRepository.save(findUser);
            return findUser;
        }
        return null;
    }

    public String deletePerson(long id) {
        Users findUser = usersJpaRepository.findById(id).orElse(null);
        if (!Objects.isNull(findUser)) {
            findUser.setEstado(0);
            usersJpaRepository.save(findUser);
            return "Usuario eliminado exitosamente";
        }
        return "Ocurrio un error al eliminar el usuario";
    }

    public ResponseEntity<String> loginUsuario(LoginUsuario loginUsuario) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getContrasenia()));
            if (authentication.isAuthenticated()) {
                return new ResponseEntity<>(jwtUtil.generateToken(customDetailServices.getUserDetail().getUsuario(), customDetailServices.getUserDetail().getId()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El usuario no está autenticado", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.info("{}", e);
        }

        return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);

    }

}
