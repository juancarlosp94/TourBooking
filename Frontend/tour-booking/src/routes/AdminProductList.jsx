import React, { useState, useEffect } from "react";
import axios from "axios";
import Footer from "../Components/Footer";
import { useUserContext } from "../Context/UserContext";
{
  /*logica de paginacion de tours existentes desde admin para gestionar */
}

const AdminProductList = () => {
  const [tours, setTours] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const { path } = useUserContext();
  const toursPerPage = 3;

  useEffect(() => {
    fetchTours();
  }, []);

  const fetchTours = async () => {
    try {
      const response = await axios.get(`http://${path}/tours`);
      setTours(response.data);
    } catch (error) {
      console.error("Error fetching tours:", error);
    }
  };

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const currentTours = tours.slice(
    (currentPage - 1) * toursPerPage,
    currentPage * toursPerPage
  );

  const handleRemove = async (id) => {
    try {
      {
        /*confirm?? */
      }
      await axios.delete(`http://${path}/tours/${id}`);
      console.log("El tour se ha borrado con éxito:", id);

      // Refresh the tours list after successful removal
      fetchTours();
    } catch (error) {
      console.error("Error removing tour:", error);
    }
  };

  return (
    <>
      <div className="bodydestinos">
        <div className="nuestros-destinos">
          <h2> Listado de productos existentes</h2>
          <div className="bodydestinos-subtitle">
            {/*<p> Disfruta de todos los lugares que tenemos para ti </p>*/}
          </div>
        </div>
        <div className="container-categorias-column">
          {Array.from({ length: Math.ceil(currentTours.length / 3) }).map(
            (_, rowIndex) => (
              <div key={rowIndex} className="container-categorias-row">
                {currentTours
                  .slice(rowIndex * 3, rowIndex * 3 + 3)
                  .map((tour) => (
                    <div key={tour.id} className="card-categorias2">
                      {/* Render the tour images */}
                      {tour.images.length > 0 ? (
                        <img src={tour.images[0].url} alt={tour.name} />
                      ) : (
                        <img src="/placeholder-image.png" alt={tour.name} />
                      )}
                      <h3>
                        {tour.id}-{tour.name}
                      </h3>
                      <p>{tour.shortDescription.slice(0, 80)}</p>
                      <button
                        className="ver-mas"
                        onClick={() => handleRemove(tour.id)}
                      >
                        Remove
                      </button>
                      <button
                        className="ver-mas"
                        onClick={() => {
                          window.location.href = `../tour/${tour.id}`;
                        }}
                      >
                        Ver más &gt;
                      </button>
                    </div>
                  ))}
              </div>
            )
          )}
        </div>
        <div className="pagination">
          {tours.length > toursPerPage && (
            <ul className="pagination-list">
              {currentPage > 1 && (
                <li className="pagination-item">
                  <button
                    onClick={() => paginate(currentPage - 1)}
                    className="pagination-link"
                  >
                    &lt;
                  </button>
                </li>
              )}
              {Array.from({
                length: Math.ceil(tours.length / toursPerPage),
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
              {currentPage < Math.ceil(tours.length / toursPerPage) && (
                <li className="pagination-item">
                  <button
                    onClick={() => paginate(currentPage + 1)}
                    className="pagination-link"
                  >
                    &gt;
                  </button>
                </li>
              )}
            </ul>
          )}
        </div>
      </div>
      <Footer />
    </>
  );
};

export default AdminProductList;
