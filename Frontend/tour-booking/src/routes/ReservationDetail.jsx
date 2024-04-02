import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import Footer from "../Components/Footer";
import "../style/ReservationDetail.css";
import "flatpickr/dist/themes/material_green.css";
import { useUserContext } from "../Context/UserContext";

const ReservationDetail = () => {
  const [reservation, setReservation] = useState(null);
  const params = useParams();
  const [ratingValue, setRatingValue] = useState("");
  const [comments, setComments] = useState("");
  const navigate = useNavigate();
  const { path } = useUserContext();

  const handleRatingValueInputChange = (event) => {
    const newRatingValue = event.target.value;
    setRatingValue(newRatingValue);
  };

  const handleCommentInputChange = (event) => {
    const newComment = event.target.value;
    setComments(newComment);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const requestBody = {
      idReservation: reservation.idReservation,
      ratingValue: ratingValue,
      comments: comments,
    };

    try {
      const response = await axios.post(`http://${path}/rating`, requestBody, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.status === 200) {
        const data = response.data;
        console.log("Calificación hecha OK");
      } else {
        console.log("No se pudo completar su calificacion.");
      }
    } catch (error) {
      console.log("No se pudo completar su calificacion.");
    }
  };

  useEffect(() => {
    const fetchReservation = async () => {
      try {
        const response = await axios.get(
          `http://${path}/reservation/byid/${params.id}`
        );
        setReservation(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching reservation:", error);
      }
    };

    fetchReservation();
  }, [params.id]);

  if (!reservation) {
    return (
      <div className="loading">
        <h1>Cargando tu reserva...</h1>
        <img src="/images/loading.gif" alt="" />
      </div>
    );
  }

  return (
    <>
      <div className="reservation-detail-container">
        <div className="reservation-detail-container-left"></div>
        <div className="reservation-detail-container-right">
          <h1>Datos de Reserva</h1>
          <h5>{reservation.tour.name}</h5>
          <p>
            <span class="material-symbols-outlined">stars</span>
            {reservation.tour.ratingStats.averageRating} (
            {reservation.tour.ratingStats.ratingsCount} evaluaciones)
          </p>

          <p>
            <span class="material-symbols-outlined">sell</span>
            {reservation.tour.price} ARS/dia
          </p>
          <p>{reservation.tour.shortDescription}</p>

          <form className="inicio-sesion-form" onSubmit={handleSubmit}>
            <h5>Dejanos tu opinión</h5>
            <div className="reservation-detail-form-line">
              <span class="material-symbols-outlined">star</span>
              <input
                type="number"
                id="ratingValue"
                placeholder="Introduce tu puntuación del 1 al 5"
                value={ratingValue}
                onChange={handleRatingValueInputChange}
                min="1"
                max="5"
                required
              />
            </div>
            <div className="reservation-detail-form-line">
              <span class="material-symbols-outlined">forum</span>
              <textarea
                type="text"
                id="comments"
                placeholder="Dejanos tu opinión sobre el tour."
                value={comments}
                onChange={handleCommentInputChange}
                required
              />
            </div>
            <button className="inicio-sesion-button" type="submit">
              Calificar
            </button>
          </form>
        </div>
      </div>
    </>
  );
};

export default ReservationDetail;
