package Application.CarRental.services;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.models.Brand;
import Application.CarRental.repositories.BrandRepository;
import Application.CarRental.validators.BrandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandValidator brandValidator;


    public void addNewObject(Brand newBrand) throws ObjectAlreadyExists {

        if(brandValidator.checkIfObjectExists(newBrand))
            brandRepository.save(newBrand);
        else
            throw new ObjectAlreadyExists();
    }
}
