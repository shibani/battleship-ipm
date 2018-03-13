package battleshipipm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private int totalPositions;
    private final int rowSize = 10;

    private ArrayList<String> positions = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private ArrayList<Integer> filledPositions = new ArrayList<>();

    public void setPositions(){
        int i = 0;
        while(i < this.getTotalPositions()) {
            positions.add(" ");
            i++;
        }
    }

    public ArrayList<String> getPositions(){
        return positions;
    }

    public void setTotalPositions(int total){
        this.totalPositions = total;
    }

    public int getTotalPositions(){
        return this.totalPositions;
    }

    public void setShips(){

        for (Map.Entry<String, Integer> shipType : Ship.shipTypes.entrySet()) {
            String name = shipType.getKey();
            int size = shipType.getValue();

            Ship ship = new Ship();
            ship.setType(name);
            ship.setSize(size);

            Integer[] shipPosition = this.calcShipPosition(size);

            ship.setPosition(shipPosition);
            this.ships.add(ship);
        }
    }

    private Integer[] calcShipPosition(int size){

        Integer shipOrigin = this.getShipOrigin(size);
        Integer[] shipPosition = new Integer[size];

        for(int i = 0; i < size; i++){
            shipPosition[i] = shipOrigin + i;
            this.filledPositions.add(shipOrigin + i);
        }
        return shipPosition;
    }

    private int getShipOrigin(int size){

        int shipOrigin = (int)(Math.random() * this.getTotalPositions());
        for(int i = 0; i < size; i++){
            if(filledPositions.contains(shipOrigin + i)){
                return this.getShipOrigin(size);
            }
        }
        if(((shipOrigin % rowSize) + size) > rowSize){
            return this.getShipOrigin(size);
        }
        return shipOrigin;
    }

    public ArrayList<Ship> getShips(){
        return this.ships;
    }

    public ArrayList<Integer> getFilledPositions(){
        return this.filledPositions;
    }
}
