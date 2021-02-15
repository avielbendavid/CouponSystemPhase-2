package coupon.system.operations.after.system.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import coupon.system.core.couponExpirationDailyJob.CouponExpirationDailyJobTimer;

@Order(value = 3)
@Component
public class StartingDailyJob implements CommandLineRunner {

	private CouponExpirationDailyJobTimer couponExpirationDailyJobTimer;

	public StartingDailyJob(CouponExpirationDailyJobTimer couponExpirationDailyJobTimer) {
		super();
		this.couponExpirationDailyJobTimer = couponExpirationDailyJobTimer;
	}

	@Override
	public void run(String... args) throws Exception {
		couponExpirationDailyJobTimer.startJob();
	}

}