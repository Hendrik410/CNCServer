package com.hendrik.CNCServer;

/*
 * Created by hendrik on 23.04.15.
 */
public class CNCController {

    private CNCStats cncStats;
    public CNCSerial cncSerial;

    public CNCController() throws Exception{
        cncStats = new CNCStats();

        cncSerial = new CNCSerial();
    }

    public void closeAll(){
        cncSerial.close();
    }

    public void move(CNCDirection direction){
        System.out.println("Move " + direction.name());
    }

    public void setDrill(byte val){
        if(val == 0){
            cncStats.drill = false;
            System.out.println("Set drill off");
        } else if(val > 0) {
            cncStats.drill = true;
            cncStats.drillSpeed = val;
            System.out.println("Set drill to " + val);
        }
    }

    public void setContinuousMovement(boolean continuousMovement){
        cncStats.continuousMovement = continuousMovement;
        System.out.println("Set continuousMovement to " + continuousMovement);
    }

    public void setMovementSpeed(int speed){
        cncStats.movementSpeed = speed;
    }

    public CNCStats getCNCStats(){
        return cncStats;
    }
}
