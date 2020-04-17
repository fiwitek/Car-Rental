package Application.CarRental.validators;

import Application.CarRental.models.Model;
import Application.CarRental.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelValidator {

    @Autowired
    ModelRepository modelRepository;

    public boolean checkIfObjectExists(Model modelToValidate) {

        if(modelRepository.findByName(modelToValidate.getName()).isPresent())
            return false;
        else
            return true;
    }

}
