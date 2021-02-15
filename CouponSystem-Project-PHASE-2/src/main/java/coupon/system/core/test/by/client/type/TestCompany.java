package coupon.system.core.test.by.client.type;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import coupon.system.core.entities.Coupon;
import coupon.system.core.enums.Category;
import coupon.system.core.enums.ClientType;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.repositories.CouponRepository;
import coupon.system.core.services.CompanyService;

@Component
public class TestCompany extends TestMaster {

	private CouponRepository couponRepository;

	public TestCompany(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}

	public void testAll() {
		CompanyService companyService = (CompanyService) this.loginManager.login(ClientType.COMPANY, "apple@mgail.com",
				"1234567");

		// login COMPANY client- method #1
		if (companyService != null) {
			System.out.println(">>>>>>>>>>>>> COMPANY CLIENT - IS CONNECTED TO 'COUPON_SYSTEM' SUCCESSFULLY.");

			// add new coupon - method #2
			Coupon coupon1 = new Coupon(Category.Cell_Phone, "aaa", "Description", LocalDate.now(), LocalDate.now(),
					Integer.valueOf(70), Double.valueOf(90), "10");
			try {
				companyService.addCoupon(coupon1);
				System.out.println(
						">>>>>>>>>>>>> COMPANY CLIENT - ADDED NEW COUPON [" + coupon1.getTitle() + "] SUCCESSFULLY");
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

			// update a company coupon- method #3
			Coupon coupon2 = couponRepository.findById(6).get();
			coupon2.setDescription("NewDescription");
			try {
				companyService.updateCoupon(coupon2);
				System.out.println(
						">>>>>>>>>>>>> COMPANY CLIENT - UPDATED COUPON [" + coupon2.getTitle() + "] SUCCESSFULLY");
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

			// delete a coupon - method #4
			try {
				companyService.deleteCoupon(6);
				System.out.println(">>>>>>>>>>>>> COMPANY CLIENT - DELETED COUPON [ID:6] SUCCESSFULLY");
			} catch (CouponSystemException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			// get all company coupons - method #5
			System.out.println(
					">>>>>>>>>>>>> COMPANY CLIENT - All COMPANY COUPONS : " + companyService.getCompanyCoupons());

			// get all company coupons with specific category - method #6
			System.out.println(">>>>>>>>>>>>> COMPANY CLIENT - All COMPANY COUPONS WITH SPECIFIC CATEGORY : "
					+ companyService.getCompanyCoupons(Category.DesktopComputer));

			// get all company coupons with price up to MaxPrice - method #7
			System.out.println(">>>>>>>>>>>>> COMPANY CLIENT - All COMPANY COUPONS WITH PRICE UP TO MaxPrice [60]: "
					+ companyService.getCompanyCoupons(Double.valueOf(60)));

			// get company details - method #8
			System.out
					.println(">>>>>>>>>>>>> COMPANY CLIENT - COMPANY DETAILS : " + companyService.getCompanyDetails());
		} else {
			System.out.println("COMPANY Client Log-in : Failed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

}
