package starter.Page;

import io.cucumber.java.en.Then;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import starter.BaseWrapper;

public class HomePage extends PageObject {

    String logo = "return document.querySelector(\"#root > div > header > div > div > a.MuiTypography-root.MuiTypography-h6.MuiTypography-noWrap.css-m5x0z-MuiTypography-root > div > h1\")";
    String logoFooter = "return document.querySelector(\"#root > div > div > footer > section > div.column-1 > h2\")";
    String sloganFooter = "return document.querySelector(\"#root > div > div > footer > section > div.column-1 > p:nth-child(2)\")";
    String btoInicioSesion = "return document.querySelector(\"#root > div > header > div > div > a.inicio-sesion-link > button\")";
    String btoCrearCuenta = "return document.querySelector(\"#root > div > header > div > div > a.registro-usuario-link > button\")";
    String textoRol = "return document.querySelector(\"#root > div > header > div > div > div.MuiBox-root.css-2uchni > button > div > div.nav-user-right > div.nav-user-profile > div > p\")";

    BaseWrapper baseWrapper = new BaseWrapper();
    public void validacionLogo() throws InterruptedException {
        baseWrapper.FactoryShadowRootObjectVisibility(logo);
        Thread.sleep(1000);
    }

    public void validacionHome() throws InterruptedException {
        String actual2 = baseWrapper.FactoryShadowRootGetText(logo);
        String esperado2 = "TourBooking";
        Assert.assertEquals(esperado2,actual2);
    }

    public void seleccionFooter() throws InterruptedException {
        baseWrapper.ScrollDownPage();
        Thread.sleep(2000);
    }

    public void validacionFooter() throws InterruptedException {
        String esperado1 = "TourBooking";
        String esperado2 = "Copyright 2023.";
        String actual1 = baseWrapper.FactoryShadowRootGetText(logoFooter);
        String actual2 = baseWrapper.FactoryShadowRootGetText(sloganFooter);
        Assert.assertEquals(esperado1,actual1);
        Assert.assertEquals(esperado2,actual2);
        String esperado4 = "hola@tourbooking.com";
        String actual4 = baseWrapper.FactoryShadowRootGetText("return document.querySelector(\"#root > div > div > footer > section > div.column-1 > div > div:nth-child(1) > div > p\")");
        Assert.assertEquals(esperado4,actual4);


    }

    public void ingresoOpcionIncioSesion() throws InterruptedException {

        baseWrapper.ClickShadowRootObject(btoInicioSesion);
        Thread.sleep(1000);
    }


    public void ingresoCrearUsuario() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(btoCrearCuenta);
        Thread.sleep(1000);
    }

    public void validacionIngresoExitosoAdmin() throws InterruptedException {
        System.out.println("***1***");
            String esperado1 = "Administrador";
        System.out.println("***2***");
            String actual1= baseWrapper.FactoryShadowRootGetText(textoRol);
        System.out.println("***3***");
            Assert.assertEquals(esperado1,actual1);
        System.out.println("***4***");
            Thread.sleep(1000);
        System.out.println("***5***");
        }

    public void validacionErrorIniciosesion() throws InterruptedException {
        String mesajeError = "return document.querySelector(\"#root > div > div > div > p\")";
        baseWrapper.FactoryShadowRootObjectVisibility(mesajeError);
        String esperado1 = "Error al iniciar sesión, verificar Usuario y Contraseña.";
        String actual1 = baseWrapper.FactoryShadowRootGetText(mesajeError);
        Assert.assertEquals(esperado1,actual1);
        Thread.sleep(1000);
    }

    public void validacionIngresoExitosoNormal() throws InterruptedException {
        String esperado1 = "Usuario";
        String actual1= baseWrapper.FactoryShadowRootGetText(textoRol);
        Assert.assertEquals(esperado1,actual1);
        Thread.sleep(1000);
    }
}

