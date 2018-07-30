package com.demo.PDFReader;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.testng.annotations.Test;

public class PDF_CREATE {
	
	/**
	 * used to create a blank pdf with no pages 
	 * @throws IOException
	 */
	
	@Test
	public void create_Blank_Pfd() throws IOException {
		 //Creating PDF document object 
	      PDDocument document = new PDDocument();    
	       
	      //Saving the document
	      document.save("/Users/sarthakgarg/Downloads/my_doc.pdf");
	         
	      System.out.println("PDF created");  
	    
	      //Closing the document  
	      document.close();
	}
	
	/**
	 * used to add pages to a blank pdf
	 * @throws IOException
	 */
	@Test
	public void addPages_Pfd() throws IOException {
		  //Creating PDF document object 
	      PDDocument document = new PDDocument();

	      for (int i=0; i<10; i++) {
	         //Creating a blank page 
	         PDPage blankPage = new PDPage();

	         //Adding the blank page to the document
	         document.addPage( blankPage );
	      } 
	     
	      //Saving the document
	      document.save("/Users/sarthakgarg/Downloads/my_doc1.pdf");
	      System.out.println("PDF created");
	      
	      //Closing the document
	      document.close();
	}
	
	/**
	 * load an existing pdf and add a new page
	 * @throws IOException
	 */
	
	@Test
	public void loadExisting_Pfd() throws IOException {
		//Loading an existing document 
	      File file = new File("/Users/sarthakgarg/Downloads/Application Form.pdf"); 
	      PDDocument document = PDDocument.load(file); 
	        
	      System.out.println("PDF loaded"); 
	        
	      //Adding a blank page to the document 
	      document.addPage(new PDPage());  

	      //Saving the document 
	      document.save("/Users/sarthakgarg/Downloads/Application Form1.pdf");

	      //Closing the document  
	      document.close(); 
	}
	
	/**
	 * load an existing pdf and add a new page
	 * @throws IOException
	 */
	
	@Test
	public void deletePage_Pfd() throws IOException {
		
		//Loading an existing document
	      File file = new File("/Users/sarthakgarg/Downloads/my_doc1.pdf");
	      PDDocument document = PDDocument.load(file);
	       
	      //Listing the number of existing pages
	      int noOfPages= document.getNumberOfPages();
	      System.out.println("noOfPages " + noOfPages);
	       
	      //Removing the pages
	      document.removePage(2);
	      
	      System.out.println("page removed");

	      //Saving the document
	      document.save("/Users/sarthakgarg/Downloads/my_doc1.pdf");

	      //Closing the document
	      document.close();
	}
	
	/**
	 * setting pdf attributes to a new file
	 * @throws IOException
	 */
	
	@Test
	public void properties_Pfd() throws IOException {
		//Creating PDF document object
	      PDDocument document = new PDDocument();

	      //Creating a blank page
	      PDPage blankPage = new PDPage();
	       
	      //Adding the blank page to the document
	      document.addPage( blankPage );

	      //Creating the PDDocumentInformation object 
	      PDDocumentInformation pdd = document.getDocumentInformation();

	      //Setting the author of the document
	      pdd.setAuthor("Tutorialspoint");
	       
	      // Setting the title of the document
	      pdd.setTitle("Sample document"); 
	       
	      //Setting the creator of the document 
	      pdd.setCreator("PDF Examples"); 
	       
	      //Setting the subject of the document 
	      pdd.setSubject("Example document"); 
	       
	      //Setting the created date of the document 
	      Calendar date = new GregorianCalendar();
	      date.set(2018, 8, 2); 
	      pdd.setCreationDate(date);
	      //Setting the modified date of the document 
	      date.set(2018, 8, 3); 
	      pdd.setModificationDate(date); 
	       
	      //Setting keywords for the document 
	      pdd.setKeywords("sample, first example, my pdf"); 
	 
	      //Saving the document 
	      document.save("/Users/sarthakgarg/Downloads/doc_attributes.pdf");

	      System.out.println("Properties added successfully ");
	       
	      //Closing the document
	      document.close();

	}
		
	
	@Test
	public void textadd_Pfd() throws IOException {
		
		 //Loading an existing document
	      File file = new File("/Users/sarthakgarg/Downloads/my_doc1.pdf");
	      PDDocument document = PDDocument.load(file);
	       
	      //Retrieving the pages of the document 
	      PDPage page = document.getPage(0);
	      PDPageContentStream contentStream = new PDPageContentStream(document, page);
	      
	      //Begin the Content stream 
	      contentStream.beginText(); 
	       
	    //Setting the font to the Content stream
	      contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
	       
	      //Setting the leading
	      contentStream.setLeading(14.5f);

	      //Setting the position for the line
	      contentStream.newLineAtOffset(25, 725);

	      String text1 = "This is an example of adding text to a page in the pdf document. we can add as many lines";
	      String text2 = "as we want like this using the ShowText()  method of the ContentStream class";

	      //Adding text in the form of string
	      contentStream.showText(text1);
	      contentStream.newLine();
	      contentStream.showText(text2);
	      //Ending the content stream
	      contentStream.endText();

	      System.out.println("Content added");

	      //Closing the content stream
	      contentStream.close();



	      //Saving the document
	      document.save(new File("/Users/sarthakgarg/Downloads/my_doc1.pdf"));

	      //Closing the document
	      document.close();

	}
	
	

}
