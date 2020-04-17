package Application.CarRental.controllers;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.models.Brand;
import Application.CarRental.services.BrandService;
import Application.CarRental.validators.BrandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(method=RequestMethod.POST,value="/newbrand")
    public void addNewBrand(@RequestBody Brand newBrand) {

        try{
            brandService.addNewObject(newBrand);
        } catch (ObjectAlreadyExists exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand already exists");
        }
        throw new ResponseStatusException(HttpStatus.OK, "Brand sucessfully added.");
    }
}
