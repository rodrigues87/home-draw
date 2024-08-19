package agilebit.homedraw.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FAMILIA")
public class FamiliaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAMILIA_SEQ")
	@SequenceGenerator(name = "FAMILIA_SEQ", sequenceName = "FAMILIA_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name = "PONTUACAO")
	private Integer pontuacao;
	
	@Column(name = "RENDA_TOTAL", precision = 10, scale = 2)
	private BigDecimal rendaTotal;
	
	@Column(name = "QUANTIDADE_DEPENDENTES")
	private Integer quantidadeDependentesAplicavel;
	
	@OneToMany(mappedBy = "familia")
	private Set<PessoaEntity> pessoas;
}
