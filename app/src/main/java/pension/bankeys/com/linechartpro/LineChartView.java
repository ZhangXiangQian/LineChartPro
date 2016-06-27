package pension.bankeys.com.linechartpro;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by zhang on 2016/6/24.
 */
public class LineChartView extends View {

    private int textSize = 14;
    private int textColorBlack = 0xff000000;
    private int textColorBlue = 0xFF4198FF;

    private int colum = 5; //行
    private int rowRedis;  //行间距

    private int row;      //列
    private int columRedis; //列间距

    private int strokeWidth; //线宽

    int desiredWidht = 0;
    int desiredHeight = 0;

    private Paint paint_text;  //文字绘制
    private Paint paint_l; //绘制横线
    private String[] tv_row = new String[]{"10.25", "11.25", "11.25", "11.25", "11.25"};

    private List<String> rowTxtList;
    private List<String> columTxtList;
    private List<Score> list;

    public void setList(List<Score> list) {
        this.list = list;
        invalidate();
    }

    public void setRowTxtList(List<String> rowTxtList) {
        this.rowTxtList = rowTxtList;
        invalidate();
    }

    public void setColumTxtList(List<String> columTxtList) {
        this.columTxtList = columTxtList;
        invalidate();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.LineChartView);
        textSize = mTypedArray.getDimensionPixelSize(R.styleable.LineChartView_textSize, 50);
        colum = mTypedArray.getInteger(R.styleable.LineChartView_colum, 5);
        row = mTypedArray.getInteger(R.styleable.LineChartView_row, 5);
        strokeWidth = mTypedArray.getDimensionPixelOffset(R.styleable.LineChartView_strokeWidth,20);

        columRedis = mTypedArray.getDimensionPixelOffset(R.styleable.LineChartView_columReDis, 100);
        rowRedis = mTypedArray.getDimensionPixelOffset(R.styleable.LineChartView_rowRedis, 100);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBaseView(canvas);
    }

    private void init() {
        paint_text = new Paint();
        paint_text.setColor(textColorBlue);
        paint_text.setAntiAlias(false);
        paint_text.setStrokeWidth(strokeWidth);
        paint_text.setTextSize(textSize);

        paint_l = new Paint();
        paint_l.setColor(textColorBlack);
        paint_text.setAntiAlias(false);
        paint_text.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        desiredWidht = (int) (3 * textSize + textSize + row * rowRedis + paint_l.getStrokeWidth() * row);
        desiredHeight = (int) (textSize + textSize + columRedis * colum + paint_l.getStrokeWidth() * colum);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = Math.min(desiredWidht, widthSize);
                break;
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = desiredWidht;
                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(desiredHeight, heightSize);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = desiredHeight;
                break;
        }
        Log.i("onMeasure", "desiredWidht:" + desiredWidht + ";desiredHeight:" + desiredHeight + "\nwidth:" + width + ";height:" + height);
        setMeasuredDimension(width, height);
    }

    private void drawBaseView(Canvas mCanvas) {
        // mCanvas.drawLine(0, 0, 0, getHeight(), paint_l);
        //mCanvas.drawLine(padding, (float) (getHeight() - padding), (float) (getWidth() - padding), (float) (getHeight() - padding), paint_l);
        Path path = new Path();
        path.moveTo(textSize * 4, columRedis);
        //绘制每行左边的字体
        if (columTxtList != null && columTxtList.size() > 0) {
            for (int i = 0; i < columTxtList.size(); i++) {
                mCanvas.drawText(columTxtList.get(i), 0, columRedis * (i + 1), paint_text);
            }
        }
        //绘制行
        for (int i = 0; i < colum; i++) {
            mCanvas.drawLine(2 * textSize, columRedis * (i + 1) - textSize / 2, getWidth() + 2 * textSize,  columRedis * (i + 1) - textSize / 2, paint_l);
        }
        //绘制底部的字体
        if (rowTxtList != null && rowTxtList.size() > 0) {
            for (int i = 0; i < row; i++) {
                mCanvas.drawText(rowTxtList.get(i), rowRedis * (i + 1), columRedis * colum + textSize, paint_text);
            }
        }
        //绘制折线
        if (list != null && list.size() > 0) {
            paint_l.setStyle(Paint.Style.STROKE);

            for (int i = 0; i < list.size(); i++) {
                mCanvas.drawCircle(rowRedis * (i + 1) + textSize,((100 - list.get(i).getScore()) * rowRedis * 5) / 100 ,7,paint_text);
                if( i == 0){
                    path.moveTo(rowRedis * (i + 1) + textSize,((100 - list.get(i).getScore()) * rowRedis * 5) / 100);
                } else {
                    path.lineTo(rowRedis * (i + 1) + textSize,((100 - list.get(i).getScore()) * rowRedis * 5) / 100);
                }
                if( i == list.size() - 1){
                }
                Log.i(".....",list.get(i).getScore() + ";" + rowRedis);
                Log.i("path:" , "x:" + rowRedis * (i + 1) + ";y:" + (list.get(i).getScore() * rowRedis * 5) / 100);
            }
            mCanvas.drawPath(path,paint_l);
        }
    }

}
