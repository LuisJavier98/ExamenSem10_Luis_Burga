package com.example.ExamenSem10_Luis_Burga.Infrastructure.Controllers;

import com.example.ExamenSem10_Luis_Burga.Application.UsersServices;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.LoginUsuario;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    UsersServices usersServices;

    @GetMapping
    public ResponseEntity<List<Users>> getPersons() {
        return new ResponseEntity<>(usersServices.getPersons(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Users> postPerson(@RequestBody Users users) {
        return new ResponseEntity<>(usersServices.createPerson(users), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Users> updatePerson(@PathVariable long id,@RequestBody Users users){
        return new ResponseEntity<>(usersServices.updatePerson(id,users),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable long id ){
        return new ResponseEntity<>(usersServices.deletePerson(id),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> usuarioLogin(@RequestBody LoginUsuario loginUsuario) {
        String respuesta = String.valueOf(usersServices.loginUsuario(loginUsuario));
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
