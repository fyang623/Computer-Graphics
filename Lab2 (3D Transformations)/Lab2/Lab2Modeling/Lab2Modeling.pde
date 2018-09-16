// Lab2: Part I
size(400,400,P3D);
translate(width/2,height/2,0);
pushMatrix();
rotateX(PI/4);
rotateY(PI/4);
box(50);
popMatrix();

pushMatrix();
translate(-width/4, 0, 0);
rotateX(PI/6);
rotateZ(PI/3);
shearY(PI/6);
box(50);
popMatrix();

pushMatrix();
translate(width/4, 0, 0);
rotateZ(PI/6);
rotateX(PI/3);
shearX(PI/6);
box(50);
popMatrix();

//printMatrix();