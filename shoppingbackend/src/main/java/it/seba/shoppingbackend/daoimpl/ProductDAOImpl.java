package it.seba.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.seba.shoppingbackend.dao.ProductDAO;
import it.seba.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory ;

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#get(int)
	 * Gettin a single product based on productId
	 */
	
	@Override
	public Product get(int productId) {
		try {
			return sessionFactory
					.getCurrentSession()
						.get(Product.class, Integer.valueOf(productId));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#list()
	 * LIST
	 */
	
	@Override
	public List<Product> list() {
		try {
			return sessionFactory
					.getCurrentSession()
						.createQuery("FROM Product", Product.class)
							.getResultList();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#add(it.seba.shoppingbackend.dto.Product)
	 * INSERT 
	 */
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory
				.getCurrentSession()
					.persist(product);
			return true;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#update(it.seba.shoppingbackend.dto.Product)
	 * UPDATE
	 */
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory
				.getCurrentSession()
					.update(product);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#delete(it.seba.shoppingbackend.dto.Product)
	 * DELETE: set the active field to false
	 */

	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			// call update method
			return this.update(product);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#listActiveProducts()
	 * List of all Active Products
	 */
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts, Product.class)
						.setParameter("active", true)
							.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#listActiveProductsByCategory(int)
	 * List of active products by categoryId
	 */
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory, Product.class)
						.setParameter("active", true)
						.setParameter("categoryId", categoryId)
							.getResultList();		
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.ProductDAO#getLatestActiveProducts(int)
	 * List of latest n products
	 */
	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
							.setMaxResults(count)
								.getResultList();		
	}

}
