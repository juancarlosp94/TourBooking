import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams, Link } from "react-router-dom";
import Footer from "../Components/Footer";
import "../style/Carrusel.css";
import "../style/TourDetail.css";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { useUserContext } from "../Context/UserContext";
import {
  Button,
  Container,
  FormControl, // Import FormControl
  Grid,
  InputLabel, // Import InputLabel
  MenuItem,
  Select,
  Typography,
  TextField,
} from "@mui/material";
import {
  FacebookShareButton,
  TwitterShareButton,
  WhatsappShareButton,
  FacebookMessengerShareButton,
  TelegramShareButton,
  LinkedinShareButton,
  PinterestShareButton,
} from "react-share";

const TourDetail = () => {
  const [tour, setTour] = useState(null);
  const [rating, setRating] = useState(null);
  const [ratingComments, setRatingComments] = useState([]);
  const [selectedTickets, setSelectedTickets] = useState(null);
  const params = useParams();
  const navigate = useNavigate();
  const [current, setCurrent] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [isFavorite, setIsFavorite] = useState(false);
  const { loggedUser, path } = useUserContext();
  const [resultadoReserva, setResultadoReserva] = useState(false);
  const [selectedDate, setSelectedDate] = useState(null);
  const [numberOfPeople, setNumberOfPeople] = useState(1);
  const [numAdults, setNumAdults] = useState(1);

  const slideLeft = () => {
    setCurrent(current === 0 ? tour.images.length - 1 : current - 1);
  };

  const slideRight = () => {
    setCurrent(current === tour.images.length - 1 ? 0 : current + 1);
  };

  const handleNumberOfPeopleChange = (event) => {
    setNumberOfPeople(event.target.value);
  };

  useEffect(() => {
    const fetchTour = async () => {
      try {
        const response = await axios.get(`http://${path}/tours/${params.id}`);
        setTour(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching tour:", error);
      }
    };

    fetchTour();
  }, [params.id]);

  useEffect(() => {
    const fetchTour = async () => {
      try {
        const response = await axios.get(
          `http://${path}/tours/ratingcomments/${params.id}`
        );
        setRatingComments(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching tour:", error);
      }
    };

    fetchTour();
  }, [params.id]);

  useEffect(() => {
    const fetchTour = async () => {
      try {
        const response = await axios.get(
          `http://${path}/tours/ratingstats/${params.id}`
        );
        setRating(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching tour:", error);
      }
    };

    fetchTour();
  }, [params.id]);

  const handleDateChange = (date) => {
    setSelectedDate(date[0]);
  };

  const handleAddFavorite = async () => {
    setIsFavorite(true);
    try {
      const response = await fetch(
        `http://${path}/auth/${params.id}/favorite`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(parseInt(loggedUser.id)),
        }
      );

      if (!response.ok) {
        throw new Error("No fue posible agregar a favorito.");
        setIsFavorite(false);
      }

      if (response.status === 200) {
        console.log("Favoritado correctamente.");
      }
    } catch (error) {
      console.log("No fue posible agregar a favorito.", error.message);
      setIsFavorite(false);
    }
  };

  const handleEraseFavorite = async () => {
    setIsFavorite(false);
    try {
      const response = await fetch(
        `http://${path}/auth/${params.id}/favorite`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(parseInt(loggedUser.id)),
        }
      );

      if (!response.ok) {
        throw new Error("No fue posible borrar favorito.");
        setIsFavorite(true);
      }

      if (response.status === 200) {
        console.log("Favorito borrado correctamente.");
      }
    } catch (error) {
      console.log("No fue posible borrar favorito.", error.message);
      setIsFavorite(true);
    }
  };

  // const handleTicketsChange = async () => {

  //   setSelectedTickets();
  // };

  if (!tour) {
    return (
      <div className="loading">
        <h1>Cargando el tour de tus sueños</h1>
        <img src="/images/loading.gif" alt="" />
      </div>
    );
  }

  return (
    <>
      <div>
        {/*         <div className="images-container">
          <div className="carrusel-wrapper">
            <div className="moveto_left" onClick={slideLeft}>
              &lsaquo;
            </div>
            <div className="moveto_right" onClick={slideRight}>
              &rsaquo;
            </div>
            {tour.images.map((image, index) => {
              return (
                <div
                  key={index}
                  className={
                    index === current ? "carrusel_card-active" : "carrusel_card"
                  }
                >
                  <img src={image.url} alt="" />
                </div>
              );
            })}
          </div>
          <div className="carruselCard-thumbnail-container">
            {tour.images.map((image, index) => {
              return (
                <div key={index} className={"carruselCard-thumbnail"}>
                  <img src={image.url} alt="" />
                </div>
              );
            })}
          </div>
        </div> */}
      </div>
      <div className="tour-details">
        <div className="tour-details-title">
          <div className="tour-details-title-left">
            <div className="volver-atras">
              <button type="button" onClick={() => navigate(-1)}>
                <span class="material-symbols-outlined">arrow_back</span>Volver
                atrás
              </button>
            </div>
            <h1>
              {tour.name}
              {"    "}
              {!isFavorite ? (
                <span
                  className="material-symbols-outlined"
                  onClick={handleAddFavorite}
                  style={{ cursor: "pointer" }}
                >
                  heart_plus
                </span>
              ) : (
                <span
                  className="material-symbols-rounded"
                  onClick={handleEraseFavorite}
                  style={{ cursor: "pointer" }}
                >
                  favorite
                </span>
              )}
            </h1>
            <div className="rating-tours">
              <span className="material-symbols-outlined">sell</span>
              {tour.category.name}
              <span className="material-symbols-outlined">star</span>
              {rating && !isNaN(rating.averageRating)
                ? rating.averageRating.toFixed(2)
                : 0}
              <span className="material-symbols-outlined">group</span>
              {rating ? rating.ratingsCount : 0}
            </div>
          </div>
          <div className="tour-details-title-right">
            <FacebookShareButton
              url={window.location.href}
              quote={`Check out this amazing tour: ${tour.name}`}
              appId="10157608390022894"
            >
              <img src="/images/social/facebook.png" alt="Facebook" />
            </FacebookShareButton>
            <TwitterShareButton
              url={window.location.href}
              title={`Check out this amazing tour: ${tour.name}`}
            >
              <img src="/images/social/twitter.png" alt="Twitter" />
            </TwitterShareButton>
            <WhatsappShareButton
              url={window.location.href}
              title={`Check out this amazing tour: ${tour.name}`}
            >
              <img src="/images/social/whatsapp.png" alt="WhatsApp" />
            </WhatsappShareButton>
            <FacebookMessengerShareButton
              url={window.location.href}
              appId="10157608390022894"
            >
              <img src="/images/social/messenger.png" alt="Messenger" />
            </FacebookMessengerShareButton>
            <TelegramShareButton
              url={window.location.href}
              title={`Check out this amazing tour: ${tour.name}`}
            >
              <img src="/images/social/telegram.png" alt="Telegram" />
            </TelegramShareButton>
            <LinkedinShareButton
              url={window.location.href}
              title={`Check out this amazing tour: ${tour.name}`}
            >
              <img src="/images/social/linkedin.png" alt="LinkedIn" />
            </LinkedinShareButton>
            <PinterestShareButton
              url={window.location.href}
              media={tour.images[0].url}
              description={`Check out this amazing tour: ${tour.name}`}
            >
              <img src="/images/social/pinterest.png" alt="Pinterest" />
            </PinterestShareButton>
          </div>
        </div>
        <div className="carousel-container">
          <div
            className="carousel-container-main"
            style={{
              background: `linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8))`,
              backgroundImage: `url(${tour.images[0].url})`,
              backgroundSize: "cover",
              backgroundRepeat: "no-repeat",
            }}
          ></div>
          <div className="carousel-container-thumbnail-box">
            {tour.images.map((image, index) => {
              return (
                <div
                  className="carousel-container-thumbnail"
                  key={index}
                  style={{
                    backgroundImage: `url(${image.url})`,
                    backgroundSize: "cover",
                    backgroundRepeat: "no-repeat",
                  }}
                ></div>
              );
            })}
          </div>
        </div>
        <div className="container-subTourDetail">
          <div className="container-subTourDetail-left">
            <div className="container-subTourDetail-card">
              <h5>Descripción del Tour</h5>
              <p>{tour.description}</p>
            </div>
            <div className="container-subTourDetail-card">
              <h5>Política de Cancelación</h5>
              <p>
                Existen diferentes políticas de cancelación, dependiendo del
                tour o actividad específica que elijas. Asegúrese de verificar
                cuál es aplicable a la experiencia elegida antes de confirmar su
                reserva. Esta información se puede encontrar al momento de
                reservar en la sección Política de cancelación.
              </p>
              <br />
              <h5>Política de Menores de edad</h5>
              <p>
                Los menores de 2 años de edad sin cumplir a la fecha de inicio
                del viaje, no pagan.(*) <br />
                Desde los 2 años en adelante, los menores abonan como un adulto.
                (*) <br />
                (*) Todos los menores de edad deberán viajar obligatoriamente
                acompañados por sus padres o responsable a cargo mayor de 18
                años. <br />
              </p>
            </div>
          </div>
          <div className="container-subTourDetail-right">
            <div className="container-reservation-card">
              <div className="container-reservation-card-price">
                <h1>${tour.price}</h1>
                <p>/persona</p>
              </div>
              <div className="rating-tours">
                <span className="material-symbols-outlined">star</span>
                {rating && !isNaN(rating.averageRating)
                  ? rating.averageRating.toFixed(2)
                  : 0}
                <span className="material-symbols-outlined">group</span>
                {rating ? rating.ratingsCount : 0}
              </div>
              {loggedUser ? (
                <>
                  <Container className="container-" style={{ color: "white" }}>
                    <FormControl
                      variant="outlined"
                      className="numAdultsFormControl"
                      style={{ color: "white", borderColor: "white" }}
                    >
                      <InputLabel
                        htmlFor="numAdults"
                        style={{ color: "white" }}
                      >
                        Adultos
                      </InputLabel>
                      <Select
                        id="numAdults"
                        label="Adultos"
                        name="numAdults"
                        value={numAdults}
                        onChange={(e) => setNumAdults(e.target.value)}
                        style={{ color: "white" }}
                      >
                        <MenuItem value={1}>1</MenuItem>
                        <MenuItem value={2}>2</MenuItem>
                        <MenuItem value={3}>3</MenuItem>
                        <MenuItem value={4}>4</MenuItem>
                        <MenuItem value={5}>5</MenuItem>
                      </Select>
                    </FormControl>
                    <TextField
                      id="calendar"
                      label="Seleccionar fecha"
                      type="date"
                      variant="outlined"
                      className="numAdultsFormControl"
                      InputLabelProps={{
                        shrink: true,
                        style: { color: "white" },
                      }}
                      style={{ borderColor: "white" }}
                    />
                    <Link to={"../bookTour/" + tour.id}>
                      <button className="reservaAhora">RESERVAR AHORA</button>
                    </Link>
                  </Container>
                </>
              ) : (
                <>
                  <p className="tour-detail-table-i">
                    <span className="material-symbols-outlined">warning</span>
                    <i className="tour-detail-table-i">
                      Necesitas primero ser usuario registrado para poder
                      reservar un tour. <br />
                      <Link
                        className="inicio-sesion-link-tour-detail"
                        to={`/Login?redirect=/tours/${params.id}`}
                      >
                        Regístrate o inicia sesión!
                      </Link>
                    </i>
                  </p>
                </>
              )}
            </div>
          </div>
        </div>
        <div className="container-subTourDetail-card">
          <h5>Ubicación</h5>
          <iframe
            className="map-responsive"
            src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d633129.2678977188!2d-64.85320429968031!3d-42.51066373444486!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses!2sar!4v1694910281733!5m2!1ses!2sar"
            width="100"
            height="550"
            style={{ borderRadius: "10px" }}
            allowFullScreen
            title="Google Map"
          ></iframe>
        </div>
        <div className="container-subTourDetail-card">
          <h5>Características del Producto</h5>
          <div className="characteristics-items">
            {tour.characteristics.map((characteristics) => {
              return (
                <ul className="characteristics-ul" key={characteristics.id}>
                  <i className={characteristics.urlCharacteristicImage}></i>
                  {characteristics.name}
                </ul>
              );
            })}
          </div>
        </div>
        <div className="tour-detail-location-container"></div>
        <div>
          <h5>Opiniones de Usuarios</h5>
          {ratingComments && ratingComments.length > 0 ? (
            ratingComments.map((data) => (
              <div className="tour-comments" key={data.usuarioId}>
                <div className="tour-comments-profile">
                  <span className="material-symbols-outlined">
                    account_circle
                  </span>
                  <p>{data.userName}</p>
                </div>
                <div className="tour-comments-text">
                  <p>{data.comment}</p>
                </div>
              </div>
            ))
          ) : (
            <p>No hay comentarios todavía.</p>
          )}
        </div>
      </div>
      <Footer />
    </>
  );
};

export default TourDetail;
