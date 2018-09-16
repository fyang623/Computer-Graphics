// CMSC427 Lab 4 Fall 2017
// HelixFly2.pde
// Draw a parametric "crown" in 3D
// Use Frenet frame to align boxes with curve 
// R. Eastman & Fan Yang

void setup() {
  size(1000,600,P3D);
  colorMode(HSB,360,100,100);
}
  
void draw() {
  background(0,0,0);
  translate(width/2,height/2);
  // rotate the 3D shape using the accumulated change of the mouse position. 
  rotateY(rx*PI/width);
  rotateX(ry*PI/height);
  for(float t = 0; t < 2*PI; t+=PI/120){
    float x = 200*cos(t);
    float y = 100*sin(10*t);
    float z = 200*sin(t);
    fill(1.3*(x+100),100,100,255);
    // We push the matrix for each box so translations don't accumulate
    pushMatrix(); 
    translate(x,y,z);
    applyFrenet(t); // Correct the method and then uncomment this
    scale(1,1,2);
    box(10);
    popMatrix();
  }
}

// Accumulate the change of mouse position as the mouse moves. 
// The accumulated changes in x and y directions are assigned to rx and ry, respectively
int mx, my, rx = 0, ry = 0;
void mouseMoved(MouseEvent e) {
  //when the mouse enters the canvas, initialize mx & my
  if(e.getX()<10 || e.getX()>width-10 || e.getY()<10 || e.getY()>height-10){
    mx = e.getX();
    my = e.getY();
    return;
  } 
  
  // Accumulate the change of mouse position as the mouse moves. 
  int dx = e.getX() - mx;
  int dy = e.getY() - my;
  if(dx*dx + dy*dy > 10) {
    rx += dx;
    ry += dy;
    mx = e.getX();
    my = e.getY();
  }
}

// Method to create a local coordinate system
// and then generate a rotation matrix
PVector T = new PVector(), N = new PVector(), B = new PVector();
void applyFrenet(float t) {
  // Tangent vector = <-sint, 5cos(10t), cost>
  T.set(-sin(t), 5*cos(10*t), cos(t)).normalize();

  // Curvature vector = <-cost, -50sin(10t), -sint>. This curvature vector is not orthogonal to the tantgent
  N.set(-cos(t), -50*sin(10*t), -sin(t));

  // Binormal
  B = T.cross(N).normalize();
  
  // recalculate Curvature vector using N = B x T so that T and N become perpendicular to each other
  N = B.cross(T);
  
  // ascert that the three vectors are of unit lengths and are perpendicular to each other
  assertEquals(T.mag(), 1.0);
  assertEquals(N.mag(), 1.0);
  assertEquals(B.mag(), 1.0); 
  assertEquals(T.dot(N), 0.0);
  assertEquals(N.dot(B), 0.0);
  assertEquals(B.dot(T), 0.0);
       
  applyMatrix(B.x, N.x, T.x, 0,
              B.y, N.y, T.y, 0,
              B.z, N.z, T.z, 0,
              0,  0,  0,  1);
}

void assertEquals(float a, float b){
  if(abs(a-b) > 0.000001){
    println("not equal!");
    System.exit(-1);
  }
}