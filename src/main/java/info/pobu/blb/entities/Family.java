package info.pobu.blb.entities;

import lombok.Getter;

public enum Family implements LiteralEntity {
	
	ALCEDINIDAE("Alcedinidae");
	
	@Getter
	private String literal;
	
	private Family(String literal) {
		this.literal = literal;
	}
}