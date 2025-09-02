package com.example.demo.repository;

import com.example.demo.entities.VoteOption;
import org.springframework.data.repository.CrudRepository;

public interface VoteOptionRepository extends CrudRepository<VoteOption,Long> {
}
