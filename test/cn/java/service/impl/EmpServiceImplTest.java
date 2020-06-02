package cn.java.service.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.java.model.Emp;
import cn.java.query.EmpQuery;
import cn.java.service.EmpService;
import cn.java.utils.Page;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class EmpServiceImplTest {

	@Autowired
	private EmpService empService;
	
	@Test
	public void testSetEmpDao() {
		Emp emp = new Emp();
		emp.setAddress("ÖÐÉ½");
		emp.setBirthday(new Date());
		emp.setDepId(1);
		emp.setEmail("1145855174@qq.com");
		emp.setEmpId(1);
		emp.setName("huangruicheng");
		emp.setPassword("123");
		emp.setTel("1111");
		emp.setUsername("huangrc");
		empService.save(emp);
	}

	@Test
	public void testSaveEmp() {
	
	}

	@Test
	public void testUpdateEmp() {
		Emp emp = empService.getObj(9);
		emp.setAddress("ÉÇÍ·");
		empService.update(emp);
	}

	@Test
	public void testGetEmp() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmp() {
		empService.delete(10);
	}

	@Test
	public void testQueryEmpByCondition() {
		EmpQuery equery = new EmpQuery();
		equery.setPageNo(2);
		List<String> exclude = new ArrayList<String>();
		exclude.add("pageNo");
		exclude.add("startNum");
		Page page = empService.queryObjByCondition(equery, exclude);
		System.out.println(page.getTotalPage());
		System.out.println(page.getStartNum());
		System.out.println(page.getPageSize());
		System.out.println(page.getPageNo());
	}

}
