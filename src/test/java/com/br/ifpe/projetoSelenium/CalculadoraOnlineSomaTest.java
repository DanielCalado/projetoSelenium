package com.br.ifpe.projetoSelenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class CalculadoraOnlineSomaTest {
	static WebDriver driver;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\danie\\Downloads\\chromedriver-win64\\chromedriver.exe");

				driver = new ChromeDriver();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get("https://www.calculadoraonline.com.br/basica");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void deveSomarDoisNumerosPositivosTest() throws InterruptedException {
		//aperta o 5 duas vezes.
		driver.findElement(By.id("b10")).click();
		driver.findElement(By.id("b10")).click();
		//aperta o 9 uma vez.
		driver.findElement(By.id("b4")).click();
		//aperta a soma.
		driver.findElement(By.id("b3")).click();
		//aperta a igualdade.
		driver.findElement(By.id("b27")).click();
		
		assertEquals("64", driver.findElement(By.id("TIExp")).getAttribute("value"));
	}
	
	@Test
	void deveSomarDoisNumerosUpositivoEUmNegativoTest() {
		//aperta o 5 uma vez.
		driver.findElement(By.id("b10")).click();
		//aperta a soma.
		driver.findElement(By.id("b4")).click();
		//aperta o menos.
		driver.findElement(By.id("b12")).click();
		//aperta o 9 uma vez.
		driver.findElement(By.id("b3")).click();
		//aperta a igualdade.
		driver.findElement(By.id("b27")).click();
				
		assertEquals("-4", driver.findElement(By.id("TIExp")).getAttribute("value"));
		
	}
	
	@Test
	void deveSomarDoisNumerosNegativosTest() {
		//aperta o menos.
		driver.findElement(By.id("b12")).click();
		//aperta o 5 uma vez.
		driver.findElement(By.id("b10")).click();
		//aperta a soma.
		driver.findElement(By.id("b4")).click();
		//aperta o menos.
		driver.findElement(By.id("b12")).click();
		//aperta o 9 uma vez.
		driver.findElement(By.id("b3")).click();
		//aperta a igualdade.
		driver.findElement(By.id("b27")).click();
				
		assertEquals("-14", driver.findElement(By.id("TIExp")).getAttribute("value"));
		
	}

}
