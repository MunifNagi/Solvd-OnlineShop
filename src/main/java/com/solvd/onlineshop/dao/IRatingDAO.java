package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.dao.mysql.MySQLDAO;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.Rating;

import java.util.List;

public interface IRatingDAO extends IBaseDAO<Rating>{
    List<Rating> getRatingsByProduct(long productId);
    List<Rating> getRatingsByReviewer(long reviewerId);
}
