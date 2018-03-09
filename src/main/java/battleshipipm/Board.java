package battleshipipm;

import java.util.ArrayList;

public class Board {


    private int totalPositions;
    private ArrayList<String> positions = new ArrayList<String>();

    public void setPositions(){
        int i = 0;
        while(i < this.totalPositions) {
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

}
