package Midterm;

interface SmartDevice {
    void turnOn();
    void turnOff();
    String getStatus();
}

class Light implements SmartDevice {
    private String name;
    private boolean isOn = false;

    Light(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " Light turned on.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " Light turned off.");
    }

    @Override
    public String getStatus() {
        return name + " Light is " + (isOn ? "on" : "off");
    }
}

class Thermostat implements SmartDevice {
    private String name;
    private int temperature = 20;

    Thermostat(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " Thermostat activated.");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " Thermostat deactivated.");
    }

    void setTemperature(int temp) {
        temperature = temp;
        System.out.println(name + " Thermostat set to " + temp + "°C.");
    }

    @Override
    public String getStatus() {
        return name + " Thermostat at " + temperature + "°C";
    }
}

class CompositeDevice implements SmartDevice {
    private String name;
    private java.util.List<SmartDevice> devices = new java.util.ArrayList<>();

    CompositeDevice(String name) {
        this.name = name;
    }

    void addDevice(SmartDevice device) {
        devices.add(device);
    }

    void removeDevice(SmartDevice device) {
        devices.remove(device);
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on all devices in " + name);
        for (SmartDevice d : devices) {
            d.turnOn();
        }
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off all devices in " + name);
        for (SmartDevice d : devices) {
            d.turnOff();
        }
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder("Status of " + name + ":\n");
        for (SmartDevice d : devices) {
            sb.append("  - ").append(d.getStatus()).append("\n");
        }
        return sb.toString();
    }
}
