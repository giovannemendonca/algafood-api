package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

  private String valorField;
  private String descricaoField;
  private String descricaoObrigatoria;


  @Override
  public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
    this.valorField = constraintAnnotation.valorField();
    this.descricaoField = constraintAnnotation.descricaoField();
    this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
  }

  @Override
  public boolean isValid(Object objectValidacao, ConstraintValidatorContext context) {
    boolean valido = true;
    try {
      BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objectValidacao.getClass(), valorField).getReadMethod().invoke(objectValidacao);
      String descricao = (String) BeanUtils.getPropertyDescriptor(objectValidacao.getClass(), descricaoField).getReadMethod().invoke(objectValidacao);

      if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
        valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
      }

    } catch (Exception e) {
      throw new ValidationException(e);
    }

    return valido;
  }
}
