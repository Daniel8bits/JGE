package game.engine.core.loader;

import de.javagl.obj.FloatTuple;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import de.javagl.obj.ObjReader;
import game.engine.core.buffer.VAO;
import game.engine.core.buffer.VBO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class OBJLoader {

    public VAO load(String pathname) {

        VAO vao = null;
        VBO verticesVbo = null;
        ArrayList<VBO> vbos = new ArrayList<>();

        try {
            InputStream inputStream = new FileInputStream(pathname);
            Obj obj = ObjReader.read(inputStream);

            ArrayList<Float> vertices = new ArrayList<>();
            ArrayList<Float> normals = new ArrayList<>();
            ArrayList<Float> texCoords = new ArrayList<>();

            for(int i = 0; i < obj.getNumFaces(); i++) {

                ObjFace face = obj.getFace(i);

                FloatTuple v0 = obj.getVertex(face.getVertexIndex(0));
                vertices.add(v0.getX());
                vertices.add(v0.getY());
                vertices.add(v0.getZ());

                FloatTuple v1 = obj.getVertex(face.getVertexIndex(1));
                vertices.add(v1.getX());
                vertices.add(v1.getY());
                vertices.add(v1.getZ());

                FloatTuple v2 = obj.getVertex(face.getVertexIndex(2));
                vertices.add(v2.getX());
                vertices.add(v2.getY());
                vertices.add(v2.getZ());

                if(obj.getNumNormals() > 0) {

                    FloatTuple n0 = obj.getNormal(face.getNormalIndex(0));
                    normals.add(n0.getX());
                    normals.add(n0.getY());
                    normals.add(n0.getZ());

                    FloatTuple n1 = obj.getNormal(face.getNormalIndex(1));
                    normals.add(n1.getX());
                    normals.add(n1.getY());
                    normals.add(n1.getZ());

                    FloatTuple n2 = obj.getNormal(face.getNormalIndex(2));
                    normals.add(n2.getX());
                    normals.add(n2.getY());
                    normals.add(n2.getZ());

                }

                if(obj.getNumTexCoords() > 0) {

                    FloatTuple uv0 = obj.getTexCoord(face.getTexCoordIndex(0));
                    texCoords.add(uv0.getX());
                    texCoords.add(uv0.getY());

                    FloatTuple uv1 = obj.getTexCoord(face.getTexCoordIndex(1));
                    texCoords.add(uv1.getX());
                    texCoords.add(uv1.getY());

                    FloatTuple uv2 = obj.getTexCoord(face.getTexCoordIndex(2));
                    texCoords.add(uv2.getX());
                    texCoords.add(uv2.getY());

                }

            }

            verticesVbo = new VBO(
                    vertices.toArray(new Float[0]),
                    obj.getVertex(0).getDimensions(),
                    true
            );

            if(obj.getNumNormals() > 0) {
                vbos.add(new VBO(
                        normals.toArray(new Float[0]),
                        obj.getNormal(0).getDimensions(),
                        true
                ));
            }

            if(obj.getNumTexCoords() > 0) {
                vbos.add(new VBO(
                        texCoords.toArray(new Float[0]),
                        obj.getNormal(0).getDimensions(),
                        true
                ));
            }

            vao = new VAO(
                    getVaoName(pathname),
                    verticesVbo,
                    vbos.toArray(new VBO[0]),
                    obj.getVertex(0).getDimensions()
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return vao;
    }

    private String getVaoName(String pathname) {
        String[] pathnameParts = pathname.split("/");
        return pathnameParts[pathnameParts.length-1].split("\\.")[0];
    }

}
