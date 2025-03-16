package Midterm;

interface SmartHomeFactory {
    SmartDevice createLight(String name);
    SmartDevice createThermostat(String name);
}

class BasicSmartHomeFactory implements SmartHomeFactory {
    @Override
    public SmartDevice createLight(String name) {
        return new Light(name);
    }

    @Override
    public SmartDevice createThermostat(String name) {
        return new Thermostat(name);
    }
}

class AdvancedSmartHomeFactory implements SmartHomeFactory {
    @Override
    public SmartDevice createLight(String name) {
        return new ScheduledOperationDecorator(new Light("Advanced " + name));
    }

    @Override
    public SmartDevice createThermostat(String name) {
        return new ScheduledOperationDecorator(new Thermostat("Advanced " + name));
    }
}

class SmartHomeController {
    private java.util.List<SmartDevice> devices = new java.util.ArrayList<>();

    void addDevice(SmartDevice device) {
        devices.add(device);
    }

    void turnAllLightsOn() {
        System.out.println("SmartHomeController: Turning on all devices.");
        for (SmartDevice d : devices) {
            d.turnOn();
        }
    }

    void turnAllLightsOff() {
        System.out.println("SmartHomeController: Turning off all devices.");
        for (SmartDevice d : devices) {
            d.turnOff();
        }
    }

    void setGlobalTemperature(int temp) {
        System.out.println("SmartHomeController: Setting global temperature to " + temp + "°C.");
        for (SmartDevice d : devices) {
            if (d instanceof Thermostat) {
                ((Thermostat) d).setTemperature(temp);
            } else if (d instanceof ScheduledOperationDecorator) {
                SmartDevice inner = ((ScheduledOperationDecorator) d).device;
                if (inner instanceof Thermostat) {
                    ((Thermostat) inner).setTemperature(temp);
                }
            }
        }
    }

    void getSystemStatusReport() {
        System.out.println("SmartHomeController: System Status Report:");
        for (SmartDevice d : devices) {
            System.out.println(d.getStatus());
        }
    }
}
