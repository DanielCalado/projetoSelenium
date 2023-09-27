package com.br.ifpe.projetoSelenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalculadoraOnlineExponencial {
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
	void deveCalcularExponencialDeDoisNumerosPositivos(){
		//preciona o botão x^n
		driver.findElement(By.id("b31")).click();
		driver.findElement(By.id("cx31_0")).sendKeys("10");
		driver.findElement(By.id("cx31_1")).sendKeys("5");
		driver.findElement(By.xpath("//*[@id=\"dpb31\"]/button")).click();
		
		assertEquals("100000",driver.findElement(By.cssSelector("#TIExp")).getAttribute("value"));
	}
	
	

}
