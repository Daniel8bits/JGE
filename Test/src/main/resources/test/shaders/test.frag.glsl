#version 330 core

in vec4 v_albedo;

out vec4 o_fragment;

void main() {

    o_fragment = v_albedo + vec4(0.0, 0.0, 0.1, 0.0);

}