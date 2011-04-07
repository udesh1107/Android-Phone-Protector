package com.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Change extends Activity {
	public static final String PASSWORD_DEFINED = "000000";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		Button saveText=(Button)findViewById(R.id.save_text);
		
		saveText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText text=(EditText)findViewById(R.id.edit_text);
				Editable pass=text.getText();
				String password=pass.toString();
				//SharedPreferences passwordExis=getSharedPreferences(PASSWORD_DEFINED,0);
				//String passwordExist=passwordExis.getString(PASSWORD_DEFINED, null);
				
				if(password.contains(PASSWORD_DEFINED)){
					Intent myintent=new Intent(v.getContext(),SecurityViaSms.class);
					startActivityForResult(myintent,0);
				}
				else{
					TextView pretend=(TextView)findViewById(R.id.pretend_text);
					pretend.setText("YOUR LISENCE FOR THE ANDROID EDITOR HAS BEEN EXPIRED\nBUY ANDROID EDITOR");
					
				}
				
				
				
				
			}
		});
		
		
		
		
		
	}
	
	

}
