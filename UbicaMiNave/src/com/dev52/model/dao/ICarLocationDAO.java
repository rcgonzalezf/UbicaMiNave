package com.dev52.model.dao;

import java.io.Serializable;
import java.util.List;

import com.dev52.model.CarLocationVO;

public interface ICarLocationDAO extends Serializable {
	
	int insertCarLocation(CarLocationVO vo);

	boolean deleteCarLocation(CarLocationVO vo);

	CarLocationVO findCarLocation(CarLocationVO vo);

	boolean updateCarLocation(CarLocationVO vo);

	List<CarLocationVO> selectCarLocations(CarLocationVO vo);

	boolean deleteCarLocations(List<CarLocationVO> vos);

}
