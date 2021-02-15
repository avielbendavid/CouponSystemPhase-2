package coupon.system.operations.after.system.boot;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import coupon.system.core.entities.Company;
import coupon.system.core.entities.Coupon;
import coupon.system.core.entities.Customer;
import coupon.system.core.enums.Category;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.repositories.CustomerRepository;
import coupon.system.core.services.AdminService;
import coupon.system.core.services.CompanyService;

@Order(value = 2)
@Component
public class PopulateDBAfterSystemBoot implements CommandLineRunner {

	private CompanyService companyService;
	private AdminService adminService;
	private CustomerRepository customerRepository;

	public PopulateDBAfterSystemBoot(CompanyService companyService, AdminService adminService,
			CustomerRepository customerRepository) {
		super();
		this.companyService = companyService;
		this.adminService = adminService;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		LocalDate d1 = LocalDate.of(1999, 5, 6);
		LocalDate d2 = LocalDate.of(2020, 7, 9);
		LocalDate d3 = LocalDate.of(2012, 10, 20);
		LocalDate d4 = LocalDate.of(2013, 2, 8);
		LocalDate d5 = LocalDate.of(2020, 6, 14);
		LocalDate d6 = LocalDate.of(2022, 3, 3);
		LocalDate d7 = LocalDate.of(2005, 8, 9);
		LocalDate d8 = LocalDate.of(2011, 4, 14);

		Company com1 = new Company("Adidas", "adidas@gmail.com", "1234567");
		Company com2 = new Company("Nike", "nike@gmail.com", "1234567");
		Company com3 = new Company("Sharp", "sharp@mgail.com", "1234567");
		Company com4 = new Company("Tadiran", "tadiran@mgail.com", "1234567");
		Company com5 = new Company("Apple", "apple@mgail.com", "1234567");

		Coupon c1 = new Coupon(Category.Cell_Phone, "aaa", "vvv", d1, d2, Integer.valueOf(50), Double.valueOf(60),
				"10");
		Coupon c2 = new Coupon(Category.Cell_Phone, "bbb", "vvv", d3, d4, Integer.valueOf(50), Double.valueOf(60),
				"10");
		Coupon c3 = new Coupon(Category.Cell_Phone, "ccc", "vvv", d5, d6, Integer.valueOf(50), Double.valueOf(60),
				"10");
		Coupon c4 = new Coupon(Category.Cell_Phone, "ddd", "vvv", d1, d3, Integer.valueOf(50), Double.valueOf(60),
				"10");
		Coupon c5 = new Coupon(Category.Cell_Phone, "eee", "vvv", d7, d8, Integer.valueOf(50), Double.valueOf(60),
				"10");
		Coupon c6 = new Coupon(Category.Cell_Phone, "fff", "vvv", d5, d6, Integer.valueOf(50), Double.valueOf(60),
				"10");

		com1.addCoupon(c1);
		com1.addCoupon(c2);
		com2.addCoupon(c3);
		com2.addCoupon(c4);
		com2.addCoupon(c5);
		com5.addCoupon(c6);

		try {
			adminService.addCompany(com1);
			adminService.addCompany(com2);
			adminService.addCompany(com3);
			adminService.addCompany(com4);
			adminService.addCompany(com5);

		} catch (CouponSystemException e) {

			e.printStackTrace();
		}
		companyService.login("adidas@mail.com", "1234567");

		Customer cv1 = new Customer("Ronen", "Sepiashvili", "ronen@mail.com", "456456");
		Customer cv2 = new Customer("Merab", "Janashvili", "merab@mail.com", "789789");
		Customer cv3 = new Customer("Michael", "Shatashvili", "michael@mail.com", "898929");

		try {
			adminService.addCustomer(cv1);
			adminService.addCustomer(cv2);
			adminService.addCustomer(cv3);
		} catch (CouponSystemException e) {

			e.printStackTrace();
		}

		cv1.addCoupon(c1);
		cv1.addCoupon(c2);
		cv2.addCoupon(c3);
		cv2.addCoupon(c4);
		cv2.addCoupon(c5);

		customerRepository.save(cv1);
		customerRepository.save(cv2);

	}

}