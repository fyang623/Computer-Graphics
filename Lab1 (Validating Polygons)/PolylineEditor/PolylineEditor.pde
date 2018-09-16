// CMSC427
// File: PolylineEditor.pde
// Fan Yang
// Polyline editor main

Polyline polyline;

void setup() {
  size(400,400);
  polyline = new Polyline();
}

void draw() {
  background(255);
  noFill();
  polyline.draw();
}

void keyPressed() {
  if (key == 'c') 
     polyline.close();
  else if (key == 'o')
     polyline.open();
  // save polyline to "polyline.dat" if 's' is pressed. 
  else if (key == 's')
     polyline.save();
  // retrieve polyline information from "polyline.dat" if 'r' is pressed.
  else if (key == 'r')
    polyline.retrieve();
  // delete a point from the polygon if 'd' is pressed.
  else if (key == 'd')
    polyline.delete();
  // output the result of isSimple() in the console if '1' is pressed.
  else if (key == '1')
    println("isSimple():  " + polyline.isSimple());
  // output the result of isConvex() in the console if '2' is pressed.
  else if (key == '2')
    println("isConvex():  " + polyline.isConvex());
  // output the result of isCW(), i.e. whether the polygon is clockwise, in the console if '3' is pressed.
  else if (key == '3')
    println("isCW():  " + polyline.isCW());
}

void mousePressed() {
  if (mouseButton == LEFT) 
    polyline.add(mouseX,mouseY);
  else if (mouseButton == RIGHT)
    polyline.pick(mouseX,mouseY);
}

void mouseDragged() {
  polyline.pickUpdate(mouseX,mouseY);
}

void mouseReleased() {
  polyline.pickRelease();
}