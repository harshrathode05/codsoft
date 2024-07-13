import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    // Replace with your API key or endpoint for real-time exchange rates
    private static final String API_ENDPOINT = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Step 1: Allow the user to choose base and target currencies
            System.out.print("Enter the base currency code (e.g., USD, EUR): ");
            String baseCurrency = scanner.next().toUpperCase();

            System.out.print("Enter the target currency code (e.g., USD, EUR): ");
            String targetCurrency = scanner.next().toUpperCase();

            // Step 2: Fetch real-time exchange rates from API
            JSONObject exchangeRates = fetchExchangeRates(baseCurrency);

            // Step 3: Take input from user for the amount to convert
            System.out.print("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ": ");
            double amount = scanner.nextDouble();

            // Step 4: Perform currency conversion
            double conversionRate = exchangeRates.getDouble(targetCurrency);
            double convertedAmount = amount * conversionRate;

            // Step 5: Display the converted amount and target currency symbol
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println(amount + " " + baseCurrency + " = " + df.format(convertedAmount) + " " + targetCurrency);

        } catch (IOException e) {
            System.out.println("Failed to fetch exchange rates. Please try again later.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static JSONObject fetchExchangeRates(String baseCurrency) throws IOException {
        URL url = new URL(API_ENDPOINT + baseCurrency);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder responseBuilder = new StringBuilder();
        String output;

        while ((output = br.readLine()) != null) {
            responseBuilder.append(output);
        }

        conn.disconnect();

        return new JSONObject(responseBuilder.toString()).getJSONObject("rates");
    }
}
