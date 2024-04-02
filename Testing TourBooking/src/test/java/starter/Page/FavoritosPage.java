package starter.Page;

import net.bytebuddy.asm.Advice;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import starter.BaseWrapper;

public class FavoritosPage extends PageObject {

    String userLink = "return document.querySelector(\"#root > div > header > div > div > div.MuiBox-root.css-2uchni > button > div > div.nav-user-right\")";
    String opcionFavorito = "return document.querySelector(\"#menu-appbar-user > div.MuiPaper-root.MuiPaper-elevation.MuiPaper-rounded.MuiPaper-elevation8.MuiMenu-paper.MuiPopover-paper.MuiMenu-paper.css-3dzjca-MuiPaper-root-MuiPopover-paper-MuiMenu-paper > ul > li:nth-child(2) > p > a\")";

    BaseWrapper baseWrapper = new BaseWrapper();
    public void visualizacionFavoritos() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(userLink);
        Thread.sleep(2000);
        baseWrapper.ClickShadowRootObject(opcionFavorito);
        Thread.sleep(2000);
        this.validacionOpcionFavoritos();
        this.tourFavoritos();
    }

    public void validacionOpcionFavoritos () throws InterruptedException {
        String tituloFavorito = "return document.querySelector(\"#root > div > div > h2\")";
        baseWrapper.FactoryShadowRootObjectVisibility(tituloFavorito);
        String actual1 = baseWrapper.FactoryShadowRootGetText(tituloFavorito);
        String esperado1 = "Mis Favoritos";
        Assert.assertEquals(esperado1,actual1);

    }

    public void tourFavoritos () throws InterruptedException {
        Thread.sleep(2000);
        String favorito1 = "return document.querySelector(\"#root > div > div > div > table > tbody\")";
        baseWrapper.FactoryShadowRootObjectVisibility(favorito1);
        Thread.sleep(2000);

    }
}
