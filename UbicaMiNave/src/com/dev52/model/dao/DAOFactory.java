package com.dev52.model.dao;

import android.content.Context;

public abstract class DAOFactory {

	public static final int SQLITE = 1;

	public abstract ICarLocationDAO getCarLocationDAO();
	
	private static Context context;
	
	public DAOFactory(Context contextInit){
		context = contextInit;
	}
	
	public DAOFactory(){}
	
	public static DAOFactory getDAOFactory(int concreteFactory) {

		switch (concreteFactory) {
		case SQLITE:
			return new SQLiteDAOFactory(context);
		default:
			return null;
		}
	}
}
