package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

  private int numeroMultiplo;

  // o initialize é chamado antes do isValid e é onde podemos inicializar os atributos da classe
  // que implementa o ConstraintValidator
  @Override
  public void initialize(Multiplo constraintAnnotation) {
    this.numeroMultiplo = constraintAnnotation.numero();
  }

  // o isValid é chamado para validar o valor do atributo anotado
  // o isValid retorna true se o valor for válido e false se o valor for inválido
  @Override
  public boolean isValid(Number value, ConstraintValidatorContext context) {
    boolean valido = true;
    if (value != null) {
      var valorDecimal = BigDecimal.valueOf(value.doubleValue());
      var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
      var resto = valorDecimal.remainder(multiploDecimal);

      valido = BigDecimal.ZERO.compareTo(resto) == 0;
    }
    return valido;
  }
}
