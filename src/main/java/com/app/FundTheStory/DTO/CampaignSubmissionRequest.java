package com.app.FundTheStory.DTO;


import com.app.FundTheStory.Entities.Campaign;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CampaignSubmissionRequest{

    @NotNull(message = "Campaign must not be null")
    @Valid
    private Campaign campaign;

    public CampaignSubmissionRequest(){};

    public CampaignSubmissionRequest(Campaign campaign){
        this.campaign = campaign;
    }

    public Campaign getCampaign(){
        return campaign;
    }

    public void setCampaign(Campaign campaign){
        this.campaign = campaign;
    }

}
