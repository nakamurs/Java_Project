 public class Circle extends AbstractShape implements CollisionDetector {

    private Point center;
    private float radius;
    private static int numberOfInstances;

    public Circle(){
        numberOfInstances++;
    }
    public Circle(Point c, float r) throws ShapeArgumentException {
        if (r <= 0){
            throw new ShapeArgumentException("ShapeArgumentException in constructing Circle");
        }
        center = c;
        radius = r;
        numberOfInstances++;
    }

    public Point getCenter(){
        return center;
    }
    public float getRadius(){
        return radius;
    }

    public static int getNumOfInstances(){
        return numberOfInstances;
    };
    
    public boolean intersect(Point s){
        return s.intersect(this);
    }
    public boolean intersect(LineSeg s){
        return s.intersect(this);
    }
    public boolean intersect(Rectangle s){
        return s.intersect(this);
    }
    public boolean intersect(Circle s){
        // intersect if distance between two centers are smaller than sum of radius
        float center_x = s.getCenter().getX();
        float center_y = s.getCenter().getY();
        float distance = (float)(Math.sqrt(Math.pow(this.center.getX() - center_x,2) + Math.pow(this.center.getY() - center_y,2)));
        float sumOfRadius = s.getRadius() + this.radius;
        return (distance < sumOfRadius);
    }
    
}
