import api from './api';

const userService = {

  login: async (email, password) => {
    const response = await api.post('/auth/login', { email, password });
    return response.data;
  },

  createUser: async (userData) => {
    const response = await api.post('/users', userData);
    return response.data;
  },

  getUserById: async (id) => {
    const response = await api.get(`/users/${id}`);
    return response.data;
  },

  getUserByEmail: async (email) => {
    const response = await api.get(`/users/email/${email}`);
    return response.data;
  },

  getAllUsers: async () => {
    const response = await api.get('/users');
    return response.data;
  },

  updateUser: async (id, userData) => {
    const response = await api.put(`/users/${id}`, userData);
    return response.data;
  },

  deleteUser: async (id) => {
    await api.delete(`/users/${id}`);
  },

  existsByEmail: async (email) => {
    const response = await api.get(`/users/exists/${email}`);
    return response.data;
  }
};

export default userService;
