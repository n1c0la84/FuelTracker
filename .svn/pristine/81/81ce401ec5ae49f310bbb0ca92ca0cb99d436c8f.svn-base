package net.nicolanicodemo.fueltracker.persistence;

import java.util.ArrayList;
import java.util.List;

import net.nicolanicodemo.fueltracker.model.Rifornimento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class RifornimentiHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 9; //2510-16
	private static final String TABLE = "RIFORNIMENTI";
	private static final String DB_NAME = "rifornimenti.db";
	
	private SQLiteDatabase db;
	
	public RifornimentiHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
		this.db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("DbHelper", "onCreate executing...");
		
		//Creazione tabella
		String sql = String.format("CREATE TABLE %s (%s integer primary key autoincrement, data text, spesa real, "
						+ "litri real, kilometri integer, costounitario real, serbpieno integer, "
						+ "consumo100 real)", TABLE, BaseColumns._ID);
		
		try {
			db.execSQL(sql);
		} catch (SQLException e){
			Log.e("DbHelper", "CREATE TABLE exception");
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE if exists " + TABLE);
		this.onCreate(db);
	}
	
	public List<Rifornimento> doQuery() {
		Cursor c = null;
		String sql = "SELECT * from " + TABLE;

		try {
			c = db.rawQuery(sql, null);
		} catch (Exception e) {
			Log.e("MainActivity", "SELECT exception");
			e.printStackTrace();
		}
		
		List<Rifornimento> rifornimenti = new ArrayList<Rifornimento>();
		
        while(c != null && c.moveToNext()) {
        	Rifornimento r = new Rifornimento();
        	
        	//Estrazione dati
        	r.setID(c.getInt(0));
        	r.setData(c.getString(1));
        	r.setSpesa(c.getFloat(2));
        	r.setLitri(c.getFloat(3));
        	r.setKilometri(c.getInt(4));
        	r.setCostoUnitario(c.getFloat(5));
        	r.setSerbatoioPieno((c.getInt(6) == 1) ? true : false);
        	r.setConsumo100(c.getFloat(7));
        	
        	rifornimenti.add(r);
        }
        c.close();
        
        return rifornimenti;
	}
	
	public void doInsert(ContentValues values) {
		try {
			db.insert(TABLE, null, values);
		} catch(Exception e) {
			Log.e("AddActivity", "db.insert() exception");
			e.printStackTrace();
		}
	}
	
}
