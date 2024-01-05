package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String apiKey = "9833a4db2cead70fdc1c5bb72b4ae5cc";
		        String city = request.getParameter("city"); 
		        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

		        try {
		            URL url = new URL(apiUrl);
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            connection.setRequestMethod("GET");

		                InputStream inputStream = connection.getInputStream();
		                InputStreamReader reader = new InputStreamReader(inputStream);
		                Scanner scanner = new Scanner(reader);
		                StringBuilder responseContent = new StringBuilder();
		                while (scanner.hasNext()) {
		                    responseContent.append(scanner.nextLine());
		                }
		                scanner.close();
		                Gson gson = new Gson();
		                JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
		          
		                Date date1 = new Date();  
		                SimpleDateFormat formatter = new SimpleDateFormat("E dd MMM yyyy z");  
		                String date = formatter.format(date1).toString();  
		                
		                double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		                int temperatureCelsius = (int) (temperatureKelvin - 273.15);

		                int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();

		                double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();

		                String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();

		                request.setAttribute("date", date);
		                request.setAttribute("city", city);
		                request.setAttribute("temperature", temperatureCelsius);
		                request.setAttribute("weatherCondition", weatherCondition); 
		                request.setAttribute("humidity", humidity);    
		                request.setAttribute("windSpeed", windSpeed);
		                request.setAttribute("weatherData", responseContent.toString());
		                
		                connection.disconnect();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
