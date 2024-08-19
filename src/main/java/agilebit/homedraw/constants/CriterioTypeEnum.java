package agilebit.homedraw.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum CriterioTypeEnum {
	RENDA("Renda"),
	DEPENDENTES("Dependentes");
	
	private final String descricao;
}
