package info.pobu.blb.entities;

import lombok.Getter;

public enum Genus implements LiteralEntity {
	
	ALCEDO ("Alcedo");
	
	@Getter
	private String literal;
	
	private Genus(String literal) {
		this.literal = literal;
	}
}
