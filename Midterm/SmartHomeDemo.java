package Midterm;

public class SmartHomeDemo {
    public static void main(String[] args) {
        SmartHomeFactory basicFactory = new BasicSmartHomeFactory();
        SmartDevice livingRoomLight = basicFactory.createLight("Living Room");
        SmartDevice kitchenThermostat = basicFactory.createThermostat("Kitchen");

        SmartHomeFactory advancedFactory = new AdvancedSmartHomeFactory();
        SmartDevice bedroomLight = advancedFactory.createLight("Bedroom");
        SmartDevice bathroomThermostat = advancedFactory.createThermostat("Bathroom");

        CompositeDevice livingRoom = new CompositeDevice("Living Room");
        livingRoom.addDevice(livingRoomLight);
        livingRoom.addDevice(bedroomLight);

        CompositeDevice smartHome = new CompositeDevice("Smart Home");
        smartHome.addDevice(livingRoom);
        smartHome.addDevice(kitchenThermostat);
        smartHome.addDevice(bathroomThermostat);

        LegacyDoorLock legacyDoorLock = new LegacyDoorLock();
        SmartDevice doorLock = new DoorLockAdapter(legacyDoorLock);
        smartHome.addDevice(doorLock);

        SmartHomeController controller = new SmartHomeController();
        controller.addDevice(smartHome);

        System.out.println("System is starting up...");
        controller.turnAllLightsOn();

        System.out.println("\nAdjusting temperature to a comfortable 22°C...");
        controller.setGlobalTemperature(22);

        System.out.println("\nHere's the current status of your smart home:");
        controller.getSystemStatusReport();

        System.out.println("\nNow shutting down the system. Have a nice day!");
        controller.turnAllLightsOff();
    }
}
