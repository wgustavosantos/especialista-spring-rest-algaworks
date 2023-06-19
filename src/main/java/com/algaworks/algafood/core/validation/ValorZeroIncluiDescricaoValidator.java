package com.algaworks.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements
        ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.valorField = constraintAnnotation.valorField();
        this.descricaoField = constraintAnnotation.descricaoField();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     *
     * @param objetoValicadao object to validate - objeto da classe na qual está anotada a valicadao
     * @param context context in which the constraint is evaluated
     */
    @Override
    public boolean isValid(Object objetoValicadao, ConstraintValidatorContext context) {
        boolean isValid = true;

        try {
            final BigDecimal taxaFrete = (BigDecimal) BeanUtils
                    .getPropertyDescriptor(objetoValicadao.getClass(), valorField)
                    .getReadMethod().invoke(objetoValicadao);

            final String nome = (String) BeanUtils
                    .getPropertyDescriptor(objetoValicadao.getClass(), descricaoField)
                    .getReadMethod().invoke(objetoValicadao);

            if(taxaFrete != null && BigDecimal.ZERO.compareTo(taxaFrete) == 0
                    && nome!= null){
                isValid = nome.toLowerCase().contains(descricaoObrigatoria.toLowerCase());
            }

        } catch (Exception e) {
            throw new ValidationException(e);
        }

        return isValid;
    }
}
