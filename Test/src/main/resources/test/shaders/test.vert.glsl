#version 330 core

//layout(location = 0)
attribute vec3 a_position;
//layout(location = 1)
attribute vec3 a_normal;
//layout(location = 2)
attribute vec2 a_texCoords;

uniform mat4 u_model;
uniform mat4 u_view;
uniform mat4 u_projection;
//uniform mat4 look_at;

out vec4 v_albedo;


void main() {

    v_albedo = vec4(a_normal, 1.0);

    gl_Position = u_projection * u_view * u_model * vec4(a_position, 1.0);

}