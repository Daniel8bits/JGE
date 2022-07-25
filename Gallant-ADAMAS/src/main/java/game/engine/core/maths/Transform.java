package game.engine.core.maths;

import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@Getter
public class Transform {

    private TransformStruct globalTransform;
    private TransformStruct localTransform;

    @Setter
    private Transform parent;

    public Transform() {
        this(new Vector3f());
    }

    public Transform(Vector3f translation) {
        this(translation, new Vector3f());
    }

    public Transform(Vector3f translation, Vector3f rotation) {
        this(translation, rotation, new Vector3f(1, 1, 1));
    }

    public Transform(Vector3f translation, Vector3f rotation, Vector3f scale) {
        this.globalTransform = new TransformStruct(translation, rotation, scale);
        this.localTransform = new TransformStruct(
            new Vector3f(),
            new Vector3f(),
            new Vector3f(1, 1, 1)
        );
    }

    public Matrix4f worldMatrix() {
        Matrix4f worldMatrix = this.globalTransform.toMatrix().mul(this.localTransform.toMatrix());
        if(this.parent != null) {
            worldMatrix = this.parent.worldMatrix().mul(worldMatrix);
        }
        return worldMatrix;
    }

    public Matrix4f view() {
        Matrix4f viewMatrix = this.globalTransform.toMatrixView().mul(this.localTransform.toMatrixView());
        if(this.parent != null) {
            viewMatrix = this.parent.view().mul(viewMatrix);
        }
        return viewMatrix;
    }

}
