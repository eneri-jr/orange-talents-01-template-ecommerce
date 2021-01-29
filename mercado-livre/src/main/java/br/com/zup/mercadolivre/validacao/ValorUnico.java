package br.com.zup.mercadolivre.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ValidaValorUnico.class)
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ValorUnico {
	
	//Padrão para se criar uma anotation:
	
	String message() default "O valor informado já esta cadastrado no banco, digite um novo valor para o campo informado.";
	
	Class<?>[] groups() default { };
	
	Class<?extends Payload>[] payload() default { };
	
	//Parametros para validar os dados:
	
	String campo();
	
	Class<?> Classe();
	
}
