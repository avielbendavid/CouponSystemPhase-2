package coupon.system.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import coupon.system.core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	// I added some methods:

	public abstract Optional<Company> findByEmailAndPassword(String email, String password);

	public abstract boolean existsByName(String name);

	public abstract boolean existsByEmail(String email);

	public abstract boolean existsByEmailAndPassword(String email, String password);

	public abstract boolean existsByIdAndCouponsTitle(int companyId, String title);

	public abstract boolean existsByIdAndCouponsId(int companyId, int id);
	
}
