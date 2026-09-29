package testdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckoutTestData {
    private String address;
    private String city;
    private String postCode;
    private String cardNumber;
    private String expiryDate;
    private String cvc;
}