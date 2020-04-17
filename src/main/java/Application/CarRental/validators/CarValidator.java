package Application.CarRental.validators;

import Application.CarRental.models.Car;
import Application.CarRental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarValidator {

    @Autowired
    CarRepository carRepository;

    public boolean checkIfObjectExists(Car carToValidate) {

        if(carRepository.findByLicense(carToValidate.getLicense()).isPresent())
            return false;
        else
            return true;
    }
}
