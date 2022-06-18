package com.nageshempire.mad.essentials.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import com.google.android.material.textview.MaterialTextView;
import com.nageshempire.nextplayer.mad.essentials.R;


public class DrawableTextView extends MaterialTextView {

    private Drawable drawableRight;
    private Drawable drawableLeft;
    private Drawable drawableTop;
    private Drawable drawableBottom;

    int actionX, actionY;

    private DrawableClickListener clickListener;

    public interface DrawableClickListener {
        enum DrawablePosition {
            TOP,
            BOTTOM,
            LEFT,
            RIGHT
        }

        void onClick(DrawablePosition target);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        initDrawable(left, top, right, bottom);
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        initDrawable(left, top, right, bottom);
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Rect bounds;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            actionX = (int) event.getX();
            actionY = (int) event.getY();
            if (drawableBottom != null && drawableBottom.getBounds().contains(actionX, actionY)) {
                clickListener.onClick(DrawableClickListener.DrawablePosition.BOTTOM);
                return super.onTouchEvent(event);
            }

            if (drawableTop != null && drawableTop.getBounds().contains(actionX, actionY)) {
                clickListener.onClick(DrawableClickListener.DrawablePosition.TOP);
                return super.onTouchEvent(event);
            }

            // this works for left since container shares 0,0 origin with bounds
            if (drawableLeft != null) {
                bounds = drawableLeft.getBounds();

                int x, y;
                int extraTapArea = (int) (13 * getResources().getDisplayMetrics().density + 0.5);

                x = actionX;
                y = actionY;

                if (!bounds.contains(actionX, actionY)) {
                    /* Gives the +20 area for tapping. */
                    x = actionX - extraTapArea;
                    y = actionY - extraTapArea;
                    if (x <= 0)
                        x = actionX;
                    if (y <= 0)
                        y = actionY;
                    if (x < y) {
                        y = x;
                    }
                }

                if (bounds.contains(x, y) && clickListener != null) {
                    clickListener
                            .onClick(DrawableClickListener.DrawablePosition.LEFT);
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    return false;
                }
            }

            if (drawableRight != null) {
                bounds = drawableRight.getBounds();
                int x, y;
                int extraTapArea = 13;
                x = (actionX + extraTapArea);
                y = (actionY - extraTapArea);
                x = getWidth() - x;
                if (x <= 0) {
                    x += extraTapArea;
                }
                if (y <= 0)
                    y = actionY;
                if (bounds.contains(x, y) && clickListener != null) {
                    clickListener.onClick(DrawableClickListener.DrawablePosition.RIGHT);
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    return false;
                }
                return super.onTouchEvent(event);
            }

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        drawableRight = null;
        drawableBottom = null;
        drawableLeft = null;
        drawableTop = null;
        super.finalize();
    }

    public void setDrawableClickListener(DrawableClickListener listener) {
        this.clickListener = listener;
    }

    private void initDrawable(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        drawableLeft = left;
        drawableRight = right;
        drawableTop = top;
        drawableBottom = bottom;
    }

    private void init(AttributeSet attrs, int defStyle) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DrawableTextView, defStyle, 0);
        try {
            drawableLeft = a.getDrawable(R.styleable.DrawableTextView_c_drawableLeftCompat);
            drawableRight = a.getDrawable(R.styleable.DrawableTextView_c_drawableRightCompat);
            drawableTop = a.getDrawable(R.styleable.DrawableTextView_c_drawableTopCompat);
            drawableBottom = a.getDrawable(R.styleable.DrawableTextView_c_drawableBottomCompat);
            super.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        } finally {
            a.recycle();
        }
    }

    public void setRightDrawable(Drawable drawable) {
        drawableRight = drawable;
        super.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

}