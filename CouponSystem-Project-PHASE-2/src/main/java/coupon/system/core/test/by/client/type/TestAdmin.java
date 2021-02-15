package coupon.system.core.test.by.client.type;

import org.springframework.stereotype.Component;

import coupon.system.core.entities.Company;
import coupon.system.core.entities.Customer;
import coupon.system.core.enums.ClientType;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.services.AdminService;

@Component
public class TestAdmin extends TestMaster {

	public void testAll() {

// --------------------------------Log-in method #1--------------------------------
		AdminService adminService = (AdminService) this.loginManager.login(ClientType.ADMIN, "admin@admin.com",
				"admin");
		if (adminService != null) {
			System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - IS CONNECTED TO 'COUPON_SYSTEM' SUCCESSFULLY.");

// --------------------------------add new company - method #2--------------------------------
			Company company1 = new Company("ZARA", "zara@gmail.com", "1234567");
			try {
				adminService.addCompany(company1);
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - ADDED NEW COMPANY '" + company1.getName()
						+ "' SUCCESSFULLY.");
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

// --------------------------------update company - method #3--------------------------------
			try {
				Company company2 = adminService.getOneCompany(2);
				company2.setEmail("stamOdMail@gmail.com");
				adminService.updateCompany(company2);
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - UPDATED COMPANY '" + company2.getName()
						+ "' SUCCESSFULLY.");

			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

// --------------------------------delete company - method #4--------------------------------
			try {
				adminService.deleteCompany(1);
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - DELETED COMPANY [id:1] SUCCESSFULLY.");
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}

// --------------------------------get all companies - method #5--------------------------------
			System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - LIST OF ALL COMPANIES: >>>>>> "
					+ adminService.getAllCompanies());

// --------------------------------get one company - method #6--------------------------------
			try {
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT -  GET ONE COMPANY >>>>>> "
						+ adminService.getOneCompany(3));
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

//-------------------------------- add new customer - method #7--------------------------------
			Customer customer1 = new Customer("Reuven", "Mirel", "reuven@gmail.com", "4567788");
			try {
				adminService.addCustomer(customer1);
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - ADDED NEW CUSTOMER ["
						+ customer1.getFirstName() + "] SUCCESSFULLY");
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
//-------------------------------- update customer - method #8--------------------------------
			try {
				Customer customer2 = adminService.getOneCustomer(2);
				customer2.setFirstName("Ronen");
				adminService.updateCustomer(customer2);
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - UPDATED CUSTOMER [" + customer2.getFirstName()
						+ "] SUCCESSFULLY");
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
// --------------------------------delete customer - method #9--------------------------------
			try {
				adminService.deleteCustomer(1);
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - DELETED CUSTOMER [1] SUCCESSFULLY");
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
// --------------------------------get all customers - method #10--------------------------------
			System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - LIST OF ALL CUSTOMERS : >>>>>> "
					+ adminService.getAllCustomers());

// --------------------------------get one customer - method #11--------------------------------
			try {
				System.out.println(">>>>>>>>>>>>> ADMINISTRATOR CLIENT - GET ONE CUSTOMER : >>>>>> "
						+ adminService.getOneCustomer(3));
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Sorry, can not log-in the ADMINISTRATOR client to 'CouponSystem'");
		}

	}

}
