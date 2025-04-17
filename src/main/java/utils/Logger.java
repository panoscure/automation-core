package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String PURPLE = "\u001B[35m";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static String timestamp() {
        return sdf.format(new Date());
    }

    private static void log(String level, String color, String message) {
        System.out.println(color + "[" + timestamp() + "] [" + level + "] " + message + RESET);
    }

    public static void debug(String message) {
        log("DEBUG", CYAN, message);
    }

    public static void info(String message) {
        log("INFO", GREEN, message);
    }

    public static void warning(String message) {
        log("WARNING", YELLOW, message);
    }

    public static void error(String message) {
        log("ERROR", RED, message);
    }

    public static void critical(String message) {
        log("CRITICAL", PURPLE, message);
    }
}
