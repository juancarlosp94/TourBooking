import React, { useEffect, useState } from "react";
import axios from "axios";
import AdminSidebar from "../Components/AdminSidebar";
import "../style/ProductList.css";
import "../style/AdminSidebar.css";
import Footer from "../Components/Footer";
import { useUserContext } from "../Context/UserContext";

const ProductList = () => {
  const [tour, setTour] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredTour, setFilteredTour] = useState([]);
  const [loading, setLoading] = useState(true); // Set loading initially to true
  const { path } = useUserContext();

  useEffect(() => {
    const fetchTour = async () => {
      try {
        const response = await axios.get(`http://${path}/tours`);
        setTour(response.data);
        setFilteredTour(response.data);
        setLoading(false);
      } catch (error) {
        console.log("Error fetching tour: ", error);
        setLoading(false);
      }
    };
    fetchTour();
  }, []);

  useEffect(() => {
    const filteredData = tour.filter(
      (data) =>
        data.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        data.id.toString().includes(searchTerm)
    );

    setFilteredTour(filteredData);
  }, [searchTerm, tour]);

  const handleDelete = async (id) => {
    try {
      const shouldDelete = window.confirm(
        "¿Estas seguro que quieres eliminar la categoria?"
      );
      if (shouldDelete) {
        await axios.delete(`http://${path}/tours/${id}`);
        const updatedTour = tour.filter((data) => data.id !== id);
        setTour(updatedTour);
        setFilteredTour(updatedTour);
      }
    } catch (error) {
      console.log("Error deleting tour: ", error);
    }
  };

  if (loading) {
    return (
      <div>
        <h1>Cargando listado de productos...</h1>
        <img src="/images/loading.gif" alt="" />
      </div>
    );
  }

  return (
    <>
      <div className="admin-format">
        <div className="admin-sidebar-position">
          <AdminSidebar />
        </div>
        <div className="product-list-container">
          <h1>Listado de Productos</h1>
          <div className="search-bar-list">
            <input
              type="text"
              placeholder="Buscar por ID o nombre"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          {filteredTour.map((data) => (
            <div className="products" key={data.id}>
              <p className="product-list-id">{data.id}</p>
              <p>{data.name}</p>
              <button
                className="delete-product"
                onClick={() => handleDelete(data.id)}
              >
                Eliminar
              </button>
            </div>
          ))}
        </div>
  
      </div>
      <Footer className="characteristic-bottom" />
    </>
  );
};

export default ProductList;
