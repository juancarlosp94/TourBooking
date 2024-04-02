package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.InicioSesionTourBookingSteps;

public class InicioSesionTourBookingStepsDefinition {

    @Steps
    InicioSesionTourBookingSteps inicioSesionTourBookingSteps;

    @Given("Que usuario ingrese a la opcion de inicio de sesion")
    public void que_usuario_ingrese_a_la_opcion_de_inicio_de_sesion() throws InterruptedException {
        inicioSesionTourBookingSteps.ingresoOpcionIncioSesion();

    }
    @When("Ingrese la informacion del correo {string}, contrasena {string}")
    public void ingrese_la_informacion_del_correo_contrasena(String string, String string2) throws InterruptedException {
        inicioSesionTourBookingSteps.ingrese_la_informacion_del_correo_contrasena(string,string2);

    }
    @Then("Dara click en la opcion inicio de sesion")
    public void dara_click_en_la_opcion_inicio_de_sesion() throws InterruptedException {
        inicioSesionTourBookingSteps.clickOpcionIniciosesion();
    }
    @Then("Se ingresara de forma exitosa")
    public void se_ingresara_de_forma_exitosa() throws InterruptedException {
        inicioSesionTourBookingSteps.validacionIngresoExitosoAdmin();
    }
    @Then("Se niega el ingreso")
    public void se_niega_el_ingreso() throws InterruptedException {
        inicioSesionTourBookingSteps.validacionErrorIniciosesion();
    }

    @Then("Se ingresara de forma exitosa con ron normal")
    public void se_ingresara_de_forma_exitosa_con_ron_normal() throws InterruptedException {
        inicioSesionTourBookingSteps.validacionIngresoExitosoNormal();
    }
}
