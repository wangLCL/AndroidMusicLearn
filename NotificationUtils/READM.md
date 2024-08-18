# notification

在Android 8.0(API26)后对通知更改了一些内容，以前是通过Notification,Builder(Context context).set方法来设置通知的震动、
灯光、音效的设置，新内容加了NotificationChannel(通知渠道)， 通过NotificatonChannel来进行震动、灯光、音效的设置，且通知必须
添加通知渠道，同样需进行版本判断，否则通知不会被发送。

## 实现步骤

1. 创建NotificationManager对象
2. 创建Notificationchannel对象
3. 通过Notificationcompat.Builder(Context.Notification)对象指定channel
4. build创建对象
5. 调用notify方法发送通知

## 申请权限

```xml
<uses-permission android:name = "android.permission.POSI_NOTIFICATIONS"/>
```

## 创建通道

使用NotificationChannel类创建通知的通道实例

- 设置位置id
- 通道可见性
- 通道的重要性

| 重要级别 | Android 8.0        | 7.1以下                      |
|------|--------------------|----------------------------|
| 紧急   | IMPORTANCE_HIGH    | PRIORITY_HIGH/PRIORITY_MAX |
| 高    | IMPORTANCE_DEFAULT | PRIORITY_DEFAULT           |
| 中    | IMPORTANCE_LOW     | PRIORITY_LOW               |
| 低    | IMPORTANCE_MIN     |PRIORITY_MIN|
























