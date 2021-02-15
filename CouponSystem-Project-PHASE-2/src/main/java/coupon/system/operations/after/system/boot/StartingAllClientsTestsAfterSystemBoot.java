package coupon.system.operations.after.system.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import coupon.system.core.test.Test;

@Order(value = 4)
@Component
public class StartingAllClientsTestsAfterSystemBoot implements CommandLineRunner {

	private Test test;

	public StartingAllClientsTestsAfterSystemBoot(Test test) {
		super();
		this.test = test;
	}

	@Override
	public void run(String... args) throws Exception {
		test.generalView();
	}

}