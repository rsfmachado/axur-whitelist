package com.axur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axur.model.RegexPattern;

@Repository
public interface ClientRegexRepository extends JpaRepository<RegexPattern, Long>{
	
	public List<RegexPattern> findByClientOrClientIsNull(String client);
}
