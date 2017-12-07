package org.usfirst.frc.team4525.robot.util;

import edu.wpi.first.wpilibj.Solenoid;

public class Piston {

    private final Solenoid extend, retract;
    private boolean isExtended;
    

    // Constructor
    public Piston(int extendPos, int retractPos, boolean isExtended) {
        extend = new Solenoid(extendPos);
        retract = new Solenoid(retractPos);

        this.isExtended = isExtended;

    }

    // Setters/Getters
    public void setExtended(boolean isExtended) {
        this.isExtended = isExtended;
    }

    public void extend() {
        if (!isExtended()) {
            retract.set(false);
            extend.set(true);
            isExtended = true;

        }
    }

    public void retract() {
        if (!isRetracted()) {
            extend.set(false);
            retract.set(true);
            this.isExtended = false;

        }
    }

    public void off() {
        extend.set(false);
        retract.set(false);
    }

    public boolean isExtended() {
        return isExtended;
    }

    public boolean isRetracted() {
        return !isExtended;
    }

}
