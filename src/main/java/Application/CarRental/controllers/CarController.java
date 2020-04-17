package Application.CarRental.controllers;

import Application.CarRental.exceptions.ObjectAlreadyExists;
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
        } catch (ObjectAlreadyExists exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already exists");
        }
        throw new ResponseStatusException(HttpStatus.OK, "Car sucessfully added.");

    }
}
