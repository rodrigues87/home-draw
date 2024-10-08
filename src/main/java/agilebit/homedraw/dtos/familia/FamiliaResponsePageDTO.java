package agilebit.homedraw.dtos.familia;

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
public class FamiliaResponsePageDTO extends PageDTO {
	private List<FamiliaResponseDTO> content;
}
