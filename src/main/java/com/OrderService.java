package com;

public class OrderService {
    private OrderDAO orderDAO;

    public Order save(Order order){
        return orderDAO.save(order);
    }
}
