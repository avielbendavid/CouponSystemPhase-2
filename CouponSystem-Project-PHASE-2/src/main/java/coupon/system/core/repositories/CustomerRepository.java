package coupon.system.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import coupon.system.core.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public abstract boolean existsByEmail(String email);

	public abstract boolean existsByEmailAndPassword(String email, String password);

	@Query(value = "select id from customers where email=?1 AND password=?2", nativeQuery = true)
	public abstract Integer getCustomerId(String email, String password);

	public abstract boolean existsByIdAndCouponsId(int customerId, int couponId);

}
