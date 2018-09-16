// Project 1 initial source code
// CMSC427 fall 2017

// ModelView class
// From Gordon program 6_1

import com.jogamp.common.nio.Buffers;
import graphicslib3D.*;
import java.io.*;
import java.nio.FloatBuffer;
import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.texture.*;

public class Code extends JFrame implements GLEventListener
{
	private GLCanvas myCanvas;
	private int rendering_program, texture;
	private int vbo[] = new int[3];
	private int mv_loc, proj_loc, camera_loc;
	private float cameraX, cameraY, cameraZ;
	private float objLocX, objLocY, objLocZ;
	private Texture joglTexture;
	private Matrix3D pMat, vMat, mMat, mvMat, rMat = new Matrix3D();
	private Mesh myObj;
	private GL4 gl;
	private boolean modelChanged = false;

	public Code(Controller myController)
	{
		setTitle("Chapter6 - program3");
		setSize(800, 800);
		// ADDED FOR MACS
		GLProfile glp = GLProfile.getMaxProgrammableCore(true);
		GLCapabilities caps = new GLCapabilities(glp);
		myCanvas = new GLCanvas(caps);
		//myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		myCanvas.addMouseListener(myController);
		myCanvas.addMouseMotionListener(myController);
		myCanvas.addKeyListener(myController);
		myCanvas.setFocusable(true);
		myCanvas.requestFocus();
		getContentPane().add(myCanvas);
		this.setVisible(true);
	}


	public void display(GLAutoDrawable drawable)
	{
		setupShaderUniform();

		// Object renders itself
		// Needs gl for OpenGL context
		// Could own its shader program and texture
		if(modelChanged) {
			setupShaderIn();
			modelChanged = false;
		}
		render();
	}


	private void setupShaderUniform()
	{
		float aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
		pMat = perspective(60.0f, aspect, 0.1f, 1000.0f);

		vMat = new Matrix3D();
		vMat.translate(-cameraX, -cameraY, -cameraZ);

		mMat = new Matrix3D();
		mMat.translate(objLocX, objLocY, objLocZ);
		mMat.rotateY(135.0f);

		// For rotation
		mMat.concatenate(rMat.transpose());
		mMat.scale(1.5f,1.5f,1.5f);

		mvMat = new Matrix3D();
		mvMat.concatenate(vMat);
		mvMat.concatenate(mMat);

		gl.glUniformMatrix4fv(mv_loc, 1, false, mvMat.getFloatValues(), 0);
		gl.glUniformMatrix4fv(proj_loc, 1, false, pMat.getFloatValues(), 0);
		gl.glUniform3f(camera_loc, cameraX, cameraY, cameraZ);
	}


	private void setupShaderIn()
	{
		gl.glGenBuffers(vbo.length, vbo, 0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		FloatBuffer vertBuf = Buffers.newDirectFloatBuffer(myObj.pvalues);
		gl.glBufferData(GL_ARRAY_BUFFER, vertBuf.limit()*4, vertBuf, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
		FloatBuffer texBuf = Buffers.newDirectFloatBuffer(myObj.tvalues);
		gl.glBufferData(GL_ARRAY_BUFFER, texBuf.limit()*4, texBuf, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[2]);
		FloatBuffer norBuf = Buffers.newDirectFloatBuffer(myObj.nvalues);
		gl.glBufferData(GL_ARRAY_BUFFER, norBuf.limit()*4,norBuf, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
		gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(1);
	}


	private void render()
	{
		gl.glClear(GL_DEPTH_BUFFER_BIT);
		gl.glClear(GL_COLOR_BUFFER_BIT);

		gl.glActiveTexture(GL_TEXTURE0);
		gl.glBindTexture(GL_TEXTURE_2D, texture);
		// added for Macs
		gl.glUniform1i(gl.glGetUniformLocation(rendering_program, "samp"), 0);

		gl.glEnable(GL_CULL_FACE);
		//gl.glEnable(GL_DEPTH_TEST);
		gl.glFrontFace(GL_CCW);
		gl.glDrawArrays(GL_TRIANGLES, 0, myObj.vertices.length);
	}


	public void init(GLAutoDrawable drawable)
	{
		gl = (GL4) GLContext.getCurrentGL();
		myObj = new ImportedModel("shuttle.obj");
		rendering_program = createShaderProgram();

		cameraX = 0.0f; cameraY = 0.0f; cameraZ = 2.0f;
		objLocX = 0.0f; objLocY = 0.0f; objLocZ = 0.0f;

		joglTexture = loadTexture("spstob_1.jpg");
		texture = joglTexture.getTextureObject();

		mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
		proj_loc = gl.glGetUniformLocation(rendering_program, "proj_matrix");
		camera_loc = gl.glGetUniformLocation(rendering_program, "camera_loc");

		gl.glUseProgram(rendering_program);

		setupShaderIn();
	}


	private Matrix3D perspective(float fovy, float aspect, float n, float f)
	{
		float q = 1.0f / ((float) Math.tan(Math.toRadians(0.5f * fovy)));
		float A = q / aspect;
		float B = (n + f) / (n - f);
		float C = (2.0f * n * f) / (n - f);
		Matrix3D r = new Matrix3D();
		r.setElementAt(0,0,A);
		r.setElementAt(1,1,q);
		r.setElementAt(2,2,B);
		r.setElementAt(3,2,-1.0f);
		r.setElementAt(2,3,C);
		r.setElementAt(3,3,0.0f);
		return r;
	}

	// Added for MVC
	// Called by controller when model changes
	void redraw() {
		myCanvas.repaint();
	}

	void setRotation(double degrees, Vector3D axis) {
		rMat.rotate(degrees,axis);
	}

	void setCamera(float x, float y, float z){
		cameraX = x;
		cameraY = y;
		cameraZ = z;
	}

	void setModel(Mesh obj){
		myObj = obj;
		modelChanged = true;
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}

	private int createShaderProgram()
	{
		String vshaderSource[] = GLSLUtils.readShaderSource("code/vert.shader");
		String fshaderSource[] = GLSLUtils.readShaderSource("code/frag.shader");

		int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
		int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

		gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
		gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);

		gl.glCompileShader(vShader);
		gl.glCompileShader(fShader);

		int vfprogram = gl.glCreateProgram();
		gl.glAttachShader(vfprogram, vShader);
		gl.glAttachShader(vfprogram, fShader);
		gl.glLinkProgram(vfprogram);
		return vfprogram;
	}
	
	private Texture loadTexture(String textureFileName)
	{
		Texture tex = null;
		try { tex = TextureIO.newTexture(new File(textureFileName), false); }
		catch (Exception e) { e.printStackTrace(); }
		return tex;
	}

	public GLCanvas getCanvas(){
		return myCanvas;
	}
}