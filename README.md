# Recyclerview
Recyclerview第一个item放大

无意间看到个需求，要求列表页的第一行放大


https://oscimg.oschina.net/oscnet/d31be238843a20559e2ca2304d35d23c3c3.jpg


主要方法是继承RecyclerView重写onScrollStateChanged和onScrolled方法

前者是确定滑动后第一个item可以完全显示，后者是在滑动时进行item改变




