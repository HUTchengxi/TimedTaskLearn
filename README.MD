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
        
        
17.通过TimerTask的cancel方法可以关闭指定任务，在关闭任务之后，还需要通过调用
   timer的purge方法来将已关闭的任务移除，该方法会返回Integer值，表示移除的任务数
   
18.Timer的cancel方法用于将该任务记录器中的所有任务移除

19.使用Timer的cancel方法之后无法使用timer.purge方法来打印移除的任务数，因为
再timer源码中的cancel方法移除了任务队列queue所有的任务
而timer的purge每次只会找到并移除对应的任务队列，并返回移除的结果数

20.ScheduledExecuteService中的两个方法：scheduleAtFixedRate和scheduleWithFixedDelay
  前者注重于固定频率运行任务，，后者注重与固定延迟运行任务
  比如：scheduleAtFixedRate(timeTask, 0, 5, Timeutils.Second);表示：
        如果timeTask命令执行需要三秒，那么下一个任务就会前一个任务执行之后等待两秒后执行
       scheduleAtFixedDelay(timeTask, 0, 5, Timeutils.Second);表示：
         不管timeTask命令执行需要几秒，下一个任务都会再前一个任务执行之后等待五秒执行
         
         
    
21.Quartz中的定时器有五种，常用的有两种，SimpleTrigger和CronTrigger
  其中前者用来触发给定时间间隔；后者可以使用cron表达式来指定任一个时间点，任一个时间间隔
  
22.TODO: Quartz的存储方式有两种: RAMJobStore和JDBCJobStore，后者可以支持集群，这个暂时还没有了解

23.TODO: Scheduler通过jobDetail来注册任务，通过trigger来调用定时任务

24.TODO: 一个job可以被多个Trigger绑定，但是一个Trigger只能绑定一个job

25.Job是一个类似于TimerTask的接口，只提供了一个execute方法，该方法中的参数JobExecutionContext提供了调度上下文的各种信息
   Job运行时的信息保存再JobDataMao实例中；
   
26.Quartz再每次执行job时都会创建一个新的job实例，所以它不会直接接收一个实例，它可以接收一个Job的实现类，
用于在运行时通过反射newInstance()来实例化Job，JobDetai类用于描述Job这个当前任务的实现类和一些静态信息

27.Trigger描述触发Job执行的时间触发规则

28.Calender：org.util.Calender是一些日历特定时间的集合，一个Trigger可以和多个Calendar对象相关联，用于
排除或者包含其他时间点

29.Scheduler：代表一个独立的运行容器，类似于jdk的Timer。用于注册Trigger和JobDetail，两者在该容器中各自都拥有
 自己的组和名称，这两个数据是在Scheduler中寻找某一个对象的依据，Trigger和JobDetail两者间的命名没有联系，可以相同
 
30.Trigger/JobDetail两者可以拥有各自的JobDataMap，可以设置各自的自定义属性值，可以在Job任务中通过JobExecutionContext对象来获得
   对应的JobDataMap，然后获得对应的值