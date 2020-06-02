package cn.java.service.impl;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.java.model.Dep;
import cn.java.model.ProductType;
import cn.java.service.ProductTypeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class ProductTypeServiceImplTest {

	@Autowired
	private ProductTypeService productTypeService;
	
	@Test
	public void testSetDepDao() {
	
	
	}

	@Test
	public void testSaveDep() {
		ProductType pt = new ProductType();
		pt.setName("ÀºÇò");
		pt.setSupplierId(1);
		productTypeService.save(pt);
	}

	@Test
	public void testUpdateDep() {
		
	}

	@Test
	public void testGetDep() {
		ProductType pt = productTypeService.getObj(7);
		System.out.println(pt);
	}

	@Test
	public void testDeleteDep() {
		
	}

	@Test
	public void testQueryEmpByCondition() {
		
	}

}
