package GameLogic;

import GameLogic.Shapes.*;

import java.util.Hashtable;

public class ShapeCache {
    private static Hashtable<String, Shape> shapeMap  = new Hashtable<String, Shape>();
    private static int maxShapeSize;
    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return cachedShape.clone();
    }
    public static int getMaxShapeSize(){
        return maxShapeSize;
    }
    public static void loadCache() {
        Alpha alpha = new Alpha();
        shapeMap.put(alpha.getId(), alpha);
        Gamma gamma = new Gamma();
        shapeMap.put(gamma.getId(), gamma);
        LeftSkew leftSkew = new LeftSkew();
        shapeMap.put(leftSkew.getId(), leftSkew);
        Line line = new Line();
        shapeMap.put(line.getId(), line);
        Pyramid pyramid = new Pyramid();
        shapeMap.put(pyramid.getId(), pyramid);
        RightSkew rightSkew = new RightSkew();
        shapeMap.put(rightSkew.getId(), rightSkew);
        Square square = new Square();
        shapeMap.put(square.getId(), square);
        countMaxShapeSize();
    }
    public static void countMaxShapeSize(){
        maxShapeSize = 0;
        for (int i=1; i<=shapeMap.size(); i++){
            int current = shapeMap.get(String.valueOf(i)).getSize();
            if (i==1 || maxShapeSize<current) maxShapeSize=current;
        }
    }
    public static void setSpawnPoint(GameBoard gameBoard){
        Position position = new Position();
        int spawnX = gameBoard.getColumnsSize()/2-2;
        int spawnY = maxShapeSize;
        position.setPosition(spawnX, spawnY);
        for (int i=1; i<8; i++){
            shapeMap.get(String.valueOf(i)).setPosition(position);
        }
    }
}
