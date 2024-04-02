package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import starter.BaseWrapper;

public class PanelAdminPage extends PageObject {
    String btoCrearTour = "return document.querySelector(\"#root > div > div.admin-format > div.admin-sidebar-position > div > div > a > button\")";
    String btoEditaTour = "return document.querySelector(\"#root > div > div.admin-format > div.admin-sidebar-position > div > a:nth-child(2) > button\")";
    String btolListarProducto = "return document.querySelector(\"#root > div > div.admin-format > div.admin-sidebar-position > div > a:nth-child(3) > button\")";
    String btoListarUsuario = "return document.querySelector(\"#root > div > div.admin-format > div.admin-sidebar-position > div > a:nth-child(4) > button\")";
    String btoListarCategoria = "return document.querySelector(\"#root > div > div.admin-format > div.admin-sidebar-position > div > a:nth-child(5) > button\")";
    String btoAgregarCategoria = "return document.querySelector(\"#root > div > div.admin-format > div.admin-sidebar-position > div > a:nth-child(6) > button\")";
    String btoPanelAdmin = "return document.querySelector(\"#root > div > header > div > div > div.MuiBox-root.css-2uchni > button > div > div.nav-user-right\")";
    String btoAdmin = "return document.querySelector(\"#menu-appbar-user > div.MuiPaper-root.MuiPaper-elevation.MuiPaper-rounded.MuiPaper-elevation8.MuiMenu-paper.MuiPopover-paper.MuiMenu-paper.css-3dzjca-MuiPaper-root-MuiPopover-paper-MuiMenu-paper > ul > li:nth-child(2) > p > a\")";
    BaseWrapper baseWrapper = new BaseWrapper();

    public void validacionOpcionesAdmin() throws InterruptedException {
        baseWrapper.FactoryShadowRootObjectVisibility(btoCrearTour);
        baseWrapper.FactoryShadowRootObjectVisibility(btoEditaTour);
        baseWrapper.FactoryShadowRootObjectVisibility(btolListarProducto);
        baseWrapper.FactoryShadowRootObjectVisibility(btoListarUsuario);
        baseWrapper.FactoryShadowRootObjectVisibility(btoListarCategoria);
        baseWrapper.FactoryShadowRootObjectVisibility(btoAgregarCategoria);
    }

    public void PanelAdminPage() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(btoCrearTour);
        Thread.sleep(2000);
    }

    public void seleccionOpcionListarProducto() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(btolListarProducto);
        Thread.sleep(1000);
    }

    public void seleccionListarUsuario() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(btoListarUsuario);
        Thread.sleep(1000);
    }

    public void seleccionOpcionAdmin() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(btoPanelAdmin);
        Thread.sleep(2000);
        baseWrapper.ClickShadowRootObject(btoAdmin);
        Thread.sleep(2000);
        System.out.println("**** Ingreso en la opcion de panal admin ****");
    }
}
