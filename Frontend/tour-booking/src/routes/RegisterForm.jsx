import React, { useState } from "react";
import "../style/RegisterForm.css";
import Footer from "../Components/Footer.jsx";
import axios from "axios";
import { useUserContext } from "../Context/UserContext";

const RegisterForm = () => {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isEmailSent, setIsEmailSent] = useState(false);
  const { path } = useUserContext();

  const [formData, setFormData] = useState({
    username: "",
    name: "",
    surname: "",
    nationality: "",
    email: "",
    password: "",
    admin: false,
  });

  const [errors, setErrors] = useState({
    username: "",
    name: "",
    surname: "",
    nationality: "",
    email: "",
    password: "",
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setIsSubmitting(true);
    const newErrors = {};
    setErrors(newErrors);

    const isValid = Object.values(newErrors).every((error) => error === "");
    if (isValid) {
      try {
        const response = await axios.post(`http://${path}/auth/register`, {
          username: formData.username,
          password: formData.password,
          name: formData.name,
          surname: formData.surname,
          nationality: formData.nationality,
          email: formData.email,
          admin: false,
        });

        console.log("Registro existoso!:", response.data);
        setIsEmailSent(true);

        setFormData({
          username: "",
          name: "",
          surname: "",
          nationality: "",
          email: "",
          password: "",
          admin: false,
        });

        setErrors({
          username: "",
          name: "",
          surname: "",
          nationality: "",
          email: "",
          password: "",
        });
      } catch (error) {
        console.error("Registro fallido:", error);
        setIsEmailSent(false);
      }
      setIsSubmitting(false);
    }
  };
  return (
    <>
      <div className="registro-usuario-container">
        <div className="registro-usuario-container-left"></div>
        <div className="registro-usuario-container-right">
          <h1>Registro de Usuario</h1>
          <form className="registro-usuario-form" onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Usuario:</label>
              <input
                type="text"
                name="username"
                autoComplete="username"
                placeholder="Escribe tu usuario"
                value={formData.username}
                onChange={handleInputChange}
                required
                style={errors.username ? { border: "2px solid #F28705" } : {}}
              />
              <p className="error">{errors.username}</p>
            </div>
            <div className="form-group">
              <label>Nombre:</label>
              <input
                type="text"
                name="name"
                autoComplete="username"
                placeholder="Escribe tu nombre"
                value={formData.name}
                onChange={handleInputChange}
                required
                style={errors.name ? { border: "2px solid #F28705" } : {}}
              />
              <p className="error">{errors.name}</p>
            </div>
            <div className="form-group">
              <label>Apellido:</label>
              <input
                type="text"
                name="surname"
                placeholder="Escribe tu apellido"
                value={formData.surname}
                onChange={handleInputChange}
                required
                style={errors.surname ? { border: "2px solid #F28705" } : {}}
              />
              <p className="error">{errors.surname}</p>
            </div>
            <div className="form-group">
              <label>País de Origen:</label>
              <input
                type="text"
                name="nationality"
                placeholder="Escribe tu pais"
                value={formData.nationality}
                onChange={handleInputChange}
                required
                style={
                  errors.nationality ? { border: "2px solid #F28705" } : {}
                }
              />
              <p className="error">{errors.nationality}</p>
            </div>
            <div className="form-group">
              <label>Email:</label>
              <input
                type="text"
                name="email"
                placeholder="Escribe tu email"
                value={formData.email}
                onChange={handleInputChange}
                required
                style={errors.email ? { border: "2px solid #F28705" } : {}}
              />
              <p className="error">{errors.email}</p>
            </div>
            <div className="form-group">
              <label>Password:</label>
              <input
                type="password"
                name="password"
                placeholder="Escribe tu contraseña"
                value={formData.password}
                onChange={handleInputChange}
                required
                style={errors.password ? { border: "2px solid #F28705" } : {}}
              />
              <p className="error">{errors.password}</p>
            </div>

            <button className="registro-usuario-button" type="submit">
              Registrarse
            </button>
          </form>
          {isEmailSent && (
            <>
              <span className="material-symbols-outlined">mail</span>{" "}
              <p>
                Registro existoso! Se ha enviado un email para verificar su
                cuenta.
              </p>
            </>
          )}
        </div>
      </div>

      <Footer />
    </>
  );
};

export default RegisterForm;
