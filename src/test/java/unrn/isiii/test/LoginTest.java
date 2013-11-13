package unrn.isiii.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unrn.isiii.model.tdd.LoginService;
import unrn.isiii.model.tdd.LoginServiceImpl;

public class LoginTest {

	Boolean login;

	@Before
	public void init() {

		login = false;
	}

	@Test
	public void loginTest() {
		LoginService loginService = new LoginServiceImpl();
		login = loginService.login("admin", "pass");
		Assert.assertEquals(true, login);
	}

}
