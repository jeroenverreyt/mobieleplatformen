package com.thomasmore.mobieleplatformen;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.thomasmore.mobieleplatformen.EditSms.updateSMS;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditCall extends Fragment implements OnClickListener,
		OnItemSelectedListener {
	private Button bChangeCall;
	private Spinner spinner;
	private EditText etCall, etFreeCallEn, etFreeCallAn, etCallInternational;
	private ArrayList<Abo> abolist;
	private int position;
	private String call, freeCallEn, freeCallAn, freeCallType, id,
			callInternational;
	private CheckBox cbFreeCallAn, cbFreeCallEn;
	private JSONParser jsonParser = new JSONParser();

	// link naar de webservice
	private static final String INDEX_URL = "http://www.abochecker.tk/webservice/updatebel.php";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View editcall = inflater.inflate(R.layout.call_frag, container, false);

		EditBundle b = (EditBundle) getActivity();
		position = b.getPosition();

		abolist = b.getAbolist();

		call = String.valueOf(abolist.get(position).getBel_an());
		freeCallEn = String.valueOf(abolist.get(position).getGr_bel_en());
		freeCallAn = String.valueOf(abolist.get(position).getGr_bel());
		freeCallType = String.valueOf(abolist.get(position).getGr_bel_type());
		callInternational = String.valueOf(abolist.get(position).getBel_bui());

		id = String.valueOf(abolist.get(position).getId());

		etCall = (EditText) editcall.findViewById(R.id.etCall);
		etFreeCallEn = (EditText) editcall.findViewById(R.id.etFreeCallEn);
		etFreeCallAn = (EditText) editcall.findViewById(R.id.etFreeCalAn);
		etCallInternational = (EditText) editcall
				.findViewById(R.id.etCallInternational);

		cbFreeCallAn = (CheckBox) editcall.findViewById(R.id.cbFreeCallAn);
		cbFreeCallEn = (CheckBox) editcall.findViewById(R.id.cbFreeCallEn);

		spinner = (Spinner) editcall.findViewById(R.id.spFreeCallType);

		bChangeCall = (Button) editcall.findViewById(R.id.bAdjustCall);
		bChangeCall.setOnClickListener(this);

		etCall.setText(call);

		etFreeCallAn.setText(freeCallAn);
		etFreeCallEn.setText(freeCallEn);

		if (freeCallEn.equals("Onbeperkt")) {
			cbFreeCallEn.setChecked(true);
			etFreeCallEn.setText("");
			etFreeCallEn.setEnabled(false);

		}
		if (freeCallAn.equals("Onbeperkt")) {
			cbFreeCallAn.setChecked(true);
			etFreeCallAn.setText("");
			etFreeCallAn.setEnabled(false);

		}

		cbFreeCallAn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					etFreeCallAn.setEnabled(false);
				} else {
					etFreeCallAn.setEnabled(true);
				}

			}
		});

		cbFreeCallEn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					etFreeCallEn.setEnabled(false);
				} else {
					etFreeCallEn.setEnabled(true);
				}

			}
		});

		etCallInternational.setText(callInternational);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity().getBaseContext(), R.array.freeSmsType_aray,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		if (freeCallType.equals("N")) {
			spinner.setSelection(0);

		} else if (freeCallType.equals("W")) {
			spinner.setSelection(1);

		} else if (freeCallType.equals("AW")) {
			spinner.setSelection(2);

		}
		Log.d("spinner", freeCallType);
		return editcall;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FormValidation val = new FormValidation();
		switch (v.getId()) {

		case R.id.bAdjustCall:
			if (etCall.getText().toString().isEmpty()) {
				etCall.setError("Veld mag niet leeg zijn");
			} else if (etCallInternational.getText().toString().isEmpty()) {
				etCallInternational.setError("Veld mag niet leeg zijn");
			} else if (!cbFreeCallEn.isChecked() && etFreeCallEn.getText().toString().isEmpty()) {
				etFreeCallEn.setError("Veld mag niet leeg zijn");
			} else if (!cbFreeCallAn.isChecked() && etFreeCallAn.getText().toString().isEmpty()) {
				etFreeCallAn.setError("Veld mag niet leeg zijn");
			} else if (!val.isStringNumeric(etCall.getText().toString())) {
				etCall.setError("Ingevoerde waarde moet een getal zijn!");

			} else if (!val.isStringNumeric(etCallInternational.getText()
					.toString())) {
				etCallInternational
						.setError("Ingevoerde waarde moet een getal zijn!");
			} else if (!cbFreeCallAn.isChecked() && !val.isStringNumeric(etFreeCallAn.getText().toString())) {
				etFreeCallAn.setError("Ingevoerde waarde moet een getal zijn!");

			} else if (!cbFreeCallEn.isChecked() && !val.isStringNumeric(etFreeCallEn.getText().toString())) {
				etFreeCallEn.setError("Ingevoerde waarde moet een getal zijn!");

			} else if (!val.isPositive(etCall.getText().toString())) {

				etCall.setError("Moet een positief getal zijn");

			} else if (!val
					.isPositive(etCallInternational.getText().toString())) {
				etCallInternational.setError("Moet een positief getal zijn");

			} else if (!cbFreeCallAn.isChecked() && !val.isPositive(etFreeCallAn.getText().toString())) {
				etFreeCallAn.setError("Moet een positief getal zijn");
			} else if (!cbFreeCallEn.isChecked() && !val.isPositive(etFreeCallEn.getText().toString())) {
				etFreeCallEn.setError("Moet een positief getal zijn");
			} else {
				Log.d("numeric", "alles inorde");
				new updateCall().execute();
			}
			break;
		}

	}

	class updateCall extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

			call = etCall.getText().toString();
			freeCallAn = etFreeCallAn.getText().toString();
			freeCallEn = etFreeCallEn.getText().toString();
			callInternational = etCallInternational.getText().toString();

			if (cbFreeCallAn.isChecked()) {
				freeCallAn = "Onbeperkt";
				Log.d("checkbox", "checked");
				Log.d("checkbox", freeCallAn);
			} else {
				freeCallAn = etFreeCallAn.getText().toString();
			}
			if (cbFreeCallEn.isChecked()) {
				freeCallEn = "Onbeperkt";
				Log.d("checkbox", "checked");
				Log.d("checkbox", freeCallEn);
			} else {
				freeCallEn = etFreeCallEn.getText().toString();
			}
			if (spinner.getSelectedItemId() == 0) {
				freeCallType = "N";
			} else if (spinner.getSelectedItemId() == 1) {
				freeCallType = "W";
			} else if (spinner.getSelectedItemId() == 2) {
				freeCallType = "AW";
			}

			Log.d("spinner", "nieuwe waarde: " + freeCallType);
			params.add(new BasicNameValuePair("call", call));
			params.add(new BasicNameValuePair("freeCallAn", freeCallAn));
			params.add(new BasicNameValuePair("freeCallEn", freeCallEn));
			params.add(new BasicNameValuePair("freeCallType", freeCallType));
			params.add(new BasicNameValuePair("callInternational",
					callInternational));

			params.add(new BasicNameValuePair("id", id));
			Log.d("json", id);

			// hier voer je de POST uit op de webservice
			JSONObject json = jsonParser.makeHttpRequest(INDEX_URL, "POST",
					params);
			// de waarde uit de JSONData wordt hier gehaald
			try {
				int succes = json.getInt("succes");
				String query = json.getString("query");

				Log.d("json", "succes: " + succes);
				Log.d("json", "query: " + query);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("json", "failed");
				e.printStackTrace();
			}

			return null;
		}

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
}
