package info.pobu.blb.entities;

import org.junit.Assert;
import org.junit.Test;

public class FamilyTest {

    @Test
    public void shouldBeEnumValuesNotNull() {
        for (Family family : Family.values()) {
            Assert.assertNotNull("literal should not be null", family.getLiteral());
          }
    }
}
