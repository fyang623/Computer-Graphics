float theta = 0;

void setup() {
size(400,400,P3D);
fill(255,0,255);
}

void draw() {
background(255);

translate(width/2,height/2,0);

rotateZ(theta);
rotateX(theta);
rotateZ(theta);
box(100);
theta += 0.01;
}

void keyPressed() {
  save("box.jpg");
}
