package utils;

import testdata.CheckoutTestData;
import testdata.CouponTestData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static BigDecimal calculateDiscount(BigDecimal originalPrice) {
        return originalPrice.multiply(CouponTestData.DISCOUNT_MULTIPLIER).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal shipping) {
        BigDecimal discount = calculateDiscount(subtotal);
        return subtotal.subtract(discount).add(shipping).setScale(2, RoundingMode.HALF_UP);
    }

    public static CheckoutTestData getCheckoutData() {
        return new CheckoutTestData(
                "1111111111",
                "Edinburgh",
                "EH11AA",
                "4242 4242 4242 4242",
                "12/26",
                "123"
        );
    }
}