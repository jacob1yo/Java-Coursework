package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Order;

/**
 * 
 * @author Mohammed Zaman
 * 
 *
 */
class InvalidConfigurationTest {

	private Order o;

	@BeforeEach
	void setUp() throws Exception {
		o = new Order();
		
	}

	/**
	 * To see how the system deals with invalid configurations from an invalid .sim file.
	 */
	@Test
	void simTest() {
		Order.fillLists();
	
	}

}
