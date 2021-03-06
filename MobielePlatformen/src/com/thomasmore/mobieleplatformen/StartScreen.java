package com.thomasmore.mobieleplatformen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class StartScreen extends Activity implements OnItemSelectedListener, OnClickListener{
	private Spinner spinner;
	private Button bToListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
		
		spinner = (Spinner) findViewById(R.id.spProvider);
		bToListView = (Button)findViewById(R.id.bToListView);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.providers_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		bToListView.setOnClickListener(this);
		
		
		
		
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		 
		Boolean isInternetPresent = cd.isConnectingToInternet(); 
		if(isInternetPresent){
			
			Bundle b = new Bundle();
			String provider = spinner.getSelectedItem().toString();
			b.putString("provider", provider);
			Intent i = new Intent(StartScreen.this, ProviderList.class);
			i.putExtras(b);
			startActivity(i);
		}else{
			  AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			  
		        // Setting Dialog Title
		        alertDialog.setTitle("Geen internet connectie");
		 
		        // Setting Dialog Message
		        alertDialog.setMessage("U hebt geen internet connectie");
		         
		        // Setting alert dialog icon
		      //  alertDialog.setIcon(R.drawable.fail);
		 
		        // Setting OK Button
		        alertDialog.setButton(RESULT_OK, "OK",  new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            }
		        });
		 
		        // Showing Alert Message
		        alertDialog.show();
		}
	
		
	}

	
}
