package Application.CarRental.validators;

import Application.CarRental.models.Brand;
import Application.CarRental.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandValidator {

    @Autowired
    BrandRepository brandRepository;

    public boolean checkIfObjectExists(Brand brandToValidate) {

        if(brandRepository.findByName(brandToValidate.getName()).isPresent())
            return false;
        else
            return true;
    }

}
