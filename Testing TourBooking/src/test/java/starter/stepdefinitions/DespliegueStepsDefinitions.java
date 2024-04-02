package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DespliegueStepsDefinitions {

    @Given("Una vez el usuario requiere desplegar la aplicacion de TourBooking")
    public void una_vez_el_usuario_requiere_desplegar_la_aplicacion_de_tour_booking() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("**** Inicio Test Despliegue ****");
        Thread.sleep(1000);
    }
    @When("Ingrese los comandos de despliegue")
    public void ingrese_los_comandos_de_despliegue() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("**** Ejecutando test Despliegue ****");
        Thread.sleep(1000);

    }
    @Then("Se despliega de forma exitosa")
    public void se_despliega_de_forma_exitosa() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("**** Ejecucion exitosas de test Despliegue ****");
        Thread.sleep(1000);

    }
}
