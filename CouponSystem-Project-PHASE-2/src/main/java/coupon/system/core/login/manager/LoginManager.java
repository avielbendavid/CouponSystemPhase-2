package coupon.system.core.login.manager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import coupon.system.core.enums.ClientType;
import coupon.system.core.services.AdminService;
import coupon.system.core.services.ClientService;
import coupon.system.core.services.CompanyService;
import coupon.system.core.services.CustomerService;

@Component
public class LoginManager implements ApplicationContextAware {

	private static ApplicationContext ctx;


// *************************************************************************  Login Manager  *****************************************************************************
	/**
	 * This method will check the request from the client that asking to log-in to
	 * 'CouponSystem'. This method will check the details that entered ('Email
	 * address'&'password') according to the ClientType. if the details are correct - this method return the appropriate clientService. if the
	 * details are not correct - this method will return 'NULL'.
	 * 
	 * This method will checks the details using 'switch case' Statement. according
	 * to the case [ClientType] - this method will check the details via the appropriate class method:
	 * 
	 * 1.for ADMINISTRATOR request : using login() method from AdminService
	 * class.
	 * 
	 * 2.for COMPANY request : using login() method from CompanyService class.
	 * 
	 * 3.for CUSTOMER request : using login() method from CustomerService
	 * class.
	 * 
	 * 
	 * @param clientType - the type of the client that ask to log-in to
	 *                   'CouponSystem'.
	 * @param email      - the email address of the client that ask to log-in to
	 *                   'CouponSystem'.
	 * @param password   - the password of the client that ask to log-in to
	 *                   'CouponSystem'.
	 * @return ClientService - returns the desired service :
	 *         ADMINISTRATOR,COMPANY,CUSTOMER according to the entered details. if
	 *         details are wrong (does not match)- method returns 'NULL'.
	 */
	public ClientService login(ClientType clientType, String email, String password) {
		
		ClientService clientService = null;

		switch (clientType) {

		case ADMIN:
			AdminService adminService = ctx.getBean(AdminService.class);
			if (adminService.login(email, password)) {
				clientService = adminService;
			}
			break;
		case COMPANY:
			CompanyService companyService = ctx.getBean(CompanyService.class);
			if (companyService.login(email, password)) {
				clientService = companyService;
			}
			break;
		case CUSTOMER:
			CustomerService customerService = ctx.getBean(CustomerService.class);
			if (customerService.login(email, password)) {
				clientService = customerService;
			}
			break;
		}
		return clientService;
	}

// ***********************************************************************************************************************************************************************

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;

	}
}
