package modulos.produtos;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import paginas.LoginPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;


@DisplayName("Testes Web do Módulo de Produtos")
public class ProdutosTest {

    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        this.driver = new ChromeDriver();

        // Maximizar a tela
        this.driver.manage().window().maximize();

        // Navegar para a página da lojinha Web
        this.driver.get("http://165.227.93.41/lojinha-web/v2/");

        //Vou definir um tempo de espera padrão de 5 segundos
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIAZero() {

        // Fazer login
        String mensagemApresentada = new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarPrecoDoProduto("000")
                .informarCoresDoProduto("branco, preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com o valor acima de R$7000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil() {
        String mensagemApresentada = new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("iPhone")
                .informarPrecoDoProduto("700001")
                .informarCoresDoProduto("amarelo, rosa")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de R$0,01")
    public void testPossoAdicionarProdutosNoLimiteDeUmCentavo() {
        String mensagemApresentada = new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Mouse")
                .informarPrecoDoProduto("001")
                .informarCoresDoProduto("prata, preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de R$7000,00")
    public void testPossoAdicionarProdutosNoLimiteDeSeteMilReais() {
        String mensagemApresentada = new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Monitor")
                .informarPrecoDoProduto("700000")
                .informarCoresDoProduto("prata, preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de R$0,01 a R$ 7000,00")
    public void testPossoAdicionarProdutosComValorDeUmCentavoASeteMilReais() {
        String mensagemApresentada = new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Teclado")
                .informarPrecoDoProduto("30000")
                .informarCoresDoProduto("rosa, preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        //Vou fechar o navegador
        //driver.quit();
    }
}
