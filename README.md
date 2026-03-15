## AIDL 客户端

## AIDL

AIDL（Android Interface Definition Language）

Android 接口定义语言

原理其实就是 Android 进程间通信（IPC）的实现机制

核心依赖于 Binder 驱动

## Binder 驱动

### 高效

支持同步调用和异步调用

内核直接传递内存，不用 socket。

### 类型安全

通过 AIDL 生成代码保证参数类型

## 概览

![概览](image/overview.png)