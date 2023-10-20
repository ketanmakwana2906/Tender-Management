package com.hit.srv;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import java.util.Date;
import java.text.ParseException; // Import ParseException from java.text



import com.hit.dao.BidderDaoImpl;
import com.hit.utility.IDUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@WebServlet("/BidTenderSrv")
@MultipartConfig
public class BidTenderSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BidTenderSrv() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String uname = (String) session.getAttribute("username");
        String pword = (String) session.getAttribute("password");

        if (user == null || !user.equalsIgnoreCase("user") || uname.equals("") || pword.equals("")) {
            response.sendRedirect("loginFailed.jsp");
            return;
        }
        
     // Handle file upload
        String tenderId = request.getParameter("tid");
        String vendorId = request.getParameter("vid");
        String bidId = "B" + tenderId + vendorId;
        Part filePart = request.getPart("licenseDocument"); // "licenseDocument" should match the input field name
        String originalFileName = getSubmittedFileName(filePart);
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = "licence_" + bidId + fileExtension;        String uploadPath = "D:\\Tender-Management-System-master\\Tender-Management-System-master\\tendermanagement\\WebContent\\licence_docs";
        
        
     // Create the directory if it doesn't exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
     // Save the uploaded file to the server
        File file = new File(uploadPath, fileName);
        try (InputStream input = filePart.getInputStream()) {
        
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        
     
        

        String bidAmountStr = request.getParameter("bidamount");
        String basePriceStr = request.getParameter("tenderamount");
        String experienceStr = request.getParameter("bidexperince"); // Get experience from the request

        String bidDeadlineStr = request.getParameter("biddeadline");
        String newDateFormatPattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date originalDate = null;
		try {
			originalDate = originalDateFormat.parse(bidDeadlineStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat newDateFormat = new SimpleDateFormat(newDateFormatPattern);
        String newDateString = newDateFormat.format(originalDate);
      
        String licence =fileName;
        System.out.print(bidAmountStr);
        System.out.print(basePriceStr);
        System.out.print(bidDeadlineStr);
        System.out.print(tenderId);
        System.out.print(vendorId);
        System.out.print(experienceStr);


     try {
            double bidAmount = Double.parseDouble(bidAmountStr);
            double basePrice = Double.parseDouble(basePriceStr);
            long bidDeadline;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
            Date date = dateFormat.parse(bidDeadlineStr);
            bidDeadline = date.getTime();
            long currentTime = System.currentTimeMillis();
            
            System.out.println("bidAmount: " + bidAmount);
            System.out.println("basePrice: " + basePrice);
            System.out.println("bidDeadline: " + bidDeadline);
            System.out.println("currentTime: " + currentTime);
            System.out.println("file url: " + licence);

            // Calculate points based on bid amount and bid deadline
            int points = 0;

           

            // Calculate the percentage difference between bidAmount and basePrice
         // Calculate the percentage difference between bidAmount and basePrice
            double percentageDifference = ((basePrice - bidAmount) / basePrice) * 100.0;

            // Calculate points based on the percentage difference
            points = (int) (Math.ceil(percentageDifference));

            // Ensure points are at least 0
            points = Math.max(points, 0);

           /* if (currentTime > bidDeadline) {
                // If bid deadline has passed, decrease one point
                points--;
            } else {
                // If bid deadline is in the future, increase one point
                points++;
            }*/
            int experiencePoints = Integer.parseInt(experienceStr); // Convert experience to int
            points += experiencePoints;

            
            System.out.println("points: " + points);


            BidderDaoImpl dao = new BidderDaoImpl();
            String status = dao.bidTender(bidId ,tenderId,basePriceStr, vendorId, bidAmountStr, newDateString, points,licence);
            System.out.print(newDateString);

            PrintWriter pw = response.getWriter();
            RequestDispatcher rd = request.getRequestDispatcher("bidTenderForm.jsp");
            rd.include(request, response);
            pw.print("<script>document.getElementById('show').innerHTML='" + status + "'</script>");
            pw.close();
     }catch (ParseException e) {
    	    // Handle parsing error
    	    response.sendRedirect("bidError.jsp"); // Redirect to an error page
    	    return;
}
     
}


private String getSubmittedFileName(Part part) {
    for (String cd : part.getHeader("content-disposition").split(";")) {
        if (cd.trim().startsWith("filename")) {
            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
        }
    }
    return null;
}

}