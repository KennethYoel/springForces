package app;
import processing.core.PApplet;

public class processing extends PApplet {
    // The argument passed to main must match the class name:
	public static void main(String[] args) {
		PApplet.main("app.processing");
	}
	// Method for setting the size of the window:
	public void settings() {
		size(600, 400);
	}
    // Properties:
    int y = 250;
    double velocity = 0.0;
    double restLength = 200;
    double k = 0.01;

	// Identical use to setup in Processing IDE except for size():
	public void setup() {
		
	}
	// Identical use to draw in Processing IDE:
	public void draw() {
		background(112, 50, 126);
        noStroke();
        fill(45, 197, 244);
        circle(300, y, 64);

        double x = y - restLength;
        double force = -k * x;

        // F(orce) = M(ass) * A(cceleration); whereas M = 1:
        velocity += force;
        y += velocity;

        // Adding some dampening simulating friction, will lose 1% of its velocity at every frame:
        velocity *= 0.99;
	}

}