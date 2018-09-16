// Lab2: Part II
void setup() {
  size(640, 360, P3D);
}

void draw() {
  background(255); 
         
  camera(width/2, height/2, (height/2) / tan(PI/6),  //At
         width/2, height/2, 0,                       //LookAt
         0, 1, 0);                                   //Up
  translate(width/2, height/2, -100);
  //printCamera();
  stroke(0);
  noFill();
  scale(2, 1);
  box(100);
  
  //camera(width/2, height/2, (height/2) / tan(PI/6),  //At
  //       width/2, height/2, 0,                       //LookAt
  //       1, 0, 0);                                   //Up
  //translate(width/2, height/2, -100);
  ////printCamera();
  //stroke(0);
  //noFill();
  //scale(2, 1);
  //box(100);
}