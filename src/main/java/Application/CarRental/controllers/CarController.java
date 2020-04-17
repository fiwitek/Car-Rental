package Application.CarRental.controllers;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.exceptions.ObjectNotFound;
import Application.CarRental.models.Car;
import Application.CarRental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(method= RequestMethod.POST,value="/newcar")
    public void addNewCar(@RequestBody Car newCar)  {
        try{
            carService.addNewObject(newCar);
        } catch (ObjectAlreadyExists exception1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with given license already exists.");
        } catch (ObjectNotFound exception2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Model, brand or rank does not exists.");
        }
        throw new ResponseStatusException(HttpStatus.OK, "Car sucessfully added.");

    }
}
