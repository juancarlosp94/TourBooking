#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Validacion favoritos tour TourBooking

  Background:
    Given Una vez el usuario requiera ingresar al TourBooking
    When Ingrese al aplicativo
    Then vera el home del aplicativo
    Given Que usuario ingrese a la opcion de inicio de sesion
    When Ingrese la informacion del correo "ULAN3535", contrasena "Asuarez40*"
    Then Dara click en la opcion inicio de sesion
    And Se ingresara de forma exitosa con ron normal
    Given Que usuario ya se encuentre logueado
    When Seleccione alguna de las categorias en el Home "Playa"
    Then visualizara todos los destinos de la categoria
    Given Que usuario ya se encuntra dentro de la categoria
    When Selecciona uno de los tour
    Then valida las secciones del tour

  @RunAll1 @ReservaTour
  @Test14
  Scenario: Validacion Favoritos
    Given Que el usuario requiere guardar el tour en favoritos
    When selecciona como favorito
    Then Se visuliza en la seccion de favoritos del cliente
