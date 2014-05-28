package com.thomasmore.mobieleplatformen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Insert extends Activity implements OnItemSelectedListener,
		OnClickListener {
	private EditText etName, etPrice;
	private Button bNext;
	private Spinner spProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertnew);

		etName = (EditText) findViewById(R.id.etNewName);
		etPrice = (EditText) findViewById(R.id.etNewPrice);

		bNext = (Button) findViewById(R.id.bNext);
		bNext.setOnClickListener(this);

		spProvider = (Spinner) findViewById(R.id.spProvider);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.providers_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spProvider.setAdapter(adapter);
		spProvider.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FormValidation val = new FormValidation();
		String name = etName.getText().toString();
		String price = etPrice.getText().toString();
		if (name.isEmpty()) {
			etName.setError("Gelieve een naam in te vullen!");
		} else if (price.isEmpty()) {
			etPrice.setError("Gelieve een prijs in te vullen!");
		} else if (!val.isStringNumeric(price)) {
			etPrice.setError("Prijs moet een getal zijn!");
		} else if (!val.isPositive(price)) {
			etPrice.setError("Prijs moet een positief getal zijn!");

		} else {
			String provider = spProvider.getSelectedItem().toString();
			Bundle basket = new Bundle();
			basket.putString("name", name);
			basket.putString("price", price);
			basket.putString("provider", provider);
			Intent i = new Intent(Insert.this, InsertSms.class);
			i.putExtras(basket);

			startActivity(i);
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
