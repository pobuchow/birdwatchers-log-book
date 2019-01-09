package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Test;

public class GenusTest {

    @Test
    public void shouldBeEnumValuesNotNull() {
        for (Genus genus : Genus.values()) {
            Assert.assertNotNull("literal should not be null", genus.getLiteral());
          }
    }
}
