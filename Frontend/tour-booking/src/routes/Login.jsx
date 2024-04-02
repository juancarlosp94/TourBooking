import React, { useState } from "react";
import axios from "axios";
import "../style/Login.css";
import { useUserContext } from "../Context/UserContext.jsx";
import { Link, useNavigate, useLocation } from "react-router-dom";
import Footer from "../Components/Footer.jsx";

const Login = () => {
  const { loggedUser, setLoggedUser, path } = useUserContext();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [verError, setVerError] = useState("");
  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const redirectPath = query.get("redirect");

  const handleUsernameChange = (event) => {
    const newUsername = event.target.value;
    setUsername(newUsername);
  };

  const handlePasswordChange = (event) => {
    const newPassword = event.target.value;
    setPassword(newPassword);
  };

  const handleLoginSuccess = (data) => {
    setLoggedUser(data);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const requestBody = {
      username: username,
      password: password,
    };

    try {
      const response = await axios.post(
        `http://${path}/auth/login`,
        requestBody,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200) {
        const data = response.data;
        handleLoginSuccess(data);
        setVerError("");
        console.log("Login OK");
        if (redirectPath) {
          navigate(redirectPath);
        } else {
          navigate("../");
        }
      } else {
        setVerError("Login falló, revisar credenciales.");
      }
    } catch (error) {
      console.error("Login error:", error);
      setVerError("Error al iniciar sesión, verificar Usuario y Contraseña.");
    }
  };

  const navigate = useNavigate();

  return (
    <div className="inicio-sesion-fondo">
      <div className="inicio-sesion">
        {loggedUser ? (
          setTimeout(() => {
            <>
              <h2 className="bienvenido-title">
                Bienvenido/a, {loggedUser.name} {loggedUser.surname}!{" "}
              </h2>
            </>;

            navigate("../");
          }, 100)
        ) : (
          <>
            <div className="login-container">
              <div className="login-container-left"></div>
              <div className="login-container-right">
                <h2>Iniciar sesión</h2>
                <form className="inicio-sesion-form" onSubmit={handleSubmit}>
                  <div className="inicio-sesion-form-line">
                    <span className="material-symbols-outlined">person</span>
                    <input
                      type="text"
                      id="username"
                      placeholder="Introduce tu usuario"
                      value={username}
                      onChange={handleUsernameChange}
                      required
                    />
                  </div>
                  <div className="inicio-sesion-form-line">
                    <span className="material-symbols-outlined">lock</span>
                    <input
                      type="password"
                      id="password"
                      placeholder="Introduce tu contraseña"
                      value={password}
                      onChange={handlePasswordChange}
                      required
                    />
                  </div>
                  <button className="inicio-sesion-button" type="submit">
                    Iniciar sesión
                  </button>
                  <Link className="no-tienes-cuenta-link" to="/RegisterForm">
                    <p className="no-tienes-cuenta-p">
                      No tenes cuenta? Registrate
                    </p>
                  </Link>
                </form>
              </div>
            </div>
          </>
        )}
        {verError && <p className="error">{verError}</p>}
      </div>
      <Footer />
    </div>
  );
};

export default Login;
