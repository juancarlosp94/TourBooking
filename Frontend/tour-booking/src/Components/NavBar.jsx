import React, { useState } from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import { useUserContext } from "../Context/UserContext";
import axios from "axios";
import { Link } from "react-router-dom";
import "../style/NavBar.css";
import Avatar from "@mui/material/Avatar";

function ResponsiveNavBar() {
  const [anchorElNav, setAnchorElNav] = useState(null);
  const [anchorElUser, setAnchorElUser] = useState(null);
  const pages = ["", "", ""];
  const { loggedUser, logout } = useUserContext();

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const settingsAdmin = [
    "Perfil",
    "Panel Admin",
    "Favoritos",
    "Reservas",
    "Cerrar sesión",
  ];

  const settings = ["Perfil", "Favoritos", "Reservas", "Cerrar sesión"];

  return (
    <AppBar
      position="sticky"
      sx={{ backgroundColor: "#1e202a", top: 0, zIndex: 1000 }}
    >
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Typography
            variant="h6"
            noWrap
            component={Link}
            to="/"
            sx={{
              mr: 2,
              display: { xs: "none", md: "flex" },
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            <div className="logo">
              <h1>TourBooking</h1>
            </div>
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar-nav"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              =
            </IconButton>
            <Menu
              sx={{ mt: "45px" }}
              id="menu-appbar-nav"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              transformOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
            >
              {pages.map((page) => (
                <MenuItem key={page} onClick={handleCloseNavMenu}>
                  {page}
                </MenuItem>
              ))}
            </Menu>
          </Box>

          <Typography
            variant="h5"
            noWrap
            component={Link}
            to="/"
            sx={{
              mr: 2,
              display: { xs: "flex", md: "none" },
              flexGrow: 1,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            <h1>TourBooking</h1>
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            {pages.map((page) => (
              <Button
                key={page}
                onClick={handleCloseNavMenu}
                sx={{ my: 2, color: "white", display: "block" }}
              >
                {page}
              </Button>
            ))}
          </Box>
          {loggedUser ? (
            <Box sx={{ flexGrow: 0 }}>
              <Tooltip title="Open settings">
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                  <div className="nav-user">
                    <div className="nav-user-left">
                      <div className="nav-user-circle">
                        <h1>
                          {loggedUser.name.charAt(0).toUpperCase() +
                            loggedUser.surname.charAt(0).toUpperCase()}
                        </h1>
                      </div>
                    </div>
                    <div className="nav-user-right">
                      <div className="nav-user-profile">
                        <p>
                          <b>
                            {loggedUser.name} {loggedUser.surname}
                          </b>
                        </p>
                        <div className="role-profile">
                          <p>
                            {loggedUser.admin ? "Administrador" : "Usuario"}
                          </p>
                        </div>
                      </div>
                      <div className="nav-user-button"></div>
                    </div>
                  </div>
                </IconButton>
              </Tooltip>
              <Menu
                sx={{ mt: "45px" }}
                id="menu-appbar-user"
                anchorEl={anchorElUser}
                anchorOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseUserMenu}
              >
                {loggedUser.admin
                  ? settingsAdmin.map((setting) => (
                      <MenuItem key={setting} onClick={handleCloseUserMenu}>
                        <Typography textAlign="center">
                          <Link
                            to={
                              setting === "Perfil"
                                ? "/userProfile"
                                : setting === "Panel Admin"
                                ? "/admin/adminPanel"
                                : setting === "Favoritos"
                                ? "/favoriteList"
                                : setting === "Reservas"
                                ? "/reservationList"
                                : "/logout"
                            }
                          >
                            {setting}
                          </Link>
                        </Typography>
                      </MenuItem>
                    ))
                  : settings.map((setting) => (
                      <MenuItem key={setting} onClick={handleCloseUserMenu}>
                        <Typography textAlign="center">
                          <Link
                            to={
                              setting === "Perfil"
                                ? "/userProfile"
                                : setting === "Favoritos"
                                ? "/favoriteList"
                                : setting === "Reservas"
                                ? "/ReservationList"
                                : "/logout"
                            }
                          >
                            {setting}
                          </Link>
                        </Typography>
                      </MenuItem>
                    ))}
              </Menu>
            </Box>
          ) : (
            <>
              <Link className="inicio-sesion-link" to="/Login">
                <button className="inicio-sesion-sin-login">
                  Iniciar Sesión
                </button>
              </Link>
              <Link className="registro-usuario-link" to="/RegisterForm">
                <button className="crearCuenta">Crear cuenta</button>
              </Link>
            </>
          )}
        </Toolbar>
      </Container>
    </AppBar>
  );
}

export default ResponsiveNavBar;
