package com.example.ExamenSem10_Luis_Burga.Infrastructure.Repository;

import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonsJpaRespository extends JpaRepository<Persons,Long> {
    public List<Persons> findAllByEstado(long id);
}
