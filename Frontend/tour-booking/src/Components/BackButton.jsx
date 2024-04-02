import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import Footer from "../Components/Footer";
import style from "../style/Carrusel.css";
import "../style/TourDetails.css";
import { useUserContext } from "../Context/UserContext";

const BackButton = () => {


    const navigate = useNavigate();

    return (
        <>
         <button className="volver" type="button" onClick={() => navigate(-1)}>
            {" "}
            Volver atras{" "}
          </button>
        </>
    )
}

export default BackButton