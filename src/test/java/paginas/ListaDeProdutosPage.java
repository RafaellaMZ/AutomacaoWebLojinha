package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    private WebDriver driver;

    public ListaDeProdutosPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioDeAdicaoDeProdutoPage acessarOFormularioDeAdicaoDeNovoProduto() {
        driver.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        return new FormularioDeAdicaoDeProdutoPage(driver);
    }

    public String capturarMensagemApresentada() {
        return driver.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
