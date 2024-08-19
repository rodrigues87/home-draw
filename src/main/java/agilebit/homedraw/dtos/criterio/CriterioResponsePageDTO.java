package agilebit.homedraw.dtos.criterio;

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
public class CriterioResponsePageDTO extends PageDTO {
	private List<CriterioResponseDTO> content;
}
