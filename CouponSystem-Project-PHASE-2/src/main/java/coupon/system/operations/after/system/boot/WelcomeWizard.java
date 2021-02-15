package coupon.system.operations.after.system.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class WelcomeWizard implements CommandLineRunner {
	
	@Override
	public void run(String... args) throws Exception {

		try {
			System.out.println(

					"\n\n" +

							"**********************************************************************************"
							+ "\n\n"
							+ ":::       ::: :::::::::: :::         ::::::::   ::::::::  ::::    ::::  :::::::::: \r\n"
							+ ":+:       :+: :+:        :+:        :+:    :+: :+:    :+: +:+:+: :+:+:+ :+:        \r\n"
							+ "+:+       +:+ +:+        +:+        +:+        +:+    +:+ +:+ +:+:+ +:+ +:+        \r\n"
							+ "+#+  +:+  +#+ +#++:++#   +#+        +#+        +#+    +:+ +#+  +:+  +#+ +#++:++#   \r\n"
							+ "+#+ +#+#+ +#+ +#+        +#+        +#+        +#+    +#+ +#+       +#+ +#+        \r\n"
							+ " #+#+# #+#+#  #+#        #+#        #+#    #+# #+#    #+# #+#       #+# #+#        \r\n"
							+ "  ###   ###   ########## ##########  ########   ########  ###       ### ########## "

							+ "\n\n"

							+ "**********************************************************************************"

							+ "\n\n");
			System.err.println("Hello Eldar, My Project will start running in 5 seconds...");
			for (int i = 0; i < 5; i++) {
				System.err.println("********** second : " + i + " **********");
				Thread.sleep(1000);
			}
		} catch (Exception e) {

		}

	}

}