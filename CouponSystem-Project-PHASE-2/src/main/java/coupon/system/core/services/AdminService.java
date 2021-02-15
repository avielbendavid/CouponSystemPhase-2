package coupon.system.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coupon.system.core.entities.Company;
import coupon.system.core.entities.Customer;
import coupon.system.core.exceptions.CouponSystemException;
import coupon.system.core.repositories.CompanyRepository;
import coupon.system.core.repositories.CustomerRepository;

@Service
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AdminService extends ClientService {

	private String adminEmail;
	private String adminPassword;

	private CompanyRepository companyRepository;
	private CustomerRepository customerRepository;

	public AdminService(CompanyRepository companyRepository, CustomerRepository customerRepository,
			@Value("${adminEmail}") String adminEmail, @Value("${adminPassword}") String adminPassword) {
		super();
		this.companyRepository = companyRepository;
		this.customerRepository = customerRepository;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}

	/**
	 * This method will check the entered Admin's Email address and Admin's
	 * password. if the entered details will match to the defined attributes from
	 * the 'admin details' properties file - this method will return 'true'.
	 * Otherwise this method will return 'false'.
	 * 
	 * @param email    - the email address of the administrator client.
	 * @param password - the password of the administrator client.
	 * 
	 * @return boolean - returns 'true' if entered values match to the defined
	 *         attributes from the 'admin details' properties file. if not correct-
	 *         returns 'false'.
	 */
	@Override
	public boolean login(String email, String password) {
		if (email.equals(adminEmail) && password.equals(adminPassword)) {
			return true;
		}
		return false;
	}

	/**
	 * This method will add a company. first, this method will check if the company
	 * name is already in use by another company. if yes - throws
	 * 'CouponSystemException'. if company name is not in use by another company
	 * ,this method will check if the company 'Email address' is already in use by
	 * another company.if yes - throws 'CouponSystemException'.if no - this method
	 * will add the desired company.
	 * 
	 * @param company - the new company that need to be added.
	 * @throws CouponSystemException (1). if there is already another company that
	 *                               using the same company name. (2). if there is
	 *                               already another company that using the same
	 *                               company email address.
	 */
	public void addCompany(Company company) throws CouponSystemException {
		if (companyRepository.existsByName(company.getName())) {
			throw new CouponSystemException("Can not add company '" + company.getName()
					+ "' because there is already another company that using the same company 'Name' ['"
					+ company.getName() + "'].");
		}
		if (companyRepository.existsByEmail(company.getEmail())) {
			throw new CouponSystemException("Can not add company '" + company.getName()
					+ "' because there is already another company that using the same company 'Email Address' ["
					+ company.getEmail() + "].");
		}
		companyRepository.save(company);
	}

	/**
	 * This method will update a company(will update the details : email address &
	 * password). first, checks if company exists. if no-throws
	 * 'CouponSystemException'.if exists-checks if the email address is already in
	 * use by another company.if yes- throws CouponSystemException. if no- this
	 * method will update the company details.
	 *
	 * @param company - the company that need to be updated.
	 * @throws CouponSystemException (1).if the company does not exists. (2).if the
	 *                               given company's email address is already in use
	 *                               by another company.
	 */
	public void updateCompany(Company company) throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(company.getId());
		if (opt.isPresent()) {
			Company companyFromDB = opt.get();
			if (!company.getEmail().equals(companyFromDB.getEmail())) {
				if (companyRepository.existsByEmail(company.getEmail())) {
					throw new CouponSystemException("Can not update Company '" + company.getName() + "' [id = "
							+ company.getId()
							+ "] because there is already another company that using the same company Email Address ['"
							+ company.getEmail() + "'].");
				}
			}
			companyFromDB.setEmail(company.getEmail());
			companyFromDB.setPassword(company.getPassword());
			return;
		}
		throw new CouponSystemException("Con not update company ['" + company.getName() + "' Id:" + company.getId()
				+ "] because the company does not exists.");
	}

	/**
	 * This method will delete a company. first - this method will check if the
	 * company exists by the given 'companyId' parameter. if exists - delete the
	 * company. if company does not exists - throws 'CouponSystemException'.
	 * 
	 * <br>
	 * <br>
	 * 
	 * Performing this method will result in the following steps: <br>
	 * 
	 * 1.The coupons created by the company will be deleted.<br>
	 * 
	 * 2.The purchase history of the company's coupons will be deleted.<br>
	 * 
	 * @param companyId - the Id of the company that need to be deleted.
	 * 
	 * @throws CouponSystemException if the company does not exists.
	 */
	public void deleteCompany(Integer companyId) throws CouponSystemException {
		if (companyRepository.existsById(companyId)) {
			companyRepository.deleteById(companyId);
			return;
		}
		throw new CouponSystemException(
				"Can not delete the company [id: " + companyId + "] because the company does not exists.");
	}

	/**
	 * This method will return a List of all existing companies.<br>
	 * in case the List is empty - returns an empty List.
	 * 
	 * @return List< Company> - returns a List of all existing companies.
	 */
	public List<Company> getAllCompanies() {
		List<Company> companies = companyRepository.findAll();
		return companies;
	}

	/**
	 * This method will return a specific company according to the given companyId
	 * parameter. first, this method will check if the company exists. if yes-
	 * returns the desired company.Otherwise throws 'CouponSystemException'.
	 * 
	 * @param companyId - the id of the desired company.
	 * @return Company - return the desired company according to the 'companyId'
	 *         parameter.
	 * @throws CouponSystemException if the company does not exists.
	 */
	public Company getOneCompany(Integer companyId) throws CouponSystemException {

		Optional<Company> opt = companyRepository.findById(companyId);
		if (opt.isPresent()) {
			Company currCom = opt.get();
			return currCom;
		}
		throw new CouponSystemException(
				"Can not get company [id: " + companyId + "] because the company does not exists.");
	}

	/**
	 * This method will add a customer to 'CouponSystem'. first, this method will
	 * check if there is already another customer that using the same customer
	 * 'Email Address'. if yes- this method will throws 'CouponSystemException'.if
	 * no- - this method will the the desired customer.
	 * 
	 * @param customer - the customer that need to be added to 'CouponSystem'.
	 * @throws CouponSystemException if there is already another customer that using
	 *                               the same customer 'Email Address'.
	 */
	public void addCustomer(Customer customer) throws CouponSystemException {
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new CouponSystemException("Can not add customer [name: '" + customer.getFirstName()
					+ "'] because there is already another customer that using the same customer Email Address ['"
					+ customer.getEmail() + "'].");
		}
		customerRepository.save(customer);
	}

	/**
	 * This method will update a customer (this method will not update
	 * 
	 * @param customer
	 * @throws CouponSystemException
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException {
		Optional<Customer> opt = customerRepository.findById(customer.getId());
		if (opt.isPresent()) {
			Customer currCus = opt.get();
			if (!customer.getEmail().equals(currCus.getEmail())) {
				if (customerRepository.existsByEmail(customer.getEmail())) {
					throw new CouponSystemException(
							"Can not update customer '" + customer.getFirstName() + "' [id: " + customer.getId()
									+ "] because there is already another customer that using the same Email address['"
									+ customer.getEmail() + "'].");
				}
			}
			currCus.setFirstName(customer.getFirstName());
			currCus.setLastName(customer.getLastName());
			currCus.setEmail(customer.getEmail());
			currCus.setPassword(customer.getPassword());

			return;
		}
		throw new CouponSystemException(
				"Can not update the customer '" + customer.getFirstName() + "' because customer does not exists.");
	}

	/**
	 * This method will delete a desired customer. first, this method will check if
	 * the customer exists. if yes- delete the customer. if not exists - throws
	 * 'CouponSystemException'.
	 * 
	 * Performing this method will result in the following steps:
	 * 
	 * 1.The customer's coupon purchase history will be deleted.
	 *
	 * 
	 * @param customerId - the id of the customer that need to be deleted.
	 * 
	 * @throws CouponSystemException if customer does not exists.
	 * 
	 */
	public void deleteCustomer(int customerId) throws CouponSystemException {
		Optional<Customer> opt = customerRepository.findById(customerId);
		if (opt.isPresent()) {
			customerRepository.deleteById(customerId);
			return;
		}

		throw new CouponSystemException(
				"Can not delete customer [id: " + customerId + "] because the customer does not exists.");
	}

	/**
	 * This method will return a List of all the existing customers.
	 * 
	 * @return List< Customer> - returns a List of all the existing customers.
	 */
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * This method will return a specific customer according to the customerId
	 * parameter. first, this method will check if the customer exists. if yes-
	 * returns the desired customer.Otherwise throws 'CouponSystemException'.
	 * 
	 * @param id - the id of the desired customer.
	 * @return Customer - return a specific customer according to the customerId
	 *         parameter.
	 * @throws CouponSystemException if the customer does not exists.
	 */
	public Customer getOneCustomer(int id) throws CouponSystemException {

		Optional<Customer> opt = customerRepository.findById(id);
		if (opt.isPresent()) {
			Customer currCustomer = opt.get();
			return currCustomer;
		}
		throw new CouponSystemException("Can not get customer [id: " + id + "] because customer does not exists.");
	}

}
