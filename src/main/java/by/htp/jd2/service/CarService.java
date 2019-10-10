package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.entity.Car;

public interface CarService {
    List<Car> getAllCars() throws ServiceException;

    boolean addNewCar(Car car) throws ServiceException;

    boolean delCar(int id) throws ServiceException;

    boolean activateCar(int id) throws ServiceException;

    List<Car> getAllAvailableCars(String startDate, String endDate) throws ServiceException;

    Car getCarById(int id) throws ServiceException;

    List<Car> getTransmissionCar(String transmission) throws ServiceException;

    List<Car> getFuelCars(String fuel) throws ServiceException;
}
