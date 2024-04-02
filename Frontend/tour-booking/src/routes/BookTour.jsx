import React, { useState, useEffect } from "react";
import axios from "axios";
import { useUserContext } from "../Context/UserContext.jsx";
import { Link, useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import Footer from "../Components/Footer.jsx";
import "../style/BookTour.css";

const BookTour = () => {
  const [tour, setTour] = useState({});
  const [rating, setRating] = useState(null);
  const { loggedUser, path } = useUserContext();
  const [reservationConfirmed, setReservationConfirmed] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [reservationResult, setReservationResult] = useState("");
  const { id } = useParams();

  const [reservation, setReservation] = useState({
    tourId: 0,
    usuarioId: 0,
    date: "2023-09-21",
    tickets: 0,
  });

  const [userData, setUserData] = useState({
    name: loggedUser.name,
    surname: loggedUser.surname,
    email: loggedUser.email,
  });

  useEffect(() => {
    const storedUserData = JSON.parse(localStorage.getItem("loggedUser"));
    if (storedUserData) {
      setUserData(storedUserData);
    }
  }, []);

  useEffect(() => {
    const fetchTourbyId = async () => {
      try {
        const response = await axios.get(
          `http://${path}/reservation/bytourid/${id}`
        );
        setTour(response.data);
      } catch (error) {
        console.error("Error fetching tour by user:", error);
      }
    };
    fetchTourbyId();
  }, [id, path]);

  useEffect(() => {
    const fetchTour = async () => {
      try {
        const response = await axios.get(`http://${path}/tours/${id}`);
        setTour(response.data);
      } catch (error) {
        console.error("Error fetching tour:", error);
      }
    };
    fetchTour();
  }, [id, path]);

  useEffect(() => {
    const fetchTourbyRatingStats = async () => {
      try {
        const response = await axios.get(
          `http://${path}/tours/ratingstats/${id}`
        );
        setRating(response.data);
      } catch (error) {
        console.error("Error fetching tour:", error);
      }
    };
    fetchTourbyRatingStats();
  }, [id, path]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setReservation({
      ...reservation,
      [name]: value,
    });
  };

  const handleReservation = async () => {
    setIsLoading(true);

    try {
      const response = await axios.post(`http://${path}/reservation`, {
        tourId: parseInt(id),
        usuarioId: parseInt(loggedUser.id),
        date: "2023-09-21",
        tickets: parseInt(reservation.tickets),
      });

      if (response.status !== 200) {
        throw new Error("No fue posible completar su reserva.");
      }

      alert("La reserva se ha realizado con éxito");
      setIsLoading(false);
      setReservationConfirmed(true);

      if (response.status === 200) {
        console.log("POST OK");
        setReservationResult("Reserva hecha correctamente");
      }
    } catch (error) {
      setReservationResult(error.message);
      setIsLoading(false);
    }
  };

  const navigate = useNavigate();

  return (
    <>
      <div className="bookTour-container">
        <h2 className="bookTour-title">Detalle de Pedido</h2>
        <h3 className="bookTour-details-title">Tour seleccionado</h3>
        <div className="bookTour-details" key={tour.id}>
          <p className="bookTour-details-p">Nombre del Tour: {tour.name}</p>
          <p className="bookTour-details-p">
            Promedio Rating: {rating ? rating.averageRating : "Calculando..."} (
            {rating ? rating.ratingsCount : "Calculando..."} votos)
          </p>
          <img
            className="bookTour-details-photo"
            src={
              tour.images && tour.images.length > 0 ? tour.images[0].url : ""
            }
            alt={`tour/${id}`}
          />
        </div>
        <div className="bookTour-purchase-details">
          <label className="bookTour-purchase-details-p">
            Cantidad de Personas:{" "}
          </label>
          <input
            type="number"
            name="tickets"
            value={reservation.tickets}
            onChange={handleInputChange}
          />
          <label className="bookTour-purchase-details-p">Fecha: </label>
          <input
            type="date"
            name="date"
            value={reservation.date}
            onChange={handleInputChange}
          />
        </div>
      </div>
      <div>
        <h3 className="bookTour-userData-title">Datos de Usuario</h3>
        <div>
          {loggedUser ? (
            <div className="bookTour-userData">
              <label>Nombre: </label>
              <input
                type="text"
                name="name"
                placeholder="Nombre"
                value={userData.name}
                onChange={handleInputChange}
              />
              <label>Apellido: </label>
              <input
                type="text"
                name="surname"
                placeholder="Apellido"
                value={userData.surname}
                onChange={handleInputChange}
              />
              <label>Mail para recibir confirmación: </label>
              <input
                type="text"
                name="email"
                placeholder="Mail para recibir confirmación"
                value={userData.email}
                onChange={handleInputChange}
              />
            </div>
          ) : (
            <>
              <i className="tour-detail-table-i">
                Necesitas primero ser usuario registrado para poder reservar un
                tour. <br />
                <Link className="inicio-sesion-link-tour-detail" to="/Login">
                  Regístrate o inicia sesión!
                </Link>
              </i>
            </>
          )}
        </div>
        <div className="bookTour-userData-container">
          <h3 className="bookTour-userData-title">Detalles del Pago</h3>
          <p className="bookTour-userData-p">
            Total a pagar:{" "}
            {/*tour.tickets && tour.price ? tour.tickets * tour.price : "Calculando..."*/}
          </p>
          <p className="bookTour-userData-p">Formas de pago disponibles: </p>
          <div className="payment-icons">
            <i className="fa-brands fa-cc-visa fa-2xl"></i>
            <i className="fa-brands fa-cc-mastercard fa-2xl"></i>
            <i className="fa-brands fa-cc-amex fa-2xl"></i>
            <i className="fa-brands fa-cc-paypal fa-2xl"></i>
            <i className="fa-brands fa-cc-amazon-pay fa-2xl"></i>
            <i className="fa-regular fa-money-bill-1 fa-2xl"></i>
          </div>
          <p className="bookTour-userData-p">
            Puedes pagarle al guía antes de comenzar el tour, los datos de la
            tarjeta solo se tomarán a modo de garantía. <br />
            Una vez confirmada tu reserva, alguien de TourBooking se contactará
            para coordinar detalles contigo.
          </p>
        </div>
        <div className="cancellation-policy-container">
          <h5 className="cancellation-policy-title-h5">
            Política de Cancelación
          </h5>
          <div>
            <p className="cancellation-policy-description">
              Existen diferentes políticas de cancelación, dependiendo del tour
              que elijas y la temporada en la que viajas.
              <br />
              Asegúrese de verificar la temporada para ver cuál es aplicable a
              la experiencia elegida antes de confirmar su reserva.
              <br />
              Puede cancelar su reserva siguiendo las instrucciones que se
              encuentran a continuación.
              <br />
            </p>
            <br />
            <p className="cancellation-policy-description">
              <strong>Cancelación Moderada (Baja Temporada)</strong>
              <br />
              Para obtener un reembolso completo, debes cancelar al menos 3 días
              completos* antes de la hora de inicio de la experiencia.
              <br />
              Si cancelas con menos de 2 días completos* antes de la hora de
              inicio de la experiencia, no se reembolsará el importe que
              pagaste.
              <br />
              No se aceptarán cambios realizados con menos de 2 días completos*
              de antelación a la hora de inicio de la experiencia.
              <br />
            </p>
            <br />
            <p className="cancellation-policy-description">
              <strong>Cancelación Estricta (Alta Temporada)</strong>
              <br />
              Para obtener un reembolso completo, debes cancelar al menos 7 días
              completos* antes de la hora de inicio de la experiencia.
              <br />
              Si cancelas con menos de 6 días completos* antes de la hora de
              inicio de la experiencia, no se reembolsará el importe que
              pagaste.
              <br />
            </p>
            <br />
          </div>
        </div>
        <button
          className="reservation-button"
          type="submit"
          disabled={reservationConfirmed}
          onClick={handleReservation}
        >
          {reservationConfirmed ? "Reserva Confirmada" : "Confirmar Reserva"}
        </button>
      </div>
      <Footer />
    </>
  );
};

export default BookTour;
