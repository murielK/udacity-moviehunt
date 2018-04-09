package hr.murielkamgang.moviehunt.components.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by muriel on 9/20/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
