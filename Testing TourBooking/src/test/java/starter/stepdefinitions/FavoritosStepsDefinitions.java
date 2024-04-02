package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.FavoritosSteps;

public class FavoritosStepsDefinitions {

    @Steps
    FavoritosSteps favoritosSteps;

    @Given("Que el usuario requiere guardar el tour en favoritos")
    public void que_el_usuario_requiere_guardar_el_tour_en_favoritos() throws InterruptedException {
        Thread.sleep(2000);
    }

    @When("selecciona como favorito")
    public void selecciona_como_favorito() throws InterruptedException{
        favoritosSteps.seleccionFavorito();
    }

    @Then("Se visuliza en la seccion de favoritos del cliente")
    public void se_visuliza_en_la_seccion_de_favoritos_del_cliente() throws InterruptedException {
        favoritosSteps.visualizacionFavoritos();

    }

}
