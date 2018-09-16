// CMSC427 Lab 4 Fall 2017
// NewShape.pde
// Draw a parametric new shape in 3D
// R. Eastman & Fan Yang

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
  // Create the cylinder
  for (float t = 0; t < 2*PI; t += PI/15) 
    for(float s = 0; s < 10; s++){
      // decrease the value of x and y as s increases
      float x = 100*cos(t)*(10-s)/10;
      float y = 50*s;
      float z = 100*sin(t)*(10-s)/10;
      // set the saturation parameter to 100 to avoid glitches of the color interpolation
      fill(1.3*(x+100),100,100,128);
      // We push the matrix for each box so translations don't accumulate
      pushMatrix();
        translate(x,250-y,z);
        // rotate the boxes so that they sit head to head
        rotateY(-t);
        scale(1,1,2);
        // decrease the size of the boxes as s increases
        box(10-s);
      popMatrix();
    }
}

void keyPressed() {
  save("boxes.jpg");
}