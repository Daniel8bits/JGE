package game.engine.core.management.scenes;

import game.engine.core.management.entities.Entity;

public interface IScenePredicate {
    boolean predicate(Entity entity);
}
