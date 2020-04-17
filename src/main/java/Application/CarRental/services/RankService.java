package Application.CarRental.services;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.models.Rank;
import Application.CarRental.repositories.RankRepository;
import Application.CarRental.validators.RankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankService {

    private RankRepository rankRepository;
    private RankValidator rankValidator;

    @Autowired
    RankService(RankRepository rankRepository, RankValidator rankValidator) {
        this.rankRepository = rankRepository;
        this.rankValidator = rankValidator;
    }

    public void addNewObject(Rank newRank) throws ObjectAlreadyExists {

        if(rankValidator.checkIfObjectExists(newRank))
            rankRepository.save(newRank);
        else
            throw new ObjectAlreadyExists();
    }
}
