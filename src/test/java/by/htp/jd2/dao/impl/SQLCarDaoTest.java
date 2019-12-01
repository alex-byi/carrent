package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.TransmissionType;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLCarDaoTest {

    @Test
    public void delCar() throws DaoException {
        SQLCarDao sqlCarDao = new SQLCarDao();
        boolean expected = sqlCarDao.delCar(1);
        Assert.assertTrue(expected);
        activateCar();
    }

    @Test
    public void activateCar() throws DaoException {
        SQLCarDao sqlCarDao = new SQLCarDao();
        boolean expected = sqlCarDao.activateCar(1);
        Assert.assertTrue(expected);
    }

    @Test
    public void getAllCars() throws DaoException {
        SQLCarDao sqlCarDao = new SQLCarDao();
        List<Car> cars = sqlCarDao.getAllCars();
        int expeted = cars.size();
        Assert.assertEquals(expeted, getCarCount());
    }

    @Test
    public void addNewCar() throws DaoException {
        Car car = new Car("test", 100, "test", "test", "test",
                TransmissionType.MANUAL, true);
        SQLCarDao sqlCarDao = new SQLCarDao();
        sqlCarDao.addNewCar(car);
        Car getCar = getCarByName(car.getName());
        car.setId(getCar.getId());
        Assert.assertEquals(car, getCar);
        delCarAfterAdd(getCar.getId());
    }

    @Test
    public void getTransmissionCar() throws DaoException {
        SQLCarDao sqlCarDao = new SQLCarDao();
        List<String> transmissionTypes = new ArrayList<>();
        transmissionTypes.add("MANUAL");
        transmissionTypes.add("AUTOMATIC");
        boolean expected = true;
        for (String transmissionType : transmissionTypes) {
            List<Car> transmissionCars = sqlCarDao.getTransmissionCar(transmissionType);
            for (Car car : transmissionCars) {
                if (!car.getTransmissionType().toString().equals(transmissionType)) {
                    expected = false;
                    break;
                }
                break;
            }
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void getFuelCars() throws DaoException {
        SQLCarDao sqlCarDao = new SQLCarDao();
        List<String> fuelTypes = new ArrayList<>();
        fuelTypes.add("дизель");
        fuelTypes.add("бензин");
        fuelTypes.add("электричество");
        boolean expected = true;
        for (String fuelType : fuelTypes) {
            List<Car> fuelCars = sqlCarDao.getFuelCars(fuelType);
            for (Car fuelCar : fuelCars) {
                if (!fuelCar.getFuel().equals(fuelType)) {
                    expected = false;
                    break;
                }
                break;
            }
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void getCarById() throws DaoException {
        Car actualCar = new Car("volvo xc70", 200, "дизель", "черный",
                "универсал", TransmissionType.AUTOMATIC, true);
        actualCar.setId(1);
        SQLCarDao sqlCarDao = new SQLCarDao();
        Car car = sqlCarDao.getCarById(1);
        Assert.assertEquals(actualCar, car);
    }

    private int getCarCount() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int pageCol = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM cars LIMIT 1;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pageCol = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
        return pageCol;
    }

    private Car getCarByName(String name) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        int id = 0;
        int price = 0;
        String fuel = null;
        String color = null;
        String body = null;
        TransmissionType transmissionType = null;
        boolean active = false;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cars WHERE name = ?;");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                price = rs.getInt(3);
                fuel = rs.getString(4);
                color = rs.getString(5);
                body = rs.getString(6);
                transmissionType = TransmissionType.valueOf(rs.getString(7).toUpperCase());
                active = rs.getBoolean(8);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
        return new Car(name, price, fuel, color, body, transmissionType, active, id);
    }

    private static void delCarAfterAdd(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.retrieve();
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM cars WHERE idcars = ?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.putback(connection);
        }
    }
}