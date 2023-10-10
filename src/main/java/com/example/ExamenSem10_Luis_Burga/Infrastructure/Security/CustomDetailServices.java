package com.example.ExamenSem10_Luis_Burga.Infrastructure.Security;

import com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity.Users;
import com.example.ExamenSem10_Luis_Burga.Infrastructure.Repository.UsersJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
@Service
@Slf4j
public class CustomDetailServices implements UserDetailsService {

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    private Users user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = usersJpaRepository.findByUsuario(username);
        log.info(String.valueOf(Objects.isNull(user)));
        if (!Objects.isNull(user)) {
            return new User(user.getUsuario(), user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    public Users getUserDetail() {
        return user;
    }
}
