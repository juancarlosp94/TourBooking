import React, { useEffect, useState } from "react";
import AdminSidebar from "../Components/AdminSidebar";
import "../style/CharacteristicList.css";
import axios from "axios";
import Footer from "../Components/Footer";
import { useUserContext } from "../Context/UserContext";


const CharacteristicList = () => {
  const [characteristic, setCharacteristic] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredCharacteristic, setFilteredCharacteristic] = useState([]);
  const [loading, setLoading] = useState(true); // Set loading initially to true


  const { path } = useUserContext();

  useEffect(() => {
    const fetchCharacteristic = async () => {
      try {
        const response = await axios.get(`http://${path}/characteristic`);
        setCharacteristic(response.data);
        setFilteredCharacteristic(response.data);
        setLoading(false);
      } catch (error) {
        console.log("Error fetching category: ", error);
        setLoading(false);
      }
    };
    fetchCharacteristic();
  }, []);

  useEffect(() => {
    const filteredData = characteristic.filter(
      (data) =>
        data.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        data.id.toString().includes(searchTerm) ||
        data.description.toLowerCase().includes(searchTerm.toLowerCase())
    );

    setFilteredCharacteristic(filteredData);
  }, [searchTerm, characteristic]);

  const handleDelete = async (id) => {
    try {
      const shouldDelete = window.confirm("¿Estas seguro que quieres eliminar la categoria?")
      if(shouldDelete){


      await axios.delete(`http://${path}/characteristic/${id}`);
      const updatedCharacteristic = characteristic.filter((data) => data.id !== id);
      setCharacteristic(updatedCharacteristic);
      setFilteredCharacteristic(updatedCharacteristic);
      }
    } catch (error) {
      console.log("Error deleting category: ", error);
    }
    
  };

  if (loading) {
    return (
      <div>
        <h1>Cargando datos de caracteristicas</h1>
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
        <div className="characteristic-list-container">
          <h1>Listado de Caracteristicas</h1>
          <div className="search-bar-characteristic-list">
            <input
              type="text"
              placeholder="Buscar por ID o nombre"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          {filteredCharacteristic.map((data) => (
            <div className="characteristic" key={data.id}>
              <p className="charateristic-id">{data.id}</p>
              <p>{data.name}</p>
              <button
                className="delete-characterist"
                onClick={() => handleDelete(data.id)}
              >
                Eliminar
              </button>
            </div>
          ))}
        </div>
        
      </div>
      <Footer className="characteristic-bottom" />
    </div>
    
  )
}

export default CharacteristicList
