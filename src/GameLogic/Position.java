package GameLogic;

public class Position{
    int positionX;
    int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public void setPosition(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public void moveRelatively(String direction, int amount){
        if(direction=="Down") positionY+=amount;
        else if(direction=="Left") positionX-=amount;
        else if(direction=="Right") positionX+=amount;
        else if(direction=="Up") positionY-=amount;
        else if(direction=="Down-Left"){
            positionY+=amount;
            positionX-=amount;
        }
        else if(direction=="Down-Right"){
            positionY+=amount;
            positionX+=amount;
        }
        else if(direction=="Up-Left"){
            positionY-=amount;
            positionX-=amount;
        }
        else if(direction=="Up-Right"){
            positionY-=amount;
            positionX+=amount;
        }
    }
}
