package coupon.system.core.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coupon.system.core.repositories.CouponRepository;

@Service
@Transactional
public class DailyJobService {

	private CouponRepository couponRepository;

	public DailyJobService(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}

	public void deleteExpiredCoupons() {
		this.couponRepository.deleteByEndDateBefore(LocalDate.now());
	}
}
