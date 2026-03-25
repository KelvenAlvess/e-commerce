import api from './api';

const productService = {

  createProduct: async (productData) => {
    const response = await api.post('/products', productData);
    return response.data;
  },

  getProductById: async (id) => {
    const response = await api.get(`/products/${id}`);
    return response.data;
  },

  getAllProducts: async () => {
    const response = await api.get('/products');
    return response.data;
  },

  getProductsByCategory: async (categoryId) => {
    const response = await api.get(`/products/category/${categoryId}`);
    return response.data;
  },

  getProductsBySeller: async (sellerId) => {
    const response = await api.get(`/products/seller/${sellerId}`);
    return response.data;
  },

  updateProduct: async (id, productData) => {
    const response = await api.put(`/products/${id}`, productData);
    return response.data;
  },

  updateStock: async (id, quantity) => {
    const response = await api.patch(`/products/${id}/stock`, { quantity });
    return response.data;
  },

  deleteProduct: async (id) => {
    await api.delete(`/products/${id}`);
  }
};

export default productService;
