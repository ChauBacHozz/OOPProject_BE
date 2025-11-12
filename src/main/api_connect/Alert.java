package main.api_connect;

import java.util.List;

public class Alert {
    public String sender_name;
    public String event;
    public long start;
    public long end;
    public String description;
    public List<String> tags;

    public Alert() {
    }
}
