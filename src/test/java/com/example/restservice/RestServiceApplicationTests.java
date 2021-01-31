package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestServiceApplicationTests {

	@Test
	void contextLoads() {
		TestProduit test1 = new TestProduit();
		test1.verifScore();
		test1.verifInfos();

		TestPanier test2 = new TestPanier();
		test2.verifPanier();

		TestAPIError test3 = new TestAPIError();
		test3.throwExeptionIdUndefined();

		TestProduitService test4 = new TestProduitService();
		test4.verifService();
		test4.verifServiceObjetSansValeur();
		test4.verifServiceObjetSansNutriscore();
	}
}
