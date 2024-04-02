import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import "../style/Card.css";
import TextField from "@mui/material/TextField";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
import ClearIcon from "@mui/icons-material/Clear";
import { useUserContext } from "../Context/UserContext";

const BodyCategoria = () => {
  const [tours, setTours] = useState([]);
  const [category, setCategory] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const toursPerPage = 9;
  const [searchInput, setSearchInput] = useState("");
  const [filteredTours, setFilteredTours] = useState([]);
  const [loading, setLoading] = useState(true);
  const params = useParams();
  const { path } = useUserContext();

  useEffect(() => {
    fetchTours();
    fetchCategory();
  }, []);

  useEffect(() => {
    setFilteredTours(tours);
    setCurrentPage(1);
  }, [tours]);

  const fetchTours = async () => {
    try {
      const response = await axios.get(
        `http://${path}/tours/toursbycategory/${params.id}`
      );
      setTours(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching tours:", error);
      setLoading(false);
    }
  };

  const fetchCategory = async () => {
    try {
      const response = await axios.get(
        `http://3.145.204.233:8080/category/${params.id}`
      );
      setCategory(response.data);
    } catch (error) {
      console.error("Error fetching tours:", error);
    }
  };

  const handleSearch = () => {
    const filtered = tours.filter((tour) =>
      tour.name.toLowerCase().includes(searchInput.toLowerCase())
    );
    setFilteredTours(filtered);
    setCurrentPage(1);
  };

  const handleErase = () => {
    setSearchInput("");
    setFilteredTours(tours);
    setCurrentPage(1);
  };

  const indexOfLastTour = currentPage * toursPerPage;
  const indexOfFirstTour = indexOfLastTour - toursPerPage;
  const currentTours = filteredTours
    ? filteredTours.slice(
        indexOfFirstTour,
        Math.min(indexOfLastTour, filteredTours.length)
      )
    : [];

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <>
      <div
        className="banner"
        style={{
          background: `linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8))`,
          backgroundImage: `url(${category.urlCategoryImage})`,
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center center",
          backgroundAttachment: "fixed", // Wrap "fixed" in quotation marks
        }}
      >
        <div className="banner-title">
          <h2>{category.name}</h2>
        </div>
        <div className="banner-subtitle">
          <h3>{category.description}</h3>
        </div>
      </div>
      <div className="bodydestinos">
        <div className="nuestros-destinos">
          <h2> Nuestros Destinos de {category.name}</h2>
          <div className="bodydestinos-subtitle">
            <p> Disfruta de todos los lugares que tenemos para ti </p>
          </div>
        </div>
        <div className="search-bar">
          <TextField
            type="text"
            label="Buscar por nombre"
            variant="outlined"
            value={searchInput}
            onChange={(e) => setSearchInput(e.target.value)}
            fullWidth
          />
          <IconButton onClick={handleSearch} aria-label="Buscar">
            Search
          </IconButton>
          {searchInput && (
            <IconButton
              className="erase-button"
              onClick={handleErase}
              aria-label="Borrar"
            >
              Clear
            </IconButton>
          )}
        </div>
        {loading ? (
          <div className="loading-card">
            <img src="/images/card-loading.gif" alt="" />
          </div>
        ) : (
          <div className="container-categorias-column">
            {Array.from({ length: Math.ceil(currentTours.length / 3) }).map(
              (_, rowIndex) => (
                <div key={rowIndex} className="container-categorias-row">
                  {currentTours
                    .slice(rowIndex * 3, rowIndex * 3 + 3)
                    .map((tour) => (
                      <Link to={"../tours/" + tour.id}>
                        <div key={tour.id} className="card-categorias2">
                          <div className="favorite-card">
                            <span class="material-symbols-outlined">
                              favorite
                            </span>
                          </div>
                          <div className="categorytag-card">
                            <span class="material-symbols-outlined">label</span>
                            <p> {tour.category.name} </p>
                          </div>
                          {tour.images.length > 0 ? (
                            <img src={tour.images[0].url} alt={tour.name} />
                          ) : (
                            <img src="/placeholder-image.png" alt={tour.name} />
                          )}
                          <h3>{tour.name}</h3>
                          <p>
                            {tour.shortDescription
                              ? tour.shortDescription.slice(0, 80)
                              : "No description available"}
                            ...
                          </p>

                          <div className="vermas-card">
                            <button
                              className="ver-mas2"
                              href={"../tours/" + tour.id}
                            >
                              Ver más &gt;
                            </button>
                          </div>
                          <div className="price-card">
                            <h3>
                              <span> $ {tour.price}</span> /persona
                            </h3>
                          </div>
                        </div>
                      </Link>
                    ))}
                </div>
              )
            )}
          </div>
        )}

        <div className="pagination">
          {filteredTours.length > toursPerPage && (
            <ul className="pagination-list">
              {Array.from({
                length: Math.ceil(filteredTours.length / toursPerPage),
              }).map((_, index) => (
                <li key={index} className="pagination-item">
                  <button
                    onClick={() => paginate(index + 1)}
                    className={`pagination-link ${
                      currentPage === index + 1 ? "active" : ""
                    }`}
                  >
                    {index + 1}
                  </button>
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </>
  );
};

export default BodyCategoria;
