import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import hospitalImg from "../assets/hospital-dashboard.jpg";
import { Button } from "../components/ui/button";
import { motion } from "framer-motion";
import {
  FaUserEdit,
  FaUserSlash,
  FaHospital,
  FaHeartbeat,
  FaClinicMedical,
  FaIdBadge,
} from "react-icons/fa";
import axiosConfig from "../config/axiosConfig";
import { toast } from "react-hot-toast";


function DashboardHome() {
  const navigate = useNavigate();
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await axiosConfig.get("/users/get-details");
        setUserData(res.data);
      } catch (err) {
        toast.error("Failed to fetch user info");
      }
    };
    fetchUser();
  }, []);

  const handleNavigate = (path) => {
    navigate(path);
  };

  const cardVariants = {
    offscreen: { y: 50, opacity: 0 },
    onscreen: {
      y: 0,
      opacity: 1,
      transition: { type: "spring", bounce: 0.4, duration: 0.8 },
    },
  };

  return (
    
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 flex items-center justify-center p-4 md:p-8">
      <div className="w-full max-w-6xl">
        {/* Header */}
        <motion.div
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
          className="flex items-center justify-center mb-8"
        >
          <FaHospital className="text-blue-600 text-4xl mr-3" />
          <h1 className="text-3xl md:text-4xl font-bold bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
            QuickClinic - User Dashboard
          </h1>
        </motion.div>

        {/* Main Card */}
        <motion.div
          initial="offscreen"
          whileInView="onscreen"
          viewport={{ once: true, amount: 0.2 }}
          className="bg-white rounded-3xl shadow-2xl overflow-hidden"
        >
          <div className="flex flex-col lg:flex-row">
            {/* Image */}
            <motion.div variants={cardVariants} className="w-full lg:w-2/5 relative">
              <img
                src={hospitalImg}
                alt="Dashboard"
                className="w-full h-full object-cover transform hover:scale-105 transition duration-700"
              />
              <div className="absolute inset-0 bg-gradient-to-t from-blue-900/70 to-blue-600/30 flex items-end p-6">
                <div>
                  <h2 className="text-white text-2xl font-bold mb-2">User Panel</h2>
                  <p className="text-blue-100">
                    Access and manage your personal health records
                  </p>
                </div>
              </div>
            </motion.div>

            {/* Text and Actions */}
            <motion.div variants={cardVariants} className="w-full lg:w-3/5 p-8 md:p-10">
              <div className="mb-8">
                <h2 className="text-2xl md:text-3xl font-bold text-gray-800 mb-2">
                  Welcome back,{" "}
                  <span className="text-blue-600">
                    {userData ? userData.username : "Loading..."}
                  </span>
                </h2>
                <p className="text-gray-600">
                  Your account is active and ready. You can view, update, or delete your information below.
                </p>
              </div>

            <div className="space-y-6 font-cool">
  <motion.div
    whileHover={{ scale: 1.03 }}
    className="bg-white shadow-lg rounded-xl overflow-hidden transition-all duration-300"
  >
    <Button
      className="w-full py-6 px-4 text-lg font-semibold bg-gradient-to-r from-blue-600 to-blue-500 hover:from-blue-700 hover:to-blue-600 text-white rounded-xl flex items-center justify-center gap-3"
      onClick={() => handleNavigate("/dashboard/view")}
    >
      <FaIdBadge size={22} />
      View My Profile
    </Button>
    <p className="text-sm text-gray-500 text-center py-2">See your saved info and account details</p>
  </motion.div>

  <motion.div
    whileHover={{ scale: 1.03 }}
    className="bg-white shadow-lg rounded-xl overflow-hidden transition-all duration-300"
  >
    <Button
      className="w-full py-6 px-4 text-lg font-semibold bg-gradient-to-r from-indigo-600 to-indigo-500 hover:from-indigo-700 hover:to-indigo-600 text-white rounded-xl flex items-center justify-center gap-3"
      onClick={() => handleNavigate("/dashboard/update")}
    >
      <FaUserEdit size={22} />
      Update My Info
    </Button>
    <p className="text-sm text-gray-500 text-center py-2">Keep your profile up to date</p>
  </motion.div>

  <motion.div
    whileHover={{ scale: 1.03 }}
    className="bg-white shadow-lg rounded-xl overflow-hidden transition-all duration-300"
  >
    <Button
      variant="destructive"
      className="w-full py-6 px-4 text-lg font-semibold bg-gradient-to-r from-red-600 to-red-500 hover:from-red-700 hover:to-red-600 text-white rounded-xl flex items-center justify-center gap-3"
      onClick={() => handleNavigate("/dashboard/delete")}
    >
      <FaUserSlash size={22} />
      Delete My Account
    </Button>
    <p className="text-sm text-red-400 text-center py-2">This action is irreversible!</p>
  </motion.div>
</div>

            </motion.div>
          </div>
        </motion.div>

        {/* Footer Icons */}
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.3, duration: 0.5 }}
          className="flex justify-center mt-8 space-x-4"
        >
          <button className="text-gray-500 hover:text-blue-600 transition">
            <FaHeartbeat className="text-xl" />
          </button>
          <button className="text-gray-500 hover:text-blue-600 transition">
            <FaClinicMedical className="text-xl" />
          </button>
        </motion.div>
      </div>
    </div>
  );
}

export default DashboardHome;
