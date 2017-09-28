package net.nicolanicodemo.fueltracker;

import java.util.List;

import net.nicolanicodemo.fueltracker.model.Rifornimento;
import net.nicolanicodemo.fueltracker.persistence.RifornimentiHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	private RifornimentiHelper dbHelper;
	private List<Rifornimento> rifornimenti;
	
	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        
        dbHelper = new RifornimentiHelper(this);
    }
	
	@Override
	public void onStart() {
		super.onStart();
		
		this.rifornimenti = dbHelper.doQuery();  
		CustomAdapter<Rifornimento> adapter = 
				new CustomAdapter<Rifornimento>(this, R.layout.listviewrow, rifornimenti); 
        
        ListView lview = (ListView) findViewById(R.id.listView1);  
        lview.setOnItemClickListener(this);
        lview.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) { 
		Rifornimento rif = (Rifornimento) adapter.getItemAtPosition(pos);  
		
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra("RIFORNIMENTO", rif);
		startActivity(intent);
	}      
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast toast;
		
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		NavUtils.navigateUpFromSameTask(this);
	    	return true;
	    		
	    	case R.id.action_add:
	    		Intent intent = new Intent(this, AddActivity.class);
	    		if (rifornimenti.size() > 0) {
	    			Rifornimento r = rifornimenti.get(rifornimenti.size() - 1);
	    			intent.putExtra("kmPrec", r.getKilometri());
	    		} else intent.putExtra("kmPrec", 0);
	    		startActivity(intent);
	    	return true;
	    	
	    	case R.id.action_stat:
	    		toast = Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT);
	    		toast.show();
	    	return true;
	    	
	    	case R.id.action_help:
	    		toast = Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT);
	    		toast.show();
	    	return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
