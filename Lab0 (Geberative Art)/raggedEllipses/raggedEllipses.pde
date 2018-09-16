// CMSC427
// File: Circle.pde
// R. Eastman
// Parametric circle

  size(1000,800);
  float ellipse_width = 300;
  float ellipse_height = 100;
  background(0);
  strokeWeight(5);
  float red = 255;
  float angleStep = 20;
  for (float t = 0; t < 360; t += angleStep) {
    float x = width/2 + 100 * cos(radians(angleStep*frameCount + t));
    float y = height/2 + 100 * sin(radians(angleStep*frameCount + t));
    stroke(random(255), random(255), random(255));
    noFill();
    translate(x, y);
    rotate(radians(angleStep*frameCount + t));
    beginShape();
    for(float ang = 0; ang< 360; ang += 20){
      float _x = cos(radians(ang)) * ellipse_width / 2 + random(cos(radians(ang)) * (2*PI - radians(ang)) * ellipse_width) / 10;
      float _y = sin(radians(ang)) * ellipse_height / 2 + random(sin(radians(ang)) * (2*PI - radians(ang)) * ellipse_height) / 10;
      vertex(_x, _y);
    }
    endShape(CLOSE);
    rotate(-radians(angleStep*frameCount + t));
    translate(-x, -y);
  }
  save("raggedEllipses.jpg");