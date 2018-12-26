package it.seba.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.seba.shoppingbackend.dao.CategoryDAO;
import it.seba.shoppingbackend.dto.Category;


@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl  implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	private static List<Category> categories = new ArrayList<Category>();
	
	static {
		Category category = new Category();
		
		// adding first category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is description for television");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
		
		// adding second category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is description for Mobile");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);
		
		// adding third category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is description for Laptop");
		category.setImageURL("CAT_3.png");
		
		categories.add(category);
		
	}
	*/
	
	@Override
	public List<Category> list() {
		//return categories;
		
		String selectActiveCategory = "FROM Category WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);		
		
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.CategoryDAO#get(int)
	 * Gettin single category based on id
	 */
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
		//enchanced for loop
		/*
		for(Category category : categories) {
			if (category.getId() == id) return category;
		}
		return null;
		*/
	}

	@Override
	@Transactional
	public boolean add(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see it.seba.shoppingbackend.dao.CategoryDAO#update(it.seba.shoppingbackend.dto.Category)
	 * 
	 *  Updating a single category
	 */
	@Override
	public boolean update(Category category) {
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);

		try {
			// update the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	

}
