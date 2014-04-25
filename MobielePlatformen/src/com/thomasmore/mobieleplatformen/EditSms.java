package com.thomasmore.mobieleplatformen;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditSms extends Fragment implements OnClickListener,
		OnItemSelectedListener {
	private Button bChangeSMS;
	private Spinner spinner;
	private EditText etSms, etFreeSmsEn, etFreeSmsAn;
	private ArrayList<Abo> abolist;
	private int position;
	private String sms, freeSmsEn, freeSmsAn, freeSmsType, id;
	private JSONParser jsonParser = new JSONParser();

	// link naar de webservice
	private static final String INDEX_URL = "http://www.abochecker.tk/webservice/updateSms.php";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View editsms = inflater.inflate(R.layout.sms_frag, container, false);

		EditBundle b = (EditBundle) getActivity();
		position = b.getPosition();

		abolist = b.getAbolist();

		sms = String.valueOf(abolist.get(position).getSms_en());
		freeSmsEn = String.valueOf(abolist.get(position).getGr_sms_en());
		freeSmsAn = String.valueOf(abolist.get(position).getGr_sms());
		freeSmsType = String.valueOf(abolist.get(position).getGr_sms_type());

		id = String.valueOf(abolist.get(position).getId());

		etSms = (EditText) editsms.findViewById(R.id.etSMS);
		etFreeSmsEn = (EditText) editsms.findViewById(R.id.etFreeSmsEn);
		etFreeSmsAn = (EditText) editsms.findViewById(R.id.etFreeSmsAn);
		spinner = (Spinner) editsms.findViewById(R.id.spFreeSmsType);

		bChangeSMS = (Button) editsms.findViewById(R.id.bAdjustSMS);
		bChangeSMS.setOnClickListener(this);

		etSms.setText(sms);
		etFreeSmsAn.setText(freeSmsAn);
		etFreeSmsEn.setText(freeSmsEn);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity().getBaseContext(), R.array.freeSmsType_aray,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		if (freeSmsType.equals("N")) {
			spinner.setSelection(0);

		} else if (freeSmsType.equals("W")) {
			spinner.setSelection(1);

		} else if (freeSmsType.equals("AW")) {
			spinner.setSelection(2);

		}
		Log.d("spinner", freeSmsType);
		return editsms;
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

		switch (v.getId()) {
		case R.id.bAdjustSMS:
			new updateSMS().execute();
			break;
		}

	}

	class updateSMS extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

			sms = etSms.getText().toString();
			freeSmsAn = etFreeSmsAn.getText().toString();
			freeSmsEn = etFreeSmsEn.getText().toString();

			if (spinner.getSelectedItemId() == 0) {
				freeSmsType = "N";
			}else if(spinner.getSelectedItemId() == 1){
				freeSmsType = "W";
			}else if(spinner.getSelectedItemId()==2){
				freeSmsType="AW";
			}

			Log.d("spinner", "nieuwe waarde: " + freeSmsType);
			params.add(new BasicNameValuePair("sms", sms));
			params.add(new BasicNameValuePair("freeSmsAn", freeSmsAn));
			params.add(new BasicNameValuePair("freeSmsEn", freeSmsEn));
			params.add(new BasicNameValuePair("freeSmsType", freeSmsType));

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
