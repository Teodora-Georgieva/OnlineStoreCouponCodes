# Online Store Coupon Codes – Test Automation

## Prerequisites

- Java 17
- Maven
- Selenium 4.49.0
- TestNG 7.12.0
- Lombok
- The application under test running on http://localhost:5173/

## Run all tests

mvn clean test

## Run positive tests

mvn test -Dgroups=positive
Runs only the tests belonging to the positive TestNG group.

## Run negative tests

mvn test -Dgroups=negative
Runs only the tests belonging to the negative TestNG group.

## Release Assessment
- Bug 1: Applying the coupon code "WARACLE25" doesn't reduce the subtotal with 25%, but with 0.25 pounds
- Bug 2: Incorrect calculation of total amount when applying the coupon code "WARACLE25" (related to Bug 1)
- AC-5 (An invalid or empty code applies no discount and shows a clear message.) is not clear enough - it doesn't specify what should be the error message and where to be displayed
- Entering an invalid or empty code only shows an info toast message "Coupon entered", but there's no clear indication that the entered code is incorrect (related to the mentioned above)

## Additional Notes
- The web app "Waracle Store" couldn't be started on localhost with "nmp run dev" command. It worked with "npm run dev:web"
- The "launch-web" shell script doesn't work