/* Lab 3
 * Lab3Camera class
 * 
 * Test driver for Camera class
 * Illustrates Camera class methods
 *
 *     Fan Yang
 */

Camera cam = new Camera();

void setup() {
  size(600,600,P3D);
  smooth();
  noFill();
  strokeWeight(2);
  // Compute the camera transformation matrix
  cam.computeCameraMatrix();
  // Computer and apply the camera transformation matrix
  cam.setCameraMatrix();
}
    
void draw() {
  background(255);
  //translate(width/2,height/2,0);
  // Modeling transformations
  pushMatrix();
  translate(120,0,0);
  box(80);
  popMatrix();
  box(160);
}

// Handle keyPressed events
// Each motion happens only once when key is pressed
void keyPressed() {
  
   if (key == 'f') // Move forward
      cam.forwardCamera( 1 );
      
   else if (key == 'b')   // Move back
      cam.forwardCamera( -1 );
      
   else if (key == 'l')   // turn left
      cam.slideLeftRight(-1);
   
   else if (key == 'r')   // turn right
      cam.slideLeftRight(1);
   
   else if (key == 'u')   // slide up
      cam.slideUpDown(-1);
   
   else if (key == 'd')   // slide down
      cam.slideUpDown(1);
   
   else if (key == 't')   // roll right
     cam.rollCamera(0.05);
   
   else if (key == 'y')   // roll left
     cam.rollCamera(-0.05);
   
   else if (key == 'w')   // pitch down
     cam.pitchCamera(0.05);
   
   else if (key == 'z')   // pitch up
     cam.pitchCamera(-0.05);
   
   else if (key == 'e')   // yaw left
     cam.yawCamera(0.05);
   
   else if (key == 'x')   // yaw right
     cam.yawCamera(-0.05);
   
   else if (key == 'h')   // Home back to the initial, default camera position
     cam.homeCamera();
}