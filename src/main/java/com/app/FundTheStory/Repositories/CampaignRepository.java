package com.app.FundTheStory.Repositories;


import com.app.FundTheStory.Entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {

    List<Campaign> findByCategory(String category);

    List<Campaign> findByGoalAmountBetween(double minAmount,double maxAmount);

    List<Campaign> findByEndDateAfter(LocalDate date);
}
