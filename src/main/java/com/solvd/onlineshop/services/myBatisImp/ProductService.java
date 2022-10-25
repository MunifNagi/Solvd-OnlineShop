package com.solvd.onlineshop.services.myBatisImp;

import com.solvd.onlineshop.dao.IPaymentDAO;
import com.solvd.onlineshop.dao.IProductDAO;
import com.solvd.onlineshop.entities.Payment;
import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.services.IProductService;
import com.solvd.onlineshop.services.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductService implements IProductService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);
    private final static SqlSessionFactory sessionFactory = MyBatisFactory.getSessionFactory();

    @Override
    public Product getProductByID(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            Product product = productDAO.getByID(id);
            if (product == null) {
                logger.error("Product with id " + id + " wasn't found!");
            }
            return product;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (SqlSession session = sessionFactory.openSession()) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            return productDAO.getAllProduct();
        }
    }

    @Override
    public void removeProduct(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            try {
                productDAO.remove(id);
                session.commit();
                logger.info("Product was removed Successfully");
            } catch (Exception e) {
                logger.error("Removing product with id " + id + " wasn't successful", e);
                session.rollback();
            }
        }
    }

    @Override
    public void createProduct(Product product) {
        try (SqlSession session = sessionFactory.openSession()) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            try {
                productDAO.create(product);
                session.commit();
                logger.info("Product was inserted Successfully");
            } catch (Exception e) {
                logger.error("Inserting Product ", e);
                session.rollback();
            }
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (SqlSession session = sessionFactory.openSession()) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            try {
                productDAO.update(product);
                session.commit();
                logger.info("product was updated Successfully");
            } catch (Exception e) {
                logger.error("Updating product wasn't successful", e);
                session.rollback();
            }
        }

    }

    @Override
    public List<Product> getProductByCategoryId(long categoryID) {
        try (SqlSession session = sessionFactory.openSession()) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            return productDAO.getProductByCategoryId(categoryID);
        }
    }

    @Override
    public List<Product> getProductByManufacturerId(long manufacturerId) {
        try (SqlSession session = sessionFactory.openSession()) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            return productDAO.getProductByManufacturerId(manufacturerId);
        }
    }
}
