package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.impl.SQLCarDao;
import by.htp.jd2.entity.Car;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.validation.CarDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarServiceImpl implements CarService {

    private static final CarDataValidator validator = CarDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(CarServiceImpl.class.getName());

    @Override
    public List<Car> getAllCars() throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list;
        try {
            list = carDao.getAllCars();
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCarsList(list)) {
            throw new ServiceException("List<Car> no valid");
        }
        return list;
    }

    @Override
    public boolean addNewCar(Car car) throws ServiceException {
        if (validator.checkCarInfo(car)) {
            throw new ServiceException("Car data is no valid");
        }
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        try {
            return carDao.addNewCar(car);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delCar(int id) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        try {
            return carDao.delCar(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateCar(int id) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        try {
            return carDao.activateCar(id);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getAllAvailableCars(String startDate, String endDate) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list;
        try {
            list = carDao.getAllAvailableCars(startDate, endDate);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCarsList(list)) {
            throw new ServiceException("List<Car> no valid");
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
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkCarInfo(car)) {
            throw new ServiceException("Car data is no valid");
        }
        return car;
    }

    @Override
    public List<Car> getTransmissionCar(String transmission) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list;
        try {
            list = carDao.getTransmissionCar(transmission);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (!validator.checkTransmissionCarList(list, transmission)) {
            throw new ServiceException("List transmission car  no valid");
        }
        return list;
    }

    @Override
    public List<Car> getFuelCars(String fuel) throws ServiceException {
        SQLCarDao carDao = DaoProvider.getInstance().getCarDao();
        List<Car> list;
        try {
            list = carDao.getFuelCars(fuel);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (!validator.checkFuelCarList(list, fuel)) {
            throw new ServiceException("List fuel car no valid");
        }
        return list;
    }

}
