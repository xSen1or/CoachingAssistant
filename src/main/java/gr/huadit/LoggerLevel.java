package gr.huadit;

public enum LoggerLevel {
    DEBUG("\u001B[34m"),
    INFO("\u001B[36m"),
    WARNING("\u001B[33m"),
    ERROR("\u001B[31m"),
    FATAL("\u001B[35m");

    private final String color;
    LoggerLevel(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
