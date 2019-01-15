uniform mat4 uMVPMatrix;
uniform mat4 uMMatrix;
uniform vec3 uCamera;
uniform vec3 uLightLocationSun;

attribute vec2 aTexCoor;
attribute vec3 aPosition;
attribute vec3 aNormal;
 
varying vec2 vTexCoor;
varying vec4 vAmbient;
varying vec4 vDiffuse;
varying vec4 vSpecular;

void pointLight(
	inout vec4 ambient,
	inout vec4 diffuse,
	inout vec4 specular,
	in vec3 normal,
	in vec3 lightLocation,
	in vec4 lightAmbient,
	in vec4 lightDiffuse,
	in vec4 lightSpecular
){
	ambient = lightAmbient;
	vec3 normalTarget = aPosition+normal;
	vec3 newNormal = (uMMatrix*vec4(normalTarget,1)).xyz-(uMMatrix*vec4(aPosition,1)).xyz;
	newNormal = normalize(newNormal);
	vec3 camera = normalize(uCamera-(uMMatrix*vec4(aPosition,1)).xyz);
	vec3 light = normalize(lightLocation-(uMMatrix*vec4(aPosition,1)).xyz); 
	vec3 halfVector = normalize(camera + light);
	float shininess = 50.0;
	float nDotViewPosition = max(0.0,dot(newNormal,light));
	diffuse = lightDiffuse * nDotViewPosition;
	float nDotViewHalfVector=dot(newNormal,halfVector);
  	float powerFactor=max(0.0,pow(nDotViewHalfVector,shininess)); 	
  	specular=lightSpecular*powerFactor;  
}
void main()
{
	gl_Position = uMVPMatrix * vec4(aPosition,1);
    vec4 ambientTemp=vec4(0.0,0.0,0.0,0.0);
    vec4 diffuseTemp=vec4(0.0,0.0,0.0,0.0);
    vec4 specularTemp=vec4(0.0,0.0,0.0,0.0);   
    pointLight(ambientTemp,diffuseTemp,specularTemp,normalize(aNormal),
    	uLightLocationSun,
    	vec4(0.05,0.05,0.05,1.0),
    	vec4(1.0,1.0,1.0,1.0),
    	vec4(0.3,0.3,0.3,1.0));
   vAmbient=ambientTemp;
   vDiffuse=diffuseTemp;
   vSpecular=specularTemp;
   
   vTexCoor=aTexCoor;
}


