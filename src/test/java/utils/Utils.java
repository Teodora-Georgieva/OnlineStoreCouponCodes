package utils;

import testdata.TestData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static BigDecimal calculateDiscount(BigDecimal originalPrice) {
        return originalPrice.multiply(TestData.DISCOUNT_MULTIPLIER).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal shipping) {
        BigDecimal discount = calculateDiscount(subtotal);
        return subtotal.subtract(discount).add(shipping).setScale(2, RoundingMode.HALF_UP);
    }
}