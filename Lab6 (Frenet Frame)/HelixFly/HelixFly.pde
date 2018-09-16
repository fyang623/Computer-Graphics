// CMSC427 Lab 6 Fall 2017
// HelixFly.pde
// Draw a parametric helix in 3D
// Use Frenet frame to align boxes with curve
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
    // Comment and uncomment the following to see the change
    applyFrenet(t); 
    scale(1,1,2);
    box(10);
    popMatrix();
  }
}

// Method to create a local coordinate system
// and then generate a rotation matrix
// This version is valid for a helix curve
void applyFrenet(float t){
    // Tangent
    float tx = -10*sin(t)/sqrt(101);
    float ty = -1/sqrt(101);   
    float tz = 10*cos(t)/sqrt(101);
    // Curvature
    float nx = -cos(t);   
    float ny = 0;   
    float nz = -sin(t);
    // Binormal
    float bx = sin(t)/sqrt(101);
    float by = -10/sqrt(101);
    float bz = -cos(t)/sqrt(101);
    applyMatrix(bx, nx, tx, 0,
                by, ny, ty, 0,
                bz, nz, tz, 0,
                0,  0,  0,  1);
}