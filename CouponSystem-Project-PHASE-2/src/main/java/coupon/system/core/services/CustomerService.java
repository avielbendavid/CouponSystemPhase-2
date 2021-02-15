package coupon.system.core.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coupon.system.core.entities.Coupon;
import coupon.system.core.entities.Customer;
import coupon.system.core.enums.Category;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.repositories.CouponRepository;
import coupon.system.core.repositories.CustomerRepository;

@Service
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerService extends ClientService {

	/**
	 * This primitive instance variable - 'customerId'(int)- is the Id of the
	 * 'CUSTOMER' client that Logged-in to 'CouponSystem'. if 'CUSTOMER' client
	 * performed Log-in successfully the variable [customerId] will initialize to
	 * the customer Id of the customer that logged-in to the 'CouponSystem'.
	 */
	private int customerId;

	private CustomerRepository customerRepository;
	private CouponRepository couponRepository;

	public CustomerService(CustomerRepository customerRepository, CouponRepository couponRepository) {
		super();
		this.customerRepository = customerRepository;
		this.couponRepository = couponRepository;
	}

	@Override
	public boolean login(String email, String password) {

		if (customerRepository.existsByEmailAndPassword(email, password)) {
			this.customerId = customerRepository.getCustomerId(email, password);
			return true;
		}
		return false;
	}

	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {

		if (!customerRepository.existsByIdAndCouponsId(this.customerId, coupon.getId())) {

			if (coupon.getAmount() == 0) {
				throw new CouponSystemException("Can not purchase coupon ['" + coupon.getTitle() + "' id:"
						+ coupon.getId() + "'] because amount = 0 [out of stock] .");
			}
			if (coupon.getEndDate().isBefore(LocalDate.now())) {
				throw new CouponSystemException("Can not purchase coupon ['" + coupon.getTitle() + "' id:"
						+ coupon.getId() + "'] because the coupon has expired.");
			}
			Coupon coupon1 = couponRepository.findById(coupon.getId()).get();
			Customer currCustomer = customerRepository.findById(customerId).get();
			currCustomer.addCoupon(coupon1);
			coupon1.setAmount(coupon.getAmount() - 1);

		} else {
			throw new CouponSystemException("Can not purchase coupon ['" + coupon.getTitle() + "' id:" + coupon.getId()
					+ "'] because customer [id:" + customerId + "] has already purchase this coupon.");
		}
	}

	public List<Coupon> getCustomerCoupons() {
		return couponRepository.findByCustomersId(this.customerId);
	}

	public List<Coupon> getCustomerCoupons(Category category) {
		return couponRepository.findByCustomersIdAndCategory(this.customerId, category);
	}

	public List<Coupon> getCustomerCoupons(Double maxPrice) {
		return couponRepository.findByCustomersIdAndPriceLessThanEqual(this.customerId, maxPrice);
	}

	public Customer getCustomerDetails() {
		return customerRepository.findById(this.customerId).get();
	}

}
