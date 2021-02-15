package coupon.system.core.test.by.client.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coupon.system.core.login.manager.LoginManager;

@Component
public abstract class TestMaster {

	@Autowired
	protected LoginManager loginManager;

	public TestMaster() {

	}

}
