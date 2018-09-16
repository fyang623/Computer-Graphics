//Project 1
//Example vertex shader
#version 410

// Input from vertex shader
in vec3 pos;
in vec2 tc;
in vec3 n;
in vec4 varyingColor;
in vec3 varyingViewDir;

// Output to screen
out vec4 color;

uniform mat4 mv_matrix;
uniform mat4 proj_matrix;
uniform sampler2D samp;
uniform vec3 camera_loc;

void main(void)
{
    // Create a directional light
    //vec3 lightDirection = -normalize(vec3(1.0f, 1.0f, 2.0f));

    //Create a point light and compute light direction for each point
    vec3 lightPosition = vec3(1.0f, 1.0f, 2.0f)*camera_loc.z;
    vec3 lightDirection = normalize(pos-lightPosition);

    // Options for starting color
    // Color option 1: fixed color
    //color = vec4(1.0f, 0.0f, 1.0f, 1.0f);
    // Color option 2: texture
  	color = texture(samp,tc);
  	// Color option 3: varying by vertex option
  	//color =  varyingColor;
  	// Color option 4: normal
    //color = vec4(n,1.0f);

  	// Computations for shading model
  	// Compute dot between light and normal
    float cosTheta = dot(lightDirection,n);
    // Compute reflection vector and dot with view vector
    vec3 reflectVector = normalize(reflect(-lightDirection,n));
  	float cosPhi = dot(reflectVector,varyingViewDir);

  	// Compute final color
  	// Only mulitiply rgb by cosine, since we don't want to change
  	// the alpha channel
  	color = vec4(color.rgb*cosPhi,1.0f);
}
