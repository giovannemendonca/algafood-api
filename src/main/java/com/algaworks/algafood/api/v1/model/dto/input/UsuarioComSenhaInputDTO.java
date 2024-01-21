package com.algaworks.algafood.api.v1.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInputDTO extends UsuarioInputDTO {

  // aqui é a senha deve ter no mínimo 6 caracteres
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9]{6,}$", message = "Senha deve ter no mínimo 6 caracteres")
  private String senha;
}
