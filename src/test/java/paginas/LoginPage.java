package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver; // Primeiro princípio do page object é ter um atributo da classe que seja do tipo webdriver

    public LoginPage(WebDriver driver){ // Segundo princípio tenha um construtor da classe
        this.driver = driver;
    }

    public LoginPage informarOUsuario(String usuario){
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.id("usuario")).sendKeys(usuario);

        return this; // Design pattern fluid page
    }

    public LoginPage informarASenha(String senha){
        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys(senha);

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeLogin() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(driver);
    }

}
