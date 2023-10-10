package com.example.ExamenSem10_Luis_Burga.Infrastructure.Controllers;

import com.example.ExamenSem10_Luis_Burga.Application.PersonsServices;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Persons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/persons")
public class PersonsController {
    @Autowired
    PersonsServices personsServices;

    @GetMapping
    public ResponseEntity<List<Persons>> getPersons() {
        return new ResponseEntity<>(personsServices.getPersons(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Persons> postPerson(@RequestBody Persons persons) {
        return new ResponseEntity<>(personsServices.createPerson(persons), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Persons> updatePerson(@PathVariable long id,@RequestBody Persons persons){
        return new ResponseEntity<>(personsServices.updatePerson(id,persons),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable long id ){
        return new ResponseEntity<>(personsServices.deletePerson(id),HttpStatus.OK);
    }


}
