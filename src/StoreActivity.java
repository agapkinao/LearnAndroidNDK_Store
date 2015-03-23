import com.packtpub.store.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StoreActivity extends Activity {
	private EditText mUIKeyEdit, mUIValueEdit;
	private Spinner mUITypeSpinner;
	private Button mUIGetButton, mUISetButton;
	private Store mStore;
	
	@Override
	public void onCreate(Bundle savedInstanceState)	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// EditText's
		mUIKeyEdit = (EditText)findViewById(R.id.uiKeyEdit);
		mUIValueEdit = (EditText)findViewById(R.id.uiValueEdit);
		
		// Spinner
		Spinner uiTypeSpinner = (Spinner)findViewById(R.id.uiTypeSpinner);
		
		ArrayAdapter<StoreType> adapter = new ArrayAdapter<StoreType>(this, android.R.layout.simple_spinner_item, StoreType.values());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		uiTypeSpinner.setAdapter(adapter);
		
		// Buttons
		mUIGetButton = (Button)findViewById(R.id.uiGetButton);
		mUIGetButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				onGetValue();
			}
		});
		
		mUISetButton = (Button)findViewById(R.id.uiSetButton);
		mUISetButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				onSetValue();
			}
		});
		
		mStore = new Store();
	}
	
	void onGetValue() {
		String sKey = mUIKeyEdit.getText().toString();
		StoreType type = (StoreType)mUITypeSpinner.getSelectedItem(); 
		
		switch(type){
		case Integer:
			mUIValueEdit.setText(Integer.toString(mStore.getInteger(sKey)));
			break;
			
		case String:
			mUIValueEdit.setText(mStore.getString(sKey));
			break;
		}
	}
	
	void onSetValue() {
		String sKey = mUIKeyEdit.getText().toString();
		String sValue = mUIValueEdit.getText().toString();
		StoreType type = (StoreType)mUITypeSpinner.getSelectedItem();
		
		try {
			switch(type) {
			case Integer:
				mStore.setIngeter(sKey, Integer.parseInt(sValue));
				break;
				
			case String:
				mStore.setString(sKey, sValue);
				break;
			}
		}
		catch(NumberFormatException eNumberFormatException) {
			displayError(getString(R.string.format_error));
		}
	}
	
	void displayError(String sError) {
		Toast.makeText(getApplicationContext(), sError, Toast.LENGTH_LONG).show();
	}
}
