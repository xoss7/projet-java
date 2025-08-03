package com.dic1git.cartographie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AdministrateurResponseDTO {
    private Long id;
    private String username;
    private String email;
}