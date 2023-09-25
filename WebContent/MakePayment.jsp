<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Make Payment</title>
    <!-- Include necessary Razorpay scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>
    <div class="container">
        <h1>Make Payment</h1>
        <button id="rzp-button">Pay Now</button>
    </div>
    <script>
        var orderId = "<%= session.getAttribute("orderId") %>";
        var amount = "<%= request.getParameter("amount") %>";
        var bidderId = "<%= request.getParameter("bid") %>";
        var tenderId = "<%= request.getParameter("tid") %>";
        var vendorId = "<%= request.getParameter("vid") %>";

        var options = {
            key: "rzp_test_MQbTBQlaI7ViKC",
            amount: amount * 100, // Amount in paise
            currency: "INR",
            name: "tender management",
            description: "Payment for Order #" + orderId,
            order_id: orderId,
            handler: function (response) {
                alert("Payment Successful! Payment ID: " + response.razorpay_payment_id);
                
                var servletUrl = "AssignBidSrv?bid=" + bidderId + "&vid=" + vendorId + "&tid=" + tenderId;

                
                window.location.href = servletUrl; // Replace "servletUrl" with the actual URL

                // You can perform further actions here, e.g., update order status in your database.
            },
            prefill: {
                name: "User Name",
                email: "user@example.com",
                contact: "1234567890"
            },
            theme: {
                color: "#3399cc"
            }
        };

        var rzp = new Razorpay(options);
        rzp.on('payment.failed', function (response) {
            alert("Payment failed: " + response.error.description);
        });

        document.getElementById('rzp-button').onclick = function () {
            rzp.open();
        };
    </script>
</body>
</html>
