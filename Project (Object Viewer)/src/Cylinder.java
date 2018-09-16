import graphicslib3D.Vertex3D;

public class Cylinder extends Mesh {
    float[] verts, tcs, normals;

    public Cylinder() {
        setupVerts();
        vertices = new Vertex3D[numVertices];
        for (int i = 0; i < numVertices; i++) {
            vertices[i] = new Vertex3D();
            vertices[i].setLocation(verts[i*3], verts[i*3+1], verts[i*3+2]);
            vertices[i].setST(tcs[i*2], tcs[i*2+1]);
            vertices[i].setNormal(normals[i*3], normals[i*3+1], normals[i*3+2]);
        }
        setupVertices();
    }

    private void setupVerts() {
        int numSlices = 5, numStacks = 20;
        numVertices = numSlices*numStacks*6 + numStacks*6;
        verts = new float[numVertices*3];
        normals = new float[numVertices*3];
        tcs = new float[numVertices*2];
        for(int i = 0; i < numSlices; i++)
            for (int j = 0; j < numStacks; j++) {
                verts[(i*numStacks+j)*18] = (float) Math.cos(Math.PI*2*j/numStacks);
                verts[(i*numStacks+j)*18+1] = (float) Math.sin(Math.PI*2*j/numStacks);
                verts[(i*numStacks+j)*18+2] = i - numSlices/2.0f;
                normals[(i*numStacks+j)*18] = (float) Math.cos(Math.PI*2*j/numStacks);
                normals[(i*numStacks+j)*18+1] = (float) Math.sin(Math.PI*2*j/numStacks);
                normals[(i*numStacks+j)*18+2] = 0;
                tcs[(i*numStacks+j)*12] = 1.0f*j/numStacks;
                tcs[(i*numStacks+j)*12+1] = 1 - 1.0f*i/numSlices;

                verts[(i*numStacks+j)*18+3] = (float) Math.cos(Math.PI*2*(j+1)/numStacks);
                verts[(i*numStacks+j)*18+4] = (float) Math.sin(Math.PI*2*(j+1)/numStacks);
                verts[(i*numStacks+j)*18+5] = i - numSlices/2.0f;
                normals[(i*numStacks+j)*18+3] = (float) Math.cos(Math.PI*2*(j+1)/numStacks);
                normals[(i*numStacks+j)*18+4] = (float) Math.sin(Math.PI*2*(j+1)/numStacks);
                normals[(i*numStacks+j)*18+5] = 0;
                tcs[(i*numStacks+j)*12+2] = 1.0f*(j+1)/numStacks;
                tcs[(i*numStacks+j)*12+3] = 1 - 1.0f*i/numSlices;

                verts[(i*numStacks+j)*18+6] = (float) Math.cos(Math.PI*2*(j+1)/numStacks);
                verts[(i*numStacks+j)*18+7] = (float) Math.sin(Math.PI*2*(j+1)/numStacks);
                verts[(i*numStacks+j)*18+8] = i - numSlices/2.0f + 1;
                normals[(i*numStacks+j)*18+6] = (float) Math.cos(Math.PI*2*(j+1)/numStacks);
                normals[(i*numStacks+j)*18+7] = (float) Math.sin(Math.PI*2*(j+1)/numStacks);
                normals[(i*numStacks+j)*18+8] = 0;
                tcs[(i*numStacks+j)*12+4] = 1.0f*(j+1)/numStacks;
                tcs[(i*numStacks+j)*12+5] = 1 - 1.0f*(i+1)/numSlices;

                verts[(i*numStacks+j)*18+9] = (float) Math.cos(Math.PI*2*j/numStacks);
                verts[(i*numStacks+j)*18+10] = (float) Math.sin(Math.PI*2*j/numStacks);
                verts[(i*numStacks+j)*18+11] = i - numSlices/2.0f;
                normals[(i*numStacks+j)*18+9] = (float) Math.cos(Math.PI*2*j/numStacks);
                normals[(i*numStacks+j)*18+10] = (float) Math.sin(Math.PI*2*j/numStacks);
                normals[(i*numStacks+j)*18+11] = 0;
                tcs[(i*numStacks+j)*12+6] = 1.0f*j/numStacks;
                tcs[(i*numStacks+j)*12+7] = 1 - 1.0f*i/numSlices;

                verts[(i*numStacks+j)*18+12] = (float) Math.cos(Math.PI*2*(j+1)/numStacks);
                verts[(i*numStacks+j)*18+13] = (float) Math.sin(Math.PI*2*(j+1)/numStacks);
                verts[(i*numStacks+j)*18+14] = i - numSlices/2.0f + 1;
                normals[(i*numStacks+j)*18+12] = (float) Math.cos(Math.PI*2*(j+1)/numStacks);
                normals[(i*numStacks+j)*18+13] = (float) Math.sin(Math.PI*2*(j+1)/numStacks);
                normals[(i*numStacks+j)*18+14] = 0;
                tcs[(i*numStacks+j)*12+8] = 1.0f*(j+1)/numStacks;
                tcs[(i*numStacks+j)*12+9] = 1 - 1.0f*(i+1)/numSlices;

                verts[(i*numStacks+j)*18+15] = (float) Math.cos(Math.PI*2*j/numStacks);
                verts[(i*numStacks+j)*18+16] = (float) Math.sin(Math.PI*2*j/numStacks);
                verts[(i*numStacks+j)*18+17] = i - numSlices/2.0f + 1;
                normals[(i*numStacks+j)*18+15] = (float) Math.cos(Math.PI*2*j/numStacks);
                normals[(i*numStacks+j)*18+16] = (float) Math.sin(Math.PI*2*j/numStacks);
                normals[(i*numStacks+j)*18+17] = 0;
                tcs[(i*numStacks+j)*12+10] = 1.0f*j/numStacks;
                tcs[(i*numStacks+j)*12+11] = 1 - 1.0f*(i+1)/numSlices;
            }


        for(int k = 0; k < numStacks; k++){
            int p = numSlices*numStacks*6 + k*3;
            verts[3*p] = (float) Math.cos(Math.PI*2*k/numStacks);
            verts[3*p+1] = (float) Math.sin(Math.PI*2*k/numStacks);
            verts[3*p+2] = numSlices/2.0f;
            normals[3*p] = 0.0f;
            normals[3*p+1] = 0.0f;
            normals[3*p+2] = 1.0f;
            tcs[2*p] = (float) (Math.cos(Math.PI*2*k/numStacks) + 1)/2;
            tcs[2*p+1] = (float) (Math.sin(Math.PI*2*k/numStacks) + 1)/2;

            verts[3*p+3] = (float) Math.cos(Math.PI*2*(k+1)/numStacks);
            verts[3*p+4] = (float) Math.sin(Math.PI*2*(k+1)/numStacks);
            verts[3*p+5] = numSlices/2.0f;
            normals[3*p+3] = 0.0f;
            normals[3*p+4] = 0.0f;
            normals[3*p+5] = 1.0f;
            tcs[2*p+2] = (float) (Math.cos(Math.PI*2*(k+1)/numStacks) + 1)/2;
            tcs[2*p+3] = (float) (Math.sin(Math.PI*2*(k+1)/numStacks) + 1)/2;

            verts[3*p+6] = 0.0f;
            verts[3*p+7] = 0.0f;
            verts[3*p+8] = numSlices/2.0f;
            normals[3*p+6] = 0.0f;
            normals[3*p+7] = 0.0f;
            normals[3*p+8] = 1.0f;
            tcs[2*p+4] = 0.5f;
            tcs[2*p+5] = 0.5f;

            int q = numSlices*numStacks*6 + numStacks*3 + k*3;
            verts[3*q] = (float) Math.cos(Math.PI*2*(k+1)/numStacks);
            verts[3*q+1] = (float) Math.sin(Math.PI*2*(k+1)/numStacks);
            verts[3*q+2] = -numSlices/2.0f;
            normals[3*q] = 0.0f;
            normals[3*q+1] = 0.0f;
            normals[3*q+2] = -1.0f;
            tcs[2*q] = (float) (Math.cos(Math.PI*2*(k+1)/numStacks) + 1)/2;
            tcs[2*q+1] = (float) (Math.sin(Math.PI*2*(k+1)/numStacks) + 1)/2;

            verts[3*q+3] = (float) Math.cos(Math.PI*2*k/numStacks);
            verts[3*q+4] = (float) Math.sin(Math.PI*2*k/numStacks);
            verts[3*q+5] = -numSlices/2.0f;
            normals[3*q+3] = 0.0f;
            normals[3*q+4] = 0.0f;
            normals[3*q+5] = -1.0f;
            tcs[2*q+2] = (float) (Math.cos(Math.PI*2*k/numStacks) + 1)/2;
            tcs[2*q+3] = (float) (Math.sin(Math.PI*2*k/numStacks) + 1)/2;

            verts[3*q+6] = 0.0f;
            verts[3*q+7] = 0.0f;
            verts[3*q+8] = -numSlices/2.0f;
            normals[3*q+6] = 0.0f;
            normals[3*q+7] = 0.0f;
            normals[3*q+8] = -1.0f;
            tcs[2*q+4] = 0.5f;
            tcs[2*q+5] = 0.5f;
        }
    }
}
