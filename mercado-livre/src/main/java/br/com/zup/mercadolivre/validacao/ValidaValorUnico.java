package br.com.zup.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.query.Query;

public class ValidaValorUnico implements ConstraintValidator<ValorUnico, Object> {

	private String domainAttribute;
	private Class<?> klass;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialize(ValorUnico params) {
		domainAttribute = params.campo();
		klass = params.Classe();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = (Query) em
				.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();

		return list.isEmpty();
	}

}
