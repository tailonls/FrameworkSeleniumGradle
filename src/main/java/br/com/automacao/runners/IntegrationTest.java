package br.com.automacao.runners;

import br.com.automacao.repositorio.config.TestContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContextConfiguration.class)
public abstract class IntegrationTest {

    // Classe que inicializa configuracoes do Spring

    // Obs.: Pode extendida de uma classe Step tipo Login, desde que seja rodada no antes dos testes
}