package com.ssafit.pjt.model.dao;

import java.util.Date;
import java.util.List;

import com.ssafit.pjt.model.dto.Reservation;

public interface CalenderDao {

	public List<Reservation> searchByIdAndMonth(Date date, int id);
}
