package com.algaworks.algafood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

    /*Valor que é passado em TaxaFrete*/
    int numero;
    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.numero = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number number,
                           ConstraintValidatorContext constraintValidatorContext) {

        boolean isValid = true;

        if(number != null){
            /*valorDecimal = valor de taxaFrete do Restaurante em Decimal*
              valorMultiplo = valor do múltiplo definido na propriedade da
              declaração da anotação*/
            var valorDecimal = BigDecimal.valueOf(number.longValue());
            var valorMultiplo = BigDecimal.valueOf((numero));

            /*Para verificar se é múltiplo:
            * se valorDecimal dividido por valorMultiplo for igual a zero
            * retorna true = é múltiplo
            * retorna false = não é múltiplo*/
            final BigDecimal remainder = valorDecimal.remainder(valorMultiplo);
            isValid = 0 == remainder.compareTo(BigDecimal.ZERO);
        }
        return isValid;
    }
}
