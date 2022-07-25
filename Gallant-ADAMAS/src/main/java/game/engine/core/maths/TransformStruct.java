package game.engine.core.maths;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@AllArgsConstructor
@Getter
@Setter
public class TransformStruct {
    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;

    public Matrix4f toMatrix() {
        return this.toMatrix(this.translation);
    }
    
    public Matrix4f toMatrixView() {
        return this.toMatrix(new Vector3f(this.translation).negate());
    }

    public Quaternionf getRotationAsQuaternion() {
        return new Quaternionf().rotateXYZ(
                this.rotation.x,
                this.rotation.y,
                this.rotation.z
        ).normalize();
    }

    private Matrix4f toMatrix(Vector3f translationVector) {
        Matrix4f translation = new Matrix4f().translate(translationVector);

        Matrix4f rotationX = new Matrix4f().rotateX((float) Math.toRadians(this.rotation.x));
        Matrix4f rotationY = new Matrix4f().rotateY((float) Math.toRadians(this.rotation.y));
        Matrix4f rotationZ = new Matrix4f().rotateZ((float) Math.toRadians(this.rotation.z));

        Matrix4f rotation = rotationZ.mul(rotationY.mul(rotationX));

        Matrix4f scale = new Matrix4f().scale(this.scale);

        return translation.mul(rotation.mul(scale));
    }
}
