package com.app.FundTheStory.Controllers;


import com.app.FundTheStory.Entities.Campaign;
import com.app.FundTheStory.DTO.CampaignSubmissionRequest;
import com.app.FundTheStory.Entities.PaymentOrder;
import com.app.FundTheStory.Services.CampaignService;
import com.app.FundTheStory.Services.PaymentService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @Autowired
    PaymentService paymentService;

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

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody PaymentOrder orderDetails){
        try {
            String serviceOrder = paymentService.createOrder(orderDetails);
            // Parse the string to a JSONObject to extract the order id
            JSONObject orderJson = new JSONObject(serviceOrder);
            String orderId = orderJson.getString("id");
            // Return a proper JSON object
            return ResponseEntity.ok("{\"orderId\": \"" + orderId + "\"}");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error Creating Order\"}");
        }
    }

    @PostMapping("/update-order")
    public ResponseEntity<String> updateOrderRequest(@RequestParam String orderId,
                                                     @RequestParam String status){
        paymentService.updateOrderStatus(orderId,status);
        return ResponseEntity.ok("Order Updated Successfully");
    }

}
