uniform mat4 uMVPMatrix; //�ܱ任����
uniform float uPointSize;//��ߴ�
attribute vec3 aPosition;  //����λ��

void main()     
{                          		
   gl_Position = uMVPMatrix * vec4(aPosition,1); 
   gl_PointSize=uPointSize;
}      