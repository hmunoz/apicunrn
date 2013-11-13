package unrn.isiii.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import unrn.isiii.dao.ICuotaDao;
import unrn.isiii.model.state.Cuota;
import unrn.isiii.model.state.CuotaEstado;


@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CuotaTest {
	
	Cuota cuota;
	
	@Autowired
	private ICuotaDao iCuotaDao;
		
	@Before
	public void init()
	{
		cuota = new Cuota();
		cuota.setDescripcion("test");
		cuota.setEstado(CuotaEstado.PENDEINTE);
		
	}
	
	@Test
	public void cambioEstado() {		
		iCuotaDao.create(cuota);		
		cuota.getEstado().pagar(cuota);
		iCuotaDao.update(cuota);
		
		Assert.assertEquals("Pagada",cuota.getEstado().nombre());		
	}
	
	
}
