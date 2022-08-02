package com.example.demo.repository;

import com.example.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByArchivedFalse();

    Optional<Company> findByIdAndArchivedFalse(Long id);

    Optional<Company> findByVatAndArchivedFalse(String vat);
}
