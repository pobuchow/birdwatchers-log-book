package info.pobu.blb.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;

public enum Species {
	
	COMMON_KINGFISHER("Common Kingfisher", Genus.ALCEDO, "Alcedo atthis", Family.ALCEDINIDAE);
	
	@Getter
	private String name;
	
	
	@Enumerated(EnumType.ORDINAL)
	@Getter
	private Genus genus;
	
	@Getter
	private String binomial;
	
	@Enumerated(EnumType.ORDINAL)
	@Getter
	private Family family;
	
	private Species(String name, Genus genus, String binomial, Family family) {
		this.name = name;
		this.genus = genus;
		this.binomial = binomial;
		this.family = family;
	}
}