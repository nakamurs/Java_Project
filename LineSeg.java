public class LineSeg extends AbstractShape implements CollisionDetector {
    
    private Point begin;
    private Point end;
    private static int numberOfInstances;

    public LineSeg(){
        numberOfInstances++;
    }
    public LineSeg(Point b, Point e) throws ShapeArgumentException {
        if(b.getX() == e.getX() && b.getY() == e.getY()){
            throw new ShapeArgumentException("ShapeArgumentException in constructing LineSeg");
        }
        begin = b;
        end = e;
        numberOfInstances++;
    }

    public Point getBegin(){
        return begin;
    }
    public Point getEnd(){
        return end;
    }

    public static int getNumOfInstances(){
        return numberOfInstances;
    }

    public void decreaseNumOfInstances(){
        numberOfInstances--;
    }
    
    public boolean intersect(Point s){
        // y  = mx + b (except for lines parallel to y-axis)
        if (this.end.getX() != this.begin.getX()){
            float m = (this.end.getY() - this.begin.getY()) / (this.end.getX() - this.begin.getX());
            float b = this.end.getY() - m * this.end.getX();
            // satisfy the equation and x within the range
            return (s.getY() == m * s.getX() + b && 
                Math.min(this.begin.getX(),this.end.getX()) <= s.getX() && 
                s.getX() <= Math.max(this.begin.getX(),this.end.getX()));
        } else {
            // same x coordinate and y within the range(y)
            return (s.getX() == this.end.getX() && 
                Math.min(this.begin.getY(), this.end.getY()) <= s.getY() &&
                s.getY() <= Math.min(this.begin.getY(), this.end.getY()));
        }
    }
    public boolean intersect(LineSeg s){
        /*  this:   y = ax + b
            s:      y = cx + d
            ax + b = cx + d 
            (a-c) x = d - b
            x = d - b / a - c
            x with in range
        */
        if (this.end.getX() != this.begin.getX()){
            float a = (this.end.getY() - this.begin.getY()) / (this.end.getX() - this.begin.getX());
            float b = this.end.getY() - a * this.end.getX();
            if(s.end.getX() != s.begin.getX()){
                float c = (s.end.getY() - s.begin.getY()) / (s.end.getX() - s.begin.getX());
                float d = s.end.getY() - c * s.end.getX();
                if (a == c){ //same slope
                    return (b == d && 
                            Math.max(s.begin.getX(),s.end.getX()) > Math.min(this.begin.getX(),this.end.getX()) &&
                            Math.max(this.begin.getX(),this.end.getX()) > Math.min(s.begin.getX(),s.end.getX()));
                } else { // different slope
                    float x0 = (d-b) / (a-c);
                    // x0 within x range
                    return (Math.min(this.begin.getX(),this.end.getX()) < x0 && 
                        x0 < Math.max(this.begin.getX(),this.end.getX()) &&
                        Math.min(s.begin.getX(),s.end.getX()) < x0 && 
                        x0 < Math.max(s.begin.getX(),s.end.getX()) );
                }
            } else { // s.end.getX() == s.begin.getX()
                float x0 = s.end.getX();
                float y0 = a * x0 + b;
                // x0, y0 within y range
                return (Math.min(this.begin.getX(),this.end.getX()) < x0 && 
                        x0 < Math.max(this.begin.getX(),this.end.getX()) &&
                        Math.min(s.begin.getY(), s.end.getY()) < y0 &&
                        y0 < Math.max(s.begin.getY(), s.end.getY()));

            }
        } else { // this.end.getX() == this.begin.getX()
            if (s.end.getX() != s.begin.getX()){
                return s.intersect(this);  // repeat the above code
            } else { // s.end.getX() == s.begin.getX()
                // x = c, x = d
                // c == d and y within the range
                return (this.end.getX() == s.end.getX() &&
                            Math.max(s.begin.getY(),s.end.getY()) > Math.min(this.begin.getY(),this.end.getY()) &&
                            Math.max(this.begin.getY(),this.end.getY()) > Math.min(s.begin.getY(),s.end.getY()));

            }
        }
    }
    public boolean intersect(Rectangle s){
        return s.intersect(this);
    }
    public boolean intersect(Circle s){
        float p = s.getCenter().getX();
        float q = s.getCenter().getY();
        float r = s.getRadius();
        /*  y = a x + b -> a x - y + b = 0
        //  distance = |a p - q + b| / (a^2 + 1)^(1/2)
        //  (x - p)^2 + (y - q)^2 = r^2 center: (p,q)
        //  (x - p)^2 + (a x + b - q)^2 = r^2
            (a^2 +1)x^2 + 2(a(b-q)-p)x + p^2 + (b-q)^2 - r^2 = 0
        */
        if (this.end.getX() != this.begin.getX()){
            float a = (this.end.getY() - this.begin.getY()) / (this.end.getX() - this.begin.getX());
            float b = this.end.getY() - a * this.end.getX();
            float d1 = (float)(Math.abs(a * p - q + b) / Math.sqrt(a*a + 1));
            if (d1 < r) { // intersect if distance smaller than radius
/* 
                float aA = a*a + 1; 
                float bB = a*(b-q) - p;
                float cC = p*p + (b-q)*(b-q) - r*r;
                // quadradic formulat -> x coordinate of intersections
                float x1 = (float)(-bB + Math.sqrt(bB*bB - aA*cC));
                float x2 = (float)(-bB - Math.sqrt(bB*bB - aA*cC));
                // x1 and x2 within range
                return ((Math.min(this.begin.getX(),this.end.getX()) <= x1 && 
                        x1 <= Math.max(this.begin.getX(),this.end.getX())) ||
                        (Math.min(this.begin.getX(),this.end.getX()) <= x2 && 
                        x2 <= Math.max(this.begin.getX(),this.end.getX())));*/
                return true;

            }
        } else { // this.end.getX() == this.begin.getX()
            // x = c -> x - 0*y - c = 0
            // d = |p - c|
            float c = this.begin.getX();
            float d = (float)(Math.abs(p - c));
            if (d < r ){/* 
                float y1 = (float)(q + Math.sqrt(r*r - (c-p)*(c-p)));
                float y2 = (float)(q - Math.sqrt(r*r - (c-p)*(c-p)));
                return ((Math.min(this.begin.getY(),this.end.getY()) <= y1 && 
                        y1 <= Math.max(this.begin.getY(),this.end.getY())) ||
                        (Math.min(this.begin.getY(),this.end.getY()) <= y2 && 
                        y2 <= Math.max(this.begin.getY(),this.end.getY()))); */
                return true;

            }
        }
    return false;
    }
    
}
