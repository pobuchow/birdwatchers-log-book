package info.pobu.blb.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import info.pobu.blb.controllers.exceptions.SpeciesNotFoundException;
import info.pobu.blb.controllers.exceptions.UserNotFoundException;
import info.pobu.blb.entities.Observation;
import info.pobu.blb.entities.Species;
import info.pobu.blb.entities.User;
import info.pobu.blb.repositories.IObservationRepository;
import info.pobu.blb.repositories.IUserRepository;

@Controller
@RequestMapping(path = "/observation")
public class ObservationController {

    @Autowired
    private IObservationRepository observationRepository;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping(path = "/add")
    public @ResponseBody Observation addNewObservation(@RequestParam int user_id, @RequestParam String species,
            @RequestParam String location, @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws UserNotFoundException, SpeciesNotFoundException{

        Species speciesValue = null;
        Optional<User> user = userRepository.findById(user_id);
        try {
            speciesValue = Species.valueOf(species);
        }catch (IllegalArgumentException e) {
            throw new SpeciesNotFoundException("Species: " + species + " not found.");
        }

        return observationRepository.save(new Observation(
                user.orElseThrow(() -> new UserNotFoundException("User with id " + user_id + " not found")), speciesValue,
                location, date));
    }
}