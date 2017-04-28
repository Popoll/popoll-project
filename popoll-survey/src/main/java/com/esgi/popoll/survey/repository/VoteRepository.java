package com.esgi.popoll.survey.repository;

import com.esgi.popoll.survey.entity.survey.Survey;
import com.esgi.popoll.survey.entity.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    @Query("select v from Vote v where v.userId=:userid and v.survey=:survey")
    Optional<Vote> findUserVote(@Param("survey") Survey survey, @Param("userid") String userId);
}
