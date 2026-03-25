import api from './api';

const categoryService = {

  createCategory: async (categoryData) => {
    const response = await api.post('/categories', categoryData);
    return response.data;
  },

  getCategoryById: async (id) => {
    const response = await api.get(`/categories/${id}`);
    return response.data;
  },

  getCategoryByName: async (name) => {
    const response = await api.get(`/categories/name/${name}`);
    return response.data;
  },

  getAllCategories: async () => {
    const response = await api.get('/categories');
    return response.data;
  },

  updateCategory: async (id, categoryData) => {
    const response = await api.put(`/categories/${id}`, categoryData);
    return response.data;
  },

  deleteCategory: async (id) => {
    await api.delete(`/categories/${id}`);
  },

  existsByName: async (name) => {
    const response = await api.get(`/categories/exists/${name}`);
    return response.data;
  }
};

export default categoryService;
