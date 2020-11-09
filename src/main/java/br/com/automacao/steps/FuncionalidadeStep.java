package br.com.automacao.steps;

import br.com.automacao.pages.FuncionalidadePage;
import br.com.automacao.runners.IntegrationTest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class FuncionalidadeStep extends IntegrationTest {

    @Autowired
    private FuncionalidadePage funcionalidadePage;

    @Dado("que acesso o site do github")
    public void queAcessoSite() {
        funcionalidadePage.acessarPaginaInicial();
    }

    @Entao("a pagina inicial deve carregar")
    public void paginaInicialDeveCarregar() {
        Assert.assertTrue("Pagina inicial nao carregou!", funcionalidadePage.deveCarregarPaginaInicial());
    }

    @Quando("pesquiso pelo termo {string}")
    public void pesquisoPeloTermo(String termo) {
        funcionalidadePage.pesquisarTermo(termo);
    }

    @Entao("deve carregar a pagina com resultados da pesquisa")
    public void deveCarregarPaginaComResultados() {
        Assert.assertTrue("Nao carregou pagina com resultados!", funcionalidadePage.deveCarregarPaginaComResultados());
    }

    @Dado("url {string}")
    public void url(String url) {
        funcionalidadePage.setarURL(url);
    }

    @Dado("endpont {string}")
    public void endpont(String endpont) {
        funcionalidadePage.setarEndpoint(endpont);
    }

    @Entao("o status do retorno deve ser {int}")
    public void statusRetornoDeveSer(int statusEsperado) {
        Assert.assertTrue("Retono diferente do esperado!", funcionalidadePage.validaStatusRetorno(statusEsperado));
    }
}
//Error creating bean with name 'testeDAO' defined in file [C:\GitPush\FrameworkSeleniumGradle\build\classes\java\main\br\com\automacao\repositorio\dao\TesteDAO.class]:
//Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException:
//
//Error creating bean with name 'jdbcTemplate' defined in br.com.automacao.repositorio.config.TestContextConfiguration: Invalid destruction signature;
//nested exception is org.springframework.beans.factory.support.BeanDefinitionValidationException: Could not find a destroy method named 'jdbcTemplate' on bean with name 'jdbcTemplate'
