package com.algaworks.algafood.api.v1.model.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDTO {

  @NotBlank
  private String nome;

  @NotBlank
  @Email
  private String email;

}
