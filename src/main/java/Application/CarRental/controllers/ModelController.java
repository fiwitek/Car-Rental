package Application.CarRental.controllers;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.models.Model;
import Application.CarRental.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(method=RequestMethod.POST,value="/newmodel")
    public void addNewBrand(@RequestBody Model newModel) {

        try{
            modelService.addNewObject(newModel);
        } catch (ObjectAlreadyExists exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Model already exists");
        }
        throw new ResponseStatusException(HttpStatus.OK, "Model sucessfully added.");
    }
}
