package Midterm;

abstract class DeviceDecorator implements SmartDevice {
    protected SmartDevice device;

    DeviceDecorator(SmartDevice device) {
        this.device = device;
    }

    @Override
    public void turnOn() {
        device.turnOn();
    }

    @Override
    public void turnOff() {
        device.turnOff();
    }

    @Override
    public String getStatus() {
        return device.getStatus();
    }
}

class ScheduledOperationDecorator extends DeviceDecorator {
    ScheduledOperationDecorator(SmartDevice device) {
        super(device);
    }

    @Override
    public void turnOn() {
        System.out.println("[Scheduled] Preparing to turn on...");
        device.turnOn();
    }

    @Override
    public void turnOff() {
        System.out.println("[Scheduled] Preparing to turn off...");
        device.turnOff();
    }

    @Override
    public String getStatus() {
        return device.getStatus() + " (with scheduled operations)";
    }
}

class LegacyDoorLock {
    void lock() {
        System.out.println("Legacy Door Lock: locked.");
    }

    void unlock() {
        System.out.println("Legacy Door Lock: unlocked.");
    }
}

class DoorLockAdapter implements SmartDevice {
    private LegacyDoorLock legacyDoorLock;
    private boolean isLocked = false;

    DoorLockAdapter(LegacyDoorLock legacyDoorLock) {
        this.legacyDoorLock = legacyDoorLock;
    }

    @Override
    public void turnOn() {
        legacyDoorLock.lock();
        isLocked = true;
    }

    @Override
    public void turnOff() {
        legacyDoorLock.unlock();
        isLocked = false;
    }

    @Override
    public String getStatus() {
        return "Door Lock is " + (isLocked ? "locked" : "unlocked");
    }
}
