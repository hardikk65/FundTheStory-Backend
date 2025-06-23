package com.app.FundTheStory.Services;


import com.app.FundTheStory.Entities.PaymentOrder;
import com.app.FundTheStory.Repositories.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Value("${razorpay.key_id}")
    private String keyId;
    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    EmailService emailService;




    public String createOrder(PaymentOrder orderDetails) throws RazorpayException {

        RazorpayClient client = new RazorpayClient(keyId,keySecret);

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount",(int)(orderDetails.getAmount()*100));
        orderRequest.put("currency","INR");
        orderRequest.put("receipt","txn_"+ UUID.randomUUID());

        Order razorpayOrder = client.orders.create(orderRequest);

        orderDetails.setOrderId(razorpayOrder.get("id"));
        orderDetails.setStatus("CREATED");

        orderDetails.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(orderDetails);

        return razorpayOrder.toString();
    }


    public void updateOrderStatus(String orderId, String status){ // Status will be fetched from UI
        PaymentOrder order = paymentRepository.findByOrderId(orderId);
        order.setStatus(status);
        paymentRepository.save(order);

        if("SUCCESS".equalsIgnoreCase(status)){
            emailService.sendEmail(order.getEmail(),order.getName(),order.getCampaign(),order.getAmount());
            System.out.println("Email Sent Successfully");
        }
    }
}
