package gr.huadit;

import gr.huadit.Helpers.ArgumentHandler;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException("No arguments provided");
        ArgumentHandler argumentHandler = new ArgumentHandler(args);
//        argumentHandler.debugIndexOfArguments();
        if (argumentHandler.isEmpty()) System.exit(1);
        argumentHandler.flag();
    }
}