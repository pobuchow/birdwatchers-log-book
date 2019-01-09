package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Test;

public class SpeciesTest {

    @Test
    public void shouldBeEnumValuesNotNull() {
        for (Species species : Species.values()) {
            Assert.assertNotNull("binomial should not be null", species.getBinomial());
            Assert.assertNotNull("family should not be null", species.getFamily());
            Assert.assertNotNull("genus should not be null", species.getGenus());
            Assert.assertNotNull("name should not be null", species.getName());
          }
    }

}
