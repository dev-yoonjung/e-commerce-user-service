package com.ecommerce.user_service.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResponseOrder {

    private String productId;

    private Integer quantity;

    private Integer unitPrice;

    private Integer totalPrice;

    private Date createdAt;

    private String orderId;

}
