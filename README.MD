1.schedule()方法更注重保持间隔时间的稳定：保障每隔period时间可调用一次。
  scheduleAtFixedRate()方法更注重保持执行频率的稳定：保障多次调用的频率趋近于period时间，如果某一次调用时间大于period，下一次就会尽量小于period，以保障频率接近于period
  
2.private final TaskQueue queue = new TaskQueue();
private final TimerThread thread = new TimerThread(queue);
timer内部维护者一个线程队列和任务队列

3.所有的scheduleXxx方法都调用了sched方法，用于进行任务分配

4.将Calendar对象转换成Date对象的方式：Calendar.getInstance().getTime()->Date
    Calendar.add(key, value)：在指定时间位置上加值
    Calendar.set(key, value)：设置指定时间位置的值
    
5.Timer使用schedule设置定时任务的方式：
    1.timer.schedule(timetask, time, perioud);
    2.timer.schedule(timetask, time);
    3.timer.schedule(timetask, delay, perioud);
    4.timer.schedule(timetask, delay);
    
6.Timer使用schedultAtFixedRate设置定时任务的方式：
    1.timer.scheduleAtFixedRate(timetask, time);
    2.timer.scheduleAtFixedRate(timetask, time, perioud);
    
7.Timer的schedule和scheduleAtFixedRate的区别：    
    1.当设置的延迟时间为time时并且设置的时间已经过去的时候，schedule方法会照常执行下去，
    将当前时间作为第一次任务执行的时间；scheduleAtFixedRate方法会以已经过去的时间作为第一次任务
    执行的时间
    2.当设置的周期间隔时间小于任务的执行时间的时候，schedule方法会执行完任务，不管周期间隔时间；
    而scheduleAtFixedRate方法会执行每次周期间隔，不管任务是否完成
    
8.TODO： mark一下，这里不太了解，scheduleAtFixedRate是什么原理，能在完成每次周期间隔的同时，还完成了任务
   看了一下源码，所有的scheduleXxx方法都是调用的同一个sched方法，所以这里自己的想法是；
   这是因为我设置的任务太简单了，所以它才会执行完成，在正常情况下是不会执行完成的
   
   
9.Timer类不能用于处理并发任务，因为只有一个线程执行所有的任务，所有的任务都是串行的
  当然，可以设置每个任务使用单独的Timer来执行来完成并行任务的方式
  
10.周期性的执行同一个任务，使用schedule会有先后顺序，而scheduleAtFixedRate方法不会，是随机的？
这个是到segment里面看到的

11.Timer貌似是一个后台守护线程

12.TimeerTask是一个抽象类

13.schedule方法侧重保存间隔时间的稳定，而scheduleAtFixedRate方法更加侧重于保持执行频率的稳定

14.Timer对调度的支持是基于绝对时间的，而不是相对时间，所以它对系统时间的改变非常敏感。
也就是说无法指定timer在每周星期五执行任务

15.Timer是不会捕获异常的，如果在一个Timer中定义了很多的任务，其中一个任务发生异常并且没有被
正常捕获，那么该定时器下的所有任务都会被关闭

16.  使用scheduleAtFixedRate当周期间隔时间小于任务执行时间时，虽然任务还是会按照周期间隔时间来执行，
        但是下一个任务还是会等待前一个任务完成之后才能完成下一个任务
        
        
17.通过