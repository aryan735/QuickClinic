import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Button } from '../components/ui/button';
import { FaSave, FaTimes } from 'react-icons/fa';
import axiosConfig from '../config/axiosConfig';
import { toast } from 'react-hot-toast';
import successImg from '../assets/profile-update-success.png';

const UpdateProfile = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({ username: '', email: '', age: '' });
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    const fetchCurrentUser = async () => {
      try {
        const { data } = await axiosConfig.get('/users/get-details');
        setFormData({ username: data.username, email: data.email, age: data.age });
      } catch {
        toast.error('Failed to load profile data');
      }
    };
    fetchCurrentUser();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

const handleSubmit = async (e) => {
  e.preventDefault();
  setLoading(true);
  try {
    const res = await axiosConfig.put('/users/update-details', formData);
    toast.success(res.data);
    setSuccess(true);
  } catch (error) {
    if (error.response?.status === 401) {
      toast.error("Session expired. Please login again.");
      localStorage.removeItem("token");
      navigate("/login");
    } else {
      toast.error(error.response?.data?.message || 'Update failed');
    }
  } finally {
    setLoading(false);
  }
};


  if (success) {
    return (
      <div className="min-h-screen bg-gradient-to-br from-green-50 to-green-100 flex flex-col items-center justify-center p-8 text-center">
        <motion.img
          src={successImg}
          alt="Success"
          initial={{ scale: 0.8, opacity: 0 }}
          animate={{ scale: 1, opacity: 1 }}
          className="w-[350px] h-[350px] mb-6"

        />
        <motion.h1
          initial={{ opacity: 0, y: 10 }}
          animate={{ opacity: 1, y: 0 }}
          className="text-3xl font-bold text-green-700 mb-2"
        >
          Profile Updated!
        </motion.h1>
        <p className="text-green-600">Congrats..! Your data is updated!</p>
        <Button onClick={() => navigate('/dashboard')} className="mt-6 bg-green-600 hover:bg-green-700">
          Go to Dashboard
        </Button>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 p-6">
      <div className="max-w-2xl mx-auto">
        <motion.div
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          className="flex justify-between items-center mb-8"
        >
          <Button variant="outline" onClick={() => navigate('/dashboard/view')} className="gap-2">
            <FaTimes /> Cancel
          </Button>
          <h1 className="text-3xl font-bold bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
            Update Profile
          </h1>
          <div className="w-10" />
        </motion.div>

        <motion.div
          initial={{ opacity: 0, scale: 0.95 }}
          animate={{ opacity: 1, scale: 1 }}
          className="bg-white rounded-2xl shadow-xl p-8"
        >
          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Username</label>
              <input
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Age</label>
              <input
                type="number"
                name="age"
                value={formData.age}
                onChange={handleChange}
                min="1"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                required
              />
            </div>

            <div className="flex justify-end gap-3">
              <Button
                type="submit"
                className="bg-blue-600 hover:bg-blue-700 gap-2"
                disabled={loading}
              >
                <FaSave /> {loading ? 'Saving...' : 'Save Changes'}
              </Button>
            </div>
          </form>
        </motion.div>
      </div>
    </div>
  );
};

export default UpdateProfile;
