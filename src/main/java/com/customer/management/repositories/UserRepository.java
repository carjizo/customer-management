package com.customer.management.repositories;

import com.customer.management.dtos.MetricsDTO;
import com.customer.management.dtos.UserWithEstimatedDeathDateResponseDTO;
import com.customer.management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdIdent(String idIdent);

    @Query(value = "SELECT \n" +
            "    CAST(COUNT(*) as INTEGER) AS total_users,\n" +
            "    CAST(MIN(age) as INTEGER) AS minimum_age,\n" +
            "    CAST(MAX(age) as INTEGER) AS maximum_age,\n" +
            "    CAST(AVG(age) as INTEGER) AS average_age,\n" +
            "    CAST(ROUND(STDDEV(age), 4) AS DOUBLE PRECISION) AS standard_deviation_age\n" +
            "FROM \n" +
            "    public.users\n" +
            "WHERE \n" +
            "    age IS NOT NULL\n" +
            "    AND status = true", nativeQuery = true)
    MetricsDTO getMetrics();

    @Query(value = "SELECT id_ident, full_name, age, birthdate, \n" +
            "    TO_CHAR(TO_DATE(birthdate, 'DD-MM-YYYY') + (?1 || ' years')::INTERVAL, 'DD-MM-YYYY') AS estimated_death_date \n" +
            "FROM public.users " +
            "WHERE status = true",  nativeQuery = true)
    List<UserWithEstimatedDeathDateResponseDTO> listUserWithEstimatedDeathDate(String lifeExpectancy);
}
