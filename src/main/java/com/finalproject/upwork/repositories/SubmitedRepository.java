package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.SubmitedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitedRepository extends JpaRepository<SubmitedModel , Long>{
}
