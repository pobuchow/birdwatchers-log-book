package info.pobu.blb.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import info.pobu.blb.entities.Observation;
import info.pobu.blb.entities.Species;
import info.pobu.blb.repositories.IObservationRepository;

@Controller
@RequestMapping(path="/observation")
public class ObservationController {
	
	@Autowired
	private IObservationRepository observationRepository;
	
	@GetMapping(path="/add")
	public @ResponseBody Observation addNewObservation (@RequestParam List<Species> species, @RequestParam String location, @RequestParam LocalDate date){

		return observationRepository.save(new Observation(species, location, date));
	}
}