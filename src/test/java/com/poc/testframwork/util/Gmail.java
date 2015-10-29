package com.poc.testframwork.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Gmail {
	
	public static String registration_email_address;

public static void main(String args[]) {
//	Gmail.doit();


}


/*public static String doit() {
    String activation_url="";

//	WebDriver driver = new FirefoxDriver();

    SelectBrowser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	// Navigate to URL
    SelectBrowser.driver.get("http://mail.google.com/");
	// Maximize the window.
	// Enter UserName
    SelectBrowser.driver.findElement(By.id("Email")).sendKeys("idprotatester");
    SelectBrowser.driver.findElement(By.id("next")).click();
    SelectBrowser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	// Enter Password
    SelectBrowser.driver.findElement(By.id("Passwd")).sendKeys("P@ssword12!");
	// Wait For Page To Load
    SelectBrowser.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	// Click on 'Sign In' button
    SelectBrowser.driver.findElement(By.id("signIn")).click();

	for (int second = 0;; second++) {
	    
	    try {
	    	Thread.sleep(20000);
	    	if (SelectBrowser.driver.findElements(By.cssSelector(".ts>b")).get(0)
	                .isDisplayed())
	            break;
	    } catch (Exception e) {
	    	SelectBrowser.driver.findElements(By.cssSelector(".searchPageLink")).get(0).click();
	    }
	}
	SelectBrowser.driver.findElements(By.cssSelector(".ts>b")).get(0).click();

	SelectBrowser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	activation_url=SelectBrowser.driver.findElements(By.cssSelector(".msg>div>font>a")).get(0).getAttribute("href");
	System.out.println(activation_url);
	//driver.close();
	 String email_content ="";// msg.getContent().toString();
     
     List<String> extractedUrls = extractUrls(email_content);

     for (String url : extractedUrls)
     {
     	if (!(url.isEmpty()))
         System.out.println("Activation url is: "+ url);
     	activation_url=url;
     }
   
    

	return activation_url;
	
}*/

	
public static String doit() throws MessagingException, IOException {
    Folder folder = null;
    Store store = null;
    String activation_url="";
    
    try {
        Properties props = System.getProperties();
        props.setProperty("http.proxySet", "true");
        props.setProperty("http.proxyHost", "smtp.test2.cloud.local");
        props.setProperty("http.proxyPort", "25");
        
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props, null);
        // session.setDebug(true);
        store = session.getStore("imaps");
        store.connect("imap.gmail.com", "idprotatester", "P@ssword12!");
        folder = store.getFolder("Inbox");
        folder.open(Folder.READ_WRITE);
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
        Message[] messages = folder.search(unseenFlagTerm);
    
        System.out.println("Waiting for new registration email.... ");
        
        while (messages.length == 0) {
        	
        	//folder.
        	 try {
 				TimeUnit.SECONDS.sleep(20);
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
             folder = store.getFolder("Inbox");
             folder.open(Folder.READ_WRITE);

        	 messages = folder.search(unseenFlagTerm);          
        }
        
        Message msg = messages[0];
    
          registration_email_address= parseAddresses(msg
                  .getRecipients(RecipientType.TO));
          
          String email_content = msg.getContent().toString();
          
          List<String> extractedUrls = extractUrls(email_content);

          for (String url : extractedUrls)
          {
          	if (!(url.isEmpty()))
              System.out.println("Activation url is: "+ url);
          	activation_url=url;
          }
        
          msg.setFlag(Flags.Flag.SEEN,true);
          // to delete the message
          // msg.setFlag(Flags.Flag.DELETED, true);
    
    }//end of try
      finally {
        if (folder != null) { folder.close(true); }
        if (store != null) { store.close(); }
      }
    return activation_url;
    }
  
public void gmail_connect(){
	
}

public static List<String> extractUrls(String text)
{
   List<String> containedUrls = new ArrayList<String>();
   String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
   Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
   Matcher urlMatcher = pattern.matcher(text);

   while (urlMatcher.find())
   {
       containedUrls.add(text.substring(urlMatcher.start(0),
               urlMatcher.end(0)));
   }

   return containedUrls;
}

 static String  parseAddresses(Address[] address) {
    String listAddress = "";

    if (address != null) {
        for (int i = 0; i < address.length; i++) {
            listAddress += address[i].toString() + ", ";
        }
    }
    if (listAddress.length() > 1) {
        listAddress = listAddress.substring(0, listAddress.length() - 2);
    }

    return listAddress;
}

}
