package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.Report;

import java.util.List;

public interface IReportDAO extends IBaseDAO<Report>  {
    List<Report> getAllReports();
    List<Report> getAllReportsByUserId(long userId);
    List<Report> getAllReportsByProductId(long productId);
}
