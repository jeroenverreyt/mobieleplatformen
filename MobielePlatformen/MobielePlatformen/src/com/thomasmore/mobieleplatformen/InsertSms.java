package com.thomasmore.mobieleplatformen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class InsertSms extends Activity implements OnClickListener {
	private EditText etSMS, etFreeSmsEn, etFreeSmsAn;
	private Button bNext;
	private Spinner spinner;
	private RadioButton rbFreeSmsEn, rbFreeSmsEnUnl, rbFreeSmsAn,
			rbFreeSmsAnUnl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertsms);

		etSMS = (EditText) findViewById(R.id.etNewSMS);
		etFreeSmsEn = (EditText) findViewById(R.id.etNewFreeSmsEn);
		etFreeSmsAn = (EditText) findViewById(R.id.etNewFreeSmsAn);

		// cbFreeSmsEn = (CheckBox) findViewById(R.id.cbNewFreeSmsEn);
		rbFreeSmsEn = (RadioButton) findViewById(R.id.rbNewFreeSmsEn);
		rbFreeSmsEnUnl = (RadioButton) findViewById(R.id.rbNewFreeSmsEnUnl);

		rbFreeSmsAn = (RadioButton) findViewById(R.id.rbNewFreeSmsAn);
		rbFreeSmsAnUnl = (RadioButton) findViewById(R.id.rbNewFreeSmsAnUnl);

		bNext = (Button) findViewById(R.id.bNext1);
		bNext.setOnClickListener(this);

		spinner = (Spinner) findViewById(R.id.spNewFreeSmsType);

		rbFreeSmsAnUnl.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					etFreeSmsAn.setEnabled(false);
				} else {
					etFreeSmsAn.setEnabled(true);
				}

			}
		});

		rbFreeSmsEnUnl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					etFreeSmsEn.setEnabled(false);
				} else {
					etFreeSmsEn.setEnabled(true);
				}

			}
		});

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.freeSmsType_aray,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FormValidation val = new FormValidation();
		Bundle b = getIntent().getExtras();
		String name = b.getString("name");
		String price = b.getString("price");
		String provider = b.getString("provider");
		String sms = etSMS.getText().toString();
		String freeSmsEn = etFreeSmsEn.getText().toString();
		String freeSmsAn = etFreeSmsAn.getText().toString();

		if (rbFreeSmsAnUnl.isChecked()) {
			freeSmsAn = "Onbeperkt";
			Log.d("checkbox", "checked");
			Log.d("checkbox", freeSmsAn);
		}
		if (rbFreeSmsEnUnl.isChecked()) {
			freeSmsEn = "Onbeperkt";
			Log.d("checkbox", "checked");
			Log.d("checkbox", freeSmsEn);
		}
		String freeSmsType = "";
		if (spinner.getSelectedItemId() == 0) {
			freeSmsType = "N";
		} else if (spinner.getSelectedItemId() == 1) {
			freeSmsType = "W";
		} else if (spinner.getSelectedItemId() == 2) {
			freeSmsType = "AW";
		}

		if (sms.isEmpty()) {
			etSMS.setError("Gelieve een prijs in te geven");
		} else if (!rbFreeSmsEnUnl.isChecked() && freeSmsEn.isEmpty()) {
			etFreeSmsEn.setError("Gelieve een prijs in te geven");

		} else if (!rbFreeSmsAnUnl.isChecked() && freeSmsAn.isEmpty()) {
			etFreeSmsAn.setError("Gelieve een prijs in te geven");
		} else if (!val.isStringNumeric(sms)) {
			etSMS.setError("Voer een correct getal in");
		} else if (!rbFreeSmsAnUnl.isChecked() && !val.isStringNumeric(freeSmsAn)) {
			etFreeSmsAn.setError("Voer een correct getal in");
		} else if (!rbFreeSmsEnUnl.isChecked() && !val.isStringNumeric(freeSmsEn)) {
			etFreeSmsEn.setError("Voer een correct getal in");
		} else if (!val.isPositive(sms)) {
			etSMS.setError("Getal moet positief zijn");
		} else if (!rbFreeSmsAnUnl.isChecked() && !val.isPositive(freeSmsAn)) {
			etFreeSmsAn.setError("Getal moet positief zijn");
		} else if (!rbFreeSmsEnUnl.isChecked() && !val.isPositive(freeSmsEn)) {
			etFreeSmsEn.setError("Getal moet positief zijn");
		} else {
			Bundle basket = new Bundle();
			basket.putString("sms", sms);

			basket.putString("provider", provider);
			basket.putString("freeSmsEn", freeSmsEn);
			basket.putString("freeSmsAn", freeSmsAn);
			basket.putString("name", name);
			basket.putString("price", price);
			basket.putString("freeSmsType", freeSmsType);
			Intent i = new Intent(InsertSms.this, InsertCall.class);
			i.putExtras(basket);
			Log.d("basket", "name: " + name + "  price  " + price + "  sms "
					+ sms + "  freeSmsEn " + freeSmsEn + "  freeSmsAn "
					+ freeSmsAn);

			startActivity(i);
		}

	}
}
