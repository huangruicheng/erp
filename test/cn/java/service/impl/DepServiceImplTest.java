package cn.java.service.impl;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.java.model.Dep;
import cn.java.service.DepService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class DepServiceImplTest {

	@Autowired
	private DepService depService;
	
	@Test
	public void testSetDepDao() {
	
	
	}

	@Test
	public void testSaveDep() {
		Dep dep = new Dep();
		dep.setName("≤‚ ‘≤ø");
		dep.setTel("010-77777777");
		depService.save(dep);
	}

	@Test
	public void testUpdateDep() {
		Dep dep = depService.getObj(9);
		dep.setName("222");
		depService.update(dep);
	}

	@Test
	public void testGetDep() {
		Dep dep = depService.getObj(9);
		System.out.println(dep);
	}

	@Test
	public void testDeleteDep() {
		
	}

	@Test
	public void testQueryEmpByCondition() {
		
	}

}
