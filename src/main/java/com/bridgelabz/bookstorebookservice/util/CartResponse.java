package com.bridgelabz.bookstorebookservice.util;

import com.bridgelabz.bookstorebookservice.dto.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purpose :Return Cart Status
 * Version : 1.0
 *
 * @author : Aviligonda Sreenivasulu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private int statusCode;
    private String statusMessage;
    private CartDTO object;
}
