package com.dev52.model.dao;

import android.content.Context;

public class SQLiteDAOFactory extends DAOFactory {

	private Context context;
	public SQLiteDAOFactory(Context context) {
		this.context = context;
	}

	@Override
	public ICarLocationDAO getCarLocationDAO() {
		SQLiteCarLocationDAO dao  = new SQLiteCarLocationDAO();
		dao.setContext(context);
		return dao;
	}

}
