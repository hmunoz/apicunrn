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
import unrn.isiii.dao.ISitioDao;
import unrn.isiii.model.Coordenada;
import unrn.isiii.model.Departamento;
import unrn.isiii.model.Propietario;
import unrn.isiii.model.Provincia;
import unrn.isiii.model.Sitio;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SitioTest {

	@Autowired
	private ISitioDao iSitioDao;
	
	@Autowired
	private IDepartamentoDao iDepartamentoDao;

	private Departamento departamento;
	private Sitio sitio;
	private Coordenada coordenada;
	private Propietario propietario;

	@Before
	public void init() {
		propietario = new Propietario("hmunoz","munoz","munozhoracio@gmail.com","342342");
		coordenada = new Coordenada();
		coordenada.setLatitud(12d);
		coordenada.setLongitud(12d);
		departamento = new Departamento("Adolfo Alsina",new Provincia("Rio Negro"),8500);
		departamento.setCoordenada(coordenada);
		sitio = new Sitio("sitio", departamento, coordenada, propietario);
	}

	@Test
	public void Crear() {
		iSitioDao.create(sitio);
		Assert.assertEquals("sitio", iSitioDao.find(sitio.getId()).getNombre());
		Assert.assertEquals("Adolfo Alsina", iSitioDao.find(sitio.getId())
				.getDepartamento().getDescripcion());
	}
	
	
	@Test
	public void Actualizar() {
		iSitioDao.create(sitio);
		sitio.setNombre("Otro nomnre");
		iSitioDao.update(sitio);
		Assert.assertEquals("Otro nomnre", iSitioDao.find(sitio.getId()).getNombre());
	}

	@Test
	public void Eliminar() {
		iSitioDao.create(sitio);
		iSitioDao.delete(sitio.getId());
		Assert.assertNull(iSitioDao.find(sitio.getId()));
	}

}
