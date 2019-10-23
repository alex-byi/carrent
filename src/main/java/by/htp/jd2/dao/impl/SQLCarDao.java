package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.CarDAO;
import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.TransmissionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author alexey
 */
public class SQLCarDao implements CarDAO {

    private static final String GET_ALL_CAR = "SELECT * FROM cars;";
    private static final String ADD_CAR = "INSERT INTO cars(name, price, fuel, color, body, transmission)"
            + " VALUES(?, ?, ?, ?, ?, ?)";
    private static final String DEL_CAR = "UPDATE cars SET cars.active = '0' WHERE cars.`idcars` = ?;";
    private static final String ACTIVATE_CAR = "UPDATE cars SET cars.active = '1' WHERE cars.`idcars` = ?;";
    private static final String GET_ALL_AVAILABLE_CARS_ID = "select * from cars where idcars NOT IN (select cars_idcars from orders where (enddate > ?) OR ((startdate > ?) AND (startdate < ?)));";
    private static final String GET_CAR_BY_ID = "SELECT * FROM cars WHERE idcars = ?;";
    private static final String GET_TRANSMISSION_CARS = "SELECT * FROM cars WHERE transmission = ?;";
    private static final String GET_FUEL_CARS = "SELECT * FROM cars WHERE fuel = ?;";

    /**
     * @return List of all cars from database
     */
    @Override
    public List<Car> getAllCars() throws DaoException {
        int id;
        String name;
        int price;
        String fuel;
        String color;
        String body;
        TransmissionType transmissionType;
        boolean active;

        List<Car> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_CAR)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getInt(3);
                fuel = rs.getString(4);
                color = rs.getString(5);
                body = rs.getString(6);
                transmissionType = TransmissionType.valueOf(rs.getString(7).toUpperCase());
                active = rs.getBoolean(8);
                list.add(new Car(name, price, fuel, color, body, transmissionType, active, id));
            }
            return list;
        } catch (SQLException e) {
            throw new DaoException("GET ALL CAR ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param car Car object
     * @return true if adding successfully or false if no
     */
    @Override
    public boolean addNewCar(Car car) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(ADD_CAR)) {
            ps.setString(1, car.getName());
            ps.setInt(2, car.getPrice());
            ps.setString(3, car.getFuel());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getBody());
            ps.setString(6, car.getTransmissionType().toString());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("ADD CAR ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param id int car id
     * @return true if deactivate successfully or false if no
     */
    @Override
    public boolean delCar(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(DEL_CAR)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("DEL CAR ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param startDate string date of start rent in format YYYY-MM-DD
     * @param endDate string date of end rent in format YYYY-MM-DD
     * @return List of all available cars from database
     */
    @Override
    public List<Car> getAllAvailableCars(String startDate, String endDate) throws DaoException {
        int id;
        String name;
        int price;
        String fuel;
        String color;
        String body;
        TransmissionType transmissionType;
        boolean active;
        List<Car> listAvailableCarsId = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AVAILABLE_CARS_ID)) {
            ps.setString(1, startDate);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getInt(3);
                fuel = rs.getString(4);
                color = rs.getString(5);
                body = rs.getString(6);
                transmissionType = TransmissionType.valueOf(rs.getString(7).toUpperCase());
                active = rs.getBoolean(8);
                listAvailableCarsId.add(new Car(name, price, fuel, color, body, transmissionType, active, id));
            }
            return listAvailableCarsId;
        } catch (SQLException e) {
            throw new DaoException("GET ALL AVAILABLE CARS IDs ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param id int car id
     * @return Car object
     */
    @Override
    public Car getCarById(int id) throws DaoException {
        String name = null;
        int price = 0;
        String fuel = null;
        String color = null;
        String body = null;
        TransmissionType transmissionType = null;
        boolean active = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(GET_CAR_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString(2);
                price = rs.getInt(3);
                fuel = rs.getString(4);
                color = rs.getString(5);
                body = rs.getString(6);
                transmissionType = TransmissionType.valueOf(rs.getString(7).toUpperCase());
                active = rs.getBoolean(8);
            }
            return new Car(name, price, fuel, color, body, transmissionType, active, id);
        } catch (SQLException e) {
            throw new DaoException("GET ALL CAR ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param id int car id
     * @return true if activate successfully or false if no
     */
    @Override
    public boolean activateCar(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement(ACTIVATE_CAR)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("ACTIVATE CAR ERROR", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param transmission string transmission type
     * @return List all car with transmission type
     */
    @Override
    public List<Car> getTransmissionCar(String transmission) throws DaoException {
        int id;
        String name;
        int price;
        String fuel;
        String color;
        String body;
        TransmissionType transmissionType;
        boolean active;
        List<Car> listAvailableCarsId = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();

        try (PreparedStatement ps = connection.prepareStatement(GET_TRANSMISSION_CARS)) {
            ps.setString(1, transmission);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getInt(3);
                fuel = rs.getString(4);
                color = rs.getString(5);
                body = rs.getString(6);
                transmissionType = TransmissionType.valueOf(rs.getString(7).toUpperCase());
                active = rs.getBoolean(8);
                listAvailableCarsId.add(new Car(name, price, fuel, color, body, transmissionType, active, id));
            }
            return listAvailableCarsId;
        } catch (SQLException e) {
            throw new DaoException("GET TRANSMISSION CARS IDs ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }

    /**
     *
     * @param fuelC string fuel type
     * @return List all car with fuel type
     */
    @Override
    public List<Car> getFuelCars(String fuelC) throws DaoException {
        int id;
        String name;
        int price;
        String fuel;
        String color;
        String body;
        TransmissionType transmissionType;
        boolean active;
        List<Car> listAvailableCarsId = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();

        try (PreparedStatement ps = connection.prepareStatement(GET_FUEL_CARS)) {
            ps.setString(1, fuelC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getInt(3);
                fuel = rs.getString(4);
                color = rs.getString(5);
                body = rs.getString(6);
                transmissionType = TransmissionType.valueOf(rs.getString(7).toUpperCase());
                active = rs.getBoolean(8);
                listAvailableCarsId.add(new Car(name, price, fuel, color, body, transmissionType, active, id));
            }
            return listAvailableCarsId;
        } catch (SQLException e) {
            throw new DaoException("GET FUEL CARS IDs ERROR!!!", e);
        } finally {
            pool.putback(connection);
        }
    }
}


