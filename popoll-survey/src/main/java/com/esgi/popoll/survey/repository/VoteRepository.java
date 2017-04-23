package com.esgi.popoll.survey.repository;

import com.esgi.popoll.survey.entity.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {}