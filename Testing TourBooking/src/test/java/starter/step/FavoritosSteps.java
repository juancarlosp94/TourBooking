package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.FavoritosPage;
import starter.Page.ReservaTourPage;

public class FavoritosSteps extends PageObject {

    ReservaTourPage reservaTourPage;
    FavoritosPage favoritosPage;

    @Step
    public void seleccionFavorito() throws InterruptedException {
        reservaTourPage.seleccionFavorito();
    }
    @Step
    public void visualizacionFavoritos() throws InterruptedException {
        favoritosPage.visualizacionFavoritos();
    }
}
