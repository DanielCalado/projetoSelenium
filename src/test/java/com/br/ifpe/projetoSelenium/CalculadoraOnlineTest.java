package com.br.ifpe.projetoSelenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.sql.Time;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Node;


public class CalculadoraOnlineTest {
	static WebDriver driver;
	private static Document doc;
	private static NodeList listaProcedimentos;
	private static NodeList listaCasos;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\danie\\Downloads\\chromedriver-win64\\chromedriver.exe");
				driver = new ChromeDriver();
				
				try {
					File inputFile = new File("src/test/resources/casos_de_test.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					doc = dBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					
					listaProcedimentos = doc.getElementsByTagName("procedimento");
				}catch(Exception e) {
					System.out.println("Erro na leitura do documento");
				}
				
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
	void deveCalcularExponencialTest() throws InterruptedException{
		Element procedimento = (Element) listaProcedimentos.item(0);
		listaCasos = procedimento.getElementsByTagName("caso");
		
		for(int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.findElement(By.id("b31")).click();
			driver.findElement(By.id("cx31_0")).sendKeys(caso.getElementsByTagName("numero01").item(0).getTextContent());
			Thread.sleep(500);
			driver.findElement(By.id("cx31_1")).sendKeys(caso.getElementsByTagName("numero02").item(0).getTextContent());
			driver.findElement(By.xpath("//*[@id=\"dpb31\"]/button")).click();
			
			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(),driver.findElement(By.cssSelector("#TIExp")).getAttribute("value"));
			//aguarda um tempo antes de executar proximo teste, garantindo que não tera erros
			Thread.sleep(200);

		}
		
	}
	
	@Test
	void deveFazerOperacoesDeSomaTest() throws InterruptedException{
		Element procedimento = (Element) listaProcedimentos.item(1);
		listaCasos = procedimento.getElementsByTagName("caso");
		
		for(int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero01").item(0).getTextContent());
			driver.findElement(By.id("b4")).click();
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero02").item(0).getTextContent());
			driver.findElement(By.id("b27")).click();
			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(),driver.findElement(By.cssSelector("#TIExp")).getAttribute("value"));
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[2]")).click();
			//aguarda um tempo antes de executar proximo teste, garantindo que não tera erros
			Thread.sleep(200);
		
		}
		
	}
	
	@Test
	void deveFazerOperacoesDeDivisaoComMultiplicacaoTest() throws InterruptedException {
		Element procedimento = (Element) listaProcedimentos.item(2);
		listaCasos = procedimento.getElementsByTagName("caso");
		
		for(int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero01").item(0).getTextContent());
			driver.findElement(By.id("b28")).click();
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero02").item(0).getTextContent());
			driver.findElement(By.id("b20")).click();
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero03").item(0).getTextContent());
			driver.findElement(By.id("b27")).click();
			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(),driver.findElement(By.cssSelector("#TIExp")).getAttribute("value"));
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[2]")).click();
			//aguarda um tempo antes de executar proximo teste, garantindo que não tera erros
			Thread.sleep(200);
		}
	
	}
	
	@Test
	void deveCalcularCircuferencviaUsandoPiTest() throws InterruptedException {
		Element procedimento = (Element) listaProcedimentos.item(3);
		listaCasos = procedimento.getElementsByTagName("caso");
		
		for(int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.findElement(By.id("b18")).click();
			driver.findElement(By.id("b20")).click();
			driver.findElement(By.id("b56")).click();
			driver.findElement(By.id("b20")).click();
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("diametro").item(0).getTextContent());
			driver.findElement(By.id("b28")).click();
			driver.findElement(By.id("b18")).click();
			driver.findElement(By.id("b27")).click();
			assertEquals(caso.getElementsByTagName("resultado").item(0).getTextContent(),driver.findElement(By.cssSelector("#TIExp")).getAttribute("value"));
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[2]")).click();
			//aguarda um tempo antes de executar proximo teste, garantindo que não tera erros
			Thread.sleep(200);
		}
	}
	
	@Test
	void deveSalvarUmNumeroNaMemoriaSomarESubtrairComOutroNumero() throws InterruptedException {
		Element procedimento = (Element) listaProcedimentos.item(4);
		listaCasos = procedimento.getElementsByTagName("caso");
		
		for(int indcaso = 0; indcaso < listaCasos.getLength(); indcaso++) {
			Element caso = (Element) listaCasos.item(indcaso);
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero01").item(0).getTextContent());
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[4]")).click();
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[2]")).click();
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero02").item(0).getTextContent());
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[5]")).click();
			assertEquals(caso.getElementsByTagName("resultadoSomar").item(0).getTextContent(),driver.findElement(By.cssSelector("#TIExp")).getAttribute("value"));
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[2]")).click();
			driver.findElement(By.id("TIExp")).sendKeys(caso.getElementsByTagName("numero02").item(0).getTextContent());
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[6]")).click();
			assertEquals(caso.getElementsByTagName("resultadoSubtrair").item(0).getTextContent(),driver.findElement(By.cssSelector("#TIExp")).getAttribute("value"));
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[2]")).click();
			driver.findElement(By.xpath("//*[@id=\"jp-right\"]/div[2]/div/main/article/div[7]/table/tbody/tr/td[3]")).click();
			//aguarda um tempo antes de executar proximo teste, garantindo que não tera erros
			Thread.sleep(200);
		}
	}

}
