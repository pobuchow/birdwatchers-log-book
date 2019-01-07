package info.pobu.blb.entities;

import lombok.Getter;

public enum Species {

    COMMON_KINGFISHER("Common Kingfisher", Genus.ALCEDO, "Alcedo atthis", Family.ALCEDINIDAE),
    WRYNECK("Wryneck", Genus.JYNX, "Jynx torquilla", Family.PICIDAE),
    GREY_HEADED_WOODPECKER("Grey-headed woodpecker", Genus.PICUS, "Picus canus", Family.PICIDAE),
    EUROPEAN_GREEN_WOODPECKER("European green woodpecker", Genus.PICUS, "Picus viridis", Family.PICIDAE),
    BLACK_WOODPECKER("Black woodpecker", Genus.DRYOCOPUS, "Dryocopus martius", Family.PICIDAE),
    GREAT_SPOTTED_WOODPECKER("Great spotted woodpecker", Genus.DENDROCOPOS, "Dendrocopos major", Family.PICIDAE),
    SYRIAN_WOODPECKER("Syrian woodpecker", Genus.DENDROCOPOS, "Dendrocopos syriacus", Family.PICIDAE),
    WHITE_BACKED_WOODPECKER("White-backed woodpecker", Genus.DENDROCOPOS, "Dendrocopos leucotos", Family.PICIDAE),
    MIDDLE_SPOTTED_WOODPECKER("Middle spotted woodpecker", Genus.DENDROCOPOS, "Dendrocoptes medius", Family.PICIDAE),
    LESSER_SPOTTED_WOODPECKER("Lesser spotted woodpecker", Genus.DRYOBATES, "Dryobates minor", Family.PICIDAE),
    THREE_TOED_WOODPECKER("Three-toed woodpecker", Genus.PICOIDES, "Picoides tridactylus", Family.PICIDAE);

    @Getter
    private String name;

    @Getter
    private Genus genus;

    @Getter
    private String binomial;

    @Getter
    private Family family;

    private Species(String name, Genus genus, String binomial, Family family) {
        this.name = name;
        this.genus = genus;
        this.binomial = binomial;
        this.family = family;
    }
}