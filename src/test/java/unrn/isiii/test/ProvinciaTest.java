package unrn.isiii.test;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import unrn.isiii.dao.IProvinciaDao;
import unrn.isiii.model.Provincia;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProvinciaTest {
	
	Provincia provincia;
	
	@Autowired
	private IProvinciaDao iProvinciaDao;
		
	@Before
	public void init()
	{
		provincia = new Provincia("Rio Negro");
		
	}
	
	@Test
	public void remove() {		
		iProvinciaDao.create(provincia);
		iProvinciaDao.delete(provincia.getId());		
		Assert.assertEquals(0, iProvinciaDao.findAll().size());		
	}
	
	@Test(expected  = ConstraintViolationException.class)
	public void caracteres() {		
		provincia = new Provincia("o");
		iProvinciaDao.create(provincia);			
	}
	
	
	@Test
	public void update() {		
		iProvinciaDao.create(provincia);		
		provincia.setDescripcion("Buenos Aires");
		iProvinciaDao.update(provincia);
		Assert.assertEquals("Buenos Aires", iProvinciaDao.findAll().get(0).getDescripcion());		
	}
	
	@Test
	public void crear() {				
		iProvinciaDao.create(provincia);		
		Assert.assertNotNull(iProvinciaDao);
		Assert.assertEquals("Rio Negro", iProvinciaDao.find(provincia.getId()).getDescripcion());
		Assert.assertEquals(1, iProvinciaDao.findAll().size());		
	}
	
	@Test
	public void list() {				
		for (int i = 0; i < 100; i++) {
			iProvinciaDao.create(new Provincia("test" + i));			
		}
				
		Assert.assertEquals(100, iProvinciaDao.findAll().size());		
	}
	
	
}
