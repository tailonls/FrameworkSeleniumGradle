package br.com.automacao.pages;

import br.com.automacao.core.DriverFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.automacao.core.DriverFactory.getDriver;
import static br.com.automacao.core.relarorios.GeradorReportHTML.*;

@Component
public class FuncionalidadePage extends FuncionalidadePageElementMap {

    private String URL = null;
    private String ENDPOINT = null;
    private HttpResponse<String> RESPONSE = null;

    public FuncionalidadePage() {
        PageFactory.initElements(getDriver(), this); // Inicializar elementos da classe ElementMap
    }

    public void acessarPaginaInicial() {
		String paginaWeb = properties.getProperty("teste.url");

		DriverFactory.getDriver().get(paginaWeb);
        aguardarSegundos(3);
        logPrintPass("Acessou página " + paginaWeb);
    }

    public boolean deveCarregarPaginaInicial() {
        aguardarSegundos(1);
        if (aguardaElemento(By.xpath(DIV_PAGINA_INICIAL), 2) != null) {
            logPrintPass("Pagina inicial carregou com sucesso!");
            return true;
        }

        logPrintFail("Pagina inicial NAO apareceu!");
        return false;
    }

    public void pesquisarTermo(String termo) {
        aguardarSegundos(1);

        WebElement campoPesquisa = aguardaElemento(By.xpath(CAMPO_PESQUISA), 3);

        if (campoPesquisa != null) {
            escrever(termo, campoPesquisa);
            logPrintPass("Informou o termo [" + termo + "] no campo de pesquisa!");
            escrever(Keys.ENTER, campoPesquisa);

        } else {
            logPrintFail("NAO encontrou o campo de pesquisa!");
            Assert.fail();
        }
    }

    public boolean deveCarregarPaginaComResultados() {
        aguardarSegundos(1);
        List<WebElement> resultados = getDriver().findElements(By.xpath(RESULTADOS_PESQUISA));

        if (resultados != null) {
            logPrintPaginaInteira("Pagina carregou com sucesso mostrando " + resultados.size() + " resultados!");
            return true;
        }

        logPrintFail("Pagina com resultados NAO apareceu!");
        return false;
    }

    // PARA USO FUTURO
    private boolean validandoVariosElementos() {
        AtomicBoolean validar = new AtomicBoolean();

        validar.set(
                saoValoresIguais("casa", "CAZA") &&
                        saoValoresIguais("casas", "CASA") &&
                        saoValoresIguais("casa", "caza"));

        if (validar.get())
            logPass("Valores estavam de acordo com o esperado!");

        return validar.get();
    }

    private boolean saoValoresIguais(String valorEsperado, String valorObtido) {
        boolean valida = valorEsperado.equalsIgnoreCase(valorObtido);
        if (!valida)
            logFail("Erro: valor esperado: [" + valorEsperado + "] valor recebido: [" + valorObtido + "]!");

        return valida;
    }

    public void setarURL(String url) {
        URL = url;
        logInfo("Setou URL: " + URL);
    }

    public void setarEndpoint(String endpont) {
        ENDPOINT = endpont;
        logInfo("Setou ENDPOINT: " + ENDPOINT);
    }

    public boolean validaStatusRetorno(int statusEsperado) {

        try {
            RESPONSE = Unirest.get(URL + ENDPOINT).header("accept", "application/json").asString();
            logInfo("Executou requisicao do tipo GET...");

        } catch (Exception e) {
            logFail("Erro ao realizar requisicao GET: " + e.getMessage());
            e.printStackTrace();
        }

        if (RESPONSE.getStatus() == statusEsperado) {
            logPass("Status da requisicao retornou conforme esperado: " + RESPONSE.getStatus());
            return true;
        }
        logFail("Erro! Status da requisicao esperado: " + statusEsperado + " status obtido: " + RESPONSE.getStatus());
        return false;
    }
}