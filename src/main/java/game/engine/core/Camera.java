package game.engine.core;

import game.engine.maths.Transform;
import lombok.Getter;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Camera {

    @Getter
    private Transform transform;

    public Camera(Vector3f translation, Vector3f rotation) {
        this.transform = new Transform(translation, rotation);
    }

    public Matrix4f getView() {
        return this.transform.view().invert();
        //return this.transform.view();
        /*
        return new Matrix4f().translationRotateScaleInvert(
                this.transform.getLocalTransform().getTranslation(),
                this.transform.getLocalTransform().getRotationAsQuaternion(),
                this.transform.getLocalTransform().getScale()
        );
        Matrix4f viewMatrix = this.transform.getParent().worldMatrix()
                .mul(this.transform.getGlobalTransform().toMatrix())
                .translate(new Vector3f(this.getTransform().getLocalTransform().getTranslation()).negate())
                .rotateYXZ(this.getTransform().getLocalTransform().getRotation().getEulerAnglesXYZ(new Vector3f()))
                .invert();
        return viewMatrix;
         */
    }

}
