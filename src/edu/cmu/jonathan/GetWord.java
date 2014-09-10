/*
 * @author: Jonathan Young 
 * @email:jonathan underscore education at yahoo dot com
 * @last modified:3/21/2013
 */
package edu.cmu.jonathan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/*
 * This class provides capabilities to search for an keyword 
 * on "http://jonathandictionary.appspot.com/" given a search term. 
 * The method "search" is the entry to the class. Network 
 * operations cannot  be done from the UI thread, therefore this 
 * class makes use of an AsyncTask inner class that will do the 
 * network operations in a separate worker thread.  However, any 
 * UI updates should  be done in the UI thread so avoid any 
 * synchronization problems. onPostExecution runs in the UI thread, 
 * and it calls gives a prompter to UI when each time Async Task completes   
 * 
 */
public class GetWord {
		private URL url= null;
		/*
		 * @method doWordSearch
		 * @description doWordSearch takes a String parameter 
		 * and search for the Chinese definition and English sentence 
		 * example on my GoogleAppEngine http://jonathandictionary.appspot.com/
		 * and returns a String array, array[0] stores Chinese definition, 
		 * array[1] stores English sentence example
	 	 * @param String
	 	 * @return String[]
	     */
		public  String[] doWordSearch(String search) {
			//define a String object response and store the contents 
			//of XML file we visit each time
			String response = "";     
			try {
				
				//Thread.sleep(1*1000);
				
				// Create a URL for the desired page, note that browser would 
				// automatically change space as %20, here in Android program we 
				// have to change the space manually with the method String.replaceAll(), 
				// otherwise it would cause URL exception
				url = new URL("http://jonathandictionary.appspot.com/" +
						"myandroiddictionarygae?search=" + search.replaceAll(" ","%20"));
				// Create an HttpURLConnection.  This is useful for setting headers 
				// and for getting the path of the resource that is returned 
				// (which may be different than the URL above if redirected. 
				 System.out.println("URL------>" + url);//for debugging
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				// Read all the text returned by the server, remember to use "UTF-8" encoding 
				// as we read Chinese characters
				BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream(), "UTF-8"));
				// str is one line of text
				String str;
				//readLine() strips the newline character(s)
				while ((str = in.readLine()) != null) { 
					response += str;
				}
				System.out.println(response);//for debugging
				in.close();
				} catch (MalformedURLException e) {
				} catch (IOException e) {
				}
			
                //In general, I use either DOM or SAXParser to parse XML file
			    //because this project only involves limited tags(2), that's why
			    //I didn't use Parser to parse, the project could be extended to 
			    //real world commercial dictionary app by using SAXparser(that is
			    //to create a new package and Java class that deals with XML stuff
             	int startfarmDef = response.indexOf("<wordDefinition>");
             	System.out.println("startfarmDef: "+startfarmDef);
  
             	int endfarmDef = response.indexOf("</wordDefinition>"); 
             	System.out.println("endfarmDef: "+ endfarmDef);
             
             	int startfarmEx = response.indexOf("<example>");
             	System.out.println("startfarmEx: "+startfarmEx);
             
             	int endfarmEx = response.indexOf("</example>"); 
             	System.out.println("endfarmEx: "+ endfarmEx);
             
             	String[] array = new String[2];
             	array[0] = response.substring(startfarmDef+16, endfarmDef);
             	array[1] = response.substring(startfarmEx+10, endfarmEx);
            
             	System.out.println(array[0]);
             	System.out.println(array[1]);
             	return array;
            
		}
         
}
