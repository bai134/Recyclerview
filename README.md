# Recyclerview
Recyclerview第一个item放大

无意间看到个需求，要求列表页的第一行放大





主要方法是继承RecyclerView重写onScrollStateChanged和onScrolled方法

前者是确定滑动后第一个item可以完全显示，后者是在滑动时进行item改变

主要方法如下

@Override
public void onScrollStateChanged(int state) {

    super.onScrollStateChanged(state);

    if(state == 0){
        postion = linearLayoutManager.findFirstVisibleItemPosition();
        view = linearLayoutManager.findViewByPosition(postion);
        top = view.getTop();
        if(viewHeight == 0){
            viewHeight = view.getHeight();
        }
        if(top == 0){//不用偏移
            return;
        }
        else if(-top < viewHeight/2){ //遮挡小于1/2
            offset = top;
        }
        else {
            offset = viewHeight+top;
            view = linearLayoutManager.findViewByPosition(postion+1);
            view.setScaleX(1.5f);
        }
        fsfsf = true;
        smoothScrollBy(0, offset);
    }
}


@Override
public void onScrolled(int dx, int dy) {
    super.onScrolled(dx, dy);
    offsetY+=dy;
    firstc = linearLayoutManager.findFirstCompletelyVisibleItemPosition(); //第一个完整可见item
    lastc = linearLayoutManager.findLastCompletelyVisibleItemPosition();//最后一个完整可见item
    first = linearLayoutManager.findFirstVisibleItemPosition();//第一个可见item
    last = linearLayoutManager.findLastVisibleItemPosition();//最后一个可见item
    firstview = linearLayoutManager.findViewByPosition(firstc);
    one = linearLayoutManager.findViewByPosition(first);
    two = linearLayoutManager.findViewByPosition(first +1);//第二可见item  --- 用来防止滑动过快item不规格变化
    three = linearLayoutManager.findViewByPosition(first + 2);//第三可见item --- 用来防止滑动过快item不规格变化
    lastv = linearLayoutManager.findViewByPosition(last);
    if (viewHeight == 0){
        flagfirst = first;
        flagend = lastc;
        viewHeight = firstview.getHeight();
        lastview = linearLayoutManager.findViewByPosition(lastc);
    }
    if (flagfirst == first -1) {
        flag = true;
        lastview = linearLayoutManager.findViewByPosition(lastc);
    }else if (flagfirst == first +1) {
        flag = false;
        lastview = linearLayoutManager.findViewByPosition(lastc);
    }
    offseta = firstview.getTop();
    float sx = 1f+(float) offseta/viewHeight/2;
    if(offsetY == 0){
        one.setScaleX(1.5f);
    }else
        one.setScaleX(sx);

    //因为最后一个不是可见item不是完全可见，所以需要减去最后一个完全可见与height的距离
    if (distance ==0)
        distance =getHeight()-lastview.getBottom();
    offseta = getHeight()-lastview.getBottom()- distance;

    if (dy!=0) {
        if (!flag)
            offseta = getHeight() - lastview.getBottom() + (viewHeight - distance) + 20;//20对应ItemDecoration的bottom
    }
    if (offseta<0)
        offseta = 0;
    sx = 1f+(float) offseta/viewHeight/2;
    two.setScaleX(sx);
    lastv.setScaleX(1f);
    three.setScaleX(1f);
    flagfirst = first;
    if (firstc == first) {//发生偏移时强制变换
        one.setScaleX(1.5f);
        two.setScaleX(1f);
    }
}
因为抛掷可能会造成有些item变换错乱，所以设置成了不可抛掷

//抛掷速度
@Override
public boolean fling(int velocityX, int velocityY) {
    velocityY = 0;//不可抛掷
    return super.fling(velocityX, velocityY);
}
