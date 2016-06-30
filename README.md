# LineChartPro
绘制折线图
朋友问我折线图怎么画，我就简单写个Demo,下面列下简单的步骤:

### 类继承自View,关键方法

 *  构造器是必须要重写的，根据需要选择一个合适的，除非特别情况一般差别不是很大，我选择的下面的
 ```java
     public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 ```
 
    具体参数不需要我们关心，但一些初始化操作可以放在这里
 *  onDraw方法的重写
 
```java
   @Override
    protected void onDraw(Canvas canvas) {
        super(canves)
    }
```

  这个方法非常重要几乎所有的View的绘制部分都是这里完成的
  
  * onMeasure 方法的重写
  
```java
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       super(int widthMeasureSpec, int heightMeasureSpec);
    }
```
  这个方法定义View的宽高，有时求简单不写也可但是View的使用会有很大的局限性，但是呐，View的宽高是由我们自己来绘制的宽高还算简单
重写此方法会更完美

###相关类和方法介绍
##### Paint（画笔）
   * 初始化
  ```java
       Paint paint_text = new Paint();
  ```
   * 参数配置
  ```java
       paint_text.setColor(textColorBlue);    //画笔颜色
       paint_text.setAntiAlias(false);        //是否抗锯齿，抗锯齿绘制出来得效果会更好
       paint_text.setAlpha(100);              //设置透明度
       paint_text.setStrokeWidth(strokeWidth); //设置空心边框的宽度
       paint_text.setTextSize(textSize);      //绘制文字的大小
       
       //Paint.Style.STROKE 绘制空心图形
       //Paint.Style.FILL  绘制实心图形
       //Paint.Style.FILL_AND_STROKE 实心且描边 实际效果同Paint.Style.Fill差别不大
       paint_text.setStyle(Paint.Style.STROKE);
  ```
  
##### Canves（画布）
     * 初始化
     
     在这里Canves不用我们单独初始化，onDraw方法里会传入一个Canves,我们直接在上面操作即可，绘制图形的方法大多是由Canver来调用的，下面才是重点
     
     * 绘制直线
     
    ```java
     drawLine(float startX, float startY, float stopX, float stopY, @NonNull Paint paint)
    ```
    
      第一个参数：起点的横坐标 x；
      第二个参数：起点的纵坐标 y；
      第三个参数：终点的横坐标 x；
      第四个参数：终点的纵坐标 y；
      第五个参数：paint(画笔)；
     
