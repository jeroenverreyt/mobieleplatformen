package com.thomasmore.mobieleplatformen;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.thomasmore.mobieleplatformen.EditCall.updateCall;

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

public class EditData extends Fragment implements OnClickListener,
		OnItemSelectedListener {
	private Button bChangeData;
	private EditText etData, etFreeData;
	private ArrayList<Abo> abolist;
	private int position;
	private String data, freeData, id;
	private JSONParser jsonParser = new JSONParser();

	// link naar de webservice
	private static final String INDEX_URL = "http://www.abochecker.tk/webservice/updatedata.php";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View editsms = inflater.inflate(R.layout.data_frag, container, false);

		EditBundle b = (EditBundle) getActivity();
		position = b.getPosition();

		abolist = b.getAbolist();

		data = String.valueOf(abolist.get(position).getMi());
		freeData = String.valueOf(abolist.get(position).getGr_mi());
		id = String.valueOf(abolist.get(position).getId());

		etData = (EditText) editsms.findViewById(R.id.etData);
		etFreeData = (EditText) editsms.findViewById(R.id.etFreeData);
		
		bChangeData = (Button) editsms.findViewById(R.id.bAdjustData);
		bChangeData.setOnClickListener(this);

		etData.setText(data);
		etFreeData.setText(freeData);

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
		FormValidation val = new FormValidation();
		switch (v.getId()) {
		case R.id.bAdjustData:
			if(etData.getText().toString().isEmpty()){
				etData.setError("Veld mag niet leeg zijn");
			}else if(etFreeData.getText().toString().isEmpty()){
				etFreeData.setError("Veld mag niet leeg zijn");
			}else if(!val.isStringNumeric(etData.getText().toString())){
				etData.setError("Ingevoerde waarde moet een getal zijn!");
					
			}else if(!val.isStringNumeric(etFreeData.getText().toString())){
				etFreeData.setError("Ingevoerde waarde moet een getal zijn!");
			}else if (!val.isPositive(etData.getText().toString())) {
				
				etData.setError("Moet een positief getal zijn");
				
			}else if (!val.isPositive(etFreeData.getText().toString())) {
				etFreeData.setError("Moet een positief getal zijn");
			
			}
			else{
				Log.d("numeric", "alles inorde");
				new updateData().execute();
			}
			break;
		}

	}

	class updateData extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {

			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

			data = etData.getText().toString();
			freeData = etFreeData.getText().toString();

	

			
			params.add(new BasicNameValuePair("data", data));
			params.add(new BasicNameValuePair("freeData", freeData));
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
