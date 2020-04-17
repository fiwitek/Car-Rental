package Application.CarRental.controllers;

import Application.CarRental.exceptions.ObjectAlreadyExists;
import Application.CarRental.models.Rank;
import Application.CarRental.services.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @RequestMapping(method=RequestMethod.POST,value="/newrank")
    public void addNewRank(@RequestBody Rank newRank) {
        try{
            rankService.addNewObject(newRank);
        } catch (ObjectAlreadyExists exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rank already exists");
        }
        throw new ResponseStatusException(HttpStatus.OK, "Rank sucessfully added.");
    }
}
