package mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import com.hit.srv.verificationEmailsrv;

public class SendOTP {
    public static void sendOTP(String otp, String number, String apiKey) {
        try {
            String sendId = "FSTSMS";
            String language = "english";
            String route = "p";

            otp = URLEncoder.encode(otp, "UTF-8"); // Important Step

            String myUrl = "https://www.fast2sms.com/dev/bulkauthorization=" + apiKey+ "&sender_id=" + sendId + "&message=" + otp + "&language=" + language + "&route=" + route + "&numbers=" + number;

            URL url = new URL(myUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");

            int responseCode = con.getResponseCode();

            StringBuffer response = new StringBuffer();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while (true) {
                String line = br.readLine();

                if (line == null) {
                    break;
                }

                response.append(line);
            }

            System.out.println(response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Program Started....");

        verificationEmailsrv emailService = new verificationEmailsrv(); // Create an instance of the verificationEmailsrv class
        String otp = emailService.otp2;// Access the otp2 variable from the instance
        System.out.print(otp);
        String apiKey = "QOwKqNWc7YuT3LHGbERIyD45CzBelAU6jgrmt90ki81sJZa2oVjaGQFV9xfmbCdlr8hs0eI1W7iw4TuZ";
        String number = "+916354888981";

        sendOTP("Hey this message is sent by MuradAli using Java Code. Your OTP is: " + otp, number, apiKey);
    }
}
