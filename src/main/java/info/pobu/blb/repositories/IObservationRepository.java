package info.pobu.blb.repositories;

import org.springframework.data.repository.CrudRepository;

import info.pobu.blb.entities.Observation;

public interface IObservationRepository extends CrudRepository<Observation, Integer> {

}
