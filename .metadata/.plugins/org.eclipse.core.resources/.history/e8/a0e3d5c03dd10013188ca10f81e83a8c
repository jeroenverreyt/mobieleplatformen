package com.thomasmore.mobieleplatformen;



import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Home extends Activity implements OnItemSelectedListener {
	Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		initvars();
	}
	private void initvars() {
		// TODO Auto-generated method stub
		spinner = (Spinner)findViewById(R.id.spProvider);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.providers_array, android.R.layout.simple_spinner_item);
				// Specify the layout to use when the list of choices appears
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner
				spinner.setAdapter(adapter);
				spinner.setOnItemSelectedListener(this);
				
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Log.d("spinner" , spinner.getSelectedItem().toString());
	//	savePrefs("PROVIDER", internationalCallsInMinutes);
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	private void savePrefs(String key, String value) {
		// TODO Auto-generated method stub
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}
	
	
}
