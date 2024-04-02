import React from "react";
import { Link } from "react-router-dom";
import "../style/AdminPanel.css";

const AdminSidebar = () => {
  return (
    <div className="admin-sidebar">
      <div className="admin-buttons">
        <Link className="adminsidebar-links" to="/admin/addTour">
          <button className="admin-sidebar-buttons"> 
          <span className="material-symbols-outlined single-line">add_circle</span>
          Crear Tour
          </button>
        </Link>
      </div>
      <Link className="adminsidebar-links" to="/admin/editTour/:id">
          <button className="admin-sidebar-buttons">
          <span className="material-symbols-outlined single-line">edit_square</span>
          Editar Tour
          </button>
      </Link>
      <Link className="adminsidebar-links" to="/admin/productList">
          <button className="admin-sidebar-buttons">
          <span className="material-symbols-outlined">view_list</span>
          Listado de Productos
          </button>
      </Link>
      <Link className="adminsidebar-links" to="/admin/userList">
          <button className="admin-sidebar-buttons">
          <span className="material-symbols-outlined">group</span>
          Listado de Usuarios
          </button>
      </Link>
      <Link className="adminsidebar-links" to="/admin/categoryList">
          <button className="admin-sidebar-buttons">
          <span className="material-symbols-outlined">category</span>
          Listado de Categorías
          </button>
      </Link>
      <Link className="adminsidebar-links" to="/admin/characteristicList">
          <button className="admin-sidebar-buttons">
          <span className="material-symbols-outlined">category</span>
          Lista de caracteristicas
          </button>
      </Link>
      <Link className="adminsidebar-links" to="/admin/addCategories">
          <button className="admin-sidebar-buttons">
          <span className="material-symbols-outlined">new_label</span>
          Agregar Categorías
          </button>
      </Link>
      <Link className="adminsidebar-links" to="/admin/addCharacteristic">
        
          <button className="admin-sidebar-buttons">
          <span className="material-symbols-outlined">new_label</span>
          Agregar Caracteristicas
          </button>
      </Link>
    </div>
  );
};

export default AdminSidebar;
