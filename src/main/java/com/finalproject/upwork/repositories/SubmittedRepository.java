package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.SubmittedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedRepository extends JpaRepository<SubmittedModel, Long>{
}
