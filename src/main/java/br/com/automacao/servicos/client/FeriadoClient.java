package br.com.automacao.servicos.client;

import br.com.automacao.servicos.request.FeriadoRequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "feriado", url = "https://automacao-api-java.empresa.com.br/", decode404 = true)
public interface FeriadoClient {

    @PostMapping(path = "feriado", consumes = "application/json")
    String capturarProximoFeriado(@RequestBody FeriadoRequestBody feriadoRequestBody,
                                  @PathVariable("data") String dataAtual,
                                  @RequestParam("usuario") String usuario
    );

    @PostMapping(path = "dia_util/{data_exata}/dia", consumes = "application/json")
    String capturarProximoDiaUtil(@PathVariable("data_exata") String dataEexata);

    @RequestMapping(path = "feriado/{data_exata}", method=RequestMethod.PUT, consumes = "application/json")
    String alteraFeriado(@PathVariable("data_exata") String dataEexata);

}