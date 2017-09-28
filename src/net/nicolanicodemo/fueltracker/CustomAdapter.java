package net.nicolanicodemo.fueltracker;

import java.util.List;

import net.nicolanicodemo.fueltracker.model.Rifornimento;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter<T> extends ArrayAdapter<T> {
	private Context context; 
    private int resource;    
    private List<T> objs;
    
    private View row;
    private RifornimentoRow rr;
    
	public CustomAdapter(Context context, int resource, List<T> objects) {
		super(context, resource, objects);
		this.context = context;
        this.resource = resource;
        this.objs = objects;
	}

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
    	row = view;
        
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            
            rr = new RifornimentoRow();
            rr.data = (TextView) row.findViewById(R.id.txtData);
            rr.spesa = (TextView) row.findViewById(R.id.txtSpesa);
            rr.consumo = (TextView) row.findViewById(R.id.txtConsumo);
            rr.prezzo = (TextView) row.findViewById(R.id.txtPrezzo);
            
            row.setTag(rr);
        }
        else 
            rr = (RifornimentoRow)row.getTag();
        
        Rifornimento r = (Rifornimento) objs.get(pos);
        rr.data.setText(r.getData());
        rr.spesa.setText(r.getSpesa() + " €");
        
        if (r.getConsumo100() > 0) {
        	double consumo = Math.round(r.getConsumo100() * 100)/100;
        	rr.consumo.setText(consumo + " l/100km  " + 100/consumo + " km/l");
        } else rr.consumo.setText("Non disponibile");
        
        double prezzo = Math.round(r.getCostoUnitario() * 1000);
        rr.prezzo.setText( prezzo/1000 + " €/l");
        
        return row;
    }
    
    static class RifornimentoRow {
        TextView data;
        TextView spesa;
        TextView consumo;
        TextView prezzo;
    }

}
