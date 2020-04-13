import java.awt.Point;


public class Ship {
    private Point shipLocation;
    OceanMap oceanMap;
    Ship(OceanMap oceanMap){
        this.oceanMap = oceanMap;
        this.shipLocation = oceanMap.getShipLocation();
    }

    public void goEast(){
        int x = shipLocation.x;
        int y = shipLocation.y;
        if(x < oceanMap.getDimensions()-1 && oceanMap.isOcean(x+1, y)){
            oceanMap.updateShipLocationInGrid(x+1, y);
            shipLocation.x++;
        }
    }

    public void goWest(){
        int x = shipLocation.x;
        int y = shipLocation.y;
        if(x > 0 && oceanMap.isOcean(x-1, y)){
            oceanMap.updateShipLocationInGrid(x-1, y);
            shipLocation.x--;
        }
    }

    public void goNorth(){
        int x = shipLocation.x;
        int y = shipLocation.y;
        if(y > 0 && oceanMap.isOcean(x, y-1)){
            oceanMap.updateShipLocationInGrid(x, y-1);
            shipLocation.y--;
        }
    }

    public void goSouth(){
        int x = shipLocation.x;
        int y = shipLocation.y;
        if(y < oceanMap.getDimensions()-1 && oceanMap.isOcean(x, y+1)){
            oceanMap.updateShipLocationInGrid(x, y+1);
            shipLocation.y++;
        }
    }

    public Point getShipLocation() {
        return shipLocation;
    }
}