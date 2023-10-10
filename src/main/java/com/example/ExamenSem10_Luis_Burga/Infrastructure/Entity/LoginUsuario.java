package com.example.ExamenSem10_Luis_Burga.Infrastructure.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class LoginUsuario {
    private String email;
    @JsonProperty("contraseña")
    private String contrasenia;
}
