package unrn.isiii.test;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import unrn.isiii.model.tdd.Cuenta;
import unrn.isiii.model.tdd.RetirarException;
import unrn.isiii.model.tdd.TranferirOperacion;
import unrn.isiii.service.InteresService;
import unrn.isiii.service.TransferenciaServiceImpl;


public class CuentaTest {

	@Test
	public void testDepositarDineroCuenta100() {
		Cuenta cuenta = new Cuenta(0d);
		cuenta.depositar(100d);		
		Assert.assertEquals(100d, cuenta.getBalance(),0.001);
	}
	@Test
	public void testDepositarDineroCuenta200() {
		Cuenta cuenta = new Cuenta(0d);
		cuenta.depositar(200d);		
		Assert.assertEquals(200d, cuenta.getBalance(),0.001);
	}
	
	@Test
	public void testRetirarDineroCuenta() {
		Cuenta cuenta = new Cuenta(0d);
		cuenta.depositar(200d);
		cuenta.retirar(100d);	
		Assert.assertEquals(100d, cuenta.getBalance(),0.001);		
	}
	
	@Test(expected=RetirarException.class)
	public void testRetirarDineroCuentaException() {
		Cuenta cuenta = new Cuenta(0d);
		cuenta.depositar(200d);
		cuenta.retirar(300d);			
	}
	
	//@Test
	public void testTranferenciasEntreCuentas()
	{
		Cuenta cuentaA = new Cuenta(100d);
		Cuenta cuentaB = new Cuenta(100d);
		TransferenciaServiceImpl transferenciaService = new TransferenciaServiceImpl();
		TranferirOperacion operacion = new TranferirOperacion().desde(cuentaA).hacia(cuentaB).monto(50d);
		transferenciaService.transferir(operacion);
		
		Assert.assertEquals(50d, cuentaA.getBalance(),0.001);	
		Assert.assertEquals(150d, cuentaB.getBalance(),0.001);		
		
	}
	
	@Test
	public void testTranferenciasEntreCuentasConIntere()
	{
		Mockery contexto  = new Mockery();
		final InteresService interesService  = contexto.mock(InteresService.class);
		
		contexto.checking(new Expectations(){
			{
				oneOf(interesService).interesPorMonto(50d);
				will(returnValue(49d));
			}
		});		
		Cuenta cuentaA = new Cuenta(100d);
		Cuenta cuentaB = new Cuenta(100d);
		TransferenciaServiceImpl transferenciaService = new TransferenciaServiceImpl();
		transferenciaService.setInteresService(interesService);
		TranferirOperacion operacion = new TranferirOperacion().desde(cuentaA).hacia(cuentaB).monto(50d);
		transferenciaService.transferir(operacion);
		
		Assert.assertEquals(50d, cuentaA.getBalance(),0.001);	
		Assert.assertEquals(149d, cuentaB.getBalance(),0.001);
		contexto.assertIsSatisfied();		
	}
	
	

}
