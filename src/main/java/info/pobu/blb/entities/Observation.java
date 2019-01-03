package info.pobu.blb.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Observation {

	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@ElementCollection(targetClass=Species.class)
	@Enumerated(EnumType.STRING)
	private List<Species> species;
	
	@NotNull
	private String location;
	
	@NotNull
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="user_id")//, nullable=false) TODO observation controller should handle users
	private User user;
	
	public Observation(List<Species> species, String location, LocalDate date) {
		super();
		this.species = species;
		this.location = location;
		this.date = date;
	}

	public Observation() {
		
	}
}