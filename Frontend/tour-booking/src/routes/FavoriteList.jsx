import React, { useEffect, useState } from "react";
import "../style/ReservationList.css";
import axios from "axios";
import { useUserContext } from "../Context/UserContext";
import Footer from "../Components/Footer";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";

const columns = [
  { id: "image", label: "#", minWidth: 50 },
  { id: "name", label: "NOMBRE", minWidth: 170 },
  { id: "shortDescription", label: "DESCRIPCIÓN", minWidth: 100 },
  {
    id: "actions",
    label: "ELIMINAR",
    minWidth: 100,
  },
];

const FavoriteList = () => {
  const { loggedUser, path } = useUserContext();
  const [favoritos, setFavoritos] = useState([]);
  const [loading, setLoading] = useState(true);

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://${path}/auth/favorites/${id}`);
      console.log("Se cargo el listado de favoritos correctamente.");
      setFavoritos((prevReservas) =>
        prevReservas.filter((reserva) => reserva.idReservation !== id)
      );
    } catch (error) {
      console.error("Error al cargar listado de favoritos: ", error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://${path}/auth/favorites/${loggedUser.id}`
        );
        setFavoritos(response.data);
        setLoading(false);
      } catch (error) {
        console.error("Error al cargar el listado de favoritos: ", error);
        setLoading(false);
      }
    };
    fetchData();
  }, [loggedUser.idReservation]);

  const handleEraseFavorite = async (id) => {
    try {
      await axios.delete(`http://${path}/auth/${id}/favorite`, {
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(parseInt(loggedUser.id)),
      });
      console.log("Favorito borrado correctamente.");
      setFavoritos((prevFavoritos) =>
        prevFavoritos.filter((favoritos) => favoritos.id !== id)
      );
    } catch (error) {
      console.log("Error borrando el favorito: ", error);
    }
  };

  if (loading) {
    return (
      <div className="loading">
        <h1>Cargando listado de favoritos...</h1>
        <img src="/images/loading.gif" alt="" />
      </div>
    );
  }

  return (
    <>
      <div className="bodyfavoritos">
        <h2>Mis Favoritos</h2>
        <TableContainer style={{ backgroundColor: "white" }}>
          <Table stickyHeader aria-label="sticky table">
            <TableHead>
              <TableRow>
                {columns.map((column) => (
                  <TableCell
                    key={column.id}
                    align="left"
                    style={{ minWidth: column.minWidth, color: "black" }}
                  >
                    {column.label}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {favoritos.map((row) => (
                <TableRow key={row.id}>
                  <TableCell>
                    <img
                      src={row.images[0].url} // Display the first image URL
                      alt={row.name} // Add alt text for accessibility
                      style={{
                        maxWidth: "70px",
                        maxHeight: "70px",
                        borderRadius: "10%",
                      }}
                    />
                  </TableCell>
                  {columns.slice(1).map((column) => {
                    const value = row[column.id];
                    return (
                      <TableCell
                        key={column.id}
                        align="left"
                        style={{ color: "black" }} // Set font color to black
                      >
                        {column.id === "actions" ? (
                          <button
                            className="button-delete"
                            onClick={() => handleEraseFavorite(row.id)}
                          >
                            <span class="material-symbols-outlined">
                              delete
                            </span>
                          </button>
                        ) : (
                          value
                        )}
                      </TableCell>
                    );
                  })}
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
      <Footer />
    </>
  );
};

export default FavoriteList;
