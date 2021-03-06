package battleshipipm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Board {

    private int totalPositions;
    private int rowSize;

    private ArrayList<String> positions = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private ArrayList<Integer> filledShipPositions = new ArrayList<>();
    private ArrayList<String> shipMarkers = new ArrayList<>();
    private HashMap<String, Integer> hitCount = new HashMap<>();

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
            this.getShips().add(ship);
        }
    }

    private Integer[] calcShipPosition(Ship ship){

        Integer shipOrigin = this.getShipOrigin(ship.getSize());
        Integer[] shipPosition = new Integer[ship.getSize()];

        int random = (int)(Math.random() * 2);
        int shipAxis = (random == 0) ? 1 : 10;

        for(int i = 0; i < ship.getSize(); i++){
            int shipSquare = shipOrigin + i * shipAxis;
            shipPosition[i] = shipSquare;
            this.getFilledShipPositions().add(shipSquare);
            String shipType = ship.getType();
            this.getShipMarkers().add(Character.toString(shipType.charAt(0)));
        }
        return shipPosition;
    }

    private int getShipOrigin(int shipSize){

        int shipOrigin = (int)(Math.random() * this.getTotalPositions());
        for(int i = 0; i < shipSize; i++){
            if(this.getFilledShipPositions().contains(shipOrigin + i)){
                return this.getShipOrigin(shipSize);
            } else if (this.getFilledShipPositions().contains(shipOrigin + (i * 10))){
                return this.getShipOrigin(shipSize);
            }
        }
        if(((shipOrigin % this.getRowSize()) + shipSize) > this.getRowSize()){
            return this.getShipOrigin(shipSize);
        } else if (((shipOrigin + (shipSize * this.getRowSize())) > this.getTotalPositions())){
            return this.getShipOrigin(shipSize);
        }
        return shipOrigin;
    }

    public ArrayList<Ship> getShips(){
        return this.ships;
    }

    public ArrayList<Integer> getFilledShipPositions(){
        return this.filledShipPositions;
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

    public int coordsToPosition(String string){
        String coords = string.trim();
        String coordAlpha = Character.toString(coords.charAt(0)).toUpperCase();
        int num = Character.getNumericValue(coords.charAt(coords.length() - 1));

        int index = Arrays.asList(BoardCLI.alpha).indexOf(coordAlpha);
        return (index * 10) + num;
    }

    public boolean isEmpty(int position){
        return this.getPositions().get(position).equals(" ");
    }

    public void addMarker(int position){
        if (this.isEmpty(position)){
            if(this.getFilledShipPositions().contains(position)){
                this.getPositions().set(position, "X");
            } else {
                this.getPositions().set(position, "n");
            }
        }
    }

    public String checkForHit(int position){
        String status;
        if(this.getFilledShipPositions().contains(position)){
            status = "hit";
        } else {
            status = "miss";
        }
        return status;
    }

    public boolean isFull(){
        return !this.getPositions().contains(" ");
    }

    public boolean allShipsAreSunk(){
        for(Integer shipPosition: this.getFilledShipPositions()){
            if(!this.getPositions().get(shipPosition).equals("X")) {
                return false;
            }
        }
        return true;
    }

    public String shipIsSunk(int position){

        int markerIndex = this.getFilledShipPositions().indexOf(position);
        String marker = this.getShipMarkers().get(markerIndex);
        this.setCurrentHitCount(marker);

        int alphaSize = this.totalMarkerCount(marker);

        int currentHitCount = this.getCurrentHitCount(marker);

        String shipType = null;

        for(Ship ship : this.getShips()){
            if(ship.getType().charAt(0) == marker.charAt(0)){
                shipType = ship.getType();
                break;
            }
        }

        if(currentHitCount == alphaSize){
            return shipType;
        } else {
            return "none";
        }
    }

    public int totalMarkerCount(String marker){
        int alphaSize = 0;
        for (String alpha : this.getShipMarkers()){
            if(alpha.equals(marker)){
                alphaSize++;
            }
        }
        return alphaSize;
    }

    public void setCurrentHitCount(String marker){
        int value = hitCount.getOrDefault(marker, 0);
        hitCount.put(marker, value+1);
    }

    public int getCurrentHitCount(String marker){
        return hitCount.get(marker);
    }
}
