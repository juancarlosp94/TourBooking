import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "../style/NavBar.css";
import { useUserContext } from "../Context/UserContext";

const NavBar3 = () => {
  const { loggedUser, logout } = useUserContext();
  const [nameSearched, setNameSearched] = useState("");
  const [searchedTours, setSearchedTours] = useState([]);

  const handleNameChange = (event) => {
    const newName = event.target.value;
    setNameSearched(newName);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const requestBody = {
      name: nameSearched,
    };

    try {
      const response = await axios.post(
        "http://3.145.204.233:8080/tours/search/byname",
        requestBody,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200) {
        const data = response.data;
        setSearchedTours(data);
        console.log("Busqueda OK");
      } else {
        console.log("Busqueda fallo");
      }
    } catch (error) {
      console.log("Busqueda fallo");
    }
  };

  return (
    <nav className="navBar">
      <Link className="logo-link" to="/">
        <div className="logo">
          <h1>TourBooking</h1>
          <p>Viví tu próxima aventura!</p>
        </div>
      </Link>

      <div className="buttonArea">
        {loggedUser ? (
          <>
            <Link className="admin-topbar-links" to="/admin/ReservationList">
              <button className="admin-topbar-buttons">Mis reservas</button>
            </Link>
            <Link className="admin-topbar-links" to="/FavoriteList">
              <button className="admin-topbar-buttons">Favoritos</button>
            </Link>
            {loggedUser.admin && (
              <>
                <Link className="admin-topbar-links" to="/admin/adminPanel">
                  <button className="admin-topbar-buttons">Panel Admin</button>
                </Link>
              </>
            )}
          </>
        ) : (
          <>
            <Link className="inicio-sesion-link" to="/Login">
              <button className="inicio-sesion-sin-login">
                Iniciar Sesión
              </button>
            </Link>
            <Link className="registro-usuario-link" to="/RegisterForm">
              <button className="crearCuenta">Crear cuenta</button>
            </Link>
          </>
        )}
      </div>
      {loggedUser && (
        <div className="nav-user">
          <div className="nav-user-left">
            <Link className="inicio-sesion-link" to="/UserProfile">
              <div className="nav-user-circle">
                <h1>
                  {loggedUser.name.charAt(0).toUpperCase() +
                    loggedUser.surname.charAt(0).toUpperCase()}
                </h1>
              </div>
            </Link>
          </div>

          <div className="nav-user-right">
            <div className="nav-user-profile">
              <Link className="inicio-sesion-link" to="/UserProfile">
                <p>
                  <b>
                    {loggedUser.name} {loggedUser.surname}
                  </b>
                </p>
              </Link>
              <p>{loggedUser.admin ? "Administrador" : "Usuario"}</p>
            </div>

            <div className="nav-user-button">
              <Link className="inicio-sesion-link" to="/">
                <button onClick={logout}>Cerrar Sesión</button>
              </Link>
            </div>
          </div>
        </div>
      )}
      <div className="hamburger-button">
        <span className="material-symbols-rounded">menu</span>
      </div>
    </nav>
  );
};

export default NavBar3;
