import React from "react";

export const Button = ({ children, onClick, className = "", ...props }) => {
  return (
    <button
      onClick={onClick}
      className={`bg-blue-900 text-white px-4 py-2 rounded hover:bg-blue-800 hover:shadow-md transform hover:scale-105 transition duration-200 ${className}`}
      {...props}
    >
      {children}
    </button>
  );
};
