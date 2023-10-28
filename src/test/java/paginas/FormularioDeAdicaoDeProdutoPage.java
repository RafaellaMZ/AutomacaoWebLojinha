package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver driver;

    public FormularioDeAdicaoDeProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioDeAdicaoDeProdutoPage informarNomeDoProduto(String produtoNome) {
        driver.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }

    public FormularioDeAdicaoDeProdutoPage informarPrecoDoProduto(String produtoValor) {
        driver.findElement(By.id("produtovalor")).sendKeys(produtoValor);

        return this;
    }

    public  FormularioDeAdicaoDeProdutoPage informarCoresDoProduto(String produtoCores){
        driver.findElement(By.id("produtocores")).sendKeys(produtoCores);

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(driver);
    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        return new FormularioDeEdicaoDeProdutoPage(driver);
    }

}
