package com.dev52.model.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dev52.model.CarLocationVO;
import com.dev52.model.sqlite.CarLocations;
import com.dev52.model.sqlite.SQLiteDatabaseHelper;

public class SQLiteCarLocationDAO implements ICarLocationDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7420125877267274096L;

	private Context context;
	
	@Override
	public int insertCarLocation(CarLocationVO vo) {
		
		int rowId;
		
		SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(context);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		
		cv.put(CarLocations.LATITUDE, vo.getLatitude() );
		cv.put(CarLocations.LONGITUDE, vo.getLongitude() );
		
		SimpleDateFormat sdateFormat = new SimpleDateFormat(SQLiteDatabaseHelper.DATE_PATTERN);
		
		cv.put(CarLocations.CREATED_AT,
				sdateFormat.format(vo.getCreatedAt()) );
		
		cv.put(CarLocations.ADDRESS, vo.getAddress() );
		
		cv.put(CarLocations.ALTITUDE, vo.getAltitude() );
		
		rowId = (int) db.insert(CarLocations.TABLE_NAME,CarLocations.ADDRESS , cv);
		
		db.close();
		
		return rowId;
	}

	@Override
	public boolean deleteCarLocation(CarLocationVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CarLocationVO findCarLocation(CarLocationVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCarLocation(CarLocationVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CarLocationVO> selectCarLocations(CarLocationVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCarLocations(List<CarLocationVO> vos) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

}
