package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

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
	private Scanner scanner;

	@BeforeEach
	void setUp() throws Exception {
		o = new Order();
		Scanner scanner = new Scanner("D:\Users\Mohammed Zaman\Downloads\sampleConfigs\configs\oneOfEverything.sim");
		
		
	}

	/**
	 * To see how the system deals with invalid configurations from an invalid .sim file.
	 */
	@Test
	void simTest() {
		Order.fillLists();
	
	}

}
