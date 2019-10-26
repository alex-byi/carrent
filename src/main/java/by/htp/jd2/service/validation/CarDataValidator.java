package by.htp.jd2.service.validation;

import by.htp.jd2.entity.Car;

import java.util.List;

public class CarDataValidator {

    private static final CarDataValidator instance = new CarDataValidator();

    private CarDataValidator() {
    }

    public boolean checkCarsList(List<Car> list) {
        Car trueCar = new Car();
        for (Car car : list) {
            if (car.getClass() != trueCar.getClass()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCarInfo(Car car) {
        return car == null || car.getName().isEmpty() || car.getFuel().isEmpty() || car.getTransmissionType() == null ||
                car.getBody().isEmpty() || car.getColor().isEmpty();
    }

    public boolean checkTransmissionCarList(List<Car> list, String transmission) {

        boolean result = true;
        for (Car car : list) {
            if (!car.getTransmissionType().toString().equals(transmission)) {
                result = false;
            }
        }
        return result;
    }

    public boolean checkFuelCarList(List<Car> list, String fuel) {

        boolean result = true;
        for (Car car : list) {
            if (!car.getFuel().equals(fuel)) {
                result = false;
                break;
            }
        }
        return result;
    }


    public static CarDataValidator getInstance() {
        return instance;
    }

}
