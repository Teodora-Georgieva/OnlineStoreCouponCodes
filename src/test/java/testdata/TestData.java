package testdata;

import java.math.BigDecimal;

public abstract class TestData {
    public static final String VALID_COUPON = "WARACLE25";
    public static final String INVALID_COUPON = "WARACLE2";
    public static final String EMPTY_COUPON = "";
    public static final BigDecimal DISCOUNT_MULTIPLIER = new BigDecimal("0.25");
    public static final BigDecimal STANDARD_SHIPPING_AMOUNT = new BigDecimal("5.00");
}