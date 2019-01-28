package info.pobu.blb.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping(path = "/observation")
public class ObservationController {

    @Autowired
    private IObservationRepository observationRepository;

    @Autowired
    private UserController userController;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/add")
    public @ResponseBody Observation addNewObservation(@RequestParam int user_id, @RequestParam String species,
            @RequestParam String location, @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws UserNotFoundException, SpeciesNotFoundException{

        logger.info("adding new observation: " + species);
        
        Species speciesValue = null;
        Optional<User> user = userController.findById(user_id);
        try {
            speciesValue = Species.valueOf(species);
        }catch (IllegalArgumentException e) {
            throw new SpeciesNotFoundException("Species: " + species + " not found.");
        }

        final Observation observation = observationRepository.save(new Observation(
                user.orElseThrow(() -> new UserNotFoundException("User with id " + user_id + " not found")), speciesValue,
                location, date));
        
        logger.info("observation: " + observation.getSpecies().getLiteral() + " on: " + observation.getDate() + " added for user: " + user.get().getNick().getLiteral());
        return observation;
    }
    
    @GetMapping(path = "/all")
    public @ResponseBody List<Observation> getAllObservations(){
        logger.info("getting all observations");
        return observationRepository.findAll();
    }
    
    @GetMapping(path = "/findBy", params = "userId")
    public @ResponseBody List<Observation> findByUsersId(@RequestParam int user_id) throws UserNotFoundException{
        logger.info("getting all observations for user: " + user_id);
        User user = userController.findById(user_id).orElseThrow(() -> new UserNotFoundException("User with id " + user_id + " not found"));
        return observationRepository.findAll().stream().filter(u -> {
            final Integer id = user.getId();
            return id.equals(u.getUser().getId());
        })
                .collect(Collectors.toList());
    }
}