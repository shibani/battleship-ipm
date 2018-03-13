package battleshipipm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Ship {

    private String type;
    private int size;
    private List<Integer> position = new ArrayList<>();

    public static final HashMap<String, Integer> shipTypes = new HashMap<>();

    static {
        shipTypes.put("Aircraft carrier", 5);
        shipTypes.put("Destroyer", 4);
        shipTypes.put("Cruiser", 3);
        shipTypes.put("Submarine", 3);
        shipTypes.put("Patrol Boat", 2);
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setSize(int i){
        this.size = i;
    }

    public int getSize(){
        return size;
    }

    public void setPosition(Integer[] position){
        this.position = Arrays.asList(position);
    }

    public List<Integer> getPosition() {
        return position;
    }
}
