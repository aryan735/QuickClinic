import React, { useState } from "react";
import axios from "axios";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("/public/login", { username, password });
      alert(`Login success. JWT: ${res.data}`);
      // localStorage.setItem("token", res.data);
    } catch (err) {
      console.error(err);
      alert("Login failed");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
      <div className="flex flex-col md:flex-row bg-white rounded-lg shadow-lg overflow-hidden w-full max-w-4xl">
        
        {/* Left side image */}
        <div className="hidden md:block md:w-1/2 relative">
          <img
            src="https://img.freepik.com/free-vector/access-control-system-abstract-concept_335657-3180.jpg?ga=GA1.1.650748767.1707059699&semt=ais_items_boosted&w=740"
            alt="Access control illustration"
            className="w-full h-full object-cover"
          />
          {/* Subtle overlay for visual polish */}
          <div className="absolute inset-0 bg-blue-900 opacity-10"></div>
        </div>

        {/* Right side form */}
        <div className="w-full md:w-1/2 p-8 flex flex-col justify-center">
          <h2 className="text-3xl font-extrabold text-blue-900 mb-4 text-center">Login to QuickClinic</h2>
          <div className="h-1 w-20 bg-blue-900 rounded mx-auto mb-6 animate-pulse"></div>
          <form onSubmit={handleSubmit} className="space-y-4">
            <input 
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Username"
              className="w-full border border-gray-300 rounded p-3 focus:outline-none focus:ring-2 focus:ring-blue-900"
            />
            <input 
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              type="password"
              placeholder="Password"
              className="w-full border border-gray-300 rounded p-3 focus:outline-none focus:ring-2 focus:ring-blue-900"
            />
            <button 
              type="submit"
              className="bg-blue-900 text-white px-4 py-2 rounded w-full hover:bg-blue-800 hover:shadow-lg transform hover:scale-105 transition"
            >
              Login
            </button>
          </form>
        </div>

      </div>
    </div>
  );
}

export default Login;
