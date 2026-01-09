package org.example


import java.net.URL
import com.google.gson.Gson // A library to turn JSON into Objects

// This data class matches the structure of the Weather API response
data class WeatherResponse(val main: Main, val name: String)
data class Main(val temp: Double, val humidity: Int)

fun main() {
    val apiKey = "YOUR_API_KEY_HERE"
    print("Enter a city name: ")
    val city = readLine() ?: "London"

    try {
        // 1. Build the URL
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey&units=metric"

        // 2. Fetch the JSON data
        val jsonResponse = URL(url).readText()

        // 3. Parse JSON into our Data Class
        val weatherData = Gson().fromJson(jsonResponse, WeatherResponse::class.java)

        // 4. Display the results
        println("\n--- Weather in ${weatherData.name} ---")
        println("Temperature: ${weatherData.main.temp}°C")
        println("Humidity: ${weatherData.main.humidity}%")

    } catch (e: Exception) {
        println("Error: Could not find that city or network is down.")
    }
}