package info.pobu.blb.entities;

import lombok.Getter;

public enum Genus implements LiteralEntity {

    ALCEDO("Alcedo"), PICUS("Picus"), JYNX("Jynx"), DRYOCOPUS("Dryocopus"), DENDROCOPOS("Dendrocopos"),
    DRYOBATES("Dryobates"), PICOIDES("Picoides");

    @Getter
    private String literal;

    private Genus(String literal) {
        this.literal = literal;
    }
}
