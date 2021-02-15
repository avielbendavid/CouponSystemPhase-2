package coupon.system.core.couponExpirationDailyJob;

import java.util.TimerTask;

import org.springframework.stereotype.Component;
import coupon.system.core.services.DailyJobService;

/**
 * 
 * This class is a component - Spring will managed it - will create one instance
 * of this class on the first run of the system [ SingleTon - Eager] . this
 * class is actually extends TimerTask. therefore, it is required to implement
 * 'run' method. The purpose of this class is to give to the Timer the 'content'
 * - which it should actually run.
 * 
 * @author Aviel Ben-David
 */
@Component
public class CouponExpirationDailyJobTimerTask extends TimerTask {

	private int cycleNumber; // automatically initialized to [0] because it is primitive of type 'int'

//	private CouponRepository couponRepository;
	private DailyJobService dailyJobService;

	public CouponExpirationDailyJobTimerTask(DailyJobService dailyJobService) {
		super();
		this.dailyJobService = dailyJobService;
	}

	/**
	 * This method will use a Coupon Repository to perform deletion of all expired
	 * coupons of 'CouponSystem'.
	 */
	@Override
	public void run() {
		System.err.println(
				"\n\t************************************  Perform deletion of expired coupons ['CouponExpirationDailyJob' (cycle number: #"
						+ (++cycleNumber) + " ) ] ************************************\n");
		dailyJobService.deleteExpiredCoupons();
	}
}
