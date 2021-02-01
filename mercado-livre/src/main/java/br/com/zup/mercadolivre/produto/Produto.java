package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@Positive
	private Integer quantidade;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@OneToMany
	private Categoria categoria;
	
	@OneToMany(mappedBy="caracteristca", cascade=CascadeType.ALL)
	private Map<String, String> caracteristicas = new HashMap<String, String>();
	
	private LocalDate dataCriacao;
}
