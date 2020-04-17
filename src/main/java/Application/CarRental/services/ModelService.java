package Application.CarRental.services;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.models.Model;
import Application.CarRental.repositories.BrandRepository;
import Application.CarRental.repositories.ModelRepository;
import Application.CarRental.validators.BrandValidator;
import Application.CarRental.validators.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private ModelRepository modelRepository;
    private BrandValidator brandValidator;
    private ModelValidator modelValidator;
    private BrandRepository brandRepository;

    @Autowired
    ModelService(ModelValidator modelValidator, ModelRepository modelRepository, BrandValidator brandValidator, BrandRepository brandRepository) {

        this.modelValidator=modelValidator;
        this.modelRepository=modelRepository;
        this.brandRepository=brandRepository;
        this.brandValidator=brandValidator;
    }

    public void addNewObject(Model newModel) throws ObjectAlreadyExists {

        if(modelValidator.checkIfObjectExists(newModel)) {
            if(!brandValidator.checkIfObjectExists(newModel.getBrand())) {
                Model model = new Model(newModel.getName(),brandRepository.findByName(newModel.getBrand().getName()).get());
                modelRepository.save(model);
            }
            else {
                modelRepository.save(newModel);
            }
        }
        else
            throw new ObjectAlreadyExists();
    }
}
