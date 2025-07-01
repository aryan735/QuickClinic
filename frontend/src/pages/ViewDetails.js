import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Button } from '../components/ui/button';
import { FaUserMd, FaIdCard, FaEnvelope, FaBirthdayCake } from 'react-icons/fa';
import { IoMdArrowRoundBack } from 'react-icons/io';
import axiosConfig from '../config/axiosConfig';
import { toast } from 'react-hot-toast';

const ViewDetails = () => {
  const navigate = useNavigate();
  const [userData, setUserData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let didCancel = false; // prevent state update after unmount

    const fetchUserDetails = async () => {
      try {
        setLoading(true);

        const res = await axiosConfig.get('/users/get-details');

        if (!didCancel) {
          setUserData(res.data);
        }
      } catch (err) {
        console.error("Error fetching user details:", err);

        if (!didCancel) {
          if (err.response?.status === 401) {
            toast.error("Session expired. Please login again.");
            setTimeout(() => navigate("/login"), 1500);
          } else {
            toast.error("Failed to load user details");
          }
        }
      } finally {
        if (!didCancel) setLoading(false);
      }
    };

    fetchUserDetails();

    return () => {
      didCancel = true;
    };
  }, [navigate]); // Added navigate here to fix ESLint warning

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-100 to-indigo-100">
        <div className="text-xl font-semibold text-blue-600 animate-pulse">Loading your profile...</div>
      </div>
    );
  }

  if (!userData) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-red-100 to-pink-100">
        <div className="text-xl font-semibold text-red-600">User profile not found</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 p-6">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <motion.div
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          className="flex justify-between items-center mb-8"
        >
          <Button variant="outline" onClick={() => navigate('/dashboard')} className="gap-2">
            <IoMdArrowRoundBack /> Dashboard
          </Button>
          <h1 className="text-3xl font-bold bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
            My Profile
          </h1>
          <div className="w-10" /> {/* Spacer */}
        </motion.div>

        {/* Card */}
        <motion.div
          initial={{ opacity: 0, scale: 0.95 }}
          animate={{ opacity: 1, scale: 1 }}
          className="bg-white rounded-2xl shadow-xl p-8"
        >
          <div className="flex flex-col md:flex-row gap-8 items-center">
            <div className="bg-blue-100 p-6 rounded-full">
              <FaUserMd className="text-blue-600 text-6xl" />
            </div>

            <div className="space-y-4 flex-1">
              <div className="flex items-center gap-3">
                <FaIdCard className="text-blue-500 text-xl" />
                <div>
                  <p className="text-sm text-gray-500">Username</p>
                  <p className="font-semibold">{userData.username}</p>
                </div>
              </div>

              <div className="flex items-center gap-3">
                <FaEnvelope className="text-indigo-500 text-xl" />
                <div>
                  <p className="text-sm text-gray-500">Email</p>
                  <p className="font-semibold">{userData.email}</p>
                </div>
              </div>

              <div className="flex items-center gap-3">
                <FaBirthdayCake className="text-red-500 text-xl" />
                <div>
                  <p className="text-sm text-gray-500">Age</p>
                  <p className="font-semibold">{userData.age}</p>
                </div>
              </div>
            </div>
          </div>

          {/* Action Button */}
          <div className="flex justify-end mt-8">
            <Button onClick={() => navigate('/dashboard/update')} className="bg-blue-600 hover:bg-blue-700">
              Edit Profile
            </Button>
          </div>
        </motion.div>
      </div>
    </div>
  );
};

export default ViewDetails;
