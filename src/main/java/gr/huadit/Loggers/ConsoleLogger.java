package gr.huadit.Loggers;

import gr.huadit.Logger;
import gr.huadit.LoggerLevel;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
    @Override
    public void print(String message, LoggerLevel l) {
        System.out.println(l.getColor() + "[" + l.name() + "] " + "\u001B[0m| " + LocalDateTime.now() + "|"+ message);
    }
}
