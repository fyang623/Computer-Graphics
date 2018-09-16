// Project 1 initial source code
// CMSC427 fall 2017

// Class Controller handles all GUI events
//

import graphicslib3D.Vector3D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

// An Adapter is a default implementation of a listener, so you don't have implement all 
// required methods
// An Interface Listener requires that you do
public class Controller extends MouseMotionAdapter implements KeyListener, MouseListener {
	int mx, my, width, height, radius;
	Code myView;

	// Need to add reference to ModelView object
	// So as to access redraw() and setAngle()
	public void addCode(Code c) {
		myView = c;
		width = myView.getCanvas().getWidth();
		height = myView.getCanvas().getHeight();
		radius = width/2;
	}

	public void mouseReleased(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {}

	// Only function event at moment
	public void mouseDragged(MouseEvent e) {
		int dx = e.getX() - mx;
		int dy = e.getY() - my;
		if(dx*dx + dy*dy < 25) return;
		if(insideCircle(mx, my) && insideCircle(e.getX(), e.getY())) {
			Vector3D u1 = setVector(mx, my);
			Vector3D u2 = setVector(e.getX(), e.getY());
			Vector3D axis = u1.cross(u2).normalize();
			double degrees = Math.acos(u1.dot(u2)) / Math.PI * 180.0;
			myView.setRotation(degrees, axis);
			myView.redraw();
		}
		mx = e.getX();
		my = e.getY();
	}

	public void mouseMoved(MouseEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		if(c == 'i') {
			myView.setCamera(0, 0, 2);
			myView.setModel(new ImportedModel("shuttle.obj"));
		} else if(c == 't') {
			myView.setCamera(0, 0, 5);
			myView.setModel(new Tetra());
		}else if(c == 'c') {
			myView.setCamera(0, 0, 10);
			myView.setModel(new Cylinder());
		}
		myView.redraw();
	}

	/** Handle the key-released event from the text field. */
	public void keyReleased(KeyEvent e) {

	}

	private boolean insideCircle(float x, float y){
		x -= width/2;
		y -= height/2;
		return x*x + y*y < radius*radius;
	}

	private Vector3D setVector(float x, float y){
		x -= width/2;
		y -= height/2;
		float z = (float) -Math.sqrt(radius*radius - x*x - y*y);
		return new Vector3D(x,y,z).normalize();
	}
}
