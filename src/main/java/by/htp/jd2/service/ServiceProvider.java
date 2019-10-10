package by.htp.jd2.service;

import by.htp.jd2.service.impl.CarServiceImpl;
import by.htp.jd2.service.impl.CrashServiceImpl;
import by.htp.jd2.service.impl.OrderServiceImpl;
import by.htp.jd2.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();


    private UserService userService = new UserServiceImpl();
    private CarService carService = new CarServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private CrashService crashService = new CrashServiceImpl();


    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public CrashService getCrashService() {
        return crashService;
    }
}
