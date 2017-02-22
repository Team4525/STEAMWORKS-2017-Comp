package org.usfirst.frc.team4525.robot.util;

import edu.wpi.first.wpilibj.Solenoid;

public class Piston {

    private final Solenoid extend, retract;
    private boolean isExtended;
    private int count;
    
    private boolean is_counting = false;

    // Constructor
    public Piston(int extendPos, int retractPos, boolean isExtended) {
        extend = new Solenoid(extendPos);
        retract = new Solenoid(retractPos);

        this.isExtended = isExtended;

        count = 0;
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

            this.count = 0;
        }
    }

    public void retract() {
        if (!isRetracted()) {
            extend.set(false);
            retract.set(true);
            this.isExtended = false;

            this.count = 0;
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

    public synchronized void countTime() {
  /*  	if(is_counting == false) { 
    		is_counting = true;
	    	new Thread(new Runnable(){
				public void run() {
			        while (count < 50) {
			            count++;
			        }
			        if(count >= 50) off();
			        is_counting = false;
				}
	    		
	    	}).start(); 
    	} */

    }

}
