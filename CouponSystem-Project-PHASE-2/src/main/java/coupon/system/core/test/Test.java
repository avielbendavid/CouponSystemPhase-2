package coupon.system.core.test;

import org.springframework.stereotype.Component;

import coupon.system.core.test.by.client.type.TestAdmin;
import coupon.system.core.test.by.client.type.TestCompany;
import coupon.system.core.test.by.client.type.TestCustomer;

/**
 * The role of this class is to check all the 'CouponSystem' client business
 * logic methods.
 */
@Component
public class Test {

	private TestAdmin testAdmin;
	private TestCompany testCompany;
	private TestCustomer testCustomer;

	public Test(TestAdmin testAdmin, TestCompany testCompany, TestCustomer testCustomer) {
		super();
		this.testAdmin = testAdmin;
		this.testCompany = testCompany;
		this.testCustomer = testCustomer;
	}

	/**
	 * This method will check the business logic methods of all 'CouponSystem' clients.
	 * 
	 * This method will perform each of the business logic methods of the following:
	 * 
	 * 1.ADMINISTRATOR service.
	 * 
	 * 2.COMPANY service.
	 * 
	 * 3.CUSTOMER service.
	 */
	public void generalView() {

		testAdmin.testAll();
		testCompany.testAll();
		testCustomer.testAll();

	}
}
