import React, { createContext, useContext, useState } from "react";

// Create a context
const UserContext = createContext();

// Create a provider component
export const UserProvider = ({ children }) => {

  const [loggedUser, setLoggedUser] = useState(null);
  // const [search, setSearch] = useState(null);
  
  const path = "localhost:8080"

  const logout = () => {
    setLoggedUser(null);
  };

  return (
    <UserContext.Provider 
    value={{ loggedUser, setLoggedUser, logout, path }}>
      {children}
    </UserContext.Provider>
  );
};

// Custom hook to use the user context
export const useUserContext = () => {
  return useContext(UserContext);
};
