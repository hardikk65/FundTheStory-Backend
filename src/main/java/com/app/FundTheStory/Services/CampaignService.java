package com.app.FundTheStory.Services;


import com.app.FundTheStory.Entities.Campaign;
import com.app.FundTheStory.DTO.CampaignSubmissionRequest;
import com.app.FundTheStory.Repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    public List<Campaign> getCampaigns(){
        return campaignRepository.findAll();
    }

    public void addCampaign(CampaignSubmissionRequest submissionRequest){
        Campaign campaign = submissionRequest.getCampaign();
        campaignRepository.save(campaign);
    }

    public List<Campaign> filterByCategory(String category){
        List<Campaign> campaigns = campaignRepository.findByCategory(category);
        return campaigns;
    }

    public List<Campaign> filterByPrice(double minAmount,double maxAmount){
        List<Campaign> campaigns = campaignRepository.findByGoalAmountBetween(minAmount,maxAmount);
        return campaigns;
    }

    public Optional<Campaign> getById(Long id){
        return campaignRepository.findById(id);
    }
}
