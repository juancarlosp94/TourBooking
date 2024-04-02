#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Validacion panel Administrador TourBooking

  Background:
    Given Una vez el usuario requiera ingresar al TourBooking
    When Ingrese al aplicativo
    Then vera el home del aplicativo
    Given Que usuario ingrese a la opcion de inicio de sesion
    When Ingrese la informacion del correo "juanP", contrasena "123456"
    Then Dara click en la opcion inicio de sesion
    And Se ingresara de forma exitosa

  @RunAll1 @PanelAdmin
  @Test7
  Scenario: Validacion de opciones admin
    Given Que el administrador se encuentra logueado
    When visualice las opciones
    Then podra seleccionar alguna
