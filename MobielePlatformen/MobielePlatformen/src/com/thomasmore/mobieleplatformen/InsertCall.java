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

public class InsertCall extends Activity implements OnClickListener {

	private EditText etCall, etFreeCallEn, etFreeCallAn, etCallInternational;
	private Button bNext;
	private Spinner spinner;
	private RadioButton rbFreeCallEn,rbFreeCallEnUnl, rbFreeCallAn, rbFreeCallAnUnl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertcall);

		etCall = (EditText) findViewById(R.id.etNewCall);
		etFreeCallEn = (EditText) findViewById(R.id.etNewFreeCallEn);
		etFreeCallAn = (EditText) findViewById(R.id.etNewFreeCalAn);
		etCallInternational = (EditText) findViewById(R.id.etNewCallInternational);
		bNext = (Button) findViewById(R.id.bNext2);
		bNext.setOnClickListener(this);

		rbFreeCallEn = (RadioButton) findViewById(R.id.rbNewFreeCallEn);
		rbFreeCallEnUnl = (RadioButton) findViewById(R.id.rbNewFreeCallEnUnl);

		rbFreeCallAn = (RadioButton) findViewById(R.id.rbNewFreeCallAn);
		rbFreeCallAnUnl = (RadioButton) findViewById(R.id.rbNewFreeCallAnUnl);
		
		

		rbFreeCallAnUnl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					etFreeCallAn.setEnabled(false);
				} else {
					etFreeCallAn.setEnabled(true);
				}

			}
		});

		rbFreeCallEnUnl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					etFreeCallEn.setEnabled(false);
				} else {
					etFreeCallEn.setEnabled(true);
				}

			}
		});
		
		spinner = (Spinner) findViewById(R.id.spNewFreeCallType);

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

		Bundle b = getIntent().getExtras();
		String name = b.getString("name");
		String price = b.getString("price");
		String sms = b.getString("sms");
		String freeSmsEn = b.getString("freeSmsEn");
		String freeSmsAn = b.getString("freeSmsAn");
		String freeSmsType = b.getString("freeSmsType");
		String provider = b.getString("provider");
		String call = etCall.getText().toString();
		String freeCallEn = etFreeCallEn.getText().toString();
		String freeCallAn = etFreeCallAn.getText().toString();
		String callInternational = etCallInternational.getText().toString();
		String freeCallType = "";
		
		if (rbFreeCallAnUnl.isChecked()) {
			freeCallAn = "Onbeperkt";
			Log.d("checkbox", "checked");
			Log.d("checkbox", freeSmsAn);
		} 
		
		if (rbFreeCallEnUnl.isChecked()) {
			freeCallEn = "Onbeperkt";
			Log.d("checkbox", "checked");
			Log.d("checkbox", freeSmsEn);
		}
		
		if (spinner.getSelectedItemId() == 0) {
			freeCallType = "N";
		} else if (spinner.getSelectedItemId() == 1) {
			freeCallType = "W";
		} else if (spinner.getSelectedItemId() == 2) {
			freeCallType = "AW";
		}

		FormValidation val = new FormValidation();
		if (call.isEmpty()) {
			etCall.setError("Gelieve een waarde in te geven");
		} else if (callInternational.isEmpty()) {
			etCallInternational.setError("Gelieve een waarde in te geven");

		} else if (!rbFreeCallAnUnl.isChecked() && freeCallAn.isEmpty()) {
			etFreeCallAn.setError("Gelieve een waarde in te geven");
		} else if (!rbFreeCallEnUnl.isChecked() && freeCallEn.isEmpty()) {
			etFreeCallEn.setError("Gelieve een waarde in te geven");
		} else if (!val.isStringNumeric(call)) {
			etCall.setError("Voer een correct getal in");
		} else if (!val.isStringNumeric(callInternational)) {
			etCallInternational.setError("Voer een correct getal in");
		} else if (!rbFreeCallAnUnl.isChecked() && !val.isStringNumeric(freeCallAn)) {
			etFreeCallAn.setError("Voer een correct getal in");
		} else if (!rbFreeCallEnUnl.isChecked() && !val.isStringNumeric(freeCallEn)) {
			etFreeCallEn.setError("Voer een correct getal in");
		} else if (!val.isPositive(call)) {
			etCall.setError("Getal moet positief zijn");
		} else if (!val.isPositive(callInternational)) {
			etCallInternational.setError("Getal moet positief zijn");
		} else if (!rbFreeCallAnUnl.isChecked() && !val.isPositive(freeCallAn)) {
			etFreeCallAn.setError("Getal moet positief zijn");
		} else if (!rbFreeCallEnUnl.isChecked() && !val.isPositive(freeCallEn)) {
			etFreeCallEn.setError("Getal moet positief zijn");
		} else {
			Bundle basket = new Bundle();
			basket.putString("sms", sms);
			basket.putString("provider", provider);
			basket.putString("freeSmsEn", freeSmsEn);
			basket.putString("freeSmsAn", freeSmsAn);
			basket.putString("name", name);
			basket.putString("price", price);
			basket.putString("call", call);
			basket.putString("freeCallEn", freeCallEn);
			basket.putString("freeCallAn", freeCallAn);
			basket.putString("freeCallType", freeCallType);
			basket.putString("callInternational", callInternational);
			basket.putString("freeSmsType", freeSmsType);
			Intent i = new Intent(InsertCall.this, InsertData.class);
			i.putExtras(basket);
			Log.d("basket", "name: " + name + "  price  " + price + "  sms "
					+ sms + "  freeSmsEn " + freeSmsEn + "  freeSmsAn "
					+ freeSmsAn + " call " + call + "  freeCallEn "
					+ freeCallEn + "  freeCallAn" + freeCallAn);

			startActivity(i);

		}

	}

}
