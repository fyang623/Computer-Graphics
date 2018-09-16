float theta = 0;
float theta2 = 0;

void setup() {
size(400,400,P3D);
fill(255,0,255);
rectMode(CENTER);
}

void draw() {
//background(255);

translate(width/2,height/2,0);
rotateZ(theta);
rotateX(theta2);
translate(80,0,0);
pushMatrix();
rotateZ(theta2);

box(50);
popMatrix();
box(50);
theta += 0.01;
theta2 += 0.1;
}


void keyPressed() {
  save("box.jpg");
}
