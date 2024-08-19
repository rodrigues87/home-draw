package agilebit.homedraw.entities;

import agilebit.homedraw.constants.CriterioTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CRITERIO")
public class CriterioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRITERIO_SEQ")
	@SequenceGenerator(name = "CRITERIO_SEQ", sequenceName = "CRITERIO_SEQ", allocationSize = 1)
	private Long id;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO")
	private CriterioTypeEnum tipo;
	
	@Column(name = "LIMITE_INFERIOR", precision = 10, scale = 2)
	private BigDecimal limiteInferior;
	
	@Column(name = "LIMITE_SUPERIOR", precision = 10, scale = 2)
	private BigDecimal limiteSuperior;
	
	@Column(name = "PONTUACAO")
	private Integer pontuacao;
}
