import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class A2Test {
        
        // Point
        @Test
        public void testPointIntersectPoint() {
            assertTrue(new Point(1, 1).intersect(new Point(1,1)));
            assertFalse(new Point(0, 0).intersect(new Point(5, 4)));
        }

        @Test
        public void testPointIntersectLineSeg() {
            try{
                assertTrue(new Point(1, 2).intersect(new LineSeg(new Point(0, 1), new Point(3, 4))));
                assertFalse(new Point(4, 5).intersect(new LineSeg(new Point(1, 0), new Point(3, 0))));
                assertTrue(new Point(0, 1).intersect(new LineSeg(new Point(0, 1), new Point(3, 4))));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testPointIntersectCircle() {
            try {
                assertTrue(new Point(1, 1).intersect(new Circle(new Point(0, 0), 5f)));
                assertFalse(new Point(10, 1).intersect(new Circle(new Point(0, 0), 5f)));
                assertTrue(new Point(0, 5).intersect(new Circle(new Point(0, 0), 5f)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testPointIntersectRectangle() {
            try {
            assertTrue(new Point(2, 2).intersect(new Rectangle(1, 3, 3, 1)));
            assertFalse(new Point(2, 6).intersect(new Rectangle(1, 3, 3, 1)));
            assertTrue(new Point(1, 3).intersect(new Rectangle(1, 3, 3, 1)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        // LineSeg
        @Test
        public void testLineSegIntersectPoint() {
            try {
            assertTrue(new LineSeg(new Point(0, 1), new Point(3, 4)).intersect(new Point(1, 2)));
            assertFalse(new LineSeg(new Point(1, 0), new Point(3, 0)).intersect(new Point(4, 5)));
            assertTrue(new LineSeg(new Point(0, 1), new Point(3, 4)).intersect(new Point(0, 1)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testLineSegIntersectLineSeg() {
            try {
            assertTrue(new LineSeg(new Point(0, 0), new Point(3, 3)).intersect(new LineSeg(new Point(0, 3), new Point(3, 0))));
            assertFalse(new LineSeg(new Point(0, 0), new Point(3, 3)).intersect(new LineSeg(new Point(0, 3), new Point(-3, 0))));
            assertTrue(new LineSeg(new Point(0, 3), new Point(3, 3)).intersect(new LineSeg(new Point(0, 3), new Point(3, 3))));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testLineSegIntersectCircle() {
            try {
            assertTrue(new LineSeg(new Point(-3, 0), new Point(3, 0)).intersect(new Circle(new Point(0, 0), 2f)));
            assertTrue(new LineSeg(new Point(-3, 0), new Point(3, 0)).intersect(new Circle(new Point(0, 0), 8f)));
            assertFalse(new LineSeg(new Point(-3, 10), new Point(3, 10)).intersect(new Circle(new Point(0, 0), 8f)));
            assertTrue(new LineSeg(new Point(-3, 0), new Point(3, 0)).intersect(new Circle(new Point(0, -2), 2f))); // fail
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testLineSegIntersectRectangle() {
            try {
            assertTrue(new LineSeg(new Point(-3, 1), new Point(3, 1)).intersect(new Rectangle(0, 2, 2, -1)));
            assertTrue(new LineSeg(new Point(-3, 1), new Point(3, 1)).intersect(new Rectangle(-4, 4, 2, -1)));
            assertFalse(new LineSeg(new Point(-3, 3), new Point(3, 3)).intersect(new Rectangle(-4, 4, 2, -1)));
            assertTrue(new LineSeg(new Point(-3, 1), new Point(0, 0)).intersect(new Rectangle(0, 2, 2, -1)));//fail
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }

        }

        // Circle
        @Test
        public void testCircleIntersectPoint() {
            try {
            assertTrue(new Circle(new Point(0, 0), 5f).intersect(new Point(1, 1)));
            assertFalse(new Circle(new Point(0, 0), 5f).intersect(new Point(10, 1)));
            assertTrue(new Circle(new Point(0, 0), 5f).intersect(new Point(0, 5)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testCircleIntersectLineSeg() {
            try {
            assertTrue(new Circle(new Point(0, 0), 2f).intersect(new LineSeg(new Point(-3, 0), new Point(3, 0))));
            assertTrue(new Circle(new Point(0, 0), 8f).intersect(new LineSeg(new Point(-3, 0), new Point(3, 0))));
            assertFalse(new Circle(new Point(0, 0), 8f).intersect(new LineSeg(new Point(-3, 10), new Point(3, 10))));
            assertTrue(new Circle(new Point(0, -2), 2f).intersect(new LineSeg(new Point(-3, 0), new Point(3, 0)))); // fail
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testCircleIntersectCircle() {
            try {
            assertTrue(new Circle(new Point(-1, 0), 3f).intersect(new Circle(new Point(1, 0), 2f)));
            assertFalse(new Circle(new Point(-1, 0), 3f).intersect(new Circle(new Point(4, 0), 1f)));
            assertFalse(new Circle(new Point(-2, 0), 2f).intersect(new Circle(new Point(2, 0), 2f)));
            assertTrue(new Circle(new Point(0, 0), 3f).intersect(new Circle(new Point(0, 0), 1f)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testCircleIntersectRectangle() {
            try {
            assertTrue(new Circle(new Point(0, 0), 1f).intersect(new Rectangle(-3, 3, 3, -3)));
            assertTrue(new Circle(new Point(0, 0), 20f).intersect(new Rectangle(-3, 3, 3, -3)));
            assertTrue(new Circle(new Point(-4, 0), 3f).intersect(new Rectangle(-3, 3, 3, -3)));
            assertFalse(new Circle(new Point(-7, 0), 3f).intersect(new Rectangle(-3, 3, 3, -3)));
            assertFalse(new Circle(new Point(-3, 0), 3f).intersect(new Rectangle(0, 3, 3, -3)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        // Rectangle
        @Test
        public void testRectangleIntersectPoint(){
            try {
            assertTrue(new Rectangle(1, 3, 3, 1).intersect(new Point(2, 2)));
            assertFalse(new Rectangle(1, 3, 3, 1).intersect(new Point(2, 6)));
            assertTrue(new Rectangle(1, 3, 3, 1).intersect(new Point(1, 3)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testRectangleIntersectLineSeg(){
            try {
            assertTrue(new Rectangle(0, 2, 2, -1).intersect(new LineSeg(new Point(-3, 1), new Point(3, 1))));
            assertTrue(new Rectangle(-4, 4, 2, -1).intersect(new LineSeg(new Point(-3, 1), new Point(3, 1))));
            assertFalse(new Rectangle(-4, 4, 2, -1).intersect(new LineSeg(new Point(-3, 3), new Point(3, 3))));
            assertTrue(new Rectangle(0, 2, 2, -1).intersect(new LineSeg(new Point(-3, 1), new Point(0, 0))));//fail
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testRectangleIntersectCircle(){
            try {
            assertTrue(new Rectangle(-3, 3, 3, -3).intersect(new Circle(new Point(0, 0), 1f)));
            assertTrue(new Rectangle(-3, 3, 3, -3).intersect(new Circle(new Point(0, 0), 20f)));
            assertTrue(new Rectangle(-3, 3, 3, -3).intersect(new Circle(new Point(-4, 0), 3f)));
            assertFalse(new Rectangle(-3, 3, 3, -3).intersect(new Circle(new Point(-7, 0), 3f)));
            assertFalse(new Rectangle(0, 3, 3, -3).intersect(new Circle(new Point(-3, 0), 3f)));
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void testRectangleIntersectRectangle(){
            try {
            assertTrue(new Rectangle(-3, 3, 5, -5).intersect(new Rectangle(-8, 0, 3, 1)));
            assertTrue(new Rectangle(-3, 3, 5, -5).intersect(new Rectangle(-3, 3, 5, -5)));
            assertTrue(new Rectangle(-3, 3, 5, -5).intersect(new Rectangle(-2, 2, 6, 4))); 
            assertFalse(new Rectangle(-5, -4, 5, 4).intersect(new Rectangle(4, 5, 3, 2)));
            assertTrue(new Rectangle(-3, 3, 5, -5).intersect(new Rectangle(-1, 1, 1, -1)));
            assertFalse(new Rectangle(-3, 3, 5, -5).intersect(new Rectangle(3, 4, 5, -5))); // fail
            } catch (ShapeArgumentException e){
                System.out.println(e.getMessage());
            }

        }
    
}
