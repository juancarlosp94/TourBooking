#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Test para despliegue

  @RunAll1 @Despliegue
  @Test1
  Scenario: Validacion test de despliegue
    Given Una vez el usuario requiere desplegar la aplicacion de TourBooking
    When Ingrese los comandos de despliegue
    Then Se despliega de forma exitosa