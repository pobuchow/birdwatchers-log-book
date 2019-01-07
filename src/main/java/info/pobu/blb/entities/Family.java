package info.pobu.blb.entities;

import lombok.Getter;

public enum Family implements LiteralEntity {

    ALCEDINIDAE("Alcedinidae"), PICIDAE("PICIDAE");

    @Getter
    private String literal;

    private Family(String literal) {
        this.literal = literal;
    }
}