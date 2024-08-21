package agilebit.homedraw.dtos.familia;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class FamiliaRequestDTO {
	private Set<Long> pessoas;
}
