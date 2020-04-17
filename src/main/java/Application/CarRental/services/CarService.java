package Application.CarRental.services;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.exceptions.ObjectNotFound;
import Application.CarRental.models.Car;
import Application.CarRental.models.Model;
import Application.CarRental.models.Rank;
import Application.CarRental.repositories.BrandRepository;
import Application.CarRental.repositories.CarRepository;
import Application.CarRental.repositories.RankRepository;
import Application.CarRental.validators.BrandValidator;
import Application.CarRental.validators.CarValidator;
import Application.CarRental.validators.ModelValidator;
import Application.CarRental.validators.RankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository carRepository;
    private RankValidator rankValidator;
    private ModelValidator modelValidator;
    private BrandRepository brandRepository;
    private CarValidator carValidator;
    private RankRepository rankRepository;
    private BrandValidator brandValidator;

    @Autowired
    CarService(CarRepository carRepository,RankValidator rankValidator,ModelValidator modelValidator,
               BrandValidator brandValidator,RankRepository rankRepository,CarValidator carValidator,
               BrandRepository brandRepository) {

        this.brandRepository=brandRepository;
        this.carRepository=carRepository;
        this.rankRepository=rankRepository;
        this.modelValidator=modelValidator;
        this.rankValidator=rankValidator;
        this.carValidator=carValidator;
        this.brandValidator=brandValidator;
    }

    public void addNewObject(Car newCar) throws ObjectAlreadyExists, ObjectNotFound {

        if(carValidator.checkIfObjectExists(newCar)) {
            if( (!modelValidator.checkIfObjectExists(newCar.getModel()))
                    || (!rankValidator.checkIfObjectExists(newCar.getRank()))
                    || (!brandValidator.checkIfObjectExists(newCar.getModel().getBrand())) ) {
                throw new ObjectNotFound();
            }
            Model newModel = new Model(newCar.getModel().getName(),brandRepository.findByName(newCar.getModel().getBrand().getName()).get());
            Rank newRank = new Rank(rankRepository.findByName(newCar.getRank().getName()).get().getName(),
                    rankRepository.findByName(newCar.getRank().getName()).get().getDeposit(),
                    rankRepository.findByName(newCar.getRank().getName()).get().getPrice());
            carRepository.save(new Car(newCar.getLicense(),newCar.getYear(),newModel,newRank));
        }
        else
            throw new ObjectAlreadyExists();
    }
}