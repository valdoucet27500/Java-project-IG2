package barPackage.UnitTest;

import barPackage.exceptions.NumberInputValueException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.Consumable;
import barPackage.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.*;

public class ContentTest {
    private Content content;
    private Consumable consumable;

    @BeforeEach
    public void setUp() throws StringInputSizeException, NumberInputValueException {
        consumable = new Consumable("Pomme", false, "Une pomme rouge", "kg", 70.0, "Fruit");
        content = new Content("Pomme", 1.0);
    }

    @Test
    public void testGetContentConsumableName() {
        assertNotNull(content.getConsumableName());
        assertEquals("Pomme", content.getConsumableName());
    }

    @Test
    public void testContentQuantity() throws NumberInputValueException {
        assertNotNull(content.getQuantity());
        assertEquals(1.0, content.getQuantity());
        content.addQuantity(2.0);
        assertEquals(3.0, content.getQuantity());
        content.setQuantity(0.0);
        assertEquals(0.0, content.getQuantity());
    }

    @Test
    public void testGetContentExpirationDate() {
        assertNull(content.getExpirationDate());
        content.setExpirationDate(LocalDate.now());
        assertNotNull(content.getExpirationDate());
        assertEquals(LocalDate.now(), content.getExpirationDate());
    }
}
