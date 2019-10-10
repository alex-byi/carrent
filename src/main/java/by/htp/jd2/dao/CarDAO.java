package by.htp.jd2.dao;

import by.htp.jd2.entity.Car;

import java.util.List;

public interface CarDAO {

    List<Car> getAllCars() throws DaoException;

    boolean addNewCar(Car car) throws DaoException;

    boolean delCar(int id) throws DaoException;

    boolean activateCar(int id) throws DaoException;

    List<Car> getAllAvailableCars(String startDate, String endDate) throws DaoException;

    Car getCarById(int id) throws DaoException;

    List<Car> getTransmissionCar(String transmission) throws DaoException;

    List<Car> getFuelCars(String fuel) throws DaoException;

}
