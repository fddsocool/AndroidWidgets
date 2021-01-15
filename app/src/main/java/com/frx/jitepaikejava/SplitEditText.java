package com.frx.jitepaikejava;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 分离式输入框
 */
public class SplitEditText extends AppCompatEditText {

    private Paint mPaint;

    //边框宽度
    private float mStrokeWidth;

    //边框颜色
    private int mBorderColor = 0xFF666666;

    //输入过的边框颜色
    private int mInputBorderColor = 0xFF4FC3F7;

    //焦点的边框颜色
    private int mFocusBorderColor = 0xFFFB8B05;

    //输入框的背景颜色
    private int mBoxBackgroundColor;

    //输入框的圆角大小
    private float mBorderCornerRadius;

    //框间距大小
    private float mBorderSpacing;

    //输入框宽度（不带边）
    private float mBoxWidth;

    //输入框高度（不带边）
    private float mBoxHeight;

    //允许输入的最大长度（框的个数）
    private int mMaxLength = 6;

    //当前输入的文本长度
    private int mTextLength;

    //路径
    private Path mPath;

    //矩形区域
    private RectF mRectF;

    //第一个框左圆角
    private float[] mRadiusFirstArray;

    //最后一个框右圆角
    private float[] mRadiusLastArray;

    //边框风格
    private @BorderStyle
    int mBorderStyle = BorderStyle.BOX;

    //文本类型
    private @TextStyle
    int mTextStyle = TextStyle.PLAIN_TEXT;

    //密文掩码
    private String mCipherMask;

    //默认密文掩码
    private static final String DEFAULT_CIPHER_MASK = "*";

    //是否是粗体
    private boolean isFakeBoldText;

    //是否正在绘制
    private boolean isDraw;

    //文本监听
    private OnTextInputListener mOnTextInputListener;


    public int getBorderColor() {
        return mBorderColor;
    }

    public int getInputBorderColor() {
        return mInputBorderColor;
    }

    public int getFocusBorderColor() {
        return mFocusBorderColor;
    }

    public int getBoxBackgroundColor() {
        return mBoxBackgroundColor;
    }

    public float getBorderCornerRadius() {
        return mBorderCornerRadius;
    }

    public float getBorderSpacing() {
        return mBorderSpacing;
    }

