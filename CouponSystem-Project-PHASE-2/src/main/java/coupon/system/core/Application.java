package coupon.system.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = { "coupon.system.core", "coupon.system.operations.after.system.boot" })
@PropertySource({ "classpath:application.properties", "classpath:adminClient.properties" })
public class Application {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

			Thread.sleep(20_000); // I will give the thread a sleep for 20 seconds to give the daily job run
									// several cycles.

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
