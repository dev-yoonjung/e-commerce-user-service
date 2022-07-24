package com.ecommerce.user_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ResponseOrder {

    private String productId;

    private Integer quantity;

    private Integer unitPrice;

    private Integer totalPrice;

    private Date createdAt;

    private String orderId;

}
