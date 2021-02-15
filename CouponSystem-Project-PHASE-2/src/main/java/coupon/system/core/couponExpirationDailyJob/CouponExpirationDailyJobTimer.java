package coupon.system.core.couponExpirationDailyJob;

import java.util.GregorianCalendar;
import java.util.Timer;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class CouponExpirationDailyJobTimer {

	private Timer timer;
	private CouponExpirationDailyJobTimerTask jobTimerTask;

	public CouponExpirationDailyJobTimer(CouponExpirationDailyJobTimerTask jobTimerTask) {
		super();
		this.jobTimerTask = jobTimerTask;
		this.timer = new Timer();
	}

	public void startCouponExpirationDailyJob() {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
//      in case the requirement is to delete the expired coupons in a certain hour of
//      a day we can use the following steps(that are now marked as comments [ Lines 41-44 ])

//		gregorianCalendar.set(Calendar.HOUR, 00);
//		gregorianCalendar.set(Calendar.MINUTE, 00);
//		gregorianCalendar.set(Calendar.SECOND, 00);
//		gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
		timer.scheduleAtFixedRate(jobTimerTask, gregorianCalendar.getTime(), 5_000);
	}

	public void startJob() {
		startCouponExpirationDailyJob();
	}

	@PreDestroy
	public void endJob() {
		System.err.println("\n\t************************************ " + Thread.currentThread().getName()
				+ " cancaled the 'CouponExpirationDailyJob' " + "************************************\n");
		timer.cancel();
	}

}
