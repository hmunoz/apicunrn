package unrn.isiii.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import unrn.isiii.dao.IDepartamentoDao;
import unrn.isiii.model.Departamento;
import unrn.isiii.model.Provincia;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DepartamentoTest {

	@Autowired
	private IDepartamentoDao iDepartamentoDao;
	
	private Departamento departamento;
	
	@Before
	public void init()
	{
		departamento = new Departamento("Adolfo Alsina", new Provincia("Rio Negro"),8500);
	}

	@Test
	public void Crear() {		
		iDepartamentoDao.create(departamento);
		Assert.assertEquals("Adolfo Alsina",
				iDepartamentoDao.find(departamento.getId()).getDescripcion());
	}

	@Test
	public void Actualizar() {
		iDepartamentoDao.create(departamento);
		departamento.setDescripcion("Otro departamento");
		iDepartamentoDao.update(departamento);
		Assert.assertEquals("Otro departamento",
				iDepartamentoDao.find(departamento.getId()).getDescripcion());
	}

	@Test
	public void Eliminar() {
		iDepartamentoDao.create(departamento);
		iDepartamentoDao.delete(departamento.getId());
		Assert.assertNull(iDepartamentoDao.find(departamento.getId()));
	}
}
