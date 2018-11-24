package com.volvo.plates.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.volvo.plates.domain.PlatesOrder;
import com.volvo.plates.dto.PlatesOrderDTO;

public interface PlatesOrderRepository extends CrudRepository<PlatesOrder, String> {

	@Query("SELECT COUNT(o) FROM PlatesOrder o WHERE o.lastUsedDateAndTime BETWEEN ?1 AND ?2")
	int getCountByLastUsedDateTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);
	@Query("SELECT SUM(ordersBreakFast) FROM PlatesOrder o WHERE o.lastUsedDateAndTime BETWEEN ?1 AND ?2")
	int getBreakFastSumByLastUsedDateTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);	
	@Query("SELECT SUM(ordersLunch) FROM PlatesOrder o WHERE o.lastUsedDateAndTime BETWEEN ?1 AND ?2")
	int getLunchSumByLastUsedDateTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);
	@Query("SELECT SUM(ordersMidDayMeal) FROM PlatesOrder o WHERE o.lastUsedDateAndTime BETWEEN ?1 AND ?2")
	int getMidDayMealSumByLastUsedDateTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);
	@Query("SELECT SUM(ordersOtSnacks) FROM PlatesOrder o WHERE o.lastUsedDateAndTime BETWEEN ?1 AND ?2")
	int getOtSnacksSumByLastUsedDateTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);
	@Query("SELECT SUM(ordersDinner) FROM PlatesOrder o WHERE o.lastUsedDateAndTime BETWEEN ?1 AND ?2")
	int getDinnerSumByLastUsedDateTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);	
	PlatesOrderDTO save(PlatesOrderDTO platesFinalOrder);
	PlatesOrderDTO findByEmployeeId(String empId);
	void deleteByEmployeeId(String employeeId);
}
