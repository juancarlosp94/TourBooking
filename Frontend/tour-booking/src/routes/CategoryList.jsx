import React, { useEffect, useState } from "react";
import AdminSidebar from "../Components/AdminSidebar";
import "../style/CategoryList.css";
import axios from "axios";
import Footer from "../Components/Footer";
import { useUserContext } from "../Context/UserContext";

const CategoryList = () => {
  const [category, setCategory] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredCategory, setFilteredCategory] = useState([]);
  const [loading, setLoading] = useState(true); // Set loading initially to true
  const { path } = useUserContext();

  useEffect(() => {
    const fetchCategory = async () => {
      try {
        const response = await axios.get(`http://${path}/category`);
        setCategory(response.data);
        setFilteredCategory(response.data);
        setLoading(false);
      } catch (error) {
        console.log("Error fetching category: ", error);
        setLoading(false);
      }
    };
    fetchCategory();
  }, []);

  useEffect(() => {
    const filteredData = category.filter(
      (data) =>
        data.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        data.id.toString().includes(searchTerm) ||
        data.description.toLowerCase().includes(searchTerm.toLowerCase())
    );

    setFilteredCategory(filteredData);
  }, [searchTerm, category]);

  const handleDelete = async (id) => {
    try {
      const shouldDelete = window.confirm(
        "¿Estas seguro que quieres eliminar la categoria?"
      );
      if (shouldDelete) {
        await axios.delete(`http://${path}/category/${id}`);
        const updatedCategory = category.filter((data) => data.id !== id);
        setCategory(updatedCategory);
        setFilteredCategory(updatedCategory);
      }
    } catch (error) {
      console.log("Error deleting category: ", error);
    }
  };

  if (loading) {
    return (
      <div>
        <h1>Cargando datos de categoria</h1>
        <img src="/images/loading.gif" alt="" />
      </div>
    );
  }

  return (
    <div>
      <div className="admin-format">
        <div className="admin-sidebar-position">
          <AdminSidebar />
        </div>
        <div className="category-list-container-box">
          <h1>Listado de Categorías</h1>
          <div className="search-bar-CategoryList">
            <input
              type="text"
              placeholder="Buscar por ID o nombre"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          {filteredCategory.map((data) => (
            <div className="categoryList" key={data.id}>
              <p className="category-list-id">{data.id}</p>
              <p>{data.name}</p>
              <p className="categoryList-description">{data.description}</p>
              <button
                className="button-delete-category-list"
                onClick={() => handleDelete(data.id)}
              >
                Eliminar
              </button>
            </div>
          ))}
        </div>
      </div>
      <Footer className="category-bottom" />
    </div>
  );
};

export default CategoryList;
