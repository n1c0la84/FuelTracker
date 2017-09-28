package net.nicolanicodemo.fueltracker;

import net.nicolanicodemo.fueltracker.model.Rifornimento;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
	
	//View widgets
	private TextView textSpesa;
	private TextView textLitri;
	private TextView textKilometri;
	private TextView textData;
	private TextView textCostoUnitario;

	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Rifornimento r = new Rifornimento();
        Bundle extras = getIntent().getExtras();
        if (extras != null) r = (Rifornimento) extras.getSerializable("RIFORNIMENTO");
        
        //View widgets initialization
        textSpesa = (TextView) findViewById(R.id.textSpesa);
        textLitri = (TextView) findViewById(R.id.textLitri);
        textKilometri = (TextView) findViewById(R.id.textKilometri);
        textData = (TextView) findViewById(R.id.textData);
        textCostoUnitario = (TextView) findViewById(R.id.textCostoUnitario);
        
        textSpesa.setText(r.getSpesa() + " €");
        textLitri.setText(r.getLitri() + " L");
        textKilometri.setText(r.getKilometri() + " Km");
        textData.setText(r.getData());
        
        double prezzo = Math.round(r.getCostoUnitario() * 1000);
        textCostoUnitario.setText(prezzo/1000 + " €/l");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast toast;
		
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		NavUtils.navigateUpFromSameTask(this);
	    	return true;
	    	
	    	case R.id.action_modify:
	    		toast = Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT);
	    		toast.show();
	    	return true;
	    	
	    	case R.id.action_delete:
	    		toast = Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT);
	    		toast.show();
	    	return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
}
