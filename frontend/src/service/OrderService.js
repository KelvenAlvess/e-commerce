import api from './api';

const orderService = {

    getOrderById: async (orderId) => {
        const response = await api.get(`/orders/${orderId}`);
        return response.data;
    },

    createOrder: async (userId) => {

        const payload = { userId: userId };
        const response = await api.post('/orders', payload);
        return response.data;
    },

    createOrderFromCart: async (userId) => {
        const payload = { userId: userId };
        const response = await api.post('/orders', payload);
        return response.data;
    }
};

export default orderService;