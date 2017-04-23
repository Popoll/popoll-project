package com.esgi.popoll.survey.repository;

import com.esgi.popoll.survey.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Julien on 23/04/2017.
 */
public interface VoteRepository extends JpaRepository<Vote,Long>{}
