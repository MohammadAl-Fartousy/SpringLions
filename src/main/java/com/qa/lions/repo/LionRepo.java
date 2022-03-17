package com.qa.lions.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.lions.entity.Lion;

@Repository
public interface LionRepo extends JpaRepository<Lion, Long>{

}
