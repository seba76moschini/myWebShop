package it.seba.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.seba.shoppingbackend.dao.ProductDAO;
import it.seba.shoppingbackend.dto.Product;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("it.seba.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
/*	
	@Test 
	public void testCRUDProduct() {
		
		// create operation
		product = new Product();
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description for oppo S53");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something went wrong while inserting a new product!", true, productDAO.add(product));
	
		// reading and updating the categoy
		product = productDAO.get(2);
		product.setName("SAmsung Galaxy S7");
		assertEquals("Something went wrong while updating the existing record!", true, productDAO.update(product));
		
		// deleting 
		assertEquals("Something went wrong while deleting the existing record!", true, productDAO.delete(product));

		// list
		assertEquals("Something went wrong while fetching a list of products!", 9, productDAO.list().size());

		

	}

*/
	
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of active products!", 8, productDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of active products by category!", 6, productDAO.listActiveProductsByCategory(3).size());
	}
	
	@Test
	public void testLatestActiveProducts() {
		assertEquals("Something went wrong while fetching the list of ltest products by category!", 5, productDAO.getLatestActiveProducts(5).size());
	}
}
