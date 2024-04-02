import React, { useState, useEffect } from "react";
import Footer from "../Components/Footer.jsx";
import { Link } from "react-router-dom";
import "../style/AdminPanel.css"
import "../style/AdminSidebar.css"
import AdminSidebar from "../Components/AdminSidebar.jsx";

const AdminPanel = () => {
   
    return (
    <>
    <div className="admin-display">
      
      </div>
      <div className="admin-display-message"> ⚠️ Este componente solo está disponible para escritorio ⚠️</div>
      
      <div className="admin-format">     
      <div className="admin-sidebar-position">

      <AdminSidebar/>
      </div>
      <div className="admin-content">
        <h1>Panel de administrador</h1>
      </div>
      </div>
      <Footer />
    </>
  );
};

export default AdminPanel;
