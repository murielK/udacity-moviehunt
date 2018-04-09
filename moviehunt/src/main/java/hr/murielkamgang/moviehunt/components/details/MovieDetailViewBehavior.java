package hr.murielkamgang.moviehunt.components.details;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by muriel on 3/11/18.
 */

/**
 * I implemented this custom behavior to animate in and out the movie poster and title when user is scrolling (inspired by the Fab behavior :))
 * see {@link android.support.design.widget.FloatingActionButton.Behavior}
 */
public class MovieDetailViewBehavior extends CoordinatorLayout.Behavior<View> {
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_SHOWING = 2;
    private static final ThreadLocal<RectF> sRectF = new ThreadLocal<>();
    private static final ThreadLocal<Matrix> sMatrix = new ThreadLocal<>();
    private static final long VIEW_ANIMATION_DURATION_LONG = 400l;
    private static final long VIEW_ANIMATION_DURATION_SHORT = 200l;
    private Rect mTmpRect;

    public MovieDetailViewBehavior() {
        super();
    }

    public MovieDetailViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    static void getDescendantRect(ViewGroup parent, View descendant, Rect out) {
        out.set(0, 0, descendant.getWidth(), descendant.getHeight());
        offsetDescendantRect(parent, descendant, out);
    }

    static void offsetDescendantRect(ViewGroup parent, View descendant, Rect rect) {
        Matrix m = sMatrix.get();
        if (m == null) {
            m = new Matrix();
            sMatrix.set(m);
        } else {
            m.reset();
        }

        offsetDescendantMatrix(parent, descendant, m);

        RectF rectF = sRectF.get();
        if (rectF == null) {
            rectF = new RectF();
            sRectF.set(rectF);
        }
        rectF.set(rect);
        m.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f),
                (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    private static void offsetDescendantMatrix(ViewParent target, View view, Matrix m) {
        final ViewParent parent = view.getParent();
        if (parent instanceof View && parent != target) {
            final View vp = (View) parent;
            offsetDescendantMatrix(target, vp, m);
            m.preTranslate(-vp.getScrollX(), -vp.getScrollY());
        }

        m.preTranslate(view.getLeft(), view.getTop());

        if (!view.getMatrix().isIdentity()) {
            m.preConcat(view.getMatrix());
        }
    }

    @Override
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams lp) {
        if (lp.dodgeInsetEdges == Gravity.NO_GRAVITY) {
            lp.dodgeInsetEdges = Gravity.BOTTOM;
        }
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child,
                                          View dependency) {
        if (dependency instanceof AppBarLayout) {
            updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child);
        } else {
            updateFabVisibilityForView(dependency, child);
        }
        return false;
    }

    private boolean updateFabVisibilityForView(View view, View child) {
        if (!shouldUpdateVisibility(view, child)) {
            return false;
        }
        if (view.getVisibility() == View.VISIBLE) {
            animateViewIn(child, VIEW_ANIMATION_DURATION_SHORT);
        } else {
            animateViewOut(child, VIEW_ANIMATION_DURATION_SHORT);
        }
        return true;
    }

    private boolean shouldUpdateVisibility(View dependency, View child) {
        final CoordinatorLayout.LayoutParams lp =
                (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        return lp.getAnchorId() == dependency.getId();

    }

    private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout parent,
                                                       AppBarLayout appBarLayout, View child) {
        if (!shouldUpdateVisibility(appBarLayout, child)) {
            return false;
        }

        if (mTmpRect == null) {
            mTmpRect = new Rect();
        }

        // First, let's get the visible rect of the dependency
        final Rect rect = mTmpRect;
        getDescendantRect(parent, appBarLayout, rect);

        if (rect.bottom <= appBarLayout.getHeight() / 3) {
            // If the anchor's bottom is below the seam, we'll animate our FAB out
            animateViewOut(child, VIEW_ANIMATION_DURATION_SHORT);
        } else {
            // Else, we'll animate our FAB back in
            animateViewIn(child, VIEW_ANIMATION_DURATION_LONG);
        }
        return true;
    }

    private void animateViewIn(View view, long duration) {
        if (isOrWillBeShown(view)) {
            return;
        }

        view.animate().cancel();

        final AnimationState animationState = getAnimationStateFor(view);

        animationState.state = ANIM_STATE_SHOWING;

        view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(duration)
                .setInterpolator(new FastOutLinearInInterpolator())
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                        animationState.cancel = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        animationState.cancel = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animationState.state = ANIM_STATE_NONE;
                        animation.removeAllListeners();
                    }
                })
                .start();
    }


    private void animateViewOut(View view, long duration) {
        if (isOrWillBeHidden(view)) {
            return;
        }
        view.animate().cancel();

        final AnimationState animationState = getAnimationStateFor(view);

        animationState.state = ANIM_STATE_HIDING;

        view.animate()
                .scaleX(0f)
                .scaleY(0f)
                .alpha(0f)
                .setDuration(duration)
                .setInterpolator(new FastOutLinearInInterpolator())
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                        animationState.cancel = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        animationState.cancel = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animationState.state = ANIM_STATE_NONE;

                        if (!animationState.cancel) {
                            view.setVisibility(View.INVISIBLE);
                        }
                    }
                })
                .start();
    }

    private boolean isOrWillBeShown(View view) {
        final int currentState = getAnimationStateFor(view).state;
        if (view.getVisibility() != View.VISIBLE) {
            return currentState == ANIM_STATE_SHOWING;
        } else {
            return currentState != ANIM_STATE_HIDING;
        }
    }

    private boolean isOrWillBeHidden(View view) {
        final int currentState = getAnimationStateFor(view).state;
        if (view.getVisibility() == View.VISIBLE) {
            return currentState == ANIM_STATE_HIDING;
        } else {
            return currentState != ANIM_STATE_SHOWING;
        }
    }

    private AnimationState getAnimationStateFor(View view) {
        AnimationState animationState = (AnimationState) view.getTag();
        if (animationState == null) {
            animationState = new AnimationState();
            view.setTag(animationState);
        }

        return animationState;
    }

    private static class AnimationState {
        int state = ANIM_STATE_NONE;
        boolean cancel;
    }
}