    @BorderStyle
    public int getBorderStyle() {
        return mBorderStyle;
    }


    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        refreshView();
    }

    public void setInputBorderColor(int inputBorderColor) {
        this.mInputBorderColor = inputBorderColor;
        refreshView();
    }

    public void setFocusBorderColor(int focusBorderColor) {
        this.mFocusBorderColor = focusBorderColor;
        refreshView();
    }

    public void setBoxBackgroundColor(int boxBackgroundColor) {
        this.mBoxBackgroundColor = boxBackgroundColor;
        refreshView();
    }

    public void setBorderCornerRadius(float borderCornerRadius) {
        this.mBorderCornerRadius = borderCornerRadius;
        refreshView();
    }

    public void setBorderSpacing(float borderSpacing) {
        this.mBorderSpacing = borderSpacing;
        refreshView();
    }

    public void setBorderStyle(@TextStyle int borderStyle) {
        this.mBorderStyle = borderStyle;
        refreshView();
    }

    @TextStyle
    public int getTextStyle() {
        return mTextStyle;
    }

    public void setTextStyle(@TextStyle int textStyle) {
        this.mTextStyle = textStyle;
        refreshView();
    }

    public String getCipherMask() {
        return mCipherMask;
    }

    /**
     * 是否粗体
     *
     * @param fakeBoldText
     */
    public void setFakeBoldText(boolean fakeBoldText) {
        isFakeBoldText = fakeBoldText;
        refreshView();
    }

    /**
     * 设置密文掩码 不设置时，默认为{@link #DEFAULT_CIPHER_MASK}
     *
     * @param cipherMask
     */
    public void setCipherMask(String cipherMask) {
        this.mCipherMask = cipherMask;
        refreshView();
    }

    /**
     * 设置文本输入监听
     *
     * @param onTextInputListener
     */
    public void setOnTextInputListener(OnTextInputListener onTextInputListener) {
        this.mOnTextInputListener = onTextInputListener;
    }

    public SplitEditText(@NonNull Context context) {
        this(context, null);
    }

    public SplitEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public SplitEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        //默认边框宽度
        mStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, displayMetrics);
        //默认间距
        mBorderSpacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, displayMetrics);
        setPadding(0, 0, 0, 0);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SplitEditText);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.SplitEditText_setStrokeWidth) {
                mStrokeWidth = typedArray.getDimension(attr, mStrokeWidth);
            } else if (attr == R.styleable.SplitEditText_setBorderColor) {
                mBorderColor = typedArray.getColor(attr, mBorderColor);
            } else if (attr == R.styleable.SplitEditText_setInputBorderColor) {
                mInputBorderColor = typedArray.getColor(attr, mInputBorderColor);
            } else if (attr == R.styleable.SplitEditText_setFocusBorderColor) {
                mFocusBorderColor = typedArray.getColor(attr, mFocusBorderColor);
            } else if (attr == R.styleable.SplitEditText_setBoxBackgroundColor) {
                mBoxBackgroundColor = typedArray.getColor(attr, mBoxBackgroundColor);
            } else if (attr == R.styleable.SplitEditText_setBorderCornerRadius) {
                mBorderCornerRadius = typedArray.getDimension(attr, mBorderCornerRadius);
            } else if (attr == R.styleable.SplitEditText_setBorderSpacing) {
                mBorderSpacing = typedArray.getDimension(attr, mBorderSpacing);
            } else if (attr == R.styleable.SplitEditText_setMaxLength) {
                mMaxLength = typedArray.getInt(attr, mMaxLength);
            } else if (attr == R.styleable.SplitEditText_setBorderStyle) {
                mBorderStyle = typedArray.getInt(attr, mBorderStyle);
            } else if (attr == R.styleable.SplitEditText_setTextStyle) {
                mTextStyle = typedArray.getInt(attr, mTextStyle);
            } else if (attr == R.styleable.SplitEditText_setCipherMask) {
                mCipherMask = typedArray.getString(attr);
            } else if (attr == R.styleable.SplitEditText_setFakeBoldText) {
                isFakeBoldText = typedArray.getBoolean(attr, false);
            }
        }
        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mPath = new Path();
        mRadiusFirstArray = new float[8];
        mRadiusLastArray = new float[8];
        mRectF = new RectF(0, 0, 0, 0);

        if (TextUtils.isEmpty(mCipherMask)) {
            mCipherMask = DEFAULT_CIPHER_MASK;
        } else if (mCipherMask.length() > 1) {
            mCipherMask = mCipherMask.substring(0, 1);
        }

        setBackground(null);
        setCursorVisible(false);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int width = w - getPaddingLeft() - getPaddingRight();
        int height = h - getPaddingTop() - getPaddingBottom();
        updateSizeChanged(width, height);
    }

    private void updateSizeChanged(int width, int height) {
        //如果框与框之间的间距小于0或者总间距大于控件可用宽度则将间距重置为0
        if (mBorderSpacing < 0 || (mMaxLength - 1) * mBorderSpacing > width) {
            mBorderSpacing = 0;
        }
        //计算出每个框的宽度
        mBoxWidth = (width - (mMaxLength - 1) * mBorderSpacing) / mMaxLength - mStrokeWidth * 2;
        if (mBoxWidth > height) {
            mBoxHeight = height - mStrokeWidth * 2;
        } else {
            mBoxHeight = mBoxWidth;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //移除super.onDraw(canvas);不绘制EditText相关的
        //绘制边框
        drawBorders(canvas);
    }

    private void drawBorders(Canvas canvas) {
        isDraw = true;
        //遍历绘制未输入文本的框边界
        for (int i = mTextLength; i < mMaxLength; i++) {
            drawBorder(canvas, i, mBorderColor);
        }

        int color = mInputBorderColor != 0 ? mInputBorderColor : mBorderColor;
        //遍历绘制已输入文本的框边界
        for (int i = 0; i < mTextLength; i++) {
            drawBorder(canvas, i, color);
        }

        //绘制焦点框边界
        if (mTextLength < mMaxLength && mFocusBorderColor != 0 && isFocused()) {
            drawBorder(canvas, mTextLength, mFocusBorderColor);
        }
    }

    private void drawBorder(Canvas canvas, int position, int borderColor) {
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setFakeBoldText(false);
        mPaint.setColor(borderColor);

        //计算出对应的矩形 口 口
        float left = getPaddingLeft() + mStrokeWidth + (mBoxWidth + mBorderSpacing) * position;
        float top = getPaddingTop() + mStrokeWidth;
        mRectF.set(left, top, left + mBoxWidth, top + mBoxHeight);

        //边框风格
        switch (mBorderStyle) {
            case BorderStyle.BOX:
                drawBorderBox(canvas, position, borderColor);
                break;
            case BorderStyle.LINE:
                drawBorderLine(canvas);
                break;
        }
        if (mTextLength > position && !TextUtils.isEmpty(getText())) {
            drawText(canvas, position);
        }
    }

    private void drawText(Canvas canvas, int position) {
        mPaint.setStrokeWidth(0);
        mPaint.setColor(getCurrentTextColor());
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextSize(getTextSize());
        mPaint.setFakeBoldText(isFakeBoldText);

        float x = mRectF.centerX();
        float y = mRectF.centerY() + (mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top) / 2 - mPaint.getFontMetrics().bottom;
        switch (mTextStyle) {
            case TextStyle.PLAIN_TEXT:
                canvas.drawText(String.valueOf(getText().charAt(position)), x, y, mPaint);
                break;
            case TextStyle.CIPHER_TEXT:
                canvas.drawText(mCipherMask, x, y, mPaint);
                break;
        }
    }

    private void drawBorderLine(Canvas canvas) {
        float lineY = mBoxHeight;
        canvas.drawLine(mRectF.left, lineY, mRectF.right, lineY, mPaint);
    }

    private void drawBorderBox(Canvas canvas, int position, int borderColor) {
        if (mBorderCornerRadius > 0) {
            //有框有圆角
            if (mBorderSpacing == 0) {
                //无间距
                if (position == 0 || position == mMaxLength - 1) {
                    //只有第一个和最后一个框才绘制圆角
                    if (mBoxBackgroundColor != 0) {
                        //绘制背景
                        mPaint.setStyle(Paint.Style.FILL);
                        mPaint.setColor(mBoxBackgroundColor);
                        canvas.drawPath(getRoundRectPath(mRectF, position == 0), mPaint);
                    }
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setColor(borderColor);
                    //绘制边框
                    canvas.drawPath(getRoundRectPath(mRectF, position == 0), mPaint);
                } else {
                    //其他绘制矩形框
                    if (mBoxBackgroundColor != 0) {
                        //绘制背景
                        mPaint.setStyle(Paint.Style.FILL);
                        mPaint.setColor(mBoxBackgroundColor);
                        canvas.drawRect(mRectF, mPaint);
                    }
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setColor(borderColor);
                    canvas.drawRect(mRectF, mPaint);
                }
            } else {
                //绘制圆角框
                if (mBoxBackgroundColor != 0) {
                    //绘制背景
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setColor(mBoxBackgroundColor);
                    canvas.drawRoundRect(mRectF, mBorderCornerRadius, mBorderCornerRadius, mPaint);
                }
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(borderColor);
                //绘制边框
                canvas.drawRoundRect(mRectF, mBorderCornerRadius, mBorderCornerRadius, mPaint);
            }
        } else {
            //绘制矩形框
            if (mBoxBackgroundColor != 0) {
                //绘制背景
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(mBoxBackgroundColor);
                canvas.drawRect(mRectF, mPaint);
            }
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(borderColor);
            //绘制边框
            canvas.drawRect(mRectF, mPaint);
        }
    }

    private Path getRoundRectPath(RectF rectF, boolean isFirst) {
        mPath.reset();
        if (isFirst) {
            //第一个框的左上角和左下角
            mRadiusFirstArray[0] = mBorderCornerRadius;
            mRadiusFirstArray[1] = mBorderCornerRadius;
            mRadiusFirstArray[6] = mBorderCornerRadius;
            mRadiusFirstArray[7] = mBorderCornerRadius;
            mPath.addRoundRect(rectF, mRadiusFirstArray, Path.Direction.CW);
        } else {
            //最后一个框的右上角和右下角
            mRadiusLastArray[2] = mBorderCornerRadius;
            mRadiusLastArray[3] = mBorderCornerRadius;
            mRadiusLastArray[4] = mBorderCornerRadius;
            mRadiusLastArray[5] = mBorderCornerRadius;
            mPath.addRoundRect(rectF, mRadiusLastArray, Path.Direction.CW);
        }
        return mPath;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        mTextLength = text.length();
        refreshView();
        if (mOnTextInputListener != null) {
            mOnTextInputListener.onTextInputChanged(text.toString(), mTextLength);
            if (mTextLength == mMaxLength) {
                mOnTextInputListener.onTextInputCompleted(text.toString());
            }
        }
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (selStart == selEnd) {
            //控制光标，按顺序输入
            setSelection(getText() == null ? 0 : getText().length());
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        //焦点改变时刷新状态
        refreshView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isDraw = false;
    }

    /**
     * 刷新视图
     */
    private void refreshView() {
        if (isDraw) {
            invalidate();
        }
    }

    //边框风格类型
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BorderStyle.BOX, BorderStyle.LINE})
    public @interface BorderStyle {
        //框
        int BOX = 0;
        //下划线
        int LINE = 1;
    }

    //文本类型
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TextStyle.PLAIN_TEXT, TextStyle.CIPHER_TEXT})
    public @interface TextStyle {
        //明文
        int PLAIN_TEXT = 0;

        //密文
        int CIPHER_TEXT = 1;
    }

    //文本输入监听
    public interface OnTextInputListener {
        /**
         * Text改变监听
         *
         * @param text
         * @param length
         */
        void onTextInputChanged(String text, int length);

        /**
         * Text输入完成
         *
         * @param text
         */
        void onTextInputCompleted(String text);
    }
}
