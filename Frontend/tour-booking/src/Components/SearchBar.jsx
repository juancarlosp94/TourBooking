import React, { useState } from "react";

const SearchBar = ({ onSearch }) => {
  const [searchTermName, setSearchTermName] = useState('');
  const [searchTermId, setSearchTermId] = useState('');

  const handleSearch = (event) => {
    event.preventDefault();
    onSearch(searchTermName, searchTermId);
  };

  const handleChangeName = (event) => {
    setSearchTermName(event.target.value);
  };

  const handleChangeId = (event) => {
    setSearchTermId(event.target.value);
  };

  return (
    <form onSubmit={handleSearch} className="search">
      <input
        type="text"
        placeholder="Buscar por nombre..."
        value={searchTermName}
        onChange={handleChangeName}
      />
      <input
        type="text"
        placeholder="Buscar por ID..."
        value={searchTermId}
        onChange={handleChangeId}
      />
      <button type="submit">Buscar</button>
    </form>
  );
};

export default SearchBar;
