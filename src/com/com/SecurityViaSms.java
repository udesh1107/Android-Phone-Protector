package com.com;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.gsm.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class SecurityViaSms extends Activity {
	
	 private static final int SMS_KEY_CHANGE_DIALOG = 7;
	 private static final int NUMBER_CHANGE_DIALOG = 8;
	 public static final String SMS_KEY_CODE = "";
	 public static final String PHONE_NUMBER = "";
	 
	   

	    private Editor smsEditor;
	    private Editor numberEditor;
	    private TextView tvVerifyCode;
	    private TextView tvHelp;
	        @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.security_via_sms);
	        
	        
	                
	        Button sms_key_button = (Button) findViewById(R.id.sms_key_change_button);
	        sms_key_button.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	                showDialog(SMS_KEY_CHANGE_DIALOG);
	                
	            }
	        });
	        
	        Button predefine_number_button = (Button) findViewById(R.id.predefine_number_change_button);
	        predefine_number_button.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	                	showDialog(NUMBER_CHANGE_DIALOG);
	                        
	            }
	        });
	        
	        SharedPreferences smsKeyShare = getSharedPreferences(SMS_KEY_CODE, 0);
	        String smsKey = smsKeyShare.getString(SMS_KEY_CODE, null);
	        smsEditor = smsKeyShare.edit();
	        
	        SharedPreferences numberShare = getSharedPreferences( PHONE_NUMBER, 0);
	        String phoneNumber = numberShare.getString(PHONE_NUMBER, null);
	        numberEditor = numberShare.edit();
	        
	        tvVerifyCode = (TextView)findViewById(R.id.textview_verify_code);
	        tvHelp = (TextView)findViewById(R.id.TextView_HELP);
	        
	        
	                
	                tvVerifyCode.setText(smsKey);
	                tvHelp.setText(smsKey);
	                tvHelp.setTextColor( 0xff6699ff );
	                tvHelp.setTextSize( 24 );
	                tvHelp.setBackgroundColor( 0xffffff00 );
	    }
	    
	    @Override
	    protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case SMS_KEY_CHANGE_DIALOG:
	            LayoutInflater factory_sms = LayoutInflater.from(this);
	            final View smsView = factory_sms.inflate(R.layout.alert_dialog_text_entry, null);
	            TextView sms_alert_box=(TextView)smsView.findViewById(R.id.above_alert_box);
	            sms_alert_box.setText(R.string.alert_sms_box);
	            return new AlertDialog.Builder(SecurityViaSms.this)
	                .setIcon(R.drawable.alert_dialog_icon)
	                .setTitle(R.string.alert_dialog_sms_title)
	                .setView(smsView)
	                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                        EditText smsText = (EditText)smsView.findViewById(R.id.edit_field);
	                        String newSmsKey = smsText.getText().toString();
	                        smsEditor.putString(SMS_KEY_CODE, newSmsKey);
	                        smsEditor.commit();
	                   }
	                })
	                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                        /* User clicked cancel so do some stuff */
	                        dialog.dismiss();
	                    }
	                })
	                .create();
	        
	        case NUMBER_CHANGE_DIALOG: 
	        	LayoutInflater factory_number = LayoutInflater.from(this);
	            final View numberView = factory_number.inflate(R.layout.alert_dialog_text_entry, null);
	            TextView number_alert_box=(TextView)numberView.findViewById(R.id.above_alert_box);
	            number_alert_box.setText(R.string.alert_number_box);
	            return new AlertDialog.Builder(SecurityViaSms.this)
	                .setIcon(R.drawable.alert_dialog_icon)
	                .setTitle(R.string.alert_dialog_number_title)
	                .setView(numberView)
	                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                        EditText numberText = (EditText)numberView.findViewById(R.id.edit_field);
	                        String newNumber = numberText.getText().toString();	                        
	                        numberEditor.putString(PHONE_NUMBER, newNumber);
	                        numberEditor.commit();
	                   }
	                })
	                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                        /* User clicked cancel so do some stuff */
	                        dialog.dismiss();
	                    }
	                })
	                .create();
	        }
	        return null;
	    }
	    
	   
	    
	    

	    


}
