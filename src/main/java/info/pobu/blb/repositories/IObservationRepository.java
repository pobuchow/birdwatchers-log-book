package info.pobu.blb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.pobu.blb.entities.Observation;

public interface IObservationRepository extends JpaRepository<Observation, Integer> {

}
