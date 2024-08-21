package agilebit.homedraw.sheduleds;

import agilebit.homedraw.entities.FamiliaEntity;
import agilebit.homedraw.services.FamiliaCriterioService;
import agilebit.homedraw.services.PessoaService;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class RecalculoPontosSheduled {
	private FamiliaCriterioService familiaCriterioService;
	private PessoaService pessoaService;
	
	@Scheduled(cron = "0 1 0 1 * ?")
	public void recalcularPontos() {
		LocalDate currentDate = LocalDate.now();
		List<FamiliaEntity> familias = pessoaService.findFamiliasWithDataDeNascimentoEqualsToCurrentDay(currentDate);
		familias.forEach(familia -> familiaCriterioService.updatePontuacaoFamilia(familia));
	}
}
