package battleshipipm;

abstract class Player {

    public abstract String getName();

    public abstract Board getBoard();

    public abstract void setType(String type);

    public abstract String getType();
}