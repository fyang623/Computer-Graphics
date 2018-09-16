// CMSC427 Lab 4 Fall 2017
// HelixFly.pde
// Draw a parametric helix in 3D
// R. Eastman

void setup() {
size(600,600,P3D);
// Use Hue Saturation Brightness (HSB)
// to interpolate colors
colorMode(HSB,360,100,100);
}

void draw() {
background(0,0,0);
// Position the helix for viewing
translate(width/2,height/2);
rotateY(mouseX*PI/width);
rotateX(mouseY*PI/height);
// Create the helix
for (float t = 0; t < 10*2*PI; t+=0.2) {
    float x = 100*cos(t);
    float y =  10*t;
    float z = 100*sin(t);
    fill(1.3*(x+100),10*y,100,128);
    // We push the matrix for each box so translations don't accumulate
    pushMatrix();
      translate(x,400-y,z);
      scale(1,1,2);
      box(10);
    popMatrix();
  }
}

void keyPressed() {
  save("boxes.jpg");
}