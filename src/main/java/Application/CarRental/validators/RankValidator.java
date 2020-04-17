package Application.CarRental.validators;

import Application.CarRental.models.Rank;
import Application.CarRental.repositories.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankValidator {

    @Autowired
    RankRepository rankRepository;

    public boolean checkIfObjectExists(Rank rankToValidate) {

        if(rankRepository.findByName(rankToValidate.getName()).isPresent())
            return false;
        else
            return true;
    }

}
