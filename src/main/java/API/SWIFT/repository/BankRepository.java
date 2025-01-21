package API.SWIFT.repository;

import API.SWIFT.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findBySwiftCode(String swiftCode);

    @Query(value = "SELECT b FROM Bank b WHERE b.swiftCode LIKE CONCAT(:prefix, '%') and b.swiftCode NOT LIKE CONCAT(:prefix, '%XXX') ")
    List<Bank> findAllBranches(@Param("prefix") String prefix);

    List<Bank> findAllByCountryISO2(String countryISO2);
}
