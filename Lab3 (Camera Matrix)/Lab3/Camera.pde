/* Lab 3
 * Camera class
 * Maintain Camera transformation for OpenGL
 * Methods 
 * 
 * Note: one strategy is to keep at, LookAt and up as
 * as primary data, and update them when moving, and 
 * then re-derive xc,yc,zc and d from them. This means
 * after each move you call computeCameraMatrix to refresh
 * xc,yc,zc and d
 *
 *    Fan Yang
 */
 
public class Camera {
  
  // Parameters to define camera transformation
  PVector at, lookAt, up;
  // Components of camera transformation 
  PVector zc, xc, yc, d;
  
  // Default camera parameters
  public Camera(){
    at = new PVector(0,0,300);
    lookAt = new PVector(width/2,height/2,0);
    up = new PVector(0,1,0); 
  }
 
  // Compute elements of camera matrix xc,yc,zc and d
  public void computeCameraMatrix(){
    zc = PVector.sub(at, lookAt).normalize();
    xc = up.cross(zc).normalize();
    yc = zc.cross(xc);
    d = new PVector(-at.dot(xc), -at.dot(yc),-at.dot(zc));
  }
 
  // Apply camera matrix to the OpenGL transform matrix
  // Uncomment this after computeCameraMatrix is complete
  public void setCameraMatrix(){
    beginCamera();
      resetMatrix();
      applyMatrix(xc.x, xc.y, xc.z, d.x, 
                  yc.x, yc.y, yc.z, d.y,
                  zc.x, zc.y, zc.z, d.z,
                  0.0, 0.0, 0.0, 1 );
    endCamera();
  }
  
  // Move the camera forward s units
  public void forwardCamera(float s) {
    d.z += 10*s;
    setCameraMatrix();
    //an alternative implementation
    //at.add(PVector.mult(zc, -10*s));
    //computeCameraMatrix();
    //setCameraMatrix();
  }
  
  public void slideLeftRight(float s) {
    d.x -= 10*s;
    setCameraMatrix();
    //an alternative implementation
    //at.add(PVector.mult(xc, 10*s));
    //lookAt.add(PVector.mult(xc, 10*s));
    //computeCameraMatrix();
    //setCameraMatrix();
  }
  
  public void slideUpDown(float s) {
    d.y -= 10*s;
    setCameraMatrix();
    //an alternative implementation
    //at.add(PVector.mult(yc, 10*s));
    //lookAt.add(PVector.mult(yc, 10*s));
    //computeCameraMatrix();
    //setCameraMatrix();
  }
  
  // Rotate the camera around camera axis (zc) 
  // positive t radians
  public void rollCamera(float t){
    PVector xc_new = PVector.mult(xc, cos(t)).add(PVector.mult(yc, sin(t)));
    yc = PVector.mult(xc, -sin(t)).add(PVector.mult(yc, cos(t)));
    xc = xc_new;
    float dx = d.x * cos(t) + d.y * sin(t);
    d.y = -d.x * sin(t) + d.y * cos(t);
    d.x = dx;
    setCameraMatrix();
  }
  
  public void pitchCamera(float s){
    PVector yc_new = PVector.mult(yc, cos(s)).add(PVector.mult(zc, sin(s)));
    zc = PVector.mult(yc, -sin(s)).add(PVector.mult(zc, cos(s)));
    yc = yc_new;
    float dy = d.y * cos(s) + d.z * sin(s);
    d.z = -d.y * sin(s) + d.z * cos(s);
    d.y = dy;
    setCameraMatrix();
  }
  
  public void yawCamera(float s){
    PVector zc_new = PVector.mult(zc, cos(s)).add(PVector.mult(xc, sin(s)));
    xc = PVector.mult(zc, -sin(s)).add(PVector.mult(xc, cos(s)));
    zc = zc_new;
    float dz = d.z * cos(s) + d.x * sin(s);
    d.x = -d.z * sin(s) + d.x * cos(s);
    d.z = dz;
    setCameraMatrix();
  }
  
  public void homeCamera(){
    // Computer and apply the camera transformation matrix from at, lookAt, up
    cam.computeCameraMatrix();
    cam.setCameraMatrix();
  }
}