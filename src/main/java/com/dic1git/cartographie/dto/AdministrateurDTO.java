package com.dic1git.cartographie.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class AdministrateurDTO {
    @NotBlank(message = "Le username est obligatoire.")
    private String username;
    @NotBlank(message = "L'email est obligatoire")
    private String email;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
}