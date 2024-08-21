package agilebit.homedraw.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PESSOA")
public class PessoaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQ")
	@SequenceGenerator(name = "PESSOA_SEQ", sequenceName = "PESSOA_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "RENDA", precision = 10, scale = 2, nullable = false)
	private BigDecimal renda;
	
	@Column(name = "DATA_DE_NASCIMENTO", length = 3, nullable = false)
	private LocalDate dataDeNascimento;
	
	@Column(name = "DEPENDENTE", nullable = false)
	private Boolean dependente;
	
	@ManyToOne
	@JoinColumn(name = "FAMILIA_ID")
	private FamiliaEntity familia;
}
