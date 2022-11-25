# nio(new io)

与传统的bio不同，nio是多路复用io，意在解决使用bio时每有一个客户端与服务端的链接就创建一个线程致使线程数量激增的问题。

nio可以使得多个客户端与服务端的链接共享一个线程，利用selector来不断的轮询各个链接。

## buffer

## channel

与传统的io流的区别: 双向通道。

## selector

- keys: 所有的事件
- selectedKeys: read、write事件
- canceledKeys: 取消的事件



- SelectionKey
  - interestOps: 关注的事件
  - readyOps: 就绪的事件



