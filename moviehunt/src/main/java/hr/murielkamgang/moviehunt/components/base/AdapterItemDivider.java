package hr.murielkamgang.moviehunt.components.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by muriel on 3/24/18.
 */
public class AdapterItemDivider extends RecyclerView.ItemDecoration {

    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;

    private Drawable divider;
    private int spacing;
    private int orientation;

    private int leftMargin;
    private int rightMargin;
    private int topMargin;
    private int bottomMargin;

    public AdapterItemDivider(Context context, Drawable divider, int orientation) {
        this.divider = divider;
        this.orientation = orientation;
    }

    public AdapterItemDivider(Context context, int spacing, int orientation) {
        this.spacing = spacing;
        this.orientation = orientation;
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        c.save();
        final int left = parent.getPaddingLeft() + leftMargin;
        final int right = parent.getWidth() - parent.getPaddingRight() - rightMargin;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin + topMargin;
            final int bottom = top + divider.getIntrinsicHeight() - bottomMargin;
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }

        c.restore();
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        c.save();
        final int top = parent.getPaddingTop() + topMargin;
        final int bottom = parent.getHeight() - parent.getPaddingBottom() - bottomMargin;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin + leftMargin;
            final int right = left + divider.getIntrinsicHeight() - rightMargin;
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }

        c.restore();
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int itemPosition = parent.getChildAdapterPosition(view);

        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        if (orientation == ORIENTATION_VERTICAL) {
            outRect.set(0, 0, 0, divider == null ? spacing : divider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, divider == null ? spacing : divider.getIntrinsicWidth(), 0);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (divider == null) {
            return;
        }
        if (orientation == ORIENTATION_VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }
}