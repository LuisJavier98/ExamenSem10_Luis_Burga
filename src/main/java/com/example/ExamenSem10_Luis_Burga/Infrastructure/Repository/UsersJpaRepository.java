package com.example.ExamenSem10_Luis_Burga.Infrastructure.Repository;

import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Persons;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersJpaRepository extends JpaRepository<Users,Long> {
    public List<Users> findAllByEstado(long id);
    public Users findByUsuario(String usuario);
}
