package coupon.system.core.test.by.client.type;

import org.springframework.stereotype.Component;

import coupon.system.core.entities.Coupon;
import coupon.system.core.enums.Category;
import coupon.system.core.enums.ClientType;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.repositories.CouponRepository;
import coupon.system.core.services.CustomerService;

@Component
public class TestCustomer extends TestMaster {

	private CouponRepository couponRepository;

	public TestCustomer(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}

	public void testAll() {

// --------------------------------Log-in method #1--------------------------------

		CustomerService customerService = (CustomerService) loginManager.login(ClientType.CUSTOMER, "reuven@gmail.com",
				"4567788");
		if (customerService != null) {

			System.out.println(">>>>>>>>>>>>> CUSTOMER CLIENT - IS CONNECTED TO 'COUPON_SYSTEM' SUCCESSFULLY.");

// --------------------------------purchase coupon - method #2--------------------------------

			Coupon coupon1 = couponRepository.findById(3).get();
			try {
				customerService.purchaseCoupon(coupon1);
				System.out.println(">>>>>>>>>>>>> CUSTOMER CLIENT - PURCHASE COUPON [ID:3] SUCCESSFULLY");

			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

// --------------------------------get all customer coupons - method #3--------------------------------

			System.out.println(">>>>>>>>>>>>> CUSTOMER CLIENT - All CUSTOMER COUPONS PURCHASES : "
					+ customerService.getCustomerCoupons());

// --------------------------------get all customer coupons with specific category- method #4--------------------------------

			System.out
					.println(">>>>>>>>>>>>> CUSTOMER CLIENT - All CUSTOMER COUPONS PURCHASES WITH SPECIFIC CATEGORY : "
							+ customerService.getCustomerCoupons(Category.Cell_Phone));

// --------------------------------get all customer coupons with max price up to the max prices- method #5--------------------------------

			System.out.println(
					">>>>>>>>>>>>> CUSTOMER CLIENT - All CUSTOMER COUPONS PURCHASES WITH PRICE UP TO THE MaxPrice [70]: "
							+ customerService.getCustomerCoupons(Double.valueOf(70)));

// --------------------------------get customer details - method #6--------------------------------

			System.out.println(
					">>>>>>>>>>>>> CUSTOMER CLIENT - CUSTOMER DETAILS : " + customerService.getCustomerDetails());

		} else {
			System.out.println("CUSTOMER Client Log-in : Failed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			;
		}

	}
}
