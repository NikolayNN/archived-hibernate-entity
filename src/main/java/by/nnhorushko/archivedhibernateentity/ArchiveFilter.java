package by.nnhorushko.archivedhibernateentity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArchiveFilter {
    State value() default State.NON_ARCHIVED;

    enum State {
        ALL, ARCHIVED, NON_ARCHIVED
    }
}
