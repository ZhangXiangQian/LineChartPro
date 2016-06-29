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
#####Paint（画笔）
