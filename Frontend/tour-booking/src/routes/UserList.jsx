import React, { useState, useEffect } from "react";
import Footer from "../Components/Footer";
import AdminSidebar from "../Components/AdminSidebar";
import axios from "axios";
import "../style/UserList.css";
import "../style/AdminSidebar.css";
import { useUserContext } from "../Context/UserContext"; // Ajusta la ruta según tu estructura de archivos

const UserList = () => {
  const url = "http://3.145.204.233:8080/auth/users";

  const { userList, path } = useUserContext(); // Acceso a la lista filtrada a través del contexto

  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredData, setFilteredData] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchUsers = async () => {
    try {
      const response = await axios.get(`http://${path}/auth/users`);
      setUsers(response.data);
      setFilteredData(response.data);
      setLoading(false);
    } catch (error) {
      console.log("Error fetching users: ", error);
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);
  //reload

  const filterData = () => {
    const filteredData = users.filter(
      (data) =>
        data.id.toString().includes(searchTerm) ||
        data.username.toLowerCase().includes(searchTerm.toLowerCase()) ||
        data.email.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredData(filteredData);
    console.log(filteredData);
  };

  useEffect(filterData, [searchTerm, users]);

  const handleUserAdmin = async (userSent) => {
    try {
      const newAdminStatus = !userSent.admin;
      const userUpdated = {
        admin: newAdminStatus,
      };

      await axios.patch(
        `http://${path}/auth/users/${userSent.id}`,
        userUpdated
      );

      //setReload(!reload);

      setUsers((prevUsers) =>
        prevUsers.map((user) =>
          user.id === userSent.id ? { ...userSent, ...userUpdated } : user
        )
      );

      console.log(
        `User ${userSent.id} ahora ${
          newAdminStatus ? "tiene" : "no tiene"
        } permisos de administrador.`
      );
    } catch (error) {
      console.error("Error editando usuario:", error.response.data); // Mostrar el mensaje de error de Axios
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
        <div className="user-container">
          <h1>Listado de Usuarios</h1>
          <div className="search-bar-user-list">
            <input
              type="text"
              placeholder="Buscar por ID, nombre o email"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>

          {filteredData.map((data) => (
            <div className="users" key={data.id}>
              <p className="userList-id">{data.id}</p>
              <p>{data.username}</p>
              <p>{data.email}</p>
              <button
                className="button-setAdmin"
                onClick={() => handleUserAdmin(data)}
              >
                {data.admin
                  ? "Quitar permisos de administrador"
                  : "Dar permisos de administrador"}
              </button>
            </div>
          ))}
        </div>
      </div>
      <Footer className="product-list-bottom" />
    </>
  );
};

export default UserList;
