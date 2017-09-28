package net.nicolanicodemo.fueltracker;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.nicolanicodemo.fueltracker.persistence.RifornimentiHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener {
	private RifornimentiHelper dbHelper;
	private int kmPrec;
	
	//View widgets
	private TextView editData;
	private EditText editSpesa;
	private EditText editLitri;
	private EditText editKilometri;
	private CheckBox serbatoioPieno;
	private Button btnInserisci;
	
	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_add);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        dbHelper = new RifornimentiHelper(this);
        
        //View widgets initialization
        editData = (TextView) findViewById(R.id.editData);
        editSpesa = (EditText) findViewById(R.id.editSpesa);
        editLitri = (EditText) findViewById(R.id.editLitri);
        editKilometri = (EditText) findViewById(R.id.editKilometri);
        serbatoioPieno = (CheckBox) findViewById(R.id.serbatoioPieno);
        btnInserisci = (Button) findViewById(R.id.btnInserisci);
        
        //Event handlers
        btnInserisci.setOnClickListener(this);
        
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd-MM-yyy");
        editData.setText(sdf.format(new Date()));
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) this.kmPrec = extras.getInt("kmPrec");
        editKilometri.setHint(kmPrec + "");
	}
	
	@Override
	public void onClick(View view) {
		ContentValues values = new ContentValues();
		values.put("data", editData.getText().toString());
		
		String sSpesa = editSpesa.getText().toString();
		String sLitri = editLitri.getText().toString();
		String sKilometri = editKilometri.getText().toString();
		
		int kilometri = 0;
		try {
			kilometri = Integer.parseInt(sKilometri);
		}catch(Exception e){}
			
		if (!sSpesa.equals("") && !sLitri.equals("") && kilometri > kmPrec) {
			values.put("spesa", sSpesa);
			values.put("litri", sLitri);
			values.put("kilometri", sKilometri);
		
			double spesa = Double.parseDouble(editSpesa.getText().toString());
			double litri = Double.parseDouble(editLitri.getText().toString());
			double costoUnitario = spesa / litri;
		
			values.put("costoUnitario", costoUnitario);
			values.put("serbPieno", serbatoioPieno.isChecked() ? 1 : 0);
		 
			double consumo100;
			if(serbatoioPieno.isChecked() && kmPrec > 0) {
				int kmAtt = Integer.parseInt(editKilometri.getText().toString());
				consumo100 = (litri * 100) / (kmAtt - kmPrec);
			} else consumo100 = 0;
			values.put("consumo100", consumo100);
		
			dbHelper.doInsert(values);
			finish();
		} else {
			Toast toast = Toast.makeText(this, "Dati inseriti non corretti!", Toast.LENGTH_LONG);
			toast.show();
		}
	}
	
	public void onDateClicked(View view) {
		DatePickerFragment newFragment = new DatePickerFragment();
		newFragment.setParam(editData);
		
	    newFragment.show(getFragmentManager(), "DatePicker");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		NavUtils.navigateUpFromSameTask(this);
	    	return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

}
