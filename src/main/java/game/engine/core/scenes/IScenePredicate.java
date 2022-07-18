package game.engine.core.scenes;

import game.engine.core.entities.Entity;

public interface IScenePredicate {
    boolean predicate(Entity entity);
}
