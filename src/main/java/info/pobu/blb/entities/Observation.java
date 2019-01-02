package info.pobu.blb.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Observation {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.ORDINAL)
	private Species species;
	
	private String location;
	
	private LocalDate date;

	public Observation() {
		
	}
}