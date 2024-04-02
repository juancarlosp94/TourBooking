import React, { useRef } from "react";

const FileUploadButton = ({ handleFileChange }) => {
  const fileInput = useRef();

  const handleClick = () => {
    fileInput.current.click();
  };

  return (
    <div>
      <button onClick={handleClick}>Browse</button>
      <input type="file" className="file" onChange={handleFileChange} ref={fileInput} style={{ display: 'none' }} />
    </div>
  );
};

export default FileUploadButton;
