package app;
import processing.core.PApplet;
import processing.core.PVector;

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
    int theBobDimension = 32;
    PVector anchor, theBob;
    PVector velocity;
    PVector gravity;
    float restLength = 200;
    float k = (float) 0.01;

	// Identical use to setup in Processing IDE except for size():
	public void setup() {
        rectMode(CENTER);
		// xPos and yPos of the anchor:
        anchor = new PVector(300, 0);
        // xPos and yPos of the the bob, where the mass is concentrated at a single point:
        theBob = new PVector(300, 250);
        velocity = new PVector(0, 0);
        gravity = new PVector(0, (float)0.1);
	}
	// Identical use to draw in Processing IDE:
	public void draw() {
		background(112, 50, 126);
        strokeWeight(3);
        stroke(255);
        // The spring:
        line(anchor.x, anchor.y, theBob.x, theBob.y);
        fill(45, 197, 244);
        // The anchor:
        triangle(anchor.x -10, anchor.y, anchor.x + 10, anchor.y, anchor.x, anchor.y + 20);
        // The bob:
        circle(theBob.x, theBob.y, theBobDimension);

        if(mousePressed) {
            theBob.x = mouseX;
            theBob.y = mouseY;
            velocity.set(0, 0);
        }

        // Unit vector pointing towards the bob:
        PVector force = PVector.sub(theBob, anchor);
        float x = force.mag() - restLength;
        force.normalize();
        // To reverse the unit vector back to the anchor:
        force.mult(-1 * k * x);

        //double x = theBob.y - restLength;
        // F(spring) = -k(spring constant) * x(extension, or displacement); this equation can give me the magnitude:
        //double force = -k * x;

        // F(orce) = M(ass) * A(cceleration); whereas M = 1:
        velocity.add(force);
        velocity.add(gravity);
        theBob.add(velocity);
        //theBobDimension += velocity;

        // Adding some dampening simulating friction, will lose 1% of its velocity at every frame:
        velocity.mult((float)0.99);
	}

}