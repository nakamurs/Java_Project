public class Rectangle extends AbstractShape implements CollisionDetector {
    private float left;
    private float right;
    private float top;
    private float bottom;
    private static int numberOfInstances;

    public Rectangle(){
        numberOfInstances++;
    }
    public Rectangle(float l, float r, float t, float b) throws ShapeArgumentException {
        if (l >= r || b >= t){
            throw new ShapeArgumentException("ShapeArgumentException in constructing Rectangle");
        }
        left = l;
        right = r;
        top = t;
        bottom = b;
        numberOfInstances++;
    }

    public float getLeft(){
        return left;
    }
    public float getRight(){
        return right;
    }
    public float getTop(){
        return top;
    }
    public float getBottom(){
        return bottom;
    }

    public static int getNumOfInstances(){
        return numberOfInstances;
    }
    
    public boolean intersect(Point s){
        // intersect when left < x < right and bottom < y < right
        return  (this.left <= s.getX() && s.getX() <= this.right && 
                this.bottom <= s.getY() && s.getY() <= this.top);
    }
    public boolean intersect(LineSeg s){
        try{
            Point bottomLeft = new Point(this.left, this.bottom);
            bottomLeft.decreaseNumOfInstances();  // not incrementing the number of instances
            Point bottomRight = new Point(this.right, this.bottom);
            bottomRight.decreaseNumOfInstances();
            Point topLeft = new Point(this.left, this.top);
            topLeft.decreaseNumOfInstances();
            Point topRight = new Point(this.right, this.top);
            topRight.decreaseNumOfInstances();
            LineSeg bottomLine = new LineSeg(bottomLeft, bottomRight);
            bottomLine.decreaseNumOfInstances();
            LineSeg rightLine = new LineSeg(bottomRight, topRight);
            rightLine.decreaseNumOfInstances();
            LineSeg topLine = new LineSeg(topRight, topLeft);
            topLine.decreaseNumOfInstances();
            LineSeg leftLine = new LineSeg(topLeft, bottomLeft);
            leftLine.decreaseNumOfInstances();

            // line intersect line(boundary of rectangle)
            if (s.intersect(bottomLine) ||
                    s.intersect(rightLine) ||
                    s.intersect(topLine) ||
                    s.intersect(leftLine)){
                return true;
            }
            
            // inside the rectangle
            if (this.left < s.getBegin().getX() && s.getBegin().getX() < this.right && 
                this.left < s.getEnd().getX() && s.getEnd().getX() < this.right &&
                this.bottom < s.getBegin().getY() && s.getBegin().getY() < this.top &&
                this.bottom < s.getBegin().getY() && s.getBegin().getY() < this.top ){
                return true;
            }
            return false;
        } catch (ShapeArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    public boolean intersect(Rectangle s){
        try{
            // rectangle1 intersect boundary of rectangle2
            Point bottomLeft = new Point(this.left, this.bottom);
            bottomLeft.decreaseNumOfInstances();  // not incrementing the number of instances
            Point bottomRight = new Point(this.right, this.bottom);
            bottomRight.decreaseNumOfInstances();
            Point topLeft = new Point(this.left, this.top);
            topLeft.decreaseNumOfInstances();
            Point topRight = new Point(this.right, this.top);
            topRight.decreaseNumOfInstances();
            LineSeg bottomLine = new LineSeg(bottomLeft, bottomRight);
            bottomLine.decreaseNumOfInstances();
            LineSeg rightLine = new LineSeg(bottomRight, topRight);
            rightLine.decreaseNumOfInstances();
            LineSeg topLine = new LineSeg(topRight, topLeft);
            topLine.decreaseNumOfInstances();
            LineSeg leftLine = new LineSeg(topLeft, bottomLeft);
            leftLine.decreaseNumOfInstances();

            // line intersect line(boundary of rectangle)
            if (s.intersect(bottomLine) ||
                    s.intersect(rightLine) ||
                    s.intersect(topLine) ||
                    s.intersect(leftLine)){
                return true;
            }
            // rectangle(this) inside rectangle(s)
            if (s.left < this.left && this.right < s.right &&
                    s.bottom < this.bottom && this.top < s.top){
                return true;
            }
            // rectangle(s) inside rectangle(this)
            if (this.left < s.left && s.right < this.right &&
                    this.bottom < s.bottom && s.top < this.top){
                return true;
            }
            return false;
        } catch (ShapeArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }

        
    }
    public boolean intersect(Circle s){
        try{
            // intersect boundary of rectangle
            Point bottomLeft = new Point(this.left, this.bottom);
            bottomLeft.decreaseNumOfInstances();  // not incrementing the number of instances
            Point bottomRight = new Point(this.right, this.bottom);
            bottomRight.decreaseNumOfInstances();
            Point topLeft = new Point(this.left, this.top);
            topLeft.decreaseNumOfInstances();
            Point topRight = new Point(this.right, this.top);
            topRight.decreaseNumOfInstances();
            LineSeg bottomLine = new LineSeg(bottomLeft, bottomRight);
            bottomLine.decreaseNumOfInstances();
            LineSeg rightLine = new LineSeg(bottomRight, topRight);
            rightLine.decreaseNumOfInstances();
            LineSeg topLine = new LineSeg(topRight, topLeft);
            topLine.decreaseNumOfInstances();
            LineSeg leftLine = new LineSeg(topLeft, bottomLeft);
            leftLine.decreaseNumOfInstances();
            
            if (s.intersect(bottomLine) ||
                    s.intersect(rightLine) ||
                    s.intersect(topLine) ||
                    s.intersect(leftLine)){
                return true;
            }
            // circle inside rectangle
            if (this.left < s.getCenter().getX() - s.getRadius() &&
                    s.getCenter().getX() + s.getRadius() < this.right &&
                    this.bottom  < s.getCenter().getY() - s.getRadius() &&
                    s.getCenter().getY() + s.getRadius() < this.top){
                return true;
            }
            // rectangle inside circle 
            if (s.getCenter().getX() - s.getRadius() < this.left &&
                this.left < s.getCenter().getX() - s.getRadius() &&
                s.getCenter().getY() - s.getRadius() < this.bottom &&
                this.bottom < s.getCenter().getY() - s.getRadius()){
                float diagonal = (float)(Math.sqrt(Math.pow(this.right - this.left, 2) + Math.pow(this.top - this.bottom, 2)));
                return (diagonal < 2*s.getRadius());
            }
        return false;
        } catch (ShapeArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
        
    }
}
