import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "../style/Banner.css";
import "../style/Card.css";
import "../style/Footer.css";
import "../style/Body.css";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Autocomplete from "@mui/material/Autocomplete";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import { useUserContext } from "../Context/UserContext";

const Body = () => {
  const [tours, setTours] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [searchInput, setSearchInput] = useState("");
  const [filteredTours, setFilteredTours] = useState([]);
  const [category, setCategory] = useState([]);
  const [searchResults, setSearchResults] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const [numAdults, setNumAdults] = useState(1);
  const navigate = useNavigate();
  const [searchByTour, setSearchByTour] = useState(true);
  const { path } = useUserContext();

  const toursPerPage = 3;

  useEffect(() => {
    fetchTours();
    fetchCategory();
  }, []);

  useEffect(() => {
    setFilteredTours(tours);
    setCurrentPage(1);
  }, [tours]);

  useEffect(() => {
    if (searchInput) {
      fetchSearchResults();
    } else {
      setSearchResults([]);
    }
  }, [searchInput]);

  const fetchTours = async () => {
    try {
      const response = await axios.get(`http://${path}/tours`);
      setTours(response.data);
    } catch (error) {
      console.error("Error fetching tours:", error);
      // Handle the error here (e.g., display an error message to the user)
    }
  };

  const fetchCategory = async () => {
    try {
      const response = await axios.get(`http://${path}/category`);
      setCategory(response.data);
    } catch (error) {
      console.error("Error fetching category:", error);
      // Handle the error here (e.g., display an error message to the user)
    }
  };

  const fetchSearchResults = async () => {
    try {
      const response = await axios.get(
        `http://${path}/tours/search/byname?name=${searchInput}`
      );
      setSearchResults(response.data);
    } catch (error) {
      console.error("Error fetching search results:", error);
      // Handle the error here (e.g., display an error message to the user)
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

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const indexOfLastTour = currentPage * toursPerPage;
  const indexOfFirstTour = indexOfLastTour - toursPerPage;
  const currentTours = filteredTours
    ? filteredTours.slice(
        indexOfFirstTour,
        Math.min(indexOfLastTour, filteredTours.length)
      )
    : [];

  const handleNextPage = () => {
    if (currentPage < Math.ceil(currentTours.length / toursPerPage)) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handlePrevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  const handleTourClick = (tourId) => {
    navigate(`/tours/${tourId}`);
  };

  const handleSearchByTour = (event, newSearchByTour) => {
    setSearchByTour(newSearchByTour);
  };

  return (
    <>
      <div className="banner">
        <div className="banner-title">
          <h2> Vive las mejores experiencias de Sudamérica </h2>
        </div>
        <div className="banner-subtitle">
          <h3>
            Un viaje se vive 3 veces: cuando lo soñamos, cuando lo vivimos y
            cuando lo recordamos
          </h3>
        </div>
        <div className="search-horizontal">
          <ToggleButtonGroup
            value={searchByTour}
            exclusive
            onChange={(e, newSearchByTour) => setSearchByTour(newSearchByTour)}
            aria-label="searchByTour"
          >
            <ToggleButton value={true} aria-label="searchByTour">
              <span className="material-symbols-outlined">event</span>
            </ToggleButton>
            <ToggleButton value={false} aria-label="searchByDate">
              <span className="material-symbols-outlined">local_activity</span>
            </ToggleButton>
          </ToggleButtonGroup>

          {searchByTour ? (
            <>
              <TextField
                label="Buscar tour por fecha"
                variant="outlined"
                className="inputSearchTour"
                value={searchInput}
                onChange={(e) => setSearchInput(e.target.value)}
              />
              <FormControl variant="outlined" className="numAdultsFormControl">
                <InputLabel htmlFor="numAdults">Adultos</InputLabel>
                <Select
                  id="numAdults"
                  label="Adultos"
                  name="numAdults"
                  value={numAdults}
                  onChange={(e) => setNumAdults(e.target.value)}
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
                }}
              />
              <Button
                variant="contained"
                color="primary"
                onClick={handleSearch}
                className="buscarPorReserva"
              >
                <span className="material-symbols-outlined">search</span>
              </Button>
            </>
          ) : (
            <Autocomplete
              id="search"
              options={searchResults}
              getOptionLabel={(result) => result.name}
              className="inputSearchTour"
              value={
                searchResults.find((result) => result.name === searchInput) ||
                null
              }
              onChange={(e, newValue) => {
                setSearchInput(newValue ? newValue.name : "");
                handleTourClick(newValue ? newValue.id : null);
              }}
              renderInput={(params) => (
                <TextField
                  {...params}
                  label="Buscar por tour"
                  variant="outlined"
                  value={searchInput}
                  onChange={(e) => setSearchInput(e.target.value)}
                />
              )}
            />
          )}
        </div>
      </div>

      <div className="bodydestinos">
        <div className="nuestros-destinos">
          <h2> Nuestros Destinos </h2>
          <div className="bodydestinos-subtitle">
            <p> Disfruta de todos los lugares que tenemos para ti </p>
          </div>
        </div>
      </div>

      <div className="container-categorias">
        {category.map((data) => (
          <Link to={"../category/" + data.id} key={data.id}>
            <div className="card-categorias">
              <img src={data.urlCategoryImage} alt="" loading="lazy" />
              <h3>{data.name}</h3>
              <p>
                {data.description.length > 40
                  ? data.description.slice(0, 40) + "..."
                  : data.description}
              </p>
              <button className="ver-mas">Ver más &gt;</button>
            </div>
          </Link>
        ))}
      </div>
      <div className="bodyrecomendados">
        <h2> Recomendados </h2>
        <div className="container-categorias-column">
          {Array.from({ length: Math.ceil(currentTours.length / 3) }).map(
            (_, rowIndex) => (
              <div key={rowIndex} className="container-categorias-row">
                {currentTours
                  .slice(rowIndex * 3, rowIndex * 3 + 3)
                  .map((tour) => (
                    <Link to={"../tours/" + tour.id} key={tour.id}>
                      <div key={tour.id} className="card-categorias2">
                        <div className="favorite-card">
                          <span className="material-symbols-outlined">
                            favorite
                          </span>
                        </div>
                        {tour.category && (
                          <div className="categorytag-card">
                            <span className="material-symbols-outlined">
                              label
                            </span>
                            <p> {tour.category.name} </p>
                          </div>
                        )}
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
      </div>
    </>
  );
};

export default Body;
