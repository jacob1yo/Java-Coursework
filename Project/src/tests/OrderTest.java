package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Order;

class OrderTest {
	private Order o;

	@BeforeEach
	void setUp() throws Exception {
		o = new Order();
		Scanner scanner = new Scanner(o.getFile());
		
		
	}

	@Test
	void testScanner() {
		assertEquals(o, o.getFile());
	}

}
