/*
 * @author: Jian Yang
 * @email:jiany1@andrew.cmu.edu
 * @last modified:3/21/2013
 */
package edu.cmu.jonathan;

import android.os.AsyncTask;
import android.widget.TextView;
/*
 * AsyncTask provides a simple way to use a thread separate from the
 * UI thread in which to do network operations. doInBackground is run 
 * in the helper thread. onPostExecute is run in the UI thread, allowing 
 * for safe UI updates.
 */


public class GetWordAsyncTask extends AsyncTask<String, Void, String[]>{
		/*
		 * <String, Void, String[]> First param String is the type of param 
		 * that doInBackground takes Second param Void is for progress update, 
		 * we don't use that in this project, last param String[] is the 
		 * return type of doInBackground and the postExecute method takes that 
		 * type, also
		 */
     	//define three textViews, 1st is the prompter, 2nd is word definition,
	    //3rd is example of that keyword
    	private TextView textView_prompter = null;
    	private TextView textView_definition = null;
    	private TextView textView_example = null;
    	//constructor of GetWordAsyncTask, takes three params from UI thread and
    	//updates UI thread thru those textViews
    	GetWordAsyncTask(TextView textView_prompter, TextView textView_definition, 
    			TextView textView_example){
    			this.textView_prompter = textView_prompter;
    			this.textView_definition = textView_definition;
    			this.textView_example = textView_example;
    	}
    	//doInBackground runs in a separate thread than UI thread, it takes a 
    	//String(array) and returns a String[] note that "String[]" will be
    	//forwarded to onPostExecute method
    	@Override
    	protected String[] doInBackground(String... arg0) {
    			// TODO Auto-generated method stub
    		    //create a GetWord object
    			GetWord myWord = new GetWord();
    			//do doWordSearch, arg0[0] is passed through execute() 
    			//method from UI thread
    			return myWord.doWordSearch(arg0[0]);
    	}
    	//onPostExecute is running in UI thread, for updating
    	@Override
    	protected void onPostExecute(String result[]){
    			textView_prompter.setText("Async Task is completed!");
    			//set the definition in textView
    			textView_definition.setText(result[0]);
    			//set the example in textView
    			textView_example.setText(result[1]);
    			
    	}
    	//onPreExecute is also running in UI thread
    	@Override 
    	protected void onPreExecute(){
    			textView_prompter.setText("Start Async Task for searching word online....");
    		    textView_definition.setText("");
    		    textView_example.setText("");
    	}

}
