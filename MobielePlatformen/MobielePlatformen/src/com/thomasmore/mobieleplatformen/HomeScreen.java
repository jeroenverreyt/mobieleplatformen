package com.thomasmore.mobieleplatformen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

public class HomeScreen extends Activity implements OnClickListener {

	private Button bVoegToe;
	private Button bPasAan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);

		initVars();
	}

	private void initVars() {
		bVoegToe = (Button) findViewById(R.id.bVoegToe);
		bPasAan = (Button) findViewById(R.id.bPasAan);
		
		bVoegToe.setOnClickListener(this);
		bPasAan.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bVoegToe:
			Intent i1 = new Intent(HomeScreen.this, Insert.class);
			startActivity(i1);
			break;

		case R.id.bPasAan:
			Intent i2 = new Intent(HomeScreen.this, ProviderList.class);
			startActivity(i2);
			break;

		}
	}

}
