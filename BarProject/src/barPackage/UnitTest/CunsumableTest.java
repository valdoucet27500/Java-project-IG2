package barPackage.UnitTest;


import barPackage.exceptions.StringInputSizeException;
import barPackage.model.Consumable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class CunsumableTest {
    private Consumable consumable;

    @BeforeEach
    public void setUp() throws StringInputSizeException {
        consumable = new Consumable("Pomme", false, "Une pomme rouge", "kg", 70.0, "Fruit");
    }

    @Test
    public void testGetConsumableName() throws StringInputSizeException {
        assertNotNull(consumable.getName());
        assertEquals("Pomme", consumable.getName());
        consumable.setName("Pomme verte");
        assertEquals("Pomme verte", consumable.getName());
    }

    @Test
    public void testGetConsumableDescription() throws StringInputSizeException {
        assertNotNull(consumable.getDescription());
        assertEquals("Une pomme rouge", consumable.getDescription());
        consumable.setDescription("Une pomme verte");
        assertEquals("Une pomme verte", consumable.getDescription());
    }

    @Test
    public void testGetConsumableUnit() {
        assertNotNull(consumable.getUnit());
        assertEquals("kg", consumable.getUnit());
        consumable.setUnit("g");
        assertEquals("g", consumable.getUnit());
    }

    @Test
    public void testGetConsumableQuantity() {
        assertNotNull(consumable.getKcal());
        assertEquals(70.0, consumable.getKcal());
        consumable.setKcal(80.0);
        assertEquals(80.0, consumable.getKcal());
    }

    @Test
    public void testGetConsumableType() {
        assertNotNull(consumable.getType());
        assertEquals("Fruit", consumable.getType());
        consumable.setType("Légume");
        assertEquals("Légume", consumable.getType());
    }

    @Test
    public void testGetConsumableVegan() {
        assertNotNull(consumable.getIsVegan());
        assertEquals(false, consumable.getIsVegan().booleanValue());
        consumable.setIsVegan(true);
        assertEquals(true, consumable.getIsVegan().booleanValue());
    }
}
