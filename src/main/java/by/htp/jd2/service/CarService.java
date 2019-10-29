package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.entity.Car;

/**
 * Car service
 *
 * @author alexey
 */
public interface CarService {

    /**
     * Service of Lists all existing cars
     *
     * @return List of car objects
     */
    List<Car> getAllCars() throws ServiceException;

    /**
     * Service of Adds a new car to the database
     *
     * @param car Car object
     * @return true if car added successfully
     */
    boolean addNewCar(Car car) throws ServiceException;

    /**
     * Service of Changes the flag to "inactive" in the corresponding vehicle in the database
     *
     * @param id int car id
     * @return true if flag changed to false successfully
     */
    boolean delCar(int id) throws ServiceException;

    /**
     * Service of Changes the flag to "active" in the corresponding vehicle in the database
     *
     * @param id int car id
     * @return true if flag changed to true successfully
     */
    boolean activateCar(int id) throws ServiceException;

    /**
     * Service of Returns all available cars based on dates
     *
     * @param startDate String of start date in format YYYY-MM-DD
     * @param endDate   String of end date in format YYYY-MM-DD
     * @return List of car objects
     */
    List<Car> getAllAvailableCars(String startDate, String endDate) throws ServiceException;

    /**
     * Service of Returns a Car object based on its ID in the database
     *
     * @param id int car id
     * @return Car object
     */
    Car getCarById(int id) throws ServiceException;

    /**
     * Service of Returns a list of all found vehicles with a specific transmission
     *
     * @param transmission String transmission type
     * @return List of car objects
     */
    List<Car> getTransmissionCar(String transmission) throws ServiceException;

    /**
     * Service of Returns a list of all found vehicles with a specific fuel
     *
     * @param fuel String fuel type
     * @return List of car objects
     */
    List<Car> getFuelCars(String fuel) throws ServiceException;
}
