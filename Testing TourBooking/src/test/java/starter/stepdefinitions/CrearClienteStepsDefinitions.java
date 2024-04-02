package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.CrearClienteSteps;

public class CrearClienteStepsDefinitions {
    @Steps
    CrearClienteSteps crearClienteSteps;

    @Given("Que usuario ingrese a la opcion de crear usuario")
    public void que_usuario_ingrese_a_la_opcion_de_crear_usuario() throws InterruptedException {
        crearClienteSteps.ingresoCrearUsuario();

    }
    @When("Ingrese la informacion del Nombre Usuario {string}, Nombre {string}, Apellido {string}, pais de origen {string}, email {string},contrasena {string}")
    public void ingrese_la_informacion_del_nombre_usuario_nombre_apellido_pais_de_origen_email_contrasena(String string, String string2, String string3, String string4, String string5, String string6) throws InterruptedException {
        crearClienteSteps.IngresoInfoCrearUsuario(string,string2,string3,string4,string5,string6);
    }
    @Then("Dara click en la opcion de Reguistar")
    public void dara_click_en_la_opcion_de_reguistar() throws InterruptedException {
        crearClienteSteps.clickOpcion();

    }

    @Then("Se notifica el reguistro exitoso")
    public void se_notifica_el_reguistro_exitoso() {

    }
}
