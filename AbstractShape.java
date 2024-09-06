public abstract class AbstractShape {
    
    private static int numberOfInstances;

    public static int getNumOfInstances(){
        numberOfInstances = Rectangle.getNumOfInstances() + LineSeg.getNumOfInstances() + Point.getNumOfInstances()
                    + Circle.getNumOfInstances();
        return numberOfInstances;
    }
}
