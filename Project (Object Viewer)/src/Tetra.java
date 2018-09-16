import graphicslib3D.Vertex3D;

public class Tetra extends Mesh {
    float[] verts, tcs, normals;

    public Tetra() {
        numVertices = 12;
        setupVerts();
        vertices = new Vertex3D[numVertices];
        for (int i = 0; i < numVertices; i++) {
            vertices[i] = new Vertex3D();
            vertices[i].setLocation(verts[i * 3], verts[i * 3 + 1], verts[i * 3 + 2]);
            vertices[i].setST(tcs[i * 2], tcs[i * 2 + 1]);
            vertices[i].setNormal(normals[i * 3], normals[i * 3 + 1], normals[i * 3 + 2]);
        }
        setupVertices();
    }

    private void setupVerts() {
        float u = (float) Math.sqrt(3);
        float v = (float) Math.sqrt(6);
        float w = (float) Math.sqrt(2);

        float[] verts =
        {
            1.0f, -u/3, 0.0f,       -1.0f,  -u/3, 0.0f,   0.0f, u*2/3, 0.0f,
            -1.0f,  -u/3, 0.0f,     1.0f, -u/3, 0.0f,     0.0f, 0.0f, v*2/3,
            -1.0f,  -u/3, 0.0f,     0.0f, 0.0f, v*2/3,    0.0f, u*2/3, 0.0f,
            1.0f,  -u/3, 0.0f,      0.0f, u*2/3, 0.0f,    0.0f, 0.0f, v*2/3
        };

        float[] normals =
        {
            0.0f, 0.0f, -1.0f,      0.0f, 0.0f, -1.0f,      0.0f, 0.0f, -1.0f,
            0.0f, -w*2/3, 1/3,      0.0f, -w*2/3, 1/3,      0.0f, -w*2/3, 1/3,
            -v/3, w/3, 1/3,         -v/3, w/3, 1/3,         -v/3, w/3, 1/3,
            v/3, w/3, 1/3,          v/3, w/3, 1/3,          v/3, w/3, 1/3
        };

        float[] tcs =
        {
            0.0f, 1.0f,         1.0f, 0.0f,         0.0f, 0.0f,
            1.0f, 0.0f,         0.0f, 1.0f,         1.0f, 1.0f,
            1.0f, 0.0f,         0.0f, 0.0f,         1.0f, 1.0f,
            0.0f, 1.0f,         1.0f, 1.0f,         0.0f, 0.0f,
        };

        this.verts = verts;
        this.normals = normals;
        this.tcs = tcs;
    }
}


