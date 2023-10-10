package com.example.ExamenSem10_Luis_Burga.Application;

import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Persons;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Repository.PersonsJpaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PersonsServices {
    @Autowired
    PersonsJpaRespository personsJpaRespository;

    public Persons createPerson(Persons persons) {
        persons.setFecha_crea(new Date());
        persons.setEstado(1);
        return personsJpaRespository.save(persons);
    }

    public List<Persons> getPersons() {
        return personsJpaRespository.findAllByEstado(1);
    }

    public Persons updatePerson(long id, Persons persons) {
        Persons findPerson = personsJpaRespository.findById(id).orElse(null);
        if (!Objects.isNull(findPerson) && findPerson.getEstado() == 1) {
            findPerson.setFecha_mod(new Date());
            if (!Objects.isNull(persons.getNombre())) {
                findPerson.setNombre(persons.getNombre());
            }
            if (!Objects.isNull(persons.getApellidos())) {
                findPerson.setApellidos(persons.getApellidos());
            }
            if (!Objects.isNull(persons.getEmail())) {
                findPerson.setEmail(persons.getEmail());
            }
            if (!Objects.isNull(persons.getDireccion())) {
                findPerson.setDireccion(persons.getDireccion());
            }
            personsJpaRespository.save(findPerson);
            return findPerson;
        }
        return null;
    }

    public String deletePerson(long id) {
        Persons findPerson = personsJpaRespository.findById(id).orElse(null);
        if (!Objects.isNull(findPerson)) {
            findPerson.setEstado(0);
            personsJpaRespository.save(findPerson);
            return "Usuario eliminado exitosamente";
        }
        return "Ocurrio un error al eliminar el usuario";
    }

}
