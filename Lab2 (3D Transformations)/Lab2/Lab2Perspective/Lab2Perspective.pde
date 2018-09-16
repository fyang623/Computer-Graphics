// Lab 2: Part III 
void setup() {
  size(200, 200, P3D);
  noFill();
}

void draw() {
  background(255);
  float fov = PI/(map(mouseX,0,width,2,5));
  float cameraZ = (height/2.0) / tan(fov/2.0);
  //perspective(fov, float(width)/float(height), 
  //            mouseY*2, cameraZ*1.0);
  perspective(fov, float(width)/(1*float(height)), 
              mouseY/2, cameraZ*10.0);
  translate(100, 100, -100);
  rotateX(-PI/6);
  rotateY(PI/3);
  box(100);
}