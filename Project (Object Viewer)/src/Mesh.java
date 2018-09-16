// Project 1 initial source code
// CMSC427 fall 2017

// Class Mesh now handles both setting up vertices
// and rendering a mesh
// As superclass it handles functions common to all
// mesh objects
// Expects meshes to be a single array of Vertice3D 
// objects, not indexed mesh data structure

// But the class is not efficient
// It sets up vertices and VBOs for each display call
// Should set up vertices and VBOs only once at creation

import graphicslib3D.Vertex3D;

public class Mesh
{
    // Model vertices
	protected Vertex3D [] vertices;
	protected int numVertices;
	public float[] pvalues;
	public float[] tvalues;
	public float[] nvalues;


	public void setupVertices()
	{
		pvalues = new float[numVertices*3];
		tvalues = new float[numVertices*2];
		nvalues = new float[numVertices*3];

		for (int i=0; i<numVertices; i++)
		{	pvalues[i*3]   = (float) (vertices[i]).getX();
			pvalues[i*3+1] = (float) (vertices[i]).getY();
			pvalues[i*3+2] = (float) (vertices[i]).getZ();
			tvalues[i*2]   = (float) (vertices[i]).getS();
			tvalues[i*2+1] = (float) (vertices[i]).getT();
			nvalues[i*3]   = (float) (vertices[i]).getNormalX();
			nvalues[i*3+1] = (float) (vertices[i]).getNormalY();
			nvalues[i*3+2] = (float) (vertices[i]).getNormalZ();
		}
	}
}
