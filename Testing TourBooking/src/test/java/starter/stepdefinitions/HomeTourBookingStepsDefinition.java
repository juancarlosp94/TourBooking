package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.HomeTourBookingSteps;


public class HomeTourBookingStepsDefinition {

    @Steps
    HomeTourBookingSteps homeTourBookingSteps;

    @Given("Una vez el usuario requiera ingresar al TourBooking")
    public void una_vez_el_usuario_requiera_ingresar_al_tour_booking() throws InterruptedException {
        homeTourBookingSteps.abrirUrl();
    }
    @When("Ingrese al aplicativo")
    public void ingrese_al_aplicativo() throws InterruptedException {
        homeTourBookingSteps.validacionLogo();
    }
    @Then("vera el home del aplicativo")
    public void vera_el_home_del_aplicativo() throws InterruptedException {
        homeTourBookingSteps.validacionHome();
    }

    @When("Valide el footer")
    public void valide_el_footer() throws InterruptedException {
        homeTourBookingSteps.seleccionFooter();
    }
    @Then("Visualizara todos los campos")
    public void visualizara_todos_los_campos() throws InterruptedException {
        homeTourBookingSteps.validacionFooter();
    }



}
