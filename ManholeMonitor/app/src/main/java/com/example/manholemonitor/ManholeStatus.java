package com.example.manholemonitor;

public class ManholeStatus {
    private String temperature;
    private String humidity;
    private String water_level;
    private String ir_status;
    private String tilt_status;

    // Default constructor required for Firebase
    public ManholeStatus() {
        // Empty constructor for Firebase
    }

    // Getters and setters
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWater_level() {
        return water_level;
    }

    public void setWater_level(String water_level) {
        this.water_level = water_level;
    }

    public String getIr_status() {
        return ir_status;
    }

    public void setIr_status(String ir_status) {
        this.ir_status = ir_status;
    }

    public String getTilt_status() {
        return tilt_status;
    }

    public void setTilt_status(String tilt_status) {
        this.tilt_status = tilt_status;
    }
}
