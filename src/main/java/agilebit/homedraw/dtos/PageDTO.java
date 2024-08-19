package agilebit.homedraw.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PageDTO {
	private Integer totalPages;
	private Long totalElements;
	private Integer size;
	private Integer number;
	private Integer numberOfElements;
	private Boolean first;
	private Boolean last;
}
