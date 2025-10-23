package com.example.langchain4j.agents.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Temperature conversion tool.
 * Demonstrates tool parameter handling with LangChain4j.
 */
@Component
public class CalculatorTool {

    private static final Logger log = LoggerFactory.getLogger(CalculatorTool.class);

    /**
     * Convert temperature from Celsius to Fahrenheit.
     */
    @Tool("Convert temperature from Celsius to Fahrenheit")
    public String celsiusToFahrenheit(@P("Temperature in Celsius") double celsius) {
        log.info("Converting {}°C to Fahrenheit", celsius);
        double fahrenheit = (celsius * 9.0 / 5.0) + 32.0;
        return String.format("%.1f°C = %.1f°F", celsius, fahrenheit);
    }

    /**
     * Convert temperature from Fahrenheit to Celsius.
     */
    @Tool("Convert temperature from Fahrenheit to Celsius")
    public String fahrenheitToCelsius(@P("Temperature in Fahrenheit") double fahrenheit) {
        log.info("Converting {}°F to Celsius", fahrenheit);
        double celsius = (fahrenheit - 32.0) * 5.0 / 9.0;
        return String.format("%.1f°F = %.1f°C", fahrenheit, celsius);
    }

    /**
     * Convert temperature from Celsius to Kelvin.
     */
    @Tool("Convert temperature from Celsius to Kelvin")
    public String celsiusToKelvin(@P("Temperature in Celsius") double celsius) {
        log.info("Converting {}°C to Kelvin", celsius);
        double kelvin = celsius + 273.15;
        return String.format("%.1f°C = %.2f K", celsius, kelvin);
    }

    /**
     * Convert temperature from Kelvin to Celsius.
     */
    @Tool("Convert temperature from Kelvin to Celsius")
    public String kelvinToCelsius(@P("Temperature in Kelvin") double kelvin) {
        log.info("Converting {} K to Celsius", kelvin);
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (0 K)");
        }
        double celsius = kelvin - 273.15;
        return String.format("%.2f K = %.1f°C", kelvin, celsius);
    }

    /**
     * Convert temperature from Fahrenheit to Kelvin.
     */
    @Tool("Convert temperature from Fahrenheit to Kelvin")
    public String fahrenheitToKelvin(@P("Temperature in Fahrenheit") double fahrenheit) {
        log.info("Converting {}°F to Kelvin", fahrenheit);
        double kelvin = (fahrenheit - 32.0) * 5.0 / 9.0 + 273.15;
        return String.format("%.1f°F = %.2f K", fahrenheit, kelvin);
    }

    /**
     * Convert temperature from Kelvin to Fahrenheit.
     */
    @Tool("Convert temperature from Kelvin to Fahrenheit")
    public String kelvinToFahrenheit(@P("Temperature in Kelvin") double kelvin) {
        log.info("Converting {} K to Fahrenheit", kelvin);
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (0 K)");
        }
        double fahrenheit = (kelvin - 273.15) * 9.0 / 5.0 + 32.0;
        return String.format("%.2f K = %.1f°F", kelvin, fahrenheit);
    }
}
