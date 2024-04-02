package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import starter.BaseWrapper;

import java.util.Objects;

public class CrearTourPage extends PageObject {
    String tituloCT = "return document.querySelector(\"#root > div > div > div.addTour-panel > h2\")";
    String nombreTour = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(1) > input[type=text]\")";
    String descripcionTour = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(2) > textarea\")";
    String descripcionCorta = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(3) > textarea\")";
    String categoriaTour = " return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(4) > select\")";
    String precioTour = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(6) > input[type=number]\")";
    String imagenTour = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > div.fileImage > label > input\")";
    String btoGuardarDestino = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > button\")";
    String carteristica1 = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > div:nth-child(5) > label:nth-child(2) > input[type=checkbox]\")";
    BaseWrapper baseWrapper = new BaseWrapper();

    public void ValidacionOpcionCT() throws InterruptedException {
        baseWrapper.FactoryShadowRootObjectVisibility(tituloCT);
        Assert.assertEquals("Añadí un nuevo tour",baseWrapper.FactoryShadowRootGetText(tituloCT));
    }

    public void ingresoInfoCT(String string, String string2, String string3, String string4, String string5, String string6) throws InterruptedException {
        baseWrapper.FactoryShadowRootSendKeys(nombreTour,string);
        baseWrapper.FactoryShadowRootSendKeys(descripcionTour,string2);
        baseWrapper.FactoryShadowRootSendKeys(descripcionCorta, string3);
        if (Objects.equals(string4, "Playa")||Objects.equals(string4, "playa")||Objects.equals(string4, "PLAYA")){
            baseWrapper.ClickShadowRootObject(categoriaTour);
            Thread.sleep(2000);
            String playa = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(4) > select > option:nth-child(2)\")";
            baseWrapper.ClickShadowRootObject(playa);
        }else if (Objects.equals(string4, "Montañas")||Objects.equals(string4, "MONTAÑAS")||Objects.equals(string4, "montañas")){
            baseWrapper.ClickShadowRootObject(categoriaTour);
            Thread.sleep(1000);
            String montaña = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(4) > select > option:nth-child(3)\")";
            baseWrapper.ClickShadowRootObject(montaña);

        }else if (Objects.equals(string4, "Inolvidables")||Objects.equals(string4, "inolvidables")||Objects.equals(string4, "INOLVIDABLES")){
            baseWrapper.ClickShadowRootObject(categoriaTour);
            Thread.sleep(1000);
            String inolvidable = "return document.querySelector(\"#root > div > div > div.addTour-panel > form > label:nth-child(4) > select > option:nth-child(5)\")";
            baseWrapper.ClickShadowRootObject(inolvidable);
        }else {
            System.out.println("*****No coincide la categoria*****");
        }
        baseWrapper.ClickShadowRootObject(carteristica1);
        Thread.sleep(2000);
        baseWrapper.ScrollDownPage();
        baseWrapper.FactoryShadowRootSendKeys(precioTour, string5);
        System.out.println("***A1***");

        //**** Validar la rutra del docuemnto e inclurila dentro del proyecto para dejarlo portable ****
        String rutaImagen = "\"C:\\Users\\andre\\OneDrive\\Imágenes\\Iguazu\\iguazu.jpg\"";
        System.out.println("***A2***");
        baseWrapper.ScrollDownPage();
        baseWrapper.UpDocumen(imagenTour,rutaImagen);
        System.out.println("***A3***");
        Thread.sleep(2000);
    }

    public void clickOpcionGuardar() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(btoGuardarDestino);
    }

    public void notificacionCreacion() throws InterruptedException {
        Thread.sleep(50000);
    }
}
