package by.htp.jd2.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.impl.SQLCarDao;
import by.htp.jd2.entity.Car;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.ServiceException;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getAllCars() throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list = new ArrayList<>();
        try {
            list = carDao.getAllCars();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public boolean addNewCar(Car car) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        try {
            return carDao.addNewCar(car);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delCar(int id) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        try {
            return carDao.delCar(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateCar(int id) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        try {
            return carDao.activateCar(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getAllAvailableCars(String startDate, String endDate) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list = new ArrayList<>();
        try {
            list = carDao.getAllAvailableCars(startDate, endDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public Car getCarById(int id) throws ServiceException {
        Car car;
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        try {
            car = carDao.getCarById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return car;

    }

    @Override
    public List<Car> getTransmissionCar(String transmission) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list = new ArrayList<>();

        try {
            list = carDao.getTransmissionCar(transmission);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public List<Car> getFuelCars(String fuel) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list = new ArrayList<>();

        try {
            list = carDao.getFuelCars(fuel);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

}
