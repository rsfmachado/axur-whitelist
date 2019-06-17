package com.axur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axur.model.RegexPattern;

@Repository
public interface ClientURLRepository extends JpaRepository<RegexPattern, Long>{

}
