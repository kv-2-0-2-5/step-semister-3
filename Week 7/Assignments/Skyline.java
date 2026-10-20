abstract class Drone {
    Drone() {
    }

    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    private final String id;

    DeliveryDrone(String id) {
        super();
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " flying";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    private final String id;

    ScoutDrone(String id) {
        super();
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " scouting";
    }
}

class GroundRobot implements Trackable {
    private final String id;

    GroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

public class Skyline {
    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable t) {
            return t.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        ScoutDrone s = new ScoutDrone("SC-1");
        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(d));
        System.out.println(getLocationIfTrackable(s));
        System.out.println(getLocationIfTrackable(g));
    }
}