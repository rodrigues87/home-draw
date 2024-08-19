package agilebit.homedraw.dtos.pessoa;

import agilebit.homedraw.dtos.PageDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@JsonPropertyOrder({"content"})
public class PessoaResponsePageDTO extends PageDTO {
	private List<PessoaResponseDTO> content;
}
