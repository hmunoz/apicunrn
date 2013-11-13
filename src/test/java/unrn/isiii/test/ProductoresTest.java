package unrn.isiii.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import unrn.isiii.dao.IPropietarioDao;
import unrn.isiii.model.Propietario;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductoresTest {
	
	Propietario  propietario;
	
	@Autowired
	private IPropietarioDao iPropietarioDao;
		
	@Before
	public void init()
	{
		propietario = new Propietario("hmunoz","munoz","munozhoracio@gmail.com","1234567891");
		
	}
	
	@Test
	public void remove() {		
		iPropietarioDao.create(propietario);
		iPropietarioDao.delete(propietario.getId());		
		Assert.assertEquals(0, iPropietarioDao.findAll().size());		
	}
	
	
	
	
	@Test
	public void update() {		
		iPropietarioDao.create(propietario);		
		propietario.setNombre("hmunoz");
		iPropietarioDao.update(propietario);
		Assert.assertEquals("hmunoz", iPropietarioDao.findAll().get(0).getNombre());		
	}
	
	@Test
	public void crear() {				
		iPropietarioDao.create(propietario);		
		Assert.assertNotNull(iPropietarioDao);
		Assert.assertEquals("hmunoz", iPropietarioDao.find(propietario.getId()).getNombre());
		Assert.assertEquals(1, iPropietarioDao.findAll().size());		
	}
	
	@Test
	public void list() {				
		for (int i = 0; i < 100; i++) {
			iPropietarioDao.create(new Propietario("test" + i,"sasd","munoz@asdasd.com","4234234"));			
		}
				
		Assert.assertEquals(100, iPropietarioDao.findAll().size());		
	}
	
	
}
