import React from "react";
import { Link, useLocation } from "react-router-dom";

export default function Header() {
  const location = useLocation();
  const isLogin = location.pathname === "/login";
  const isSignup = location.pathname === "/signup";

  return (
    <header className="flex justify-between items-center p-4 bg-white shadow">
      <div className="flex items-center">
        <img
          src="https://cdn-icons-png.flaticon.com/512/2795/2795280.png"
          alt="QuickClinic Logo"
          className="w-10 h-10 mr-3"
        />
        <span className="text-3xl font-extrabold text-blue-950">QuickClinic</span>
      </div>

      <nav className="space-x-6 hidden md:flex">
        <Link to="/" className="text-blue-900 font-semibold hover:text-blue-700 transition">Home</Link>
        <Link to="/about" className="text-blue-900 font-semibold hover:text-blue-700 transition">About</Link>
        <Link to="/contact" className="text-blue-900 font-semibold hover:text-blue-700 transition">Contact</Link>
      </nav>

      <div className="space-x-2">
        <Link to="/login">
          <button className={`px-4 py-1.5 rounded-lg transition duration-300 transform ${
            isLogin 
              ? "bg-blue-900 text-white shadow-md scale-105" 
              : "border-2 border-blue-800 text-blue-700 hover:bg-blue-50 hover:shadow"
          }`}>
            Login
          </button>
        </Link>
        <Link to="/signup">
          <button className={`px-5 py-1.5 rounded-lg transition duration-300 transform ${
            isSignup 
              ? "bg-blue-900 text-white shadow-md scale-105" 
              : "border-2 border-blue-800 text-blue-700 hover:bg-blue-50 hover:shadow"
          }`}>
            Sign Up
          </button>
        </Link>
      </div>
    </header>
  );
}
