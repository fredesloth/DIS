package src.Talk_v4_Name;

public class ServerObject {

    private String name="";
    private String ip="";

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public ServerObject(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }
}
