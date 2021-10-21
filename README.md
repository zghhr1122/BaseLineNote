# BaseLineNote


修改AndroidManifest.xml的启动看效果。
1、kugou包是旧版酷狗的左右滑。
2、seekText包是seekbar加文字在居中位置。





自定义View画文本时的基线笔记(seekText包)

![image](https://github.com/zghhr1122/BaseLineNote/baeline.png)

如上图红色方框所示，文字在绘制的时候，会根据当前的字体，字体大小等信息，确认Leading，top，bottom，ascent，descent，baseline的值，这些值会最终影响到字体在TextView中的显示位置，通过getPaint().getFontMetricsInt()可以获取到这些值（这些值都是以baseLine为原点的坐标系测量的值）

Leading：文字上方可能出现一些特殊的符号，为什么第一幅图中没有说明Leading的存在呢，原因是我们通常在绘制一行英文或者中文时，Leading的高度为0

top：是指的是最高字符到baseline的值，即ascent的最大值，为负数

bottom：是指最低字符到baseline的值，即descent的最大值，为正数

ascent：是baseline之上至字符最高处的距离，为负数

descent：是baseline之下至字符最低处的距离，为正数

这些值我们都可以通过Paint.FontMetricsInt获取到



Paint.FontMetricsInt  fontMetricsInt = mPaint.getFontMetricsInt();

        int top = fontMetricsInt.top;
        
        int bottom = fontMetricsInt.bottom;
        
        int ascent = fontMetricsInt.ascent;
        
        int descent = fontMetricsInt.descent;
        


从图中我们可以看出
文字的高度 = Descent+Ascent+Leading

当我们在画布Canvas的顶部绘制一行文字的时候，规定了一行文字的高度是Height，文字区域的高度是textHeight（一般由于文字都是居中，因此A坐标系中0-TOP的距离和BOTTOM-Y的距离应该一样），因此Height/2 = textHeight/2

当我们绘制文字的时候Y的值我们一定是知道的，所以中线的位置就是Height/2，Ascent和Descent值我们也能得到，那么

基线到中线的距离 = （Descent - Ascent）/ 2 - Descent

也可以是基线到中线的距离 = （bottom - top）/ 2 - bottom  【比较精准】

注意Ascent的实际值为负值

那么实际baseLine在已屏幕为原点的坐标系（A坐标系）中的y值应该为

baseLineY = Height / 2 + 基线到中线的距离  即

1、直观写法
baseLineY = (int) (getHeight() / 2 + ((bottom-top)/2 - bottom));

2、去括号换算后写法
baseLineY = (int) (getHeight() / 2 - top / 2 - bottom / 2);
