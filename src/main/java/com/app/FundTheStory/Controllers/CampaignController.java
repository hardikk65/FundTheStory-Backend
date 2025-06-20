package com.app.FundTheStory.Controllers;


import com.app.FundTheStory.Entities.Campaign;
import com.app.FundTheStory.DTO.CampaignSubmissionRequest;
import com.app.FundTheStory.Services.CampaignService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    CampaignService campaignService;


    @GetMapping({"", "/"})
    public List<Campaign> getCampaigns(){ // Display All listed Campaigns
        return campaignService.getCampaigns();
    }

    @PostMapping("/addCampaign")
    public ResponseEntity<?> addCampaign(@Valid @RequestBody CampaignSubmissionRequest submissionRequest){ // Create a New Campaign... This method requires the user to be Logged in
        campaignService.addCampaign(submissionRequest);
        return ResponseEntity.ok("Success!");
    }


    @GetMapping("/filterByCategory")
    public ResponseEntity<List<Campaign>> filterByCategory(@RequestParam(required = false) String category){
        List<Campaign> campaigns = campaignService.filterByCategory(category);

        if(campaigns.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(campaigns);
    }
    @GetMapping("/filterByPrice")
    public ResponseEntity<List<Campaign>> filterByPrice(@RequestParam(required = false) Double minAmount,
                                                        @RequestParam(required = false) Double maxAmount){

        if(minAmount == null || maxAmount == null){
            return ResponseEntity.ok(campaignService.getCampaigns());
        }

        List<Campaign> campaigns = campaignService.filterByPrice(minAmount, maxAmount);

        if(campaigns.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/supportStory/{id}")
    public ResponseEntity<Campaign> getById(@PathVariable Long id) {
        Optional<Campaign> campaignOpt = campaignService.getById(id);
        return campaignOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
