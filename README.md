## AIDL 客户端

## AIDL

AIDL（Android Interface Definition Language）

Android 接口定义语言

原理其实就是 Android 进程间通信（IPC）的实现机制

核心依赖于 Binder 驱动

## Binder

不同应用进程、系统服务进程之间可以像调用本地方法一样调用接口。

使用内核驱动在内存中传递数据，避免了 socket 或共享文件的开销。

Binder 驱动可以验证调用者身份，防止非法进程访问系统服务。

## 概览

![概览](image/overview.png)