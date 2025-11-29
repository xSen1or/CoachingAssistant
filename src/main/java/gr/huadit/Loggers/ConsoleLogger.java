package gr.huadit.Loggers;

import gr.huadit.Interfaces.Logger;
import gr.huadit.Enums.LoggerLevel;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
    @Override
    public void print(String message, LoggerLevel l) {
        System.out.println(l.getColor() + "[" + l.name() + "] " + "\u001B[0m | \u001B[32m" + LocalDateTime.now() + " \u001B[0m| "+ message);
    }
}
