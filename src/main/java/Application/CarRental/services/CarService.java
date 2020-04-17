package Application.CarRental.services;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.models.Car;
import Application.CarRental.models.Model;
import Application.CarRental.models.Rank;
import Application.CarRental.repositories.BrandRepository;
import Application.CarRental.repositories.CarRepository;
import Application.CarRental.repositories.RankRepository;
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

    @Autowired
    CarService(CarRepository carRepository, RankValidator rankValidator, ModelValidator modelValidator,
               BrandRepository brandRepository, RankRepository rankRepository, CarValidator carValidator){

        this.brandRepository=brandRepository;
        this.carRepository=carRepository;
        this.rankRepository=rankRepository;
        this.modelValidator=modelValidator;
        this.rankValidator=rankValidator;
        this.carValidator = carValidator;
    }

    public void addNewObject(Car newCar) throws ObjectAlreadyExists {

        Model newModel = new Model(newCar.getModel().getName(), newCar.getModel().getBrand());
        Rank newRank = new Rank(newCar.getRank().getName(),newCar.getRank().getDeposit(),newCar.getRank().getPrice());

        //JEŻELI NIE MA TAKIEGO AUTA:
        if(carValidator.checkIfObjectExists(newCar)) {
            //JEZELI JEST TAKI MODEL:
            if(!modelValidator.checkIfObjectExists(newCar.getModel())){
                newModel = new Model(newCar.getModel().getName(),brandRepository.findByName(newCar.getModel().getBrand().getName()).get());
            }
            //JEZELI JEST TAKIE RANK:
            if(!rankValidator.checkIfObjectExists(newCar.getRank())){
                newRank = new Rank(rankRepository.findByName(newCar.getRank().getName()).get().getName(),
                        rankRepository.findByName(newCar.getRank().getName()).get().getDeposit(),
                        rankRepository.findByName(newCar.getRank().getName()).get().getPrice());
            }
            newCar = new Car(newCar.getLicense(), newCar.getYear(), newModel, newRank);
        }
        else
            throw new ObjectAlreadyExists();
        carRepository.save(newCar);
    }
}