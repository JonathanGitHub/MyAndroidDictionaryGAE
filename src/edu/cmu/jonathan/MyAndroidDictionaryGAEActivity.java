/*
 * @author: Jian Yang
 * @email:jiany1@andrew.cmu.edu
 * @last modified:3/21/2013
 */
package edu.cmu.jonathan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/*
 * MyAndroidDictionaryGAEActivity class enables users to input a keyword
 * in EditText and retrieves its Chinese translation & English example sentence
 * from my GoogleAppEngine and display those contents in TextViews of UI thread
 * Note that my GoogleAppEngine is a separate project which interacts with a non-commercial
 * API
 */
public class MyAndroidDictionaryGAEActivity extends Activity {
		//define EditText, Button, and TextView in UI thread
		//assign "null" value to them for safety concern
		private EditText editText = null;
		private Button button = null;
		private TextView textView_prompter = null;
		private TextView textView_definition = null;
		private TextView textView_example = null;
	
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.main);
				//set the background of this activity as "snow" while color
				getWindow().setBackgroundDrawableResource(R.color.snow); 
				//bind components through their IDs
				button = (Button)findViewById(R.id.button1);
				editText = (EditText)findViewById(R.id.editText1);
				textView_prompter = (TextView)findViewById(R.id.textView_prompter);
				textView_definition = (TextView)findViewById(R.id.textView_definition);
				textView_example = (TextView)findViewById(R.id.textView_example);
				//add a listener to the submit button
				button.setOnClickListener(new ButtonOnClickListener());
        
		}
    
		//class buttonOnClickListner is an inner class which implements the 
		//actions when users click submit button
		class ButtonOnClickListener implements OnClickListener{
				public void onClick(View arg0) {
				// TODO Auto-generated method stub		
				//get the text that users typed
			    String search = editText.getText().toString();
			    //initialize GetWordAsyncTask and pass three textViews to it
			    GetWordAsyncTask asyncTask = new GetWordAsyncTask(textView_prompter, 
			    		textView_definition, textView_example);
			    // Done asynchronously in another thread.
			    asyncTask.execute(search);
		}
		
	}
    
       
       
}
