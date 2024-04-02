#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Validacion reserva tour TourBooking

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

  @RunAll1 @ReservaTour
  @Test12
  Scenario: Validacion de tour
    Given Que usuario ya se encuntra dentro de la categoria
    When Selecciona uno de los tour
    Then valida las secciones del tour
    When selecciona la fecha y da click reservar
    Then Visualizara el detalle del pedido
    And Confirmara la reserva
