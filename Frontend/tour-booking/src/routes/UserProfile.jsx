import React, { useState } from "react";
import axios from "axios";
import "../style/UserProfile.css";
import Footer from "../Components/Footer.jsx";
import { useUserContext } from "../Context/UserContext.jsx";

const UserProfile = () => {
  const { loggedUser, setLoggedUser } = useUserContext();

  return (
    <>
      <div className="page-user-profile">
        <div className="user-profile-container">
          <div className="user-profile-container-left"></div>
          <div className="user-profile-container-right">
            <h1>Perfil de Usuario</h1>
            <table class="tg">
              <thead>
                <tr>
                  <th>Campo</th>
                  <th>Valor</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Id</td>
                  <td>{loggedUser.id}</td>
                </tr>
                <tr>
                  <td>Nombre de Usuario</td>
                  <td>{loggedUser.username}</td>
                </tr>
                <tr>
                  <td>Nombre</td>
                  <td>{loggedUser.name}</td>
                </tr>
                <tr>
                  <td>Apellido</td>
                  <td>{loggedUser.surname}</td>
                </tr>
                <tr>
                  <td>País</td>
                  <td>{loggedUser.nationality}</td>
                </tr>
                <tr>
                  <td>Email</td>
                  <td>{loggedUser.name}</td>
                </tr>
                <tr>
                  <td>Rol</td>
                  <td>{loggedUser.admin ? "Administrador" : "Usuario"}</td>
                </tr>
                <tr>
                  <td>Cuenta expirada</td>
                  <td>{loggedUser.accountNonExpired ? "No" : "Sí"}</td>
                </tr>
                <tr>
                  <td>Cuenta bloqueada</td>
                  <td>{loggedUser.accountNonLocked ? "No" : "Sí"}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default UserProfile;
