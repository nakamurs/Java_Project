public class Point extends AbstractShape implements CollisionDetector {

    private float x = 0;
    private float y = 0;
    private static int numberOfInstances;

    public Point(){
        numberOfInstances++;
    }
    public Point(float x, float y){
        this.x = x;
        this.y = y;
        numberOfInstances++;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public static int getNumOfInstances(){
        return numberOfInstances;
    };

    public void decreaseNumOfInstances(){
        numberOfInstances--;
    }
    
    public boolean intersect(Point s){
        // intersect if they have the same coordinates
        return (this.x == s.getX() && this.y == s.getY());
    }
    public boolean intersect(LineSeg s){
        return s.intersect(this);
    }
    public boolean intersect(Rectangle s){
        return s.intersect(this);
    }
    public boolean intersect(Circle s){
        // intersect if the distance to center is smaller than raidus
        float center_x = s.getCenter().getX();
        float center_y = s.getCenter().getY();
        float distance = (float)(Math.sqrt(Math.pow(this.x - center_x,2) + Math.pow(this.y - center_y, 2)));
        return (distance <= s.getRadius());
    }
    
}
