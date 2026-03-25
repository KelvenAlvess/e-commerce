import api from './api';

const cartService = {
  addItem: async (cartItemData) => {
    const response = await api.post('/cart-items', cartItemData);
    return response.data;
  },

  updateQuantity: async (id, quantity) => {
    const response = await api.put(`/cart-items/${id}/quantity`, { quantity });
    return response.data;
  },

  removeItem: async (id) => {
    await api.delete(`/cart-items/${id}`);
  },

  getCartItems: async (userId) => {
    const response = await api.get(`/cart-items/user/${userId}`);
    return response.data;
  },

  clearCart: async (userId) => {
    await api.delete(`/cart-items/user/${userId}`);
  }
};

export default cartService;
