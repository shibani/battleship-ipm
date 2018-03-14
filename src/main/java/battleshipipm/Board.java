package battleshipipm;

import java.util.ArrayList;
import java.util.Map;

public class Board {

    private int totalPositions;
    private int rowSize;

    private ArrayList<String> positions = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private ArrayList<Integer> filledPositions = new ArrayList<>();
    private ArrayList<String> shipMarkers = new ArrayList<>();

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
        this.setRowSize();
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

            Integer[] shipPosition = this.calcShipPosition(ship);

            ship.setPosition(shipPosition);
            this.ships.add(ship);
        }
    }

    private Integer[] calcShipPosition(Ship ship){

        Integer shipOrigin = this.getShipOrigin(ship.getSize());
        Integer[] shipPosition = new Integer[ship.getSize()];

        for(int i = 0; i < ship.getSize(); i++){
            shipPosition[i] = shipOrigin + i;
            this.getFilledPositions().add(shipOrigin + i);
            String shipType = ship.getType();
            this.getShipMarkers().add(Character.toString(shipType.charAt(0)));
        }
        return shipPosition;
    }

    private int getShipOrigin(int size){

        int shipOrigin = (int)(Math.random() * this.getTotalPositions());
        for(int i = 0; i < size; i++){
            if(this.getFilledPositions().contains(shipOrigin + i)){
                return this.getShipOrigin(size);
            }
        }
        if(((shipOrigin % this.getRowSize()) + size) > this.getRowSize()){
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

    public ArrayList<String> getShipMarkers(){
        return this.shipMarkers;
    }

    public void setRowSize(){
         this.rowSize = (int)Math.sqrt(this.totalPositions);
    }

    public int getRowSize(){
        return this.rowSize;
    }
}
