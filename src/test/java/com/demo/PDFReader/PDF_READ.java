package com.demo.PDFReader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PDF_READ {
	
WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./resource/chromedriver2");
		driver = new ChromeDriver();
	}
	
	/**
	 * To verify PDF content in the pdf document in browser
	 * @throws InterruptedException 
	 */
	
	@Test
	public void testVerifyPDFTextInBrowser() throws InterruptedException {
		
		driver.get("http://www.princexml.com/samples/");
		driver.findElement(By.xpath("(//p[@class='links']/a[text()='PDF'])[1]")).click();
		Thread.sleep(2000);
		Assert.assertTrue(verifyPDFContent(driver.getCurrentUrl(), "Prince Cascading"));
	}
	
	/**
	 * To verify PDF content in the downloaded PDF document  
	 * @throws InterruptedException 
	 */
	
	@Test
	public void testVerifyDowloadedPDFText() throws InterruptedException {
		Assert.assertTrue(verifydownloadedPDFContent("/Users/sarthakgarg/Downloads/Application Form.pdf", "Prince Cascading"));
	}

	/**
	 * To verify pdf extension in the URL 
	 * @throws InterruptedException 
	 */
	
	@Test
	public void testVerifyPDFInURL() throws InterruptedException {
		driver.get("http://www.princexml.com/samples/");
		driver.findElement(By.xpath("(//p[@class='links']/a[text()='PDF'])[1]")).click();
		String getURL = driver.getCurrentUrl();
		Thread.sleep(2000);
		Assert.assertTrue(getURL.contains(".pdf"));
	}
	
	/**
	 * read pdf text
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvalidPasswordException 
	 */
	@Test
	public void readPdf() throws InterruptedException, InvalidPasswordException, IOException {
		 //Loading an existing document
	      File file = new File("/Users/sarthakgarg/Downloads/my_doc1.pdf");
	      PDDocument document = PDDocument.load(file);

	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      //Retrieving text from PDF document
	      String text = pdfStripper.getText(document);
	      System.out.println(text);

	      //Closing the document
	      document.close();
	}
	
	
	

	public boolean verifydownloadedPDFContent(String filePath, String reqTextInPDF) {
		
		boolean flag = false;
		
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;

		try {
			File file = new File(filePath);
			PDFParser parser = new PDFParser(new RandomAccessFile(file,"r"));
			
			
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(3);
			
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed "+e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		
		System.out.println("+++++++++++++++++");
		System.out.println(parsedText);
		System.out.println("+++++++++++++++++");

		if(parsedText.contains(reqTextInPDF)) {
			flag=true;
		}
		
		return flag;
	}

	
	public boolean verifyPDFContent(String strURL, String reqTextInPDF) {
		
		boolean flag = false;
		
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;

		try {
			URL url = new URL(strURL);
			BufferedInputStream file = new BufferedInputStream(url.openStream());
			PDFParser parser = new PDFParser(new RandomAccessBuffer(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(3);
			
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed "+e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		
		System.out.println("+++++++++++++++++");
		System.out.println(parsedText);
		System.out.println("+++++++++++++++++");

		if(parsedText.contains(reqTextInPDF)) {
			flag=true;
		}
		
		return flag;
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
