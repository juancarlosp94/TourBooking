package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import starter.BaseWrapper;

import java.util.Objects;

public class HomeUsuarioPage extends PageObject {

    String logoTourBooing = "return document.querySelector(\"#root > div > header > div > div > a.MuiTypography-root.MuiTypography-h6.MuiTypography-noWrap.css-m5x0z-MuiTypography-root > div > h1\")";
    BaseWrapper baseWrapper= new BaseWrapper();

    public void validacionHomeUserAunteticado() throws InterruptedException {
        Thread.sleep(2000);
        baseWrapper.FactoryShadowRootObjectVisibility(logoTourBooing);
        Thread.sleep(2000);
    }

    public void seleccionCategoria(String string) throws InterruptedException {
        System.out.println("*** ingreso Seccion Categorias ");

        Thread.sleep(5000);
        for (int i=1; i<=50; i++){
            System.out.println("*** aaa ***");
            String categoria = "return document.querySelector(\"#root > div > div > div.container-categorias > a:nth-child("+i+") > div > h3\")";

            System.out.println("*** bbb ***");
            String actual = baseWrapper.FactoryShadowRootGetText(categoria);
            System.out.println("***"+actual+"***");
            if (Objects.equals(actual, string)){
                System.out.println("*** Entreo al If por que encontro la categoria ***");
                baseWrapper.ClickShadowRootObject(categoria);
                Thread.sleep(2000);
                break;
            }
        }
    }

    public void validacionDestinos() throws InterruptedException {
        String destinoUno = "return document.querySelector(\"#root > div > div > div.bodydestinos > div.container-categorias-column > div:nth-child(1) > a:nth-child(1) > div\")";
        baseWrapper.FactoryShadowRootObjectVisibility(destinoUno);
    }
}
